package net.sanchezapps.libs;

import net.sanchezapps.libs.speechToText.GCPSpeechToText;
import net.sanchezapps.libs.speechToTextMicrophone.GCPMicToText;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        GCPMicToText gcpMicToText=new GCPMicToText("");
        gcpMicToText.start();
    }
}
