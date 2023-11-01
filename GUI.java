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
import classes.*;
import java.util.ArrayList;

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

	private JPanel voHomePanel;
	private JTextField makeField;
	private JTextField modelField;
	private JTextField yearField;
	private JTextField plateField;
	private JTextField lengthOfStayField;
	private JComboBox<String> lengthOfStayUnitComboBox;
	private JPanel vccHomePanel;

	private JPanel departurePanel;

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
		voHomePanel = createVOHomePanel();
		departurePanel = createDeparturePanel();
		JPanel adminLoginPanel = createSystemAdminPanel(); // Create the System Admin login panel
		vccHomePanel = createVCCHomePanel();

		cardPanel.add(welcomePanel, "Welcome");
		cardPanel.add(clientPanel, "Computation Resource Requestor");
		cardPanel.add(ownerPanel, "Vehicle Owner");
		cardPanel.add(adminLoginPanel, "AdminPanel"); // Add the System Admin login panel to the card layout
		cardPanel.add(voHomePanel, "VOHome");
		cardPanel.add(departurePanel, "Departure");
		cardPanel.add(vccHomePanel, "VCCHome");

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
					cardLayout.show(cardPanel, "VCCHome");
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

		gbc.gridx = 0; // Place the "Back to Welcome" button on a new row
		gbc.gridy = 3;
		panel.add(backButton, gbc);

		return panel;
	}

	private boolean authenticateAdmin(char[] password) {
		// Implement your authentication logic here
		// Compare adminId and password with your predefined values or check against a
		// database
		// Return true if authentication is successful, otherwise return false

		if (Arrays.equals(password, "password".toCharArray())) {
			// If authentication is successful, show the VCCHome panel
			cardLayout.show(cardPanel, "VCCHome");
			return true;
		} else {
			JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
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

	public static String arrayListToString(ArrayList<Integer> list) {
		StringBuilder sb = new StringBuilder();

		for (Integer num : list) {
			sb.append(num).append(" "); // Add each integer followed by a space
		}

		// Remove the trailing space and return the result as a String
		return sb.toString().trim();
	}

	private JPanel createVCCHomePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.CENTER;

		JLabel vccHomeLabel = new JLabel("Welcome to Vehicular Cloud Computing (VCC) Home");
		vccHomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
		vccHomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		String jobCompletionString = arrayListToString(VCController.jobCompletion());
		JTextArea jobCompletionText = new JTextArea(jobCompletionString);
		jobCompletionText.setFont(new Font("Arial", Font.PLAIN, 18));
		jobCompletionText.setWrapStyleWord(true);
		jobCompletionText.setLineWrap(true);
		jobCompletionText.setOpaque(false);
		jobCompletionText.setEditable(false);
		jobCompletionText.setMargin(new Insets(20, 20, 20, 20));

		JButton logoutButton = createStyledButton("Logout", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Welcome");
			}
		});

		// Apply the same styling as the "Park" button to the logout button
		logoutButton.setBackground(new Color(51, 153, 255));
		logoutButton.setFont(new Font("Arial", Font.PLAIN, 18));

		// Use the same GridBagConstraints (gbc) settings as the "Park" button
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;

		panel.add(vccHomeLabel, gbc);

		// Increment the grid y-coordinate for the logout button
		gbc.gridy = 1;
		panel.add(logoutButton, gbc);

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

		JLabel jobTitleLabel = new JLabel("Job Title:");
		JTextField jobTitleField = new JTextField(20);

		JLabel jobDescriptionLabel = new JLabel("Job Description:");
		JTextField jobDescriptionField = new JTextField(20);

		JLabel jobDeadlineLabel = new JLabel("Job Deadline (mm/dd/yyyy hh:mm):");
		JTextField jobDeadlineField = new JTextField(20);

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
		panel.add(jobTitleLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		panel.add(jobTitleField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(jobDescriptionLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		panel.add(jobDescriptionField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(jobDeadlineLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		panel.add(jobDeadlineField, gbc);

		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveClientInformation(jobTitleField.getText(), jobDescriptionField.getText(),
						jobDeadlineField.getText());
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
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(backButton, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4; // Adjusted the grid y-coordinate for the submit button
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(submitButton, gbc);

		return panel;
	}

	private void saveClientInformation(String jobTitle, String jobDescription, String jobDeadline) {
		// Validate the job deadline date and time format
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		dateFormat.setLenient(false); // Ensure strict date format checking

		try {
			Date deadline = dateFormat.parse(jobDeadline);
			Date now = new Date();

			if (deadline.before(now)) {
				JOptionPane.showMessageDialog(frame, "Invalid deadline. Please enter a future date and time.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				String clientId = clientIdField.getText();
				String timestamp = getCurrentTimestamp();

				String clientInfo = clientId + ", " + jobTitle + ", " + jobDescription + "\nDeadline:" + jobDeadline
						+ ", " + timestamp;

				// Write the client information to the CRR.txt file in CSV format
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("CRR.txt", true))) {
					writer.write(clientInfo);
					writer.newLine();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frame, "Error writing client information to file: " + e.getMessage(),
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (java.text.ParseException e) {
			JOptionPane.showMessageDialog(frame, "Invalid date and time format. Please use 'MM/dd/yyyy HH:mm'.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel createOwnerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;

		JLabel usernameLabel = new JLabel("Username:");
		usernameField = new JTextField(20);

		JLabel passwordLabel = new JLabel("Password:");
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
				writeOwnerInfoToFile();
				cardLayout.show(cardPanel, "VOHome");
			}
		});

		JButton backButton = new JButton("Back to Welcome"); // Add "Back to Welcome" button
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
		backButton.setPreferredSize(backButtonSize);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(backButton, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2; // Adjusted the grid y-coordinate for the login button
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(loginButton, gbc);

		return panel;
	}

	private JPanel createVOHomePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;

		JLabel makeLabel = new JLabel("Make:");
		makeField = new JTextField(20);

		JLabel modelLabel = new JLabel("Model:");
		modelField = new JTextField(20);

		JLabel yearLabel = new JLabel("Year:");
		yearField = new JTextField(20);

		JLabel plateLabel = new JLabel("Plate:");
		plateField = new JTextField(20);

		JLabel lengthOfStayLabel = new JLabel("Length of Stay:");
		lengthOfStayField = new JTextField(10);

		lengthOfStayUnitComboBox = new JComboBox<>(new String[] { "hours", "days" });

		JButton parkButton = new JButton("Park");
		parkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeVehicleInfoToFile();
				cardLayout.show(cardPanel, "Departure");
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(makeLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		panel.add(makeField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(modelLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		panel.add(modelField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(yearLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		panel.add(yearField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(plateLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		panel.add(plateField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(lengthOfStayLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(lengthOfStayField, gbc);

		gbc.gridx = 2;
		gbc.gridy = 4;
		panel.add(lengthOfStayUnitComboBox, gbc);

		// Add the "Park" button
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(parkButton, gbc);

		// Add the "Park" button
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(parkButton, gbc);

		return panel;
	}

	private JPanel createDeparturePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.CENTER;

		JButton departButton = new JButton("Depart");
		departButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeDepartureTimestampToFile();
				cardLayout.show(cardPanel, "Welcome");
			}
		});

		// Add the "Depart" button
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(departButton, gbc);

		return panel;
	}

	private void writeVehicleInfoToFile() {
		String make = makeField.getText();
		String model = modelField.getText();
		String year = yearField.getText();
		String plate = plateField.getText();
		String lengthOfStay = lengthOfStayField.getText() + " " + lengthOfStayUnitComboBox.getSelectedItem();
		String timestamp = getCurrentTimestamp();

		String vehicleInfo = make + " : " + model + " : " + year + " : " + plate + " : " + lengthOfStay + " : "
				+ timestamp;

		// Write the vehicle information to the file vehicleOwners.txt
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehicleOwners.txt", true))) {
			writer.write(vehicleInfo);
			writer.newLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error writing vehicle information to file: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void writeOwnerInfoToFile() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		String ownerInfo = username + " : " + password;

		// Write the owner information to the file vehicleOwners.txt
		try (BufferedWriter writer2 = new BufferedWriter(new FileWriter("vehicleOwners.txt", true))) {
			writer2.write(ownerInfo);
			writer2.newLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error writing owner information to file: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void writeDepartureTimestampToFile() {
		String timestamp = getCurrentTimestamp();

		// Write the departure timestamp to the file vehicleOwners.txt
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehicleOwners.txt", true))) {
			writer.write("Departure: " + timestamp);
			writer.newLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error writing departure timestamp to file: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private String getCurrentTimestamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(new Date());
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
