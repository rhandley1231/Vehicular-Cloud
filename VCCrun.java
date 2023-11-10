import classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VCCrun extends JFrame {
    private JFrame frame;
    private JTextField jobIDField, jobDurationField, completionTimeField;

    public VCCrun() {
        super("Computation Resource Requester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        createWelcomePanel(); // Call the method to create the initial welcome panel
    }

    private void createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        getContentPane().removeAll();
        add(welcomePanel);

        JLabel titleLabel = new JLabel("Computation Resource Requester", SwingConstants.CENTER);
        JButton proceedButton = new JButton("Proceed");

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computationTimePanel(); // Call the method to switch to the computation time panel
            }
        });

        welcomePanel.add(titleLabel, BorderLayout.NORTH);
        welcomePanel.add(proceedButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void computationTimePanel() {
        JPanel computationPanel = new JPanel(new BorderLayout());
        getContentPane().removeAll();
        add(computationPanel);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        jobIDField = new JTextField(10);
        jobDurationField = new JTextField(10);
        completionTimeField = new JTextField(10);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Job ID:"), gbc);
        gbc.gridy++;
        inputPanel.add(jobIDField, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Job Duration:"), gbc);
        gbc.gridy++;
        inputPanel.add(jobDurationField, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Completion Time:"), gbc);
        gbc.gridy++;
        inputPanel.add(completionTimeField, gbc);

        JButton submitButton = new JButton("Submit");
        JButton goBackButton = new JButton("Go Back");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Process the submitted information (you can store it in variables, print it, etc.)
                System.out.println("Job ID: " + jobIDField.getText());
                System.out.println("Job Duration: " + jobDurationField.getText());
                System.out.println("Completion Time: " + completionTimeField.getText());
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createWelcomePanel(); // Call the method to switch back to the welcome panel
            }
        });

        computationPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(goBackButton);
        buttonPanel.add(submitButton);
        computationPanel.add(buttonPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VCCrun().setVisible(true);
            }
        });
    }


/* THIS IS THE OLD CODE IN CASE
    private JFrame frame;

    public CRRrun() {
        super("Computation Resource Requester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        createWelcomePanel(); // Call the method to create the initial welcome panel
    }

    private void createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        getContentPane().removeAll();
        add(welcomePanel);

        JLabel titleLabel = new JLabel("Computation Resource Requester", SwingConstants.CENTER);
        JButton proceedButton = new JButton("Proceed");

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computationTimePanel(); // Call the method to switch to the computation time panel
            }
        });

        welcomePanel.add(titleLabel, BorderLayout.NORTH);
        welcomePanel.add(proceedButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void computationTimePanel() {
        JPanel computationPanel = new JPanel(new BorderLayout());
        getContentPane().removeAll();
        add(computationPanel);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        JTextField jobIDField = new JTextField(10);
        JTextField jobDurationField = new JTextField(10);
        JTextField completionTimeField = new JTextField(10);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Job ID:"), gbc);
        gbc.gridy++;
        inputPanel.add(jobIDField, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Job Duration:"), gbc);
        gbc.gridy++;
        inputPanel.add(jobDurationField, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Completion Time:"), gbc);
        gbc.gridy++;
        inputPanel.add(completionTimeField, gbc);

        JButton submitButton = new JButton("Submit");
        JButton goBackButton = new JButton("Go Back");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Process the submitted information (you can store it in variables, print it, etc.)
                System.out.println("Job ID: " + jobIDField.getText());
                System.out.println("Job Duration: " + jobDurationField.getText());
                System.out.println("Completion Time: " + completionTimeField.getText());
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createWelcomePanel(); // Call the method to switch back to the welcome panel
            }
        });

        computationPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(goBackButton);
        buttonPanel.add(submitButton);
        computationPanel.add(buttonPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CRRrun().setVisible(true);
            }
        });
    }
    */
/*
    private ArrayList<job> jobList = new ArrayList<job>();
    private JFrame frame;

    public CRRrun() {
        super();
        this.jobList = null;

        setTitle("Computation Resource Requester");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        computationTimePanel();

    }

    private void setDefaultCloseOperation(int exitOnClose) {
    }

    private void setSize(int i, int j) {
    }

    private void setTitle(String string) {
    }

    private void computationTimePanel() {
        JPanel currentPanel = new JPanel();
        frame.getContentPane().removeAll();
        frame.add(currentPanel);
    
        JButton goBackButton = new JButton("Go Back");
    
        JTextArea computationResultArea = new JTextArea(10, 40);
        computationResultArea.setEditable(false);
        computationResultArea.setWrapStyleWord(true);
        computationResultArea.setLineWrap(true);
        computationResultArea.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        ArrayList[] result = VCC.jobCompletion();
        ArrayList<Integer> jobIDs = result[0];
        ArrayList<Integer> jobTimes = result[1];
        ArrayList<Integer> jobCompletionTimes = result[2];
    
        StringBuilder resultText = new StringBuilder();
        for (int i = 0; i < jobIDs.size(); i++) {
            resultText.append("Job ID: ").append(jobIDs.get(i)).append(", Job Duration: ").append(jobTimes.get(i)).append(", Completion Time: ").append(jobCompletionTimes.get(i)).append("\n");
        }
    
        computationResultArea.setText(resultText.toString());
    
        JScrollPane scrollPane = new JScrollPane(computationResultArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createWelcomePanel();
            }

            private void createWelcomePanel() {
            }
        });
    
        currentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        currentPanel.add(scrollPane, gbc);
    
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(goBackButton, gbc);
    
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CRRrun();
            }
        });
    }
*/
}
