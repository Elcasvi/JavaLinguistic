package net.sanchezapps.libs.speechToText;
import com.google.cloud.speech.v1.*;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;


public class GCPSpeechToText {
    private final String projectId;
    private final String gcsUri;
    public GCPSpeechToText(String projectId, String gcsUri) {
        this.projectId = projectId;
        this.gcsUri = gcsUri;
    }
    private  String generateRandomCustomId(){
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
    private AdaptationClient createAdaptationClient(String parent,String customClassId){
        try {
            AdaptationClient adaptationClient = AdaptationClient.create();
            adaptationClient.createCustomClass(
                    CreateCustomClassRequest
                            .newBuilder()
                            .setParent(parent)
                            .setCustomClassId(customClassId)
                            .build()
            );
            return adaptationClient;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private PhraseSet createPhraseSet(AdaptationClient adaptationClient,String parent,String phrase_set_id)
    {
        return adaptationClient.createPhraseSet(
                CreatePhraseSetRequest
                        .newBuilder()
                        .setParent(parent)
                        .setPhraseSetId(phrase_set_id)
                        .build()
        );

    }
    private void deleteAdaptationClient(AdaptationClient adaptationClient,String projectId,String location,String customClassId)
    {
        adaptationClient.deleteCustomClass(
                DeleteCustomClassRequest
                        .newBuilder()
                        .setName(CustomClassName.of(projectId, location, customClassId).toString())
                        .build()
        );

    }
    private void deletePhraseSet(AdaptationClient adaptationClient,String projectId,String location,String phraseSetId) {
        adaptationClient.deletePhraseSet(
                DeletePhraseSetRequest
                        .newBuilder()
                        .setName(PhraseSetName.of(projectId, location, phraseSetId).toString())
                        .build()
        );
    }
    private RecognitionConfig createRecognitionConfig(SpeechAdaptation speechAdaptation) throws IOException {
        return RecognitionConfig.newBuilder()
                .setEncoding(AudioEncoding.MP3)
                .setSampleRateHertz(16000)
                .setLanguageCode("en-US")
                .setAdaptation(speechAdaptation)
                .build();
    }
    public RecognizeResponse convert()throws Exception {
        String location="global";
        String customClassId =generateRandomCustomId();
        String phraseSetId=generateRandomCustomId();
        String parent=LocationName.of(projectId, location).toString();
        try {
            AdaptationClient adaptationClient = createAdaptationClient(parent, customClassId);
            PhraseSet phraseSetResponse = createPhraseSet(adaptationClient, parent, phraseSetId);
            String phraseSetName = phraseSetResponse.getName();


            SpeechAdaptation speechAdaptation = SpeechAdaptation.newBuilder()
                    .addPhraseSetReferences(phraseSetName)
                    .build();

            RecognitionConfig config = createRecognitionConfig(speechAdaptation);
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();

            SpeechClient speech_client = SpeechClient.create();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speech_client.recognize(config, audio);

            deleteAdaptationClient(adaptationClient, projectId, location, customClassId);
            deletePhraseSet(adaptationClient, projectId, location, phraseSetId);
            speech_client.close();
            return response;

        } finally {

        }
    }
}