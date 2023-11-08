import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class GUI {
    private JFrame frame;
    private JPanel currentPanel;
    private JTextField clientIDField, jobIDField, jobDurationField, deadlineField;
    private JTextField vOIDField, makeField, modelField, plateField;
    private JTextArea computationResultArea;
    private ObjectOutputStream objectOutputStream;

    public GUI(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
        frame = new JFrame("Utopia VCRTS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        createWelcomePanel();

        frame.setVisible(true);
    }

    private void createWelcomePanel() {
        currentPanel = new JPanel();
        frame.getContentPane().removeAll();
        frame.add(currentPanel);

        currentPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to the Utopia VCRTS, here you can create jobs, manage vehicles, and see computation times!");
        currentPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        currentPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton createJobButton = new JButton("Create Job");
        JButton createVOandVButton = new JButton("Manage Vehicle");
        JButton computationTimeButton = new JButton("See Wait Time");

        buttonPanel.add(createJobButton);
        buttonPanel.add(createVOandVButton);
        buttonPanel.add(computationTimeButton);

        createJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createJobPanel();
            }
        });

        createVOandVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createVOandVPanel();
            }
        });

        computationTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computationTimePanel();
            }
        });

        frame.revalidate();
        frame.repaint();
    }

    private void createJobPanel() {
        currentPanel = new JPanel();
        frame.getContentPane().removeAll();
        frame.add(currentPanel);

        currentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

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

        gbc.gridx = 0;
        gbc.gridy = 0;
        currentPanel.add(clientIDLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(clientIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        currentPanel.add(jobIDLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(jobIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        currentPanel.add(jobDurationLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(jobDurationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        currentPanel.add(deadlineLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(deadlineField, gbc);

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

                // Create a Client object and send it to VCController
                Client clientObject = new Client(client, jobID, jobDuration, deadline);
                sendObjectToServer(clientObject);

                JOptionPane.showMessageDialog(frame, "Job data sent to VCController.");
                createWelcomePanel();
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createWelcomePanel();
            }
        });

        frame.revalidate();
        frame.repaint();
    }

    private void createVOandVPanel() {
        currentPanel = new JPanel();
        frame.getContentPane().removeAll();
        frame.add(currentPanel);

        currentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

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

        gbc.gridx = 0;
        gbc.gridy = 0;
        currentPanel.add(vOIDLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(vOIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        currentPanel.add(makeLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(makeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        currentPanel.add(modelLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(modelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        currentPanel.add(plateLabel, gbc);
        gbc.gridx = 1;
        currentPanel.add(plateField, gbc);

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

                // Create a VehicleOwner object and send it to VCController
                VehicleOwner vehicleOwner = new VehicleOwner(vOID, make, model, plate);
                sendObjectToServer(vehicleOwner);

                JOptionPane.showMessageDialog(frame, "Vehicle Owner and Vehicle data sent to VCController.");
                createWelcomePanel();
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createWelcomePanel();
            }
        });

        frame.revalidate();
        frame.repaint();
    }

    private void computationTimePanel() {
        currentPanel = new JPanel();
        frame.getContentPane().removeAll();
        frame.add(currentPanel);
    
        JButton goBackButton = new JButton("Go Back");
    
        computationResultArea = new JTextArea(10, 40);
        computationResultArea.setEditable(false);
        computationResultArea.setWrapStyleWord(true);
        computationResultArea.setLineWrap(true);
        computationResultArea.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        ArrayList[] result = VCC.jobCompletion();
        ArrayList<Integer> jobIDs = result[0];
        ArrayList<Integer> jobTimes = result[1];
        ArrayList<Integer> jobCompletionTimes = result[2];
    
        StringBuilder resultText = new StringBuilder();
        for (int i = 0; i < jobIDs.size(); i++) {
            resultText.append("Job ID: ").append(jobIDs.get(i)).append(", Job Duration: ").append(jobTimes.get(i)).append(", Completion Time: ").append(jobCompletionTimes.get(i)).append("\n");
        }
    
        computationResultArea.setText(resultText.toString());
    
        JScrollPane scrollPane = new JScrollPane(computationResultArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createWelcomePanel();
            }
        });
    
        currentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        currentPanel.add(scrollPane, gbc);
    
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(goBackButton, gbc);
    
        frame.revalidate();
        frame.repaint();
    }     

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Replace with your server's hostname and port
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            SwingUtilities.invokeLater(() -> {
                new GUI(objectOutputStream);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Method to send objects (Client or VehicleOwner) to VCController
    private void sendObjectToServer(Object object) {
        try {
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


