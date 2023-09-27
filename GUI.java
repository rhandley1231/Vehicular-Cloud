	
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class GUI {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Account Type Selection");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Createing a main panel to house all the other panels/components
        JPanel mainPanel = new JPanel();
        frame.add(mainPanel);

        // Creating a user selection panel
        JPanel userSelectionPanel = new JPanel();
        mainPanel.add(userSelectionPanel);
        // Create a label to display instructions
        JLabel label = new JLabel("Select your account type:");
        userSelectionPanel.add(label);


        /*Create User panels to hold user data, our buttons should navigate a user
        to their respective dashboard.  Assume a login is necessary to access the page
        */
        //JPanel VO
        JPanel VO = new JPanel();
        JLabel VOLabel = new JLabel("Vehicle Owner Homepage");
        VO.add(VOLabel);  //Add more functionality for Vehicle Owners within this Panel
        

        //JPanel JO 


        //JPanel Admin (Garage admin)


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

        //Storing information on file from user input
        public static void saveFile(String data) throws IOException {
            String fileName = new SimpleDataFormat("yyyyMMddHHmm'.txt'").format(new java.util.Date());
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(data);
            writer.close();
            System.out.println("File Saved");
        }
        
	}
