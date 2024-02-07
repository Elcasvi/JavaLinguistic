package net.sanchezapps.libs.speechToTextMicrophone;

import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import javax.sound.sampled.AudioInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GCPMicToText {
    private final ResponseObserver<StreamingRecognizeResponse> responseObserver;
    private final SpeechClient client;
    private ClientStream<StreamingRecognizeRequest> clientStream;
    private final RecognitionConfig recognitionConfig;
    private StreamingRecognizeRequest request;
    private final String[] realTimeResponse = {""};
    private final MicrophoneFactory  mic;
    private final AudioInputStream audio;
    public GCPMicToText() throws Exception {

        this.responseObserver =
                new ResponseObserver<StreamingRecognizeResponse>() {
                    ArrayList<StreamingRecognizeResponse> responses = new ArrayList<>();

                    public void onStart(StreamController controller) {}

                    public void onResponse(StreamingRecognizeResponse response) {
                        responses.add(response);
                    }

                    public void onComplete() {
                        if(!responses.isEmpty())
                        {
                            StreamingRecognizeResponse response=responses.get(responses.size()-1);
                            StreamingRecognitionResult result =response.getResultsList().get(0);
                            SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                            //System.out.printf("Transcript : %s\n", alternative.getTranscript());
                            realTimeResponse[0] =alternative.getTranscript();
                        }
                    }
                    public void onError(Throwable t) {
                        System.out.println(t);
                    }
                };
        this.client = SpeechClient.create();

        this.recognitionConfig =
                RecognitionConfig.newBuilder()
                        .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                        .setLanguageCode("en-US")
                        .setSampleRateHertz(16000)
                        .build();
        StreamingRecognitionConfig streamingRecognitionConfig =
                StreamingRecognitionConfig.newBuilder().setConfig(recognitionConfig).build();

        this.request =
                StreamingRecognizeRequest.newBuilder()
                        .setStreamingConfig(streamingRecognitionConfig)
                        .build(); // The first request in a streaming call has to be a config

        this.clientStream =
                client.streamingRecognizeCallable().splitCall(responseObserver);

        this.clientStream.send(request);

        this.mic = new MicrophoneFactory();
        this.audio = new AudioInputStream(mic.Start());
    }
    private double calculateSignalEnergy(byte[] audioData) {
        double sum = 0;
        for (byte b : audioData) {
            sum += b * b; // Square each byte value
        }
        return sum / audioData.length; // Average energy
    }

    private void startRealTimeSpeech(byte[] data) throws Exception {
        try {
            request = StreamingRecognizeRequest.newBuilder()
                    .setAudioContent(ByteString.copyFrom(data))
                    .build();
            clientStream.send(request);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private String[] stopAndGetTranscript() {
        responseObserver.onComplete(); // Call onComplete() only when done
        return realTimeResponse;
    }


    private void processSpeech()
    {
        int silenceCount = 0;
        int silenceThreshold = 5;
        double threshold=2200;

        String cacheResponse="";
        while (true) {
            byte[] data = new byte[6400]; // Adjust buffer size if needed
            try {
                audio.read(data);
            } catch (IOException e) {
                System.out.println("Error reading audio: " + e);
                break; // Exit the loop on read errors
            }

            double energy = calculateSignalEnergy(data); // Implement this method

            if (energy > threshold) {
                // Speech detected
                //System.out.println("Sound detected");
                silenceCount = 0; // Reset silence count
                try {
                    //System.out.println("Inside the try");
                    startRealTimeSpeech(data);
                } catch (Exception e) {
                    System.out.println("Error sending audio: " + e);
                }
            } else {
                // Silence detected
                //System.out.println("Silence detected");
                silenceCount++;
                if (silenceCount >= silenceThreshold) {
                    // End of speech
                    break; // Exit the inner loop to get transcript
                }
            }
            try {
                String[]transcript=stopAndGetTranscript();
                if(!cacheResponse.equals(transcript[0]))
                {
                    System.out.println("Transcript: " + transcript[0]);
                    cacheResponse=transcript[0];
                }
                else{
                    System.out.println("Same");
                }
            } catch (Exception e) {
                System.out.println("Error retrieving transcript: " + e);
            }
        }
    }
    public void start()
    {
        System.out.println("Start speaking");
        while (true) {
            processSpeech();
        }
    }
}
