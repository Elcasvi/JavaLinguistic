# Speech To Text

## Speech-to-Text Conversion

The **javaLinguisticLib** library provides functionality for converting speech to text using Google Cloud Platform's Speech-to-Text API. Below are examples demonstrating how to use this core functionality:

### Using GCPSpeechToText Class

The `GCPSpeechToText` class facilitates speech-to-text conversion from audio files stored in Google Cloud Storage (GCS). Here's how to use it:

1. **General Use**:
   Create a method to use GCPSpeechToText:

    ```java
    import net.sanchezapps.libs.speechToText.GCPSpeechToText;

    public class Main {
        public static void main(String[] args) {
            // Create an instance of GCPSpeechToText
            GCPSpeechToText speechToText = new GCPSpeechToText("your_project_id", "gs://path/to/audio/file");

            try {
                // Perform speech-to-text conversion
                RecognizeResponse response = speechToText.convert();

                // Process the response as needed
                System.out.println("Transcribed Text: " + response.getResults(0).getAlternatives(0).getTranscript());
            } catch (Exception e) {
                // Handle any exceptions
                e.printStackTrace();
            }
        }
    }
    ```

2. **Instantiate GCPSpeechToText**:
   Create an instance of `GCPSpeechToText` by providing your GCP project ID and the URI of the audio file to transcribe.
    ```java
    GCPSpeechToText speechToText = new GCPSpeechToText("your_project_id", "gs://path/to/audio/file");
    ```

3. **Perform Speech-to-Text Conversion**:
   Use the `convert()` method to initiate the speech-to-text conversion process. This method returns a `RecognizeResponse` containing the transcribed text.

    ```java
    try {
        RecognizeResponse response = speechToText.convert();
        // Process the transcribed text from the response
        System.out.println("Transcribed Text: " + response.getResults(0).getAlternatives(0).getTranscript());
    } catch (Exception e) {
        // Handle any exceptions
        e.printStackTrace();
    }
    ```



### Real-time Speech Recognition

The **javaLinguisticLib** also supports real-time speech recognition from microphone input. Here's how to use this functionality:

### Using GCPMicToText Class

The `GCPMicToText` class enables real-time speech recognition using Google Cloud Platform's Speech-to-Text API. Follow these steps to use it:


## Class and Method Explanation

- **GCPSpeechToText Class**: Responsible for converting speech from audio files to text using GCP's Speech-to-Text API.
    - Constructor: Initializes the class with the GCP project ID and the URI of the audio file.
    - `convert()`: Initiates the speech-to-text conversion process and returns the transcribed text.

- **GCPMicToText Class**: Enables real-time speech recognition from microphone input using GCP's Speech-to-Text API.
    - Constructor: Initializes the class with the GCP project ID.
    - `start()`: Initiates real-time speech recognition from the microphone.

These classes and methods provide convenient interfaces for integrating speech-to-text functionality into your Java applications.
