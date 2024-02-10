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
