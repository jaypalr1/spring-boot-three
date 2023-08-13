package org.jay.assistant;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.awt.Desktop;
import java.net.URI;

public class VoiceAssistant {

  public static void main(String[] args) {
    Configuration config = new Configuration();
    config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    config.setDictionaryPath("src/main/resources/dic.dic");
    config.setLanguageModelPath("src/main/resources/lm.lm");

    try {
      LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config);
      speech.startRecognition(true);

      Desktop desk = Desktop.getDesktop();
      SpeechResult speechResult = null;

      while ((speechResult = speech.getResult()) != null) {
        String voiceCommand = speechResult.getHypothesis();
        System.out.println("Voice Command is " + voiceCommand);

        if (voiceCommand.equalsIgnoreCase("google")) {
          System.out.println("Opening: " + voiceCommand);
          URI uri = new URI("https://google.com");
          desk.browse(uri);
        } else if (voiceCommand.equalsIgnoreCase("youtube")) {
          System.out.println("Opening: " + voiceCommand);
          URI uri = new URI("https://youtube.com");
          desk.browse(uri);
        } else if (voiceCommand.equalsIgnoreCase("Open Chrome")) {
          Runtime.getRuntime().exec("cmd.exe /c start chrome www.infybuzz.com");
        } else if (voiceCommand.equalsIgnoreCase("Close Chrome")) {
          Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM chrome.exe");
        }
      }
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
