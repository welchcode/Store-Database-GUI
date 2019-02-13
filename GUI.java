import java.awt.Container;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * This class creates a JFrame GUI for the application
 * The main purpose of this class is to display a graphical user interface for a user to 
 * interact with a "store" database
 * 
 * */
public class GUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	/*
	 * Static final variables
	 * */
	private static final int FRAME_WIDTH = 600;							//default frame width
	private static final int FRAME_HEIGHT = 600;							//default frame height
	private static final int FRAME_X_ORIGIN = 500;						//default frame origin
	private static final int FRAME_Y_ORIGIN = 500;
	private static Font f = new Font("Verdana",Font.BOLD,30);				//font size used by title
	private static JPanel titlePanel,optionsPanel;						//2 panels used in the borderLayout of the main contentPane
	private static JTextField titleBox;									
	private static JButton addEmployee, backButton,add,addItem, checkoutItem;
	private static GUI mainFrame;										
	private static JFrame addEmployeeFrame, addItemFrame, checkoutFrame;	//frames used throughout the program (so far)

	/*
	 * 	Main method to launch application
	 * */
	public static void main(String[] args) throws SQLException {
		DB.connect();								
		mainFrame = new GUI();
		mainFrame.setVisible(true);
		
	}

	/*
	 * Constructor method that builds GUI
	 */
	public GUI() {
		
		//properties of the frame
		Container contentPane = getContentPane();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
		contentPane.setLayout(new BorderLayout());
		
		//default close operation
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
		
		//adding addEmployee to centerPane
		addEmployee = new JButton("Add Employee");
		addEmployee.setSize(150,150);
		addEmployee.setLocation(100,75);
		optionsPanel.add(addEmployee);
		
		//adding addItem button to optionsPanel
		addItem = new JButton("Add Item");
		addItem.setSize(150,150);
		addItem.setLocation(350,75);
		optionsPanel.add(addItem);
		
		//adding checkOutButton to optionsPanel
		checkoutItem = new JButton("Checkout");
		checkoutItem.setSize(150,150);
		checkoutItem.setLocation(100,250);
		optionsPanel.add(checkoutItem);
		
		//setting onClickListener for checkoutItem
		checkoutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//properties of the frame
				checkoutFrame = new JFrame();
				checkoutFrame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
				checkoutFrame.setLocation(getLocation());
				checkoutFrame.getContentPane().setLayout(null);
				checkoutFrame.setBackground(Color.LIGHT_GRAY);
				checkoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//init JLabels and JTextfields
				JLabel idLabel = new JLabel("Enter ID or Scan Barcode");
				JTextField id = new JTextField();
				
				//setting size of JLabels
				idLabel.setSize(200,40);
				
				//setting location of JLabels
				idLabel.setLocation(100,90);
				
				//setting size of JTextFields
				id.setSize(200,40);
				
				//setting location of JTextFields
				id.setLocation(270,90);
				
				//adding JTextFields and JLabels to Frame
				checkoutFrame.add(idLabel);
				checkoutFrame.add(id);
				
				//init of back button and checkoutbutton
				backButton = new JButton("<-- Back");
				add = new JButton("Checkout Item");
				
				//adding back and add button to frame
				backButton.setSize(70,40);
				backButton.setLocation(20,20);
				checkoutFrame.add(backButton);
				add.setSize(150,40);
				add.setLocation(230,500);
				checkoutFrame.add(add);
				
				/*
				 * BackButton ActionListener that sets checkoutFrame to visible=false
				 * and sets the main frame to visible=true
				 * */
				backButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						checkoutFrame.setVisible(false);						
						mainFrame.setLocation(checkoutFrame.getLocation());	
						mainFrame.setVisible(true);
						
					}
					
				});
				
				/*
				 * Calls the DB.checkOut method if the text box is not blank
				 * */
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!id.getText().isEmpty()) {				//if textfield is not blank
							try {
								DB.checkOut(id.getText());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				
				mainFrame.setVisible(false);
				checkoutFrame.setVisible(true);
				
			}
		});
		
		//setting onClickListener for addItem
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItemFrame = new JFrame();
				addItemFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
				addItemFrame.setLocation(getLocation());
				addItemFrame.getContentPane().setLayout(null);
				addItemFrame.setBackground(Color.lightGray);
				
				//init JLabels and JTextfields
				JLabel idLabel = new JLabel("Enter ID or Scan Barcode");
				JLabel nameLabel = new JLabel("Enter the name");
				JLabel priceLabel = new JLabel("Enter the price");
				JTextField id = new JTextField();
				JTextField name = new JTextField();
				JTextField price = new JTextField();
				
				//setting size of JLabels
				idLabel.setSize(200,40);
				nameLabel.setSize(150,40);
				priceLabel.setSize(150,40);
				
				//setting location of JLabels
				idLabel.setLocation(100,90);
				nameLabel.setLocation(100,150);
				priceLabel.setLocation(100,210);
				
				//setting size of TextFields
				id.setSize(100,40);
				name.setSize(100,40);
				price.setSize(100,40);
				
				//setting location of TextFields
				id.setLocation(270,90);
				name.setLocation(270,150);
				price.setLocation(270,210);
				
				//adding TextFields and JLabels to JFrame
				addItemFrame.add(idLabel);
				addItemFrame.add(nameLabel);
				addItemFrame.add(priceLabel);
				addItemFrame.add(id);
				addItemFrame.add(name);
				addItemFrame.add(price);
				
				backButton = new JButton("<-- Back");
				add = new JButton("Add Item");
				
				
				//setting properties of add and back buttons
				add.setSize(100,40);
				add.setLocation(270,500);
				backButton.setSize(70,40);
				backButton.setLocation(20,20);
				addItemFrame.getContentPane().add(backButton);
				addItemFrame.getContentPane().add(add);
				
				mainFrame.setVisible(false);
				addItemFrame.setVisible(true);
				
				/*
				 * Adding actionListener for add button
				 * */
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Boolean empty = false;
						if(id.getText().isEmpty() || name.getText().isEmpty() || price.getText().isEmpty())		//if isEmpty() then do not call the DB.addItem method
							empty = true;
						if(!empty) {																				//method is only called if the fields are all full
							try {
								DB.addItem(id.getText(),name.getText(),price.getText());
								id.setText("");
								name.setText("");
								price.setText("");
							} catch (SQLException e1) {
								System.out.println("Error with updating the database -- check add.ActionListener method");
							}
						}
					}
					
				});
				
				/*
				 * Action Listener for backButton
				 * -sets the mainFrame visible=true and the current frame to visible=false
				 * */
				backButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addItemFrame.setVisible(false);
						mainFrame.setLocation(addItemFrame.getLocation());
						mainFrame.setVisible(true);
						
					}
					
				});
			}
		});
		
		//setting onClickListener for addEmployee
		addEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//adding properties of addEmployeePanel
				addEmployeeFrame = new JFrame();
				addEmployeeFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
				addEmployeeFrame.setLocation(getLocation());
				addEmployeeFrame.getContentPane().setLayout(null);
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
				
				//success label init and add to screen
				JLabel success = new JLabel("Update Successful");
				success.setSize(150,100);
				success.setLocation(255,5);
				addEmployeeFrame.getContentPane().add(success);
				
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
				addEmployeeFrame.getContentPane().add(idLabel);
				addEmployeeFrame.getContentPane().add(firstNameLabel);
				addEmployeeFrame.getContentPane().add(lastNameLabel);
				addEmployeeFrame.getContentPane().add(ageLabel);
				addEmployeeFrame.getContentPane().add(addressLabel);
				addEmployeeFrame.getContentPane().add(stateLabel);
				addEmployeeFrame.getContentPane().add(salaryLabel);
				
				addEmployeeFrame.getContentPane().add(id);
				addEmployeeFrame.getContentPane().add(first_name);
				addEmployeeFrame.getContentPane().add(last_name);
				addEmployeeFrame.getContentPane().add(age);
				addEmployeeFrame.getContentPane().add(address);
				addEmployeeFrame.getContentPane().add(state);
				addEmployeeFrame.getContentPane().add(salary);
				
				//init back and add buttons
				backButton = new JButton("<--Back");
				add = new JButton("Add Employee");
				
				
				//adding properties of buttons
				add.setSize(100,40);
				add.setLocation(270,500);
				backButton.setSize(70,40);
				backButton.setLocation(20,20);
				addEmployeeFrame.getContentPane().add(add);
				addEmployeeFrame.getContentPane().add(backButton);
				
				//once this button is clicked, the mainFrame visible is set to false
				//the addEmployeeFrame visible is set to true
				mainFrame.setVisible(false);										//mainFrame visible == false
				addEmployeeFrame.setVisible(true);								//currentFrame visible == true
				addEmployeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//actionListener for back button
				backButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addEmployeeFrame.setVisible(false);
						mainFrame.setLocation(addEmployeeFrame.getLocation());
						mainFrame.setVisible(true);
						
					}
					
				});
				
				//add button actionListener
				/*
				 * 	DB.addEmployee is only called if all text areas are full 
				 * */
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Boolean empty = false;
						if(id.getText().isEmpty() || first_name.getText().isEmpty() || last_name.getText().isEmpty() 
								|| age.getText().isEmpty() || address.getText().isEmpty() || state.getText().isEmpty()
								|| salary.getText().isEmpty())
							empty = true;
						if(!empty) {																									//checks if text areas are full
							try {
								/*
								 * addEmployee method called
								 * */
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

	public void actionPerformed(ActionEvent e) {
		// actionListeners are being added to buttons themselves
	}
}
