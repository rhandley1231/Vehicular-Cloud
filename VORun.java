import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VORun {
    private JFrame frame;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField plateField;
    private JTextField lengthOfStayField;
    private JComboBox<String> lengthOfStayUnitComboBox;

    public VORun() {
        frame = new JFrame("Vehicle Owner Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JTextField(20);

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
        lengthOfStayUnitComboBox = new JComboBox<>(new String[]{"hours", "days"});

        JButton parkButton = new JButton("Park");
        parkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeVehicleInfoToFile();
                frame.dispose();
            }
        });

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
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(makeLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(makeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(modelLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(modelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(yearLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(yearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(plateLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(plateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(lengthOfStayLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(lengthOfStayField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        panel.add(lengthOfStayUnitComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(parkButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void writeVehicleInfoToFile() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String make = makeField.getText();
        String model = modelField.getText();
        String year = yearField.getText();
        String plate = plateField.getText();
        String lengthOfStay = lengthOfStayField.getText() + " " + lengthOfStayUnitComboBox.getSelectedItem();
        String timestamp = getCurrentTimestamp();

        String vehicleInfo = "Username: " + username + " : Password: " + password + " : Make: " + make + " : Model: " + model +
                " : Year: " + year + " : Plate: " + plate + " : Length of Stay: " + lengthOfStay + " : Timestamp: " + timestamp;

        // Write the vehicle information to a file (you can specify the file name)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehicleInfo.txt", true))) {
            writer.write(vehicleInfo);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error writing vehicle information to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                new VORun();
            }
        });
    }
}
