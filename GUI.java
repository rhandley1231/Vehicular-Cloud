import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class GUI {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private JPanel welcomePanel; // Add the welcome panel
    private JPanel clientPanel;
    private JPanel ownerPanel;

    private JTextField userIdField;
    private JTextField vehicleInfoField;
    private JTextField residencyTimeField;

    private JTextField clientIdField;
    private JTextField jobDurationField;
    private JTextField jobDeadlineField;

    public GUI() {
        frame = new JFrame("The Utopia VCRTS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        welcomePanel = createWelcomePanel();
        clientPanel = createClientPanel();
        ownerPanel = createOwnerPanel();
        JPanel adminLoginPanel = createSystemAdminPanel(); // Create the System Admin login panel

        cardPanel.add(welcomePanel, "Welcome");
        cardPanel.add(clientPanel, "Computation Resource Requestor");
        cardPanel.add(ownerPanel, "Vehicle Owner");
        cardPanel.add(adminLoginPanel, "AdminPanel"); // Add the System Admin login panel to the card layout

        frame.add(cardPanel);

        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBackground(new Color(51, 153, 255));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        if (actionListener != null) {
            button.addActionListener(actionListener);
        }
        return button;
    }
    private JPanel createSystemAdminPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel adminIdLabel = new JLabel("Admin ID:");
        JTextField adminIdField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(adminIdLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(adminIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminId = adminIdField.getText();
                char[] password = passwordField.getPassword();
                if (authenticateAdmin(adminId, password)) {
                    cardLayout.show(cardPanel, "AdminPanel");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                passwordField.setText("");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        return panel;
    }

    private boolean authenticateAdmin(String adminId, char[] password) {
        // Implement your authentication logic here
        // Compare adminId and password with your predefined values or check against a database
        // Return true if authentication is successful, otherwise return false
        return adminId.equals("admin") && Arrays.equals(password, "adminPassword".toCharArray());
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(230, 230, 230));

        JLabel welcomeLabel = new JLabel("Welcome to Vehicle Management App");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea welcomeText = new JTextArea("The welcome page of this app serves as an entry point for Vehicle Management using static vehicular cloud computing. Users can choose to access either the \"Client\" or \"Owner\" functionality, where clients can request vehicle services and owners can provide a vehicle. This app facilitates vehicle management by connecting clients and owners through vehicular cloud computing for efficient, location-based vehicle solutions.");
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomeText.setWrapStyleWord(true);
        welcomeText.setLineWrap(true);
        welcomeText.setOpaque(false);
        welcomeText.setEditable(false);
        welcomeText.setMargin(new Insets(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(welcomeText);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel centeredContent = new JPanel(new BorderLayout());
        centeredContent.add(welcomeLabel, BorderLayout.NORTH);
        centeredContent.add(scrollPane, BorderLayout.CENTER);
        centeredContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(centeredContent, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton ownerButton = createStyledButton("Vehicle Owner", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Vehicle Owner");
            }
        });

        JButton clientButton = createStyledButton("Computation Resource Requestor", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Computation Resource Requestor");
            }
        });

        JButton systemAdminButton = createStyledButton("System Admin", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "AdminPanel"); // Show the System Admin login panel
            }
        });

        buttonPanel.add(ownerButton);
        buttonPanel.add(clientButton);
        buttonPanel.add(systemAdminButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
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
        JButton backButton = new JButton("Back to Welcome");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Welcome"); // Show the welcome panel
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(backButton, gbc);

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

        JLabel residencyTimeLabel = new JLabel("Residency Time:");

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
        JButton backButton = new JButton("Back to Welcome");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Welcome"); // Show the welcome panel
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(backButton, gbc);

        return panel;
    }
    

    private void saveInformationToFile(String type, String id, String info1, String info2) {
        String encryptedData = encryptUserData(type, id, info1, info2);

    	// Get the current timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());

        // Create a filename based on the timestamp
        String filename = type + "_" + id + "_" + timestamp + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Type: " + type);
            writer.newLine();
            writer.write("ID: " + id);
            writer.newLine();
            writer.write("Info 1: " + info1);
            writer.newLine();
            writer.write("Info 2: " + info2);
            writer.newLine();
            // Optionally, you can write more information as needed

            JOptionPane.showMessageDialog(frame, "Information saved to file: " + filename, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving information to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);}}
        



    private String encryptUserData(String type, String id, String info1, String info2) {
		// TODO Auto-generated method stub
        String encryptedData = "Encrypted Data: Type=" + type + ", ID=" + id + ", Info1=" + info1 + ", Info2=" + info2;
        return encryptedData;

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
