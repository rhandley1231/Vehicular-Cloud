import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class VCC {
    private JFrame frame;
    private JPanel currentPanel;
    private JPanel previousPanel;
    private JTextArea messageArea;
    private AdminLoginPanel adminLoginPanel;
    private AdminWelcomePanel adminWelcomePanel;
    private JobSubmissionPanel jobSubmissionPanel;
    private VehicleSubmissionPanel vehicleSubmissionPanel;
    

    private List<JobSubmissionData> jobSubmissions = new ArrayList<>();
    private List<VehicleSubmissionData> vehicleSubmissions = new ArrayList<>();

    public VCC() {
        frame = new JFrame("VCC Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        createWelcomePanel();

        frame.setVisible(true);
    }

    private void createWelcomePanel() {
        currentPanel = new JPanel();
        frame.getContentPane().removeAll();
        frame.add(currentPanel);

        currentPanel.setLayout(new BorderLayout());

        adminLoginPanel = new AdminLoginPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminUsername = adminLoginPanel.getAdminUsername();
                char[] adminPassword = adminLoginPanel.getAdminPassword();

                if (isAdminValid(adminUsername, adminPassword)) {
                    showMessage("Admin login successful.");
                    showAdminWelcomePanel();
                } else {
                    showMessage("Invalid admin credentials. Please try again.");
                }
            }
        });
        currentPanel.add(adminLoginPanel, BorderLayout.CENTER);

        messageArea = new JTextArea(10, 40);
        messageArea.setEditable(false);
        messageArea.setWrapStyleWord(true);
        messageArea.setLineWrap(true);
        messageArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentPanel.add(messageArea, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private boolean isAdminValid(String username, char[] password) {
        String adminUsername = "123";
        char[] adminPassword = "123".toCharArray();

        return username.equals(adminUsername) && Arrays.equals(password, adminPassword);
    }

    private void showMessage(String message) {
        if (messageArea != null) {
            messageArea.append(message + "\n");
        }
    }

    private void showAdminWelcomePanel() {
        adminWelcomePanel = new AdminWelcomePanel(this);

        previousPanel = currentPanel;

        currentPanel.removeAll();
        currentPanel.add(adminWelcomePanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showJobSubmissionPanel() {
        jobSubmissionPanel = new JobSubmissionPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clientID = jobSubmissionPanel.getClientID();
                int jobID = jobSubmissionPanel.getJobID();
                int jobDuration = jobSubmissionPanel.getJobDuration();
                String deadline = jobSubmissionPanel.getDeadline();

                JobSubmissionData jobSubmission = new JobSubmissionData(clientID, jobID, jobDuration, deadline);
                jobSubmissions.add(jobSubmission);

                DataSaver.saveJobSubmission(jobSubmission);

                showMessage("Job submitted successfully.");
                showAdminWelcomePanel();
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminWelcomePanel();
            }
        });

        previousPanel = currentPanel;

        currentPanel.removeAll();
        currentPanel.add(jobSubmissionPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showVehicleSubmissionPanel() {
        vehicleSubmissionPanel = new VehicleSubmissionPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int vOID = vehicleSubmissionPanel.getVOID();
                String make = vehicleSubmissionPanel.getMake();
                String model = vehicleSubmissionPanel.getModel();
                String plate = vehicleSubmissionPanel.getPlate();

                VehicleSubmissionData vehicleSubmission = new VehicleSubmissionData(vOID, make, model, plate);
                vehicleSubmissions.add(vehicleSubmission);

                DataSaver.saveVehicleSubmission(vehicleSubmission);

                showMessage("Vehicle submitted successfully.");
                showAdminWelcomePanel();
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminWelcomePanel();
            }
        });

        previousPanel = currentPanel;

        currentPanel.removeAll();
        currentPanel.add(vehicleSubmissionPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public static class AdminLoginPanel extends JPanel {
        private JTextField adminUsernameField;
        private JPasswordField adminPasswordField;
        private JButton loginButton;

        public AdminLoginPanel(ActionListener loginListener) {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            adminUsernameField = new JTextField(20);
            adminPasswordField = new JPasswordField(20);
            loginButton = new JButton("Login");

            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel("Admin Username (its 123):"), gbc);
            gbc.gridx = 1;
            add(adminUsernameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Admin Password (its 123):"), gbc);
            gbc.gridx = 1;
            add(adminPasswordField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(loginButton, gbc);

            loginButton.addActionListener(loginListener);
        }

        public String getAdminUsername() {
            return adminUsernameField.getText();
        }

        public char[] getAdminPassword() {
            return adminPasswordField.getPassword();
        }
    }

    public static class AdminWelcomePanel extends JPanel {
        public AdminWelcomePanel(VCC vcc) {
            JLabel welcomeLabel = new JLabel("Welcome, Admin!");
            JButton jobSubmissionButton = new JButton("Submit Job");
            JButton vehicleSubmissionButton = new JButton("Submit Vehicle");

            jobSubmissionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vcc.showJobSubmissionPanel();
                }
            });

            vehicleSubmissionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vcc.showVehicleSubmissionPanel();
                }
            });

            add(welcomeLabel);
            add(jobSubmissionButton);
            add(vehicleSubmissionButton);
        }
    }

    public static class JobSubmissionPanel extends JPanel {
        private JTextField clientIDField;
        private JTextField jobIDField;
        private JTextField jobDurationField;
        private JTextField deadlineField;
        private JButton submitButton;
        private JButton homeButton;

        public JobSubmissionPanel(ActionListener submitListener, ActionListener homeListener) {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            clientIDField = new JTextField(20);
            jobIDField = new JTextField(20);
            jobDurationField = new JTextField(20);
            deadlineField = new JTextField(20);
            submitButton = new JButton("Submit");
            homeButton = new JButton("Home");

            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel("Client ID (Number):"), gbc);
            gbc.gridx = 1;
            add(clientIDField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Job ID (Number):"), gbc);
            gbc.gridx = 1;
            add(jobIDField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            add(new JLabel("Job Duration (Hours):"), gbc);
            gbc.gridx = 1;
            add(jobDurationField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            add(new JLabel("Deadline (mm/dd/yyyy):"), gbc);
            gbc.gridx = 1;
            add(deadlineField, gbc);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(submitButton);
            buttonPanel.add(homeButton);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.SOUTH;
            add(buttonPanel, gbc);

            submitButton.addActionListener(submitListener);
            homeButton.addActionListener(homeListener);
        }

        public String getClientID() {
            return clientIDField.getText();
        }

        public int getJobID() {
            return Integer.parseInt(jobIDField.getText());
        }

        public int getJobDuration() {
            return Integer.parseInt(jobDurationField.getText());
        }

        public String getDeadline() {
            return deadlineField.getText();
        }
    }

    public static class VehicleSubmissionPanel extends JPanel {
        private JTextField vOIDField;
        private JTextField makeField;
        private JTextField modelField;
        private JTextField plateField;
        private JButton submitButton;
        private JButton homeButton;

        public VehicleSubmissionPanel(ActionListener submitListener, ActionListener homeListener) {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            vOIDField = new JTextField(20);
            makeField = new JTextField(20);
            modelField = new JTextField(20);
            plateField = new JTextField(20);
            submitButton = new JButton("Submit");
            homeButton = new JButton("Home");

            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel("Vehicle Owner ID (Number):"), gbc);
            gbc.gridx = 1;
            add(vOIDField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Make:"), gbc);
            gbc.gridx = 1;
            add(makeField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            add(new JLabel("Model:"), gbc);
            gbc.gridx = 1;
            add(modelField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            add(new JLabel("License Plate:"), gbc);
            gbc.gridx = 1;
            add(plateField, gbc);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(submitButton);
            buttonPanel.add(homeButton);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.SOUTH;
            add(buttonPanel, gbc);

            submitButton.addActionListener(submitListener);
            homeButton.addActionListener(homeListener);
        }

        public int getVOID() {
            return Integer.parseInt(vOIDField.getText());
        }

        public String getMake() {
            return makeField.getText();
        }

        public String getModel() {
            return modelField.getText();
        }

        public String getPlate() {
            return plateField.getText();
        }
    }

    public static class JobSubmissionData {
        private String clientID;
        private int jobID;
        private int jobDuration;
        private String deadline;

        public JobSubmissionData(String clientID, int jobID, int jobDuration, String deadline) {
            this.clientID = clientID;
            this.jobID = jobID;
            this.jobDuration = jobDuration;
            this.deadline = deadline;
        }

        public String getClientID() {
            return clientID;
        }

        public int getJobID() {
            return jobID;
        }

        public int getJobDuration() {
            return jobDuration;
        }

        public String getDeadline() {
            return deadline;
        }
    }

    public static class VehicleSubmissionData {
        private int vOID;
        private String make;
        private String model;
        private String plate;

        public VehicleSubmissionData(int vOID, String make, String model, String plate) {
            this.vOID = vOID;
            this.make = make;
            this.model = model;
            this.plate = plate;
        }

        public int getVOID() {
            return vOID;
        }

        public String getMake() {
            return make;
        }

        public String getModel() {
            return model;
        }

        public String getPlate() {
            return plate;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VCC();
        });
        viewSavedData();
    }

    public static void viewSavedData() {
        // File paths
        String jobSubmissionFile = "job_submissions.txt";
        String vehicleSubmissionFile = "vehicle_submissions.txt";

        // Read and display job submissions
        System.out.println("Job Submissions:");
        readAndDisplayFileContents(jobSubmissionFile);

        // Read and display vehicle submissions
        System.out.println("\nVehicle Submissions:");
        readAndDisplayFileContents(vehicleSubmissionFile);
    }

    public static void readAndDisplayFileContents(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DataSaver {
    private static final String JOB_SUBMISSION_FILE = "job_submissions.txt"; // Define constant for job submissions file
    private static final String VEHICLE_SUBMISSION_FILE = "vehicle_submissions.txt"; // Define constant for vehicle submissions file

    public static void saveJobSubmission(VCC.JobSubmissionData data) {
        try (FileWriter writer = new FileWriter(JOB_SUBMISSION_FILE, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            String line = String.format("%s,%d,%d,%s", data.getClientID(), data.getJobID(), data.getJobDuration(), data.getDeadline());
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveVehicleSubmission(VCC.VehicleSubmissionData data) {
        try (FileWriter writer = new FileWriter(VEHICLE_SUBMISSION_FILE, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            String line = String.format("%d,%s,%s,%s", data.getVOID(), data.getMake(), data.getModel(), data.getPlate());
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
