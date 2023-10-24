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
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;

    JLabel userIdLabel = new JLabel("Client ID:");
    clientIdField = new JTextField(20);

    JLabel jobDurationLabel = new JLabel("Job Duration (hh:mm):");
    jobDurationField = new JTextField(8);

    JLabel jobDeadlineLabel = new JLabel("Job Deadline:");

    // Add separate fields for month, day, year, hour, and minute
    JTextField monthField = new JTextField(4);
    JTextField dayField = new JTextField(4);
    JTextField yearField = new JTextField(4);
    JTextField hourField = new JTextField(4);
    JTextField minuteField = new JTextField(4);

    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(userIdLabel, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    panel.add(clientIdField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(jobDurationLabel, gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    panel.add(jobDurationField, gbc);

    // Add explanatory text for the job duration format
    JLabel jobDurationFormatLabel = new JLabel("Format: hh:mm (e.g., 02:30 for 2 hours and 30 minutes)");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    panel.add(jobDurationFormatLabel, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    panel.add(jobDeadlineLabel, gbc);

    // Add labels and fields for month, day, year, hour, and minute
    gbc.gridx = 0;
    gbc.gridy = 4;
    panel.add(new JLabel("Month:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 4;
    panel.add(monthField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    panel.add(new JLabel("Day:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 5;
    panel.add(dayField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 6;
    panel.add(new JLabel("Year:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 6;
    panel.add(yearField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 7;
    panel.add(new JLabel("Hour:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 7;
    panel.add(hourField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 8;
    panel.add(new JLabel("Minute:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 8;
    panel.add(minuteField, gbc);

    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveClientInformation();
        }
    });

    gbc.gridx = 0;
    gbc.gridy = 9;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.CENTER;
    panel.add(submitButton, gbc);

    return panel;
}
private JPanel createOwnerPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;

    JLabel userIdLabel = new JLabel("Owner ID:");
    userIdField = new JTextField(20);

    JLabel vehicleInfoLabel = new JLabel("Vehicle Info:");
    vehicleInfoField = new JTextField(20);

    JLabel residencyTimeLabel = new JLabel("Residency Time (hh:mm):");

    // Add separate fields for month, day, year, hour, and minute
    JTextField monthField = new JTextField(4);
    JTextField dayField = new JTextField(4);
    JTextField yearField = new JTextField(4);
    JTextField hourField = new JTextField(4);
    JTextField minuteField = new JTextField(4);

    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(userIdLabel, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    panel.add(userIdField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(vehicleInfoLabel, gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    panel.add(vehicleInfoField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    panel.add(residencyTimeLabel, gbc);

    // Add labels and fields for month, day, year, hour, and minute for residency time
    gbc.gridx = 0;
    gbc.gridy = 3;
    panel.add(new JLabel("Month:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 3;
    panel.add(monthField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    panel.add(new JLabel("Day:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 4;
    panel.add(dayField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    panel.add(new JLabel("Year:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 5;
    panel.add(yearField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 6;
    panel.add(new JLabel("Hour:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 6;
    panel.add(hourField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 7;
    panel.add(new JLabel("Minute:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 7;
    panel.add(minuteField, gbc);

    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveOwnerInformation();
        }
    });

    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.CENTER;
    panel.add(submitButton, gbc);

    return panel;
}
private void saveClientInformation() {
    String clientId = clientIdField.getText();
    String jobDurationInput = jobDurationField.getText();
    String jobDeadline = jobDeadlineField.getText();

    int hours = 0;
    int minutes = 0;

    // Validate and parse job duration input
    try {
        String[] parts = jobDurationInput.split(":");
        if (parts.length == 2) {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
        } else {
            // Invalid format
            JOptionPane.showMessageDialog(frame, "Invalid job duration format. Please use hh:mm (e.g., 02:30).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } catch (NumberFormatException e) {
        // Invalid number format
        JOptionPane.showMessageDialog(frame, "Invalid job duration format. Please use hh:mm (e.g., 02:30).", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Handle other validations as needed

    // Save information to a file with a timestamp
    saveInformationToFile("Client", clientId, jobDurationInput, jobDeadline);

    // Clear fields
    clientIdField.setText("");
    jobDurationField.setText("");
    jobDeadlineField.setText("");
}

private void saveOwnerInformation() {
    String ownerId = userIdField.getText();
    String vehicleInfo = vehicleInfoField.getText();
    String residencyTimeInput = residencyTimeField.getText();

    int hours = 0;
    int minutes = 0;

    // Validate and parse residency time input
    try {
        String[] parts = residencyTimeInput.split(":");
        if (parts.length == 2) {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
        } else {
            // Invalid format
            JOptionPane.showMessageDialog(frame, "Invalid residency time format. Please use hh:mm (e.g., 02:30).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } catch (NumberFormatException e) {
        // Invalid number format
        JOptionPane.showMessageDialog(frame, "Invalid residency time format. Please use hh:mm (e.g., 02:30).", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Handle other validations as needed

    // Save information to a file with a timestamp
    saveInformationToFile("Owner", ownerId, vehicleInfo, residencyTimeInput);

    // Clear fields
    userIdField.setText("");
    vehicleInfoField.setText("");
    residencyTimeField.setText("");
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
