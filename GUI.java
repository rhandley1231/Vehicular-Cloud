	
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Account Type Selection");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        frame.add(panel);

        // Create a label to display instructions
        JLabel label = new JLabel("Select your account type:");
        panel.add(label);

        // Create radio buttons for account types
        JRadioButton customerRadioButton = new JRadioButton("Customer");
        JRadioButton renterRadioButton = new JRadioButton("Renter");

        // Create a button group for radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(customerRadioButton);
        buttonGroup.add(renterRadioButton);

        // Create a submit button
        JButton submitButton = new JButton("Submit");

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (customerRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "You selected Customer.");
                } else if (renterRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "You selected Renter.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select an account type.");
                }
            }
        });

        // Add components to the panel
        panel.add(customerRadioButton);
        panel.add(renterRadioButton);
        panel.add(submitButton);

        // Center the JFrame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
	        frame.setVisible(true);
	    }
	}
