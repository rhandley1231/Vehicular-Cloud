import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vccRun {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel welcomePanel;
    private JPanel vehicleOwnerPanel;
    private JPanel cRRPanel;

    private JButton ownerButton;
    private JButton clientButton;
    private JButton systemAdminButton;

    public vccRun() {
        frame = new JFrame("The Utopia VCRTS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        welcomePanel = createWelcomePanel();
        vehicleOwnerPanel = createVehicleOwnerPanel();
        cRRPanel = createCRRPanel();

        cardPanel.add(welcomePanel, "Welcome");
        cardPanel.add(vehicleOwnerPanel, "Vehicle Owner");
        cardPanel.add(cRRPanel, "Computation Resource Requestor");

        frame.add(cardPanel);

        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBackground(new Color(51, 153, 255));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        if (actionListener != null) {
            button.addActionListener(actionListener);
        }
        return button;
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(230, 230, 230));

        JLabel welcomeLabel = new JLabel("Welcome to Vehicle Management App");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centeredContent = new JPanel(new BorderLayout());
        centeredContent.add(welcomeLabel, BorderLayout.NORTH);
        centeredContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(centeredContent, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        ownerButton = createStyledButton("Vehicle Owner", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Vehicle Owner");
            }
        });

        clientButton = createStyledButton("Computation Resource Requestor", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Computation Resource Requestor");
            }
        });

        systemAdminButton = createStyledButton("System Admin", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the logic for transitioning to the System Admin login panel
                // cardLayout.show(cardPanel, "AdminPanel");
            }
        });

        buttonPanel.add(ownerButton);
        buttonPanel.add(clientButton);
        buttonPanel.add(systemAdminButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createVehicleOwnerPanel() {
        JPanel panel = new JPanel();
        // Customize the panel for Vehicle Owner
        // Add components as needed
        return panel;
    }

    private JPanel createCRRPanel() {
        JPanel panel = new JPanel();
        // Customize the panel for Computation Resource Requestor
        // Add components as needed
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new vccRun();
            }
        });
    }
}


