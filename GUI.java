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

    private JTextField usernameField;
    private JTextField passwordField;
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

		JLabel passwordLabel = new JLabel("Password:");
		JPasswordField passwordField = new JPasswordField(20);

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
				char[] password = passwordField.getPassword();
				if (authenticateAdmin(password)) {
					cardLayout.show(cardPanel, "AdminPanel");
				} else {
					JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				passwordField.setText("");
			}
		});

		JButton backButton = new JButton("Back to Welcome"); // Add "Back to Welcome" button
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Welcome"); // Show the welcome panel
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(loginButton, gbc);

		gbc.gridx = 0;
        gbc.gridy = 0; // Moved the "Back to Welcome" button to the top left corner
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST; // Adjusted the anchor to align the button on the left
        gbc.insets = new Insets(5, 5, 20, 5); // Added 20px spacing below the button
        panel.add(backButton, gbc);

		return panel;
	}

	private boolean authenticateAdmin(char[] password) {
		// Implement your authentication logic here
		// Compare adminId and password with your predefined values or check against a
		// database
		// Return true if authentication is successful, otherwise return false
		return Arrays.equals(password, "adminPassword".toCharArray());
	}

	private JPanel createWelcomePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(230, 230, 230));

		JLabel welcomeLabel = new JLabel("Welcome to Vehicle Management App");
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JTextArea welcomeText = new JTextArea(
				"The welcome page of this app serves as an entry point for Vehicle Management using static vehicular cloud computing. Users can choose to access either the \"Client\" or \"Owner\" functionality, where clients can request vehicle services and owners can provide a vehicle. This app facilitates vehicle management by connecting clients and owners through vehicular cloud computing for efficient, location-based vehicle solutions.");
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

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel PasswordLabel = new JLabel("Password:");
        passwordField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5); // Adjust the insets for spacing
        panel.add(PasswordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOwnerInformation();
            }
        });
        JButton newUserButton = new JButton("New User"); // Create a "New User" button
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to switch to the "NewUserPanel" here
                // You'll need to define and implement "NewUserPanel"
            }
        });
        JButton backButton = new JButton("Back to Welcome");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Welcome"); // Show the welcome panel
            }
        });

        // Set preferred size for the buttons
        Dimension buttonSize = new Dimension(100, 25);
        Dimension backButtonSize = new Dimension(150, 25); // Adjust the dimensions as needed
        loginButton.setPreferredSize(buttonSize);
        newUserButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(backButtonSize);

        gbc.gridx = 0;
        gbc.gridy = 0; // Moved the "Back to Welcome" button to the top left corner
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST; // Adjusted the anchor to align the button on the left
        gbc.insets = new Insets(5, 5, 60, 5); // Added 20px spacing below the button
        panel.add(backButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2; // Adjusted the grid y-coordinate for the login button
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 20, 5); // Added 20px spacing below the button
        panel.add(newUserButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2; // Adjusted the grid y-coordinate for the login button
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 20, 5); // Added 20px spacing below the button
        panel.add(loginButton, gbc);

		return panel;
	}

	private JPanel createOwnerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel PasswordLabel = new JLabel("Password:");
        passwordField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5); // Adjust the insets for spacing
        panel.add(PasswordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOwnerInformation();
            }
        });
        JButton newUserButton = new JButton("New User"); // Create a "New User" button
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to switch to the "NewUserPanel" here
                // You'll need to define and implement "NewUserPanel"
            }
        });
        JButton backButton = new JButton("Back to Welcome");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Welcome"); // Show the welcome panel
            }
        });

        // Set preferred size for the buttons
        Dimension buttonSize = new Dimension(100, 25);
        Dimension backButtonSize = new Dimension(150, 25); // Adjust the dimensions as needed
        loginButton.setPreferredSize(buttonSize);
        newUserButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(backButtonSize);

        gbc.gridx = 0;
        gbc.gridy = 0; // Moved the "Back to Welcome" button to the top left corner
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST; // Adjusted the anchor to align the button on the left
        gbc.insets = new Insets(5, 5, 60, 5); // Added 20px spacing below the button
        panel.add(backButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2; // Adjusted the grid y-coordinate for the login button
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 20, 5); // Added 20px spacing below the button
        panel.add(newUserButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2; // Adjusted the grid y-coordinate for the login button
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 20, 5); // Added 20px spacing below the button
        panel.add(loginButton, gbc);

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

			JOptionPane.showMessageDialog(frame, "Information saved to file: " + filename, "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error saving information to file: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

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
				JOptionPane.showMessageDialog(frame, "Invalid job duration format. Please use hh:mm (e.g., 02:30).",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (NumberFormatException e) {
			// Invalid number format
			JOptionPane.showMessageDialog(frame, "Invalid job duration format. Please use hh:mm (e.g., 02:30).",
					"Error", JOptionPane.ERROR_MESSAGE);
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
        String ownerId = usernameField.getText();
        String vehicleInfo = passwordField.getText();
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
				JOptionPane.showMessageDialog(frame, "Invalid residency time format. Please use hh:mm (e.g., 02:30).",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (NumberFormatException e) {
			// Invalid number format
			JOptionPane.showMessageDialog(frame, "Invalid residency time format. Please use hh:mm (e.g., 02:30).",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Handle other validations as needed

		// Save information to a file with a timestamp
		saveInformationToFile("Owner", ownerId, vehicleInfo, residencyTimeInput);

        // Clear fields
        usernameField.setText("");
        passwordField.setText("");
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
