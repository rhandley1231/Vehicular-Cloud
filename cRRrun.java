import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import classes.*;

public class cRRrun extends JFrame {
    private JFrame frame;
    private JTextField userIDField;
    private JTextField jobInfoField;
    private JTextField durationField;
    private JTextField deadlineField;
    private ArrayList<job> jobList;

    public cRRrun() {
        super("Computation Resource Requester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        jobList = new ArrayList<>();

        createWelcomePanel();

        setLocationRelativeTo(null);
        setVisible(true);
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
        jobInfoField = new JTextField(10);
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
        inputPanel.add(jobInfoField, gbc);

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
                JOptionPane.showMessageDialog(frame, "Job created successfully!");
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

    private void submitJob() {
        try {
            int userID = Integer.parseInt(userIDField.getText());
            String jobInfo = jobInfoField.getText();
            int duration = Integer.parseInt(durationField.getText());
            String deadline = deadlineField.getText();

            saveToFile(userID, jobInfo, duration, deadline);

            User currentUser = new User(userID);
            job currentJob = new job(userID, duration, deadline);

            jobList.add(currentJob);

            System.out.println("Job submitted for User ID: " + userID + ", Job Info: " + jobInfo +
                    ", Duration: " + duration + " hours, Deadline: " + deadline);

            userIDField.setText("");
            jobInfoField.setText("");
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