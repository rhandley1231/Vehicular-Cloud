import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VCController {
    private JFrame frame;
    private JPanel currentPanel;
    private JTextField vOIDField, makeField, modelField, plateField;
    private JTextField clientIDField, jobIDField, jobDurationField, deadlineField;

    public VCController() {
        frame = new JFrame("Welcome to VCC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        createVCCWelcomePanel();

        frame.setVisible(true);
    }

    private void createVCCWelcomePanel() {
        currentPanel = new JPanel();
        frame.getContentPane().removeAll();
        frame.add(currentPanel);

        currentPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to the Vehicle and Job Management Page for VCC");
        currentPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Buttons for managing vehicles, managing jobs, and going back to the welcome page
        JButton manageVehiclesButton = new JButton("Manage Vehicles");
        JButton manageJobsButton = new JButton("Manage Jobs");
        JButton backButton = new JButton("Back to Welcome Page");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Use a flow layout for buttons
        buttonPanel.add(manageVehiclesButton);
        buttonPanel.add(manageJobsButton);
        buttonPanel.add(backButton);

        currentPanel.add(buttonPanel, BorderLayout.CENTER);

        manageVehiclesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createManageVehiclesPanel();
            }
        });

        manageJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createManageJobsPanel();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirect to the main welcome page
                new GUI();
                frame.dispose();
            }
        });

        frame.revalidate();
        frame.repaint();
    }

    private void createManageVehiclesPanel() {
        currentPanel = new JPanel();
        frame.getContentPane().removeAll();
        frame.add(currentPanel);

        currentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left

        vOIDField = new JTextField(20);
        makeField = new JTextField(20);
        modelField = new JTextField(20);
        plateField = new JTextField(20);

        JLabel vOIDLabel = new JLabel("Vehicle Owner ID (Number):");
        JLabel makeLabel = new JLabel("Make:");
        JLabel modelLabel = new JLabel("Model:");
        JLabel plateLabel = new JLabel("License Plate:");

        JButton submitButton = new JButton("Submit");
        JButton goBackButton = new JButton("Go Back");

        // Add Vehicle Owner ID label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        currentPanel.add(vOIDLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(vOIDField, gbc);

        // Add Make label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        currentPanel.add(makeLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(makeField, gbc);

        // Add Model label and field
        gbc.gridx = 0;
        gbc.gridy = 2;
        currentPanel.add(modelLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(modelField, gbc);

        // Add License Plate label and field
        gbc.gridx = 0;
        gbc.gridy = 3;
        currentPanel.add(plateLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(plateField, gbc);

        // Create a panel for buttons and add it at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(goBackButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        currentPanel.add(buttonPanel, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int vOID = Integer.parseInt(vOIDField.getText());
                String make = makeField.getText();
                String model = modelField.getText();
                String plate = plateField.getText();

                // Handle adding vehicle information here
                // For example: VCC.createVOandV(vOID, make, model, plate);

                JOptionPane.showMessageDialog(frame, "Vehicle information added successfully!");
                createVCCWelcomePanel();
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createVCCWelcomePanel();
            }
        });

        frame.revalidate();
        frame.repaint();
    }

    private void createManageJobsPanel() {
        currentPanel = new JPanel();
        frame.getContentPane().removeAll();
        frame.add(currentPanel);

        currentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left

        clientIDField = new JTextField(20);
        jobIDField = new JTextField(20);
        jobDurationField = new JTextField(20);
        deadlineField = new JTextField(20);

        JLabel clientIDLabel = new JLabel("Client ID (Number):");
        JLabel jobIDLabel = new JLabel("Job ID (Number):");
        JLabel jobDurationLabel = new JLabel("Job Duration (Hours):");
        JLabel deadlineLabel = new JLabel("Deadline (mm/dd/yyyy):");

        JButton submitButton = new JButton("Submit");
        JButton goBackButton = new JButton("Go Back");

        // Add client ID label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        currentPanel.add(clientIDLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(clientIDField, gbc);

        // Add job ID label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        currentPanel.add(jobIDLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(jobIDField, gbc);

        // Add job duration label and field
        gbc.gridx = 0;
        gbc.gridy = 2;
        currentPanel.add(jobDurationLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(jobDurationField, gbc);

        // Add deadline label and field
        gbc.gridx = 0;
        gbc.gridy = 3;
        currentPanel.add(deadlineLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(deadlineField, gbc);

        // Create a panel for buttons and add it at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(goBackButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        currentPanel.add(buttonPanel, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clientID = clientIDField.getText();
                int client = Integer.parseInt(clientID);
                int jobID = Integer.parseInt(jobIDField.getText());
                int jobDuration = Integer.parseInt(jobDurationField.getText());
                String deadline = deadlineField.getText();

                // Handle adding job information here
                // For example: VCC.createJob(client, jobID, jobDuration, deadline);

                JOptionPane.showMessageDialog(frame, "Job created successfully!");
                createVCCWelcomePanel();
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createVCCWelcomePanel();
            }
        });

        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VCController();
        });
    }
}

