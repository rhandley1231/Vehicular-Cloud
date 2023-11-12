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
    private JTextField ownerIDField;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField licensePlateField;

    public VORun() {
        frame = new JFrame("Vehicle Owner Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel ownerIDLabel = new JLabel("Vehicle Owner ID:");
        ownerIDField = new JTextField(20);

        JLabel makeLabel = new JLabel("Make:");
        makeField = new JTextField(20);

        JLabel modelLabel = new JLabel("Model:");
        modelField = new JTextField(20);

        JLabel licensePlateLabel = new JLabel("License Plate:");
        licensePlateField = new JTextField(20);

        JButton parkButton = new JButton("Sumbit");
        parkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeVehicleInfoToFile();
                frame.dispose();
            }
        });

        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(ownerIDLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(ownerIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(makeLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(makeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(modelLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(modelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(licensePlateLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(licensePlateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(parkButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(goBackButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void writeVehicleInfoToFile() {
        String ownerID = ownerIDField.getText();
        String make = makeField.getText();
        String model = modelField.getText();
        String licensePlate = licensePlateField.getText();
        String timestamp = getCurrentTimestamp();

        String vehicleInfo = "Owner ID: " + ownerID + " : Make: " + make + " : Model: " + model +
                " : License Plate: " + licensePlate + " : Timestamp: " + timestamp;

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
