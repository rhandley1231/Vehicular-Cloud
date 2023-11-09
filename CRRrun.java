import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CRRrun {
    private ArrayList<job> jobList = new ArrayList<job>();

    public CRRrun(int userID) {
        super(userID);
        this.jobList = null;

        setTitle("Computation Resource Requester");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        computationTimePanel();

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

}