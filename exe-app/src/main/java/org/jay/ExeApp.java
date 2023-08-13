package org.jay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ExeApp extends JDialog {

  private static final String DASH = "============================================================";

  public ExeApp() {
    //Create a frame
    Frame f = new Frame();
    f.setSize(500, 300);

    //Prepare font
    Font font = new Font("SansSerif", Font.PLAIN, 22);

    //Write something
    Label label = new Label("Launch4j Maven Demo");
    label.setForeground(Color.ORANGE);
    label.setFont(font);
    f.add(label);

    //Make visible
    f.setVisible(true);
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }

  public static void main(String[] args) {
    SpringApplication.run(ExeApp.class, args);

    Thread shoutDownHook = new Thread(() -> log.info("\n {} \n Terminating JVM \n {}", DASH, DASH));

    Runtime.getRuntime().addShutdownHook(shoutDownHook);
  }
}
