import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 750, 750);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Management System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(168, 10, 439, 33);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("View All Employees");
		btnNewButton.setBackground(Color.MAGENTA);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(77, 158, 204, 63);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DisplayAll displayPage = new DisplayAll();  // open DisplayAll.java window
		        displayPage.setVisible(true);               // make it visible
		        dispose();                                  // close Home.java window
		    }
		});

		contentPane.add(btnNewButton);
		
		JButton btnViewEmployee = new JButton("View Employee");
		btnViewEmployee.setBackground(Color.MAGENTA);
		btnViewEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnViewEmployee.setBounds(444, 158, 213, 63);
		btnViewEmployee.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        SingleEmployee singleEmpPage = new SingleEmployee();
		        singleEmpPage.setVisible(true);
		        dispose(); // Optional: Close Home window
		    }
		});

		contentPane.add(btnViewEmployee);
		
		JButton btnDeleteEmployee = new JButton("Delete Employee");
		btnDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeleteEmployee.setBackground(Color.RED);
		btnDeleteEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDeleteEmployee.setBounds(444, 341, 213, 63);
		btnDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Previously empty, let’s fix it
				DeboardEmployee deboardPage = new DeboardEmployee();
				deboardPage.setVisible(true);
				dispose(); // Optional: close Home window
			}
		});

		contentPane.add(btnDeleteEmployee);
		
		JButton btnUpdateEmployee = new JButton("Update Employee");
		btnUpdateEmployee.setBackground(Color.GRAY);
		btnUpdateEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdateEmployee.setBounds(260, 501, 199, 63);
		btnUpdateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateEmployee updatePage = new UpdateEmployee();
				updatePage.setVisible(true);
				dispose(); // Optional
			}
		});

		contentPane.add(btnUpdateEmployee);
		
		JButton btnAddEmployees = new JButton("Add Employee");
		btnAddEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddEmployees.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddEmployees.setBackground(Color.YELLOW);
		btnAddEmployees.setBounds(77, 341, 204, 63);
		btnAddEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OnboardEmployee onboardPage = new OnboardEmployee();
				onboardPage.setVisible(true);
				dispose(); // Optional: close Home window
			}
		});


		contentPane.add(btnAddEmployees);
	}
}
