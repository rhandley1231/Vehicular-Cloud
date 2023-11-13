
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import classes.vehicle;
import classes.vO;

public class voRun {
    private JFrame frame;
    private JTextField ownerIDField, makeField, modelField, licensePlateField;
    private JButton submitButton, goBackButton;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;


    public voRun() {
        frame = new JFrame("Vehicle Owner Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        initComponents();
        buildUI();
        initializeSocket();
        frame.setVisible(true);
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
            sendVehicleInfoToServer();
            
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
    private void sendVehicleInfoToServer() {
        try {
            int ownerID = Integer.parseInt(ownerIDField.getText());
            String make = makeField.getText();
            String model = modelField.getText();
            String licensePlate = licensePlateField.getText();

            // Create a Vehicle object
            vehicle vehicle = new vehicle(make, model, licensePlate);
            vO owner = new vO(ownerID, vehicle);

            // Send the Vehicle object to the server
            objectOutputStream.writeUTF("Vehicle and Owner Info: " + Integer.toString(owner.getUserID()) +" "+ owner.getVehicle().getMake()+" " + owner.getVehicle().getModel()+" " + owner.getVehicle().getPlate());
            objectOutputStream.flush();

            // Show success message
            JOptionPane.showMessageDialog(frame, "Successfully sent vehicle information to server!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear input fields for new entries
            ownerIDField.setText("");
            makeField.setText("");
            modelField.setText("");
            licensePlateField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error sending vehicle information to server: " + e.getMessage(),
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

