import java.awt.Container;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * This class creates a JFrame GUI for the application
 * */
public class GUI extends JFrame implements ActionListener{

	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_X_ORIGIN = 500;
	private static final int FRAME_Y_ORIGIN = 500;
	
	private static Font f = new Font("Verdana",Font.BOLD,30);				//font size used by title
	
	private static JPanel titlePanel,optionsPanel;
	private static JTextField titleBox;
	private static JButton addEmployee, backButton,add;
	private static GUI mainFrame;
	
	private static JFrame addEmployeeFrame;

	/*
	 * 	Main method to launch application
	 * */
	public static void main(String[] args) throws SQLException {
		DB.connect();								//connects to database class
		//DB.addEmployee("1","Bill Noris","32","302 West Main Street","32000","dfh","8");							//calls the current test method
		mainFrame = new GUI();
		mainFrame.setVisible(true);
		
	}

	/*
	 * Constructor
	 */
	public GUI() {
		Container contentPane = getContentPane();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
		contentPane.setLayout(new BorderLayout());
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initializing panels
		optionsPanel = new JPanel();
		titlePanel = new JPanel();
		optionsPanel.setLayout(null);
		titlePanel.setLayout(new BorderLayout());
		optionsPanel.setBackground(Color.lightGray);
		titlePanel.setBackground(Color.lightGray);
		contentPane.add(optionsPanel,BorderLayout.CENTER);
		contentPane.add(titlePanel,BorderLayout.NORTH);
		
		//adding titleBox to title panel
		titleBox = new JTextField("Store Database");
		titleBox.setEditable(false);
		titleBox.setFont(f);
		titleBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		titleBox.setHorizontalAlignment(JButton.CENTER);
		titleBox.setBackground(Color.lightGray);
		titlePanel.add(titleBox,BorderLayout.CENTER);
		
		//adding options panel attributes to contentPane
		addEmployee = new JButton("Add Employee");
		addEmployee.setSize(200,200);
		addEmployee.setLocation(75,75);
		optionsPanel.add(addEmployee);
		
		//setting onClickListener for addEmployee
		addEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//adding properties of addEmployeePanel
				addEmployeeFrame = new JFrame();
				addEmployeeFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
				addEmployeeFrame.setLocation(getLocation());
				addEmployeeFrame.setLayout(null);
				addEmployeeFrame.setBackground(Color.lightGray);
				
				//initializing JTextFields
				JTextField id = new JTextField();
				JTextField first_name = new JTextField();
				JTextField last_name = new JTextField();
				JTextField age = new JTextField();
				JTextField address = new JTextField();
				JTextField state = new JTextField();
				JTextField salary = new JTextField();
				
				//initializing JLabels
				JLabel idLabel = new JLabel("Enter ID");
				JLabel firstNameLabel = new JLabel("Enter First Name");
				JLabel lastNameLabel = new JLabel("Enter Last Name");
				JLabel ageLabel = new JLabel("Enter Age");
				JLabel addressLabel = new JLabel("Enter Street Address");
				JLabel stateLabel = new JLabel("Enter State");
				JLabel salaryLabel = new JLabel("Enter Salary");
				
				//setting location and size of JLabels
				idLabel.setSize(100,40);
				firstNameLabel.setSize(150,40);
				lastNameLabel.setSize(150,40);
				ageLabel.setSize(150,40);
				addressLabel.setSize(150,40);
				stateLabel.setSize(150,40);
				salaryLabel.setSize(150,40);
				
				idLabel.setLocation(100,90);
				firstNameLabel.setLocation(100,150);
				lastNameLabel.setLocation(100,210);
				ageLabel.setLocation(100,270);
				addressLabel.setLocation(100,330);
				stateLabel.setLocation(100,390);
				salaryLabel.setLocation(100,450);
				
				//setting location and size of JTextFields
				id.setSize(100,40);
				first_name.setSize(100,40);
				last_name.setSize(100,40);
				age.setSize(100,40);
				address.setSize(100,40);
				state.setSize(100,40);
				salary.setSize(100,40);
				
				id.setLocation(270,90);
				first_name.setLocation(270,150);
				last_name.setLocation(270,210);
				age.setLocation(270,270);
				address.setLocation(270,330);
				state.setLocation(270,390);
				salary.setLocation(270,450);
				
				//adding them to the frame
				addEmployeeFrame.add(idLabel);
				addEmployeeFrame.add(firstNameLabel);
				addEmployeeFrame.add(lastNameLabel);
				addEmployeeFrame.add(ageLabel);
				addEmployeeFrame.add(addressLabel);
				addEmployeeFrame.add(stateLabel);
				addEmployeeFrame.add(salaryLabel);
				
				addEmployeeFrame.add(id);
				addEmployeeFrame.add(first_name);
				addEmployeeFrame.add(last_name);
				addEmployeeFrame.add(age);
				addEmployeeFrame.add(address);
				addEmployeeFrame.add(state);
				addEmployeeFrame.add(salary);
				
				backButton = new JButton("<--Back");
				add = new JButton("Add Employee");
				
				
				//adding properties of buttons
				add.setSize(100,40);
				add.setLocation(270,500);
				backButton.setSize(70,40);
				backButton.setLocation(20,20);
				addEmployeeFrame.add(add);
				addEmployeeFrame.add(backButton);
				
				mainFrame.setVisible(false);
				addEmployeeFrame.setVisible(true);
				addEmployeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//actionListener for back button
				backButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addEmployeeFrame.setVisible(false);
						mainFrame.setLocation(addEmployeeFrame.getLocation());
						mainFrame.setVisible(true);
						
					}
					
				});
				
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Boolean empty = false;
						if(id.getText().isEmpty() || first_name.getText().isEmpty() || last_name.getText().isEmpty() 
								|| age.getText().isEmpty() || address.getText().isEmpty() || state.getText().isEmpty()
								|| salary.getText().isEmpty())
							empty = true;
						if(!empty) {
							try {
								DB.addEmployee(id.getText(),first_name.getText(),last_name.getText(),age.getText(),address.getText(),state.getText(),salary.getText());
								id.setText("");
								first_name.setText("");
								last_name.setText("");
								age.setText("");
								address.setText("");
								state.setText("");
								salary.setText("");
							} catch (SQLException e1) {
								System.out.println("Error with updating the database -- check add.ActionListener method");
							}
						}
					}
					
				});
			}
		});
		

	}
}
