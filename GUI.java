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

        ownerButton.addActionListener(new Actionlistener() {
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

        //Using CardLayout to switch beteween panels
        CardLayout CL = new CardLayout(0, 0);
        mainPanel.setLayout(CL);

        //Add components to the main panel
        mainPanel.add(VO);


        // Create radio buttons for account types
        JRadioButton VO_RadioButton = new JRadioButton("Vehicle Owner");
        JRadioButton JO_RadioButton = new JRadioButton("Job Owner");

        // Create a button group for radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(VO_RadioButton);
        buttonGroup.add(JO_RadioButton);

        // Create a submit button
        JButton submitButton = new JButton("Submit");

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (VO_RadioButton.isSelected()) {
                    CL.show(VO, "Vehicle Owner Home Page");
                } else if (JO_RadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(userSelectionPanel, "Redirecting to Job Owner Login");
                } else {
                    JOptionPane.showMessageDialog(userSelectionPanel, "Please select an account type.");
                }
            }
        });

        // Add components to the user selection panel
        userSelectionPanel.add(VO_RadioButton);
        userSelectionPanel.add(JO_RadioButton);
        userSelectionPanel.add(submitButton);

        // Center the JFrame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
	        frame.setVisible(true);
	    }
	}
