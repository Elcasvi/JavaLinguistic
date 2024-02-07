package net.sanchezapps.libs.speechToTextMicrophone;

import javax.sound.sampled.*;

public class MicrophoneFactory {
    private final TargetDataLine targetDataLine;
    private final AudioFormat audioFormat;
    public MicrophoneFactory() throws LineUnavailableException {
        this.audioFormat = new AudioFormat(16000, 16, 1, true, false);
        DataLine.Info targetInfo =
                new DataLine.Info(TargetDataLine.class, audioFormat);

        if (!AudioSystem.isLineSupported(targetInfo)) {
            System.out.println("Microphone not supported");
            System.exit(0);
        }
        else
        {
            System.out.println("Microphone connected");
        }
        this.targetDataLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
    }
    public TargetDataLine Start()
    {
        try {
            // Target data line captures the audio stream the microphone produces.
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            return targetDataLine;
        }
        catch (Exception e)
        {

        }
        finally {

        }
        return null;
    }
    public void Stop()
    {
        targetDataLine.stop();
        targetDataLine.close();
    }
}
