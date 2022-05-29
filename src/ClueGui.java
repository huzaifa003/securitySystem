
import javax.swing.*;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class ClueGui extends JFrame {
	private JTextField DescriptionTxtBox;
	private JTextField streetTxtBox;
	private JTextField yearTxt;
	private JTextField dayTxt;
	private JTextField monthTxt;
	JComboBox<String> cluesBox;
	TextArea clueArea;
	private JButton getData;
	public ClueGui() {

		String[] tableNames = new String[]{"","airports","atm_transactions","bakery_security_logs","bank_accounts","crime_scene_reports","flights","interviews","passengers","people","phone_calls"};
		getContentPane().setLayout(null);
		
		JPanel clueShowPanel = new JPanel();
		clueShowPanel.setBounds(10, 78, 856, 374);
		getContentPane().add(clueShowPanel);
		clueShowPanel.setLayout(null);
		
		cluesBox = new JComboBox<>(tableNames);
		cluesBox.setBounds(10, 11, 120, 22);
		clueShowPanel.add(cluesBox);

		getData = new JButton("Retrieve Data");
		getData.setLocation(10,300);
		getData.setSize(100,50);
		getData.addActionListener(new retrieveActionListener());
		clueShowPanel.add(getData);

		clueArea = new TextArea();
		clueArea.setText("Please Select From the side menu which table is to be displayed");
		clueArea.setEditable(false);
		clueArea.setBounds(136, 11, 710, 353);
		clueShowPanel.add(clueArea);
		
		JLabel ClueHeadLabel = new JLabel("Display Clues and Upload Clues");
		ClueHeadLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ClueHeadLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ClueHeadLabel.setBounds(10, 26, 856, 41);
		getContentPane().add(ClueHeadLabel);
		
		JButton addStatements = new JButton("Upload Witness Statements");
		addStatements.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String street = streetTxtBox.getText();
				String description = DescriptionTxtBox.getText();
				int month = Integer.parseInt(monthTxt.getText());
				int day = Integer.parseInt(dayTxt.getText());
				int year = Integer.parseInt(yearTxt.getText());

				try {
					ClueDatabase clueDatabase = new ClueDatabase();
					clueDatabase.addReport(year,month,day,street,description);
					JOptionPane.showMessageDialog(null,"Added Successfully","Confirmation box",JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}

			}
		});
		addStatements.setBounds(717, 495, 149, 41);
		getContentPane().add(addStatements);
		
		DescriptionTxtBox = new JTextField();
		DescriptionTxtBox.setBounds(144, 495, 563, 41);
		getContentPane().add(DescriptionTxtBox);
		DescriptionTxtBox.setColumns(10);
		
		streetTxtBox = new JTextField();
		streetTxtBox.setBounds(10, 495, 124, 41);
		getContentPane().add(streetTxtBox);
		streetTxtBox.setColumns(10);
		
		yearTxt = new JTextField();
		yearTxt.setColumns(10);
		yearTxt.setBounds(144, 547, 114, 41);
		getContentPane().add(yearTxt);
		
		dayTxt = new JTextField();
		dayTxt.setColumns(10);
		dayTxt.setBounds(407, 545, 86, 41);
		getContentPane().add(dayTxt);
		
		monthTxt = new JTextField();
		monthTxt.setColumns(10);
		monthTxt.setBounds(647, 547, 86, 41);
		getContentPane().add(monthTxt);
		
		JLabel nameWitnessLabel = new JLabel("Enter Street");
		nameWitnessLabel.setBounds(20, 463, 150, 26);
		getContentPane().add(nameWitnessLabel);
		
		JLabel witnessStatementLabel = new JLabel("Enter Description");
		witnessStatementLabel.setBounds(379, 463, 150, 26);
		getContentPane().add(witnessStatementLabel);
		
		JLabel yearLabel = new JLabel("Enter Year");
		yearLabel.setBounds(10, 547, 124, 39);
		getContentPane().add(yearLabel);
		
		JLabel dayLabel = new JLabel("Enter Day");
		dayLabel.setBounds(268, 547, 124, 39);
		getContentPane().add(dayLabel);
		
		JLabel monthLabel = new JLabel("Enter Month");
		monthLabel.setBounds(513, 547, 124, 39);
		getContentPane().add(monthLabel);
	}

	private class retrieveActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String tableName = (String) cluesBox.getSelectedItem();
			try {
				ClueDatabase clueDatabase = new ClueDatabase();
				clueDatabase.showCluesOnScreen(tableName,clueArea);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}

		}
	}
}
