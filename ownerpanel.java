
public class ownerpanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		private JPanel createOwnerPanel() {
		    JPanel panel = new JPanel();
		    panel.setLayout(new GridBagLayout());
		    GridBagConstraints gbc = new GridBagConstraints();
		    gbc.insets = new Insets(5, 5, 5, 5);
		    gbc.anchor = GridBagConstraints.WEST;

		    JLabel userIdLabel = new JLabel("Owner ID:");
		    userIdField = new JTextField();
		    JLabel vehicleInfoLabel = new JLabel("Vehicle Info:");
		    vehicleInfoField = new JTextField();
		    JLabel residencyTimeLabel = new JLabel("Residency Time:");
		    residencyTimeField = new JTextField();

		    // Adding components to the panel with GridBagConstraints
		    gbc.gridx = 0;
		    gbc.gridy = 0;
		    panel.add(userIdLabel, gbc);
		    gbc.gridx = 1;
		    gbc.gridy = 0;
		    gbc.fill = GridBagConstraints.HORIZONTAL;
		    gbc.weightx = 1.0;
		    panel.add(userIdField, gbc);

		    gbc.gridx = 0;
		    gbc.gridy = 1;
		    panel.add(vehicleInfoLabel, gbc);
		    gbc.gridx = 1;
		    gbc.gridy = 1;
		    gbc.fill = GridBagConstraints.HORIZONTAL;
		    gbc.weightx = 1.0;
		    panel.add(vehicleInfoField, gbc);

		    gbc.gridx = 0;
		    gbc.gridy = 2;
		    panel.add(residencyTimeLabel, gbc);
		    gbc.gridx = 1;
		    gbc.gridy = 2;
		    gbc.fill = GridBagConstraints.HORIZONTAL;
		    gbc.weightx = 1.0;
		    panel.add(residencyTimeField, gbc);

		    JButton submitButton = new JButton("Submit");
		    submitButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            saveOwnerInformation();
		        }
		    });

		    gbc.gridx = 0;
		    gbc.gridy = 3;
		    gbc.gridwidth = 2;
		    gbc.fill = GridBagConstraints.NONE;
		    gbc.anchor = GridBagConstraints.CENTER;
		    panel.add(submitButton, gbc);

		    return panel;
		}

	}

}
