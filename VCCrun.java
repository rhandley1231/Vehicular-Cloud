import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import classes.VCC;

public class VCCrun {
    private JFrame frame;
    private JPanel currentPanel;
    private JTextArea computationResultArea;

    public VCCrun() {
        frame = new JFrame("Utopia VCRTS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        createWelcomePanel();
        frame.setVisible(true);
        VCC.dbConnect();
        VCC.runServer();
    }

    private void createWelcomePanel() {
        currentPanel = new JPanel();
        frame.getContentPane().removeAll();
        frame.add(currentPanel);

        currentPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'><p>Welcome to the Utopia VCRTS Vehicular Cloud Controller, where you can:</p>"
        + "<ul>"
        + "<li>Accept or Reject Incoming Jobs or Parking Requests</li>"
        + "<li>See computation times</li>"
        + "</ul></div></html>");
        
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font labelFont = welcomeLabel.getFont();
        welcomeLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 18));

        currentPanel.add(welcomeLabel, BorderLayout.NORTH);

        JButton computationTimeButton = new JButton("See Computation Time");

        currentPanel.add(computationTimeButton, BorderLayout.SOUTH);

        computationTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computationTimePanel();
                
            }
        });

        frame.revalidate();
        frame.repaint();
        
    }

    private void computationTimePanel() {
        currentPanel = new JPanel();
        frame.getContentPane().removeAll();
        frame.add(currentPanel);
    
        JButton goBackButton = new JButton("Go Back");
    
        computationResultArea = new JTextArea(10, 40);
        computationResultArea.setEditable(false);
        computationResultArea.setWrapStyleWord(true);
        computationResultArea.setLineWrap(true);
        computationResultArea.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Call VCC.jobCompletion() and capture the results
        ArrayList[] result = VCC.jobCompletion();  // Fixed this line
        ArrayList<Integer> jobIDs = result[0];
        ArrayList<Integer> jobTimes = result[1];
        ArrayList<Integer> jobCompletionTimes = result[2];
    
        StringBuilder resultText = new StringBuilder();
        for (int i = 0; i < jobIDs.size(); i++) {
            resultText.append("Job ID: ").append(jobIDs.get(i)).append(", Job Duration: ")
                    .append(jobTimes.get(i)).append(", Completion Time: ").append(jobCompletionTimes.get(i)).append("\n");
        }
    
        computationResultArea.setText(resultText.toString());
    
        JScrollPane scrollPane = new JScrollPane(computationResultArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createWelcomePanel();
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
        SwingUtilities.invokeLater(() -> {
            new VCCrun();
        });
    }
}
