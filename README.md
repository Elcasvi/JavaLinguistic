# Introduction

The **javaLinguisticLib** is a Java library designed to provide comprehensive speech-to-text conversion capabilities using Google Cloud Platform (GCP) services. It offers convenient functionalities for both offline speech recognition from audio files and real-time transcription from microphone input.

## Purpose and Functionalities

The primary purpose of **javaLinguisticLib** is to simplify the integration of speech recognition functionality into Java applications. It allows developers to leverage the powerful speech-to-text capabilities of GCP without the need for complex setup or configuration.

## Key Features and Advantages

- **Speech-to-Text Conversion**: Easily convert speech from audio files or microphone input into text with high accuracy.
- **Real-time Transcription**: Enable real-time speech recognition for live applications such as dictation, transcription services, or voice-controlled interfaces.
- **Seamless Integration**: Integrate speech recognition capabilities into your Java applications with minimal effort, thanks to the straightforward APIs provided by the library.
- **Customization Options**: Customize speech recognition models and adapt them to specific use cases using features like custom classes and phrase sets.
- **Efficient Resource Management**: Automatically manage resources and cleanup after speech recognition tasks, ensuring efficient usage of system resources.

By using **javaLinguisticLib**, developers can quickly add speech-to-text functionality to their Java applications, opening up a wide range of possibilities for voice-enabled interactions and automated transcription tasks.



# Installation

To install the **javaLinguisticLib** library, you can use Maven, which simplifies dependency management in Java projects. Follow the steps below to add the library to your Maven project:

1. **Update Your `pom.xml` File**:

Add the following dependency to your `pom.xml` file within the `<dependencies>` section:

```xml
<dependency>
    <groupId>net.sanchezapps.libs</groupId>
    <artifactId>javaLinguisticLib</artifactId>
    <version>1.0</version>
</dependency>
```


# Usage

## Speech-to-Text Conversion

To perform speech-to-text conversion from audio files, you can use the `GCPSpeechToText` class.

- [SpeechToText](Speech-to-text.md): Guide on using `GCPSpeechToText` for converting speech from audio files to text.

## Real-time Speech Recognition

For real-time speech recognition from microphone input, you can utilize the `GCPMicToText` class.

- [MicToText](Mic-To-Tex.md): Guide on using `GCPMicToText` for real-time speech recognition from microphone input.


# Usage

## Speech-to-Text Conversion

To perform speech-to-text conversion from audio files, you can use the `GCPSpeechToText` class.

- [SpeechToText](Speech-to-text.md): Guide on using `GCPSpeechToText` for converting speech from audio files to text.

## Real-time Speech Recognition

For real-time speech recognition from microphone input, you can utilize the `GCPMicToText` class.

- [MicToText](Mic-To-Tex.md): Guide on using `GCPMicToText` for real-time speech recognition from microphone input.


# Mic To Text

## Real-time Speech Recognition

The **javaLinguisticLib** library also supports real-time speech recognition from microphone input. Follow these steps to use this functionality:

### Using GCPMicToText Class

The `GCPMicToText` class enables real-time speech recognition using Google Cloud Platform's Speech-to-Text API.

1. **General Use**:
   Create a method to use `GCPMicToText`:

    ```java
    import net.sanchezapps.libs.speechToTextMicrophone.GCPMicToText;

    public class Main {
        public static void main(String[] args) {
            // Create an instance of GCPMicToText
            GCPMicToText micToText = new GCPMicToText("your_project_id");

            try {
                // Start real-time speech recognition
                micToText.start();
            } catch (IOException e) {
                // Handle any exceptions
                e.printStackTrace();
            }
        }
    }
    ```

2. **Instantiate GCPMicToText**:
   Create an instance of `GCPMicToText` by providing your GCP project ID.
    ```java
    GCPMicToText micToText = new GCPMicToText("your_project_id");
    ```

3. **Start Real-time Speech Recognition**:
   Call the `start()` method to initiate real-time speech recognition from the microphone. This method continuously listens for speech input, transcribes it in real-time, and prints the transcribed text to the console.

    ```java
    try {
        micToText.start();
    } catch (IOException e) {
        // Handle any exceptions
        e.printStackTrace();
    }
    ```

## Class and Method Explanation

- **GCPSpeechToText Class**: Responsible for converting speech from audio files to text using GCP's Speech-to-Text API.
    - Constructor: Initializes the class with the GCP project ID and the URI of the audio file.
    - `convert()`: Initiates the speech-to-text conversion process and returns the transcribed text.

- **GCPMicToText Class**: Enables real-time speech recognition from microphone input using GCP's Speech-to-Text API.
    - Constructor: Initializes the class with the GCP project ID.
    - `start()`: Initiates real-time speech recognition from the microphone.

These classes and methods provide convenient interfaces for integrating real-time speech recognition functionality into your Java applications.



