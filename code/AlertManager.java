public class Main {
    public static void main(String[] args) {
        System.out.println("Phishing Detecting Tool Started...");

        // Run analyzers
        new analysis.URLAnalyzer().analyze("http://phishingsite.com");
        new analysis.EmailAnalyzer().analyze("From: fake@spoofed.com");
        new analysis.ClickAnalyzer().analyzeClick("http://example.com/redirect");
        new analysis.ImageAnalyzer().analyzeImage("fake_logo.png");
    }
}
package utils;

public class AlertManager {
    public static void sendAlert(String message) {
        System.out.println("🚨 ALERT: " + message);
    }
}
package analysis;

import utils.AlertManager;

public class URLAnalyzer {
    public void analyze(String url) {
        if (url.contains("phish") || url.contains("login") || url.contains("verify")) {
            AlertManager.sendAlert("Suspicious URL Detected: " + url);
        } else {
            System.out.println("URL looks clean: " + url);
        }
    }
}
package analysis;

import utils.AlertManager;

public class EmailAnalyzer {
    public void analyze(String emailHeader) {
        if (emailHeader.toLowerCase().contains("spoofed") || emailHeader.toLowerCase().contains("unknown")) {
            AlertManager.sendAlert("Suspicious Email Header Detected: " + emailHeader);
        } else {
            System.out.println("Email Header OK: " + emailHeader);
        }
    }
}
package analysis;

import utils.AlertManager;

public class ClickAnalyzer {
    public void analyzeClick(String link) {
        if (link.contains("redirect") || link.contains("tracker")) {
            AlertManager.sendAlert("Suspicious Click Activity Detected: " + link);
        } else {
            System.out.println("Click behavior normal: " + link);
        }
    }
}
package analysis;

import utils.AlertManager;

public class ImageAnalyzer {
    public void analyzeImage(String imagePath) {
        // Placeholder
        if (imagePath.toLowerCase().contains("fake") || imagePath.toLowerCase().contains("logo")) {
            AlertManager.sendAlert("Suspicious Logo/Image Found: " + imagePath);
        } else {
            System.out.println("Image seems safe: " + imagePath);
        }
    }
}
package db;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:phishing_logs.db";

    public DatabaseManager() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String createTable = "CREATE TABLE IF NOT EXISTS logs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "message TEXT," +
                    "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";
            Statement stmt = conn.createStatement();
            stmt.execute(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void logAlert(String message) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String insert = "INSERT INTO logs (message) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setString(1, message);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
