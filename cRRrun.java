import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import classes.*;

public class cRRrun extends JFrame {
    private JFrame frame;
    private JTextField userIDField;
    private JTextField jobIDField;
    private JTextField durationField;
    private JTextField deadlineField;
    private ArrayList<job> jobList;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;

    public cRRrun() {
        super("Computation Resource Requester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        jobList = new ArrayList<>();

        createWelcomePanel();

        setLocationRelativeTo(null);
        setVisible(true);
        initializeSocket();
    }

    private void createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        getContentPane().removeAll();
        add(welcomePanel);

        JTextArea summaryTextArea = new JTextArea("This is the Computational Resource Requester page where you can request a job.");
        summaryTextArea.setEditable(false);
        summaryTextArea.setWrapStyleWord(true);
        summaryTextArea.setLineWrap(true);
        summaryTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        welcomePanel.add(summaryTextArea, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        userIDField = new JTextField(10);
        jobIDField = new JTextField(10);
        durationField = new JTextField(10);
        deadlineField = new JTextField(10);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 5, 5); 

        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("  User ID (Number):"), gbc);

        gbc.gridx++;
        gbc.insets = new Insets(0, 5, 5, 0); 
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(userIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(5, 0, 0, 5); 
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("  Job ID (Number):"), gbc);

        gbc.gridx++;
        gbc.insets = new Insets(5, 5, 0, 0); 
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(jobIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(5, 0, 0, 5); 
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("  Job Duration (Hours):"), gbc);

        gbc.gridx++;
        gbc.insets = new Insets(5, 5, 0, 0); 
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(durationField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(5, 0, 0, 5); 
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("  Deadline (mm/dd/yyyy):"), gbc);

        gbc.gridx++;
        gbc.insets = new Insets(5, 5, 0, 0); 
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(deadlineField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(Box.createHorizontalStrut(85)); 
        JButton submitJobButton = new JButton("Submit");

        submitJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitJob();
            }
        });

        buttonPanel.add(submitJobButton);

        JPanel centeringPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        centeringPanel.add(inputPanel, gbc);
        gbc.gridy++;
        centeringPanel.add(buttonPanel, gbc);

        welcomePanel.add(centeringPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
    private void initializeSocket() {
        try {
            // Replace "localhost" and 8080 with the actual server address and port
            socket = new Socket("localhost", 8080);
            System.out.println("Connected to Server!");
            OutputStream outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error connecting to the server: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendInfo(String info) {
        try {
            // Send the information to the server
            objectOutputStream.writeUTF(info);
            objectOutputStream.flush();

               // Wait for a response from the server
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        String serverResponse = (String) objectInputStream.readObject();

        // Show the appropriate success or failure message based on the server's response
        if (serverResponse.equals("accepted")) {
            JOptionPane.showMessageDialog(frame, "The server has accepted the request!\nThank you for parking with us",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (serverResponse.equals("rejected")) {
            JOptionPane.showMessageDialog(frame, "The server has rejected the request.\nPlease see the lot attendent.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Unexpected server response: " + serverResponse,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error sending information to server: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
             JOptionPane.showMessageDialog(frame, "Error sending information to server: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


     private void submitJob() {
        try {
            int userID = Integer.parseInt(userIDField.getText());
            int jobID =  Integer.parseInt(jobIDField.getText());
            int duration = Integer.parseInt(durationField.getText());
            String deadline = deadlineField.getText();

            // Construct the information string
            String info = "Job Information: " + userID + " " + jobID + " " + duration + " " + deadline;

            // Send the information to the server
            sendInfo(info);
            
            userIDField.setText("");
            jobIDField.setText("");
            durationField.setText("");
            deadlineField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }

    private void saveToFile(int userID, String jobInfo, int duration, String deadline) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CRR.txt", true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String currentTime = dateFormat.format(new Date());

            writer.write("User ID: " + userID + ", Job Info: " + jobInfo +
                    ", Duration: " + duration + " hours, Deadline: " + deadline + ", Submitted at: " + currentTime);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new cRRrun();
        });
    }
    
}