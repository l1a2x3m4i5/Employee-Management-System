import java.awt.EventQueue;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kodnest.EMS.Employee;
import com.kodnest.EMS.HibernateUtil;

public class OnboardEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_3;
	private JTextField textField_2;
	private JLabel lblNewLabel_4;
	private JTextField textField_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblStatusMessage_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnboardEmployee frame = new OnboardEmployee();
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
	public OnboardEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Onboarding");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 26));
		lblNewLabel.setBounds(209, 10, 278, 70);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 99, 88, 35);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(99, 101, 507, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Salary:");
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 153, 88, 29);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(99, 152, 507, 35);
		contentPane.add(textField_1);
		
		lblNewLabel_3 = new JLabel("Department:");
		lblNewLabel_3.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 201, 88, 29);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(99, 202, 507, 32);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Position:");
		lblNewLabel_4.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 255, 88, 29);
		contentPane.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(99, 252, 507, 32);
		contentPane.add(textField_3);
		
		btnNewButton = new JButton("Onboard");
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 16));
		btnNewButton.setBounds(226, 314, 202, 29);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBackground(Color.white);
		btnNewButton_1.setFont(new Font("Serif", Font.BOLD, 14));
		btnNewButton_1.setBounds(10, 377, 94, 35);
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Home homePage = new Home();
		        homePage.setVisible(true);
		        dispose(); // Close current OnboardEmployee window
		    }
		});

		contentPane.add(btnNewButton_1);
		
		lblStatusMessage_1 = new JLabel("");
		lblStatusMessage_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblStatusMessage_1.setBounds(186, 360, 400, 29);
		contentPane.add(lblStatusMessage_1);

		
		btnNewButton.addActionListener(new ActionListener() {
		    @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
		        String name = textField.getText().trim();
		        String salaryStr = textField_1.getText().trim();
		        String department = textField_2.getText().trim();
		        String position = textField_3.getText().trim();

		        // Validate input
		        if (name.isEmpty() || salaryStr.isEmpty() || department.isEmpty() || position.isEmpty()) {
		            lblStatusMessage_1.setForeground(Color.RED);
		            lblStatusMessage_1.setText("Please fill all fields.");
		            return;
		        }

		        try {
		            double salary = Double.parseDouble(salaryStr);

		            Session session = HibernateUtil.getSessionFactory().openSession();
		            Transaction tx = session.beginTransaction();

		            Employee emp = new Employee();
		            emp.setName(name);
		            emp.setDepartment(department);
		            emp.setSalary(salary);
		            emp.setPosition(position);

		            session.save(emp);
		            System.out.println("Onboarded Employee: " + emp);
		            tx.commit();
		            session.close();
		            
		            lblStatusMessage_1.setForeground(Color.BLACK);
		            lblStatusMessage_1.setText("Employee onboarded successfully!");

		            // Optional: clear input fields
		            textField.setText("");
		            textField_1.setText("");
		            textField_2.setText("");
		            textField_3.setText("");
                
		        } catch (NumberFormatException ex) {
		            lblStatusMessage_1.setForeground(Color.RED);
		            lblStatusMessage_1.setText("Salary must be a number.");
		        } catch (Exception ex) {
		            lblStatusMessage_1.setForeground(Color.RED);
		            lblStatusMessage_1.setText("Error: " + ex.getMessage());
		            ex.printStackTrace();
		        }
		    }
		});

	}

}
