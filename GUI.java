import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GUI {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private JPanel clientPanel;
    private JPanel ownerPanel;

    private JTextField userIdField;
    private JTextField vehicleInfoField;
    private JTextField residencyTimeField;

    private JTextField clientIdField;
    private JTextField jobDurationField;
    private JTextField jobDeadlineField;

    public GUI() {
        frame = new JFrame("User Information App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        clientPanel = createClientPanel();
        ownerPanel = createOwnerPanel();

        cardPanel.add(clientPanel, "Client");
        cardPanel.add(ownerPanel, "Owner");

        frame.add(cardPanel);

        JButton clientButton = new JButton("Client");
        JButton ownerButton = new JButton("Owner");

        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Client");
            }
        });

        ownerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Owner");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(clientButton);
        buttonPanel.add(ownerButton);

        frame.add(buttonPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private JPanel createClientPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel userIdLabel = new JLabel("Client ID:");
        clientIdField = new JTextField();
        JLabel jobDurationLabel = new JLabel("Job Duration:");
        jobDurationField = new JTextField();
        JLabel jobDeadlineLabel = new JLabel("Job Deadline:");
        jobDeadlineField = new JTextField();

        panel.add(userIdLabel);
        panel.add(clientIdField);
        panel.add(jobDurationLabel);
        panel.add(jobDurationField);
        panel.add(jobDeadlineLabel);
        panel.add(jobDeadlineField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveClientInformation();
            }
        });

        panel.add(submitButton);

        return panel;
    }

    private JPanel createOwnerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel userIdLabel = new JLabel("Owner ID:");
        userIdField = new JTextField();
        JLabel vehicleInfoLabel = new JLabel("Vehicle Info:");
        vehicleInfoField = new JTextField();
        JLabel residencyTimeLabel = new JLabel("Residency Time:");
        residencyTimeField = new JTextField();

        panel.add(userIdLabel);
        panel.add(userIdField);
        panel.add(vehicleInfoLabel);
        panel.add(vehicleInfoField);
        panel.add(residencyTimeLabel);
        panel.add(residencyTimeField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOwnerInformation();
            }
        });

        panel.add(submitButton);

        return panel;
    }

    private void saveClientInformation() {
        String clientId = clientIdField.getText();
        String jobDuration = jobDurationField.getText();
        String jobDeadline = jobDeadlineField.getText();

        // Save information to a file with a timestamp
        saveInformationToFile("Client", clientId, jobDuration, jobDeadline);

        // Clear fields
        clientIdField.setText("");
        jobDurationField.setText("");
        jobDeadlineField.setText("");
    }

    private void saveOwnerInformation() {
        String ownerId = userIdField.getText();
        String vehicleInfo = vehicleInfoField.getText();
        String residencyTime = residencyTimeField.getText();

        // Save information to a file with a timestamp
        saveInformationToFile("Owner", ownerId, vehicleInfo, residencyTime);

        // Clear fields
        userIdField.setText("");
        vehicleInfoField.setText("");
        residencyTimeField.setText("");
    }

    private void saveInformationToFile(String userType, String id, String info1, String info2) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_info.txt", true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(new Date());

            String entry = timestamp + " - " + userType + " ID: " + id + ", Info1: " + info1 + ", Info2: " + info2;
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
