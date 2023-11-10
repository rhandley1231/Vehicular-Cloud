import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import classes.*;

import java.util.ArrayList;

public class cRRrun extends JFrame {
    private JTextField userIDField;
    private ArrayList<Integer> requestedUserIDs = new ArrayList<>();

    public cRRrun() {
        super("Computation Resource Requester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        createWelcomePanel();

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        getContentPane().removeAll();
        add(welcomePanel);

        JLabel titleLabel = new JLabel("Computation Resource Requester", SwingConstants.CENTER);
        welcomePanel.add(titleLabel, BorderLayout.NORTH);

        userIDField = new JTextField(10);
        JButton requestJobButton = new JButton("Request Job");

        requestJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestJob();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("User ID:"));
        inputPanel.add(userIDField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(requestJobButton);

        welcomePanel.add(inputPanel, BorderLayout.CENTER);
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void requestJob() {
        try {
            int userID = Integer.parseInt(userIDField.getText());
            requestedUserIDs.add(userID);
            System.out.println("Job requested for User ID: " + userID);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid User ID. Please enter a valid integer.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new cRRrun();
        });
    }
    
}
