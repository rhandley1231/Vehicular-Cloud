
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class voRun {
    private JFrame frame;
    private JTextField ownerIDField, makeField, modelField, licensePlateField;
    private JButton submitButton, goBackButton;

    public voRun() {
        frame = new JFrame("Vehicle Owner Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        initComponents();
        buildUI();
        frame.setVisible(true);
    }

    private void initComponents() {
        ownerIDField = new JTextField(20);
        makeField = new JTextField(20);
        modelField = new JTextField(20);
        licensePlateField = new JTextField(20);
        submitButton = new JButton("Submit");
        goBackButton = new JButton("Quit");
    }

    private void buildUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        addLabelAndField(panel, "Vehicle Owner ID (Number):", ownerIDField, gbc, 0);
        addLabelAndField(panel, "Make:", makeField, gbc, 1);
        addLabelAndField(panel, "Model:", modelField, gbc, 2);
        addLabelAndField(panel, "License Plate:", licensePlateField, gbc, 3);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        submitButton.addActionListener(e -> {
            writeVehicleInfoToFile();
            
        });
        panel.add(submitButton, gbc);

        gbc.gridx = 1;
        goBackButton.addActionListener(e -> frame.dispose());
        panel.add(goBackButton, gbc);

        frame.add(panel);
    }

    private void addLabelAndField(JPanel panel, String label, JTextField field, GridBagConstraints gbc, int gridY) {
        JLabel fieldLabel = new JLabel(label);
        gbc.gridx = 0;
        gbc.gridy = gridY;
        panel.add(fieldLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = gridY;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }

    private void writeVehicleInfoToFile() {
        String ownerID = ownerIDField.getText();
        String make = makeField.getText();
        String model = modelField.getText();
        String licensePlate = licensePlateField.getText();
        String timestamp = getCurrentTimestamp();

        String vehicleInfo = String.format(
                "Vehicle Owner ID (Number): %s : Make: %s : Model: %s : License Plate: %s : Timestamp: %s",
                ownerID, make, model, licensePlate, timestamp);

        // Write the vehicle information to a file (you can specify the file name)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehicleInfo.txt", true))) {
            writer.write(vehicleInfo);
            writer.newLine();
            // Show success message
            JOptionPane.showMessageDialog(frame, "Successfully wrote vehicle information to file!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            // Clear input fields for new entries
            ownerIDField.setText("");
            makeField.setText("");
            modelField.setText("");
            licensePlateField.setText("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error writing vehicle information to file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(voRun::new);
    }
}

