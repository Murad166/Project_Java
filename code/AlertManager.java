import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PhishingDetectorGUI().createAndShowGUI());
    }
}

class PhishingDetectorGUI {
    private JTextArea outputArea;

    public void createAndShowGUI() {
        JFrame frame = new JFrame("🔐 Phishing Detection Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField urlField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField clickField = new JTextField();
        JTextField imageField = new JTextField();
        JButton analyzeButton = new JButton("🚨 Analyze");

        inputPanel.add(new JLabel("🌐 URL:"));
        inputPanel.add(urlField);
        inputPanel.add(new JLabel("📧 Email Header:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("🖱️ Click Link:"));
        inputPanel.add(clickField);
        inputPanel.add(new JLabel("🖼️ Image Name:"));
        inputPanel.add(imageField);
        inputPanel.add(new JLabel());  // empty cell
        inputPanel.add(analyzeButton);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);

        analyzeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.setText(""); // Clear output
                analyze(urlField.getText(), emailField.getText(), clickField.getText(), imageField.getText());
            }
        });
    }

    private void analyze(String url, String email, String click, String image) {
        outputArea.append("🔍 Starting Analysis...\n\n");

        new URLAnalyzer().analyze(url, outputArea);
        new EmailAnalyzer().analyze(email, outputArea);
        new ClickAnalyzer().analyze(click, outputArea);
        new ImageAnalyzer().analyze(image, outputArea);

        outputArea.append("\n✅ All checks completed!\n");
    }
}

// ---- Simulated Analyzer Classes ----

class URLAnalyzer {
    public void analyze(String url, JTextArea output) {
        if (url.contains("phish") || url.contains("login") || url.contains("verify")) {
            output.append("🚨 Suspicious URL Detected: " + url + "\n");
        } else {
            output.append("✅ URL looks clean: " + url + "\n");
        }
    }
}

class EmailAnalyzer {
    public void analyze(String email, JTextArea output) {
        if (email.toLowerCase().contains("spoofed") || email.toLowerCase().contains("unknown")) {
            output.append("🚨 Suspicious Email Header: " + email + "\n");
        } else {
            output.append("✅ Email header looks fine: " + email + "\n");
        }
    }
}

class ClickAnalyzer {
    public void analyze(String click, JTextArea output) {
        if (click.contains("redirect") || click.contains("tracker")) {
            output.append("🚨 Suspicious Click Activity: " + click + "\n");
        } else {
            output.append("✅ Click behavior normal: " + click + "\n");
        }
    }
}

class ImageAnalyzer {
    public void analyze(String image, JTextArea output) {
        if (image.toLowerCase().contains("fake") || image.toLowerCase().contains("logo")) {
            output.append("🚨 Suspicious Image Detected: " + image + "\n");
        } else {
            output.append("✅ Image looks okay: " + image + "\n");
        }
    }
}
