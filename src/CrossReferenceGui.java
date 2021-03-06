import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class CrossReferenceGui extends JFrame{
	CrossReferenceDatabase crossReferenceDatabase;
	public int PROGRESS_CONSTANT = 10;
	public CrossReferenceGui() {

		getContentPane().setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(681, 492, 161, 68);
		getContentPane().add(progressBar);
		progressBar.setStringPainted(true);

		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(20, 67, 822, 414);
		getContentPane().add(textArea);

		try {
			crossReferenceDatabase = new CrossReferenceDatabase(textArea);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(10, 492, 400, 100);
		getContentPane().add(panel);
		
		JButton reportButton = new JButton("Get Reports");

		JButton getInterviews = new JButton("Get Interviews");
		getInterviews.setEnabled(false);

		reportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int month = Integer.parseInt(JOptionPane.showInputDialog("Enter the Month please"));
				int day = Integer.parseInt(JOptionPane.showInputDialog("Enter the Day please"));
				int year = Integer.parseInt(JOptionPane.showInputDialog("Enter the Year please"));
				String street = (String) JOptionPane.showInputDialog("Enter the Street");

				crossReferenceDatabase.getCrimeSceneReport(month,day,year,street); //28-7-2021 Humphrey Street

				getInterviews.setEnabled(true);
				progressBar.setValue(PROGRESS_CONSTANT);
			}
		});

		JButton licenseButton = new JButton("Get License Plates");
		licenseButton.setEnabled(false);

		getInterviews.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					crossReferenceDatabase.getTranscripts();
					licenseButton.setEnabled(true);
					progressBar.setValue(PROGRESS_CONSTANT * 2);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});

		JButton transactionButton = new JButton("Get Transaction");
		transactionButton.setEnabled(false);

		licenseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int hour = Integer.parseInt(JOptionPane.showInputDialog("Enter the Hour please")); //10
				int startingMinutes = Integer.parseInt(JOptionPane.showInputDialog("Enter the Starting minutes please")); //15
				int endingMinutes = Integer.parseInt(JOptionPane.showInputDialog("Enter the Ending Minutes please")); //25
				String activity = (String) JOptionPane.showInputDialog("Enter the Activity, exit or entrance"); //exit

				crossReferenceDatabase.getLicensePlate(hour,startingMinutes,endingMinutes,activity);
				transactionButton.setEnabled(true);
				progressBar.setValue(PROGRESS_CONSTANT * 3);
			}
		});


		JButton accountDetailsButton = new JButton("Get Account Details");
		accountDetailsButton.setEnabled(false);

		transactionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("HELLO");
				String atm_street = (String) JOptionPane.showInputDialog("Enter the Atm Street "); //Leggett Street
				crossReferenceDatabase.getAtmLocation(atm_street);
				accountDetailsButton.setEnabled(true);
				progressBar.setValue(PROGRESS_CONSTANT * 4);
			}
		});


		JButton airportButton = new JButton("Get Airports");
		airportButton.setEnabled(false);

		accountDetailsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crossReferenceDatabase.getAccountNumbers();
				airportButton.setEnabled(true);
				progressBar.setValue(PROGRESS_CONSTANT * 5);
			}
		});


		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(reportButton);
		panel.add(getInterviews);
		panel.add(licenseButton);
		panel.add(transactionButton);
		panel.add(accountDetailsButton);
		panel.add(airportButton);
		
		JButton flightButton = new JButton("Get Flights");
		flightButton.setEnabled(false);
		panel.add(flightButton);

		airportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String city = (String) JOptionPane.showInputDialog("Enter the City "); //Fiftyville

				crossReferenceDatabase.getAirport(city);
				flightButton.setEnabled(true);
				progressBar.setValue(PROGRESS_CONSTANT * 6);
			}
		});


		JButton passengerButton = new JButton("Get Passengers");
		passengerButton.setEnabled(false);
		panel.add(passengerButton);

		flightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crossReferenceDatabase.getEarliestFlight();
				passengerButton.setEnabled(true);
				progressBar.setValue(PROGRESS_CONSTANT * 7);
			}
		});

		JButton callButton = new JButton("Get Calls");
		callButton.setEnabled(false);
		panel.add(callButton);

		passengerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crossReferenceDatabase.getPassportNo();
				callButton.setEnabled(true);
				progressBar.setValue(PROGRESS_CONSTANT * 8);
			}
		});





		
		JLabel lblNewLabel = new JLabel("Cross Referencing Section");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 832, 45);
		getContentPane().add(lblNewLabel);
		
		JButton culpritButton = new JButton("Find Culprit");
		culpritButton.setEnabled(false);
		culpritButton.setBounds(571, 492, 100, 68);
		getContentPane().add(culpritButton);

		callButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String duration = (String) JOptionPane.showInputDialog("Enter the Duration in format <,>,<=,>= number "); //< 60

				crossReferenceDatabase.getCallerId(duration);
				culpritButton.setEnabled(true);
				progressBar.setValue(PROGRESS_CONSTANT * 9);
			}
		});

		culpritButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crossReferenceDatabase.getCulprit();
				JOptionPane.showMessageDialog(null,"Congratulations, You have finished the case","Completion Message",JOptionPane.PLAIN_MESSAGE);
				progressBar.setValue(PROGRESS_CONSTANT * 10);
			}
		});


	}
}
