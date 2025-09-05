import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class DeboardEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblStatusMessage_2;
	private JLabel lblEmpIdValue, lblEmpNameValue, lblEmpSalaryValue, lblEmpDeptValue, lblEmpPositionValue;
	private JLabel textField_1;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeboardEmployee frame = new DeboardEmployee();
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
	public DeboardEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 650, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE EXIT");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 26));
		lblNewLabel.setBounds(183, 27, 249, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Id:");
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(34, 117, 98, 27);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Serif", Font.BOLD, 14));
		textField.setBounds(142, 117, 146, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 16));
		btnNewButton.setBounds(386, 108, 120, 45);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(e -> {
		    String empId = textField.getText().trim();

		    if (empId.isEmpty()) {
		        showStatus("Please enter Employee ID.", Color.RED);
		        return;
		    }

		    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		        int id = Integer.parseInt(empId);
		        Employee emp = session.get(Employee.class, id);

		        if (emp != null) {
		            // ✅ Populate UI labels
		            lblEmpIdValue.setText(String.valueOf(emp.getId()));
		            lblEmpNameValue.setText(emp.getName());
		            lblEmpSalaryValue.setText(String.valueOf(emp.getSalary()));
		            lblEmpDeptValue.setText(emp.getDepartment());
		            lblEmpPositionValue.setText(emp.getPosition());

		            showStatus("Employee data loaded. Click 'Confirm Deboard' to delete.", Color.BLUE);

		        } else {
		            showStatus("Employee not found.", Color.RED);
		        }
		    } catch (Exception ex) {
		        showStatus("Error: " + ex.getMessage(), Color.RED);
		        ex.printStackTrace();
		    }
		});




		
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_2.setBounds(51, 183, 26, 23);
		contentPane.add(lblNewLabel_2);
		
		// Value label for ID
		lblEmpIdValue = new JLabel("");
		lblEmpIdValue.setFont(new Font("Serif", Font.PLAIN, 14));
		lblEmpIdValue.setBounds(51, 210, 60, 25);
		contentPane.add(lblEmpIdValue);

		// Value label for NAME
		lblEmpNameValue = new JLabel("");
		lblEmpNameValue.setFont(new Font("Serif", Font.PLAIN, 14));
		lblEmpNameValue.setBounds(134, 210, 100, 25);
		contentPane.add(lblEmpNameValue);

		// Value label for SALARY
		lblEmpSalaryValue = new JLabel("");
		lblEmpSalaryValue.setFont(new Font("Serif", Font.PLAIN, 14));
		lblEmpSalaryValue.setBounds(243, 210, 100, 25);
		contentPane.add(lblEmpSalaryValue);

		// Value label for DEPARTMENT
		lblEmpDeptValue = new JLabel("");
		lblEmpDeptValue.setFont(new Font("Serif", Font.PLAIN, 14));
		lblEmpDeptValue.setBounds(360, 210, 120, 25);
		contentPane.add(lblEmpDeptValue);

		// Value label for POSITION
		lblEmpPositionValue = new JLabel("");
		lblEmpPositionValue.setFont(new Font("Serif", Font.PLAIN, 14));
		lblEmpPositionValue.setBounds(508, 210, 100, 25);
		contentPane.add(lblEmpPositionValue);

		
		JLabel lblNewLabel_3 = new JLabel("NAME");
		lblNewLabel_3.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_3.setBounds(134, 185, 45, 19);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("SALARY");
		lblNewLabel_4.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_4.setBounds(243, 183, 65, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("DEPARTMENT");
		lblNewLabel_5.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_5.setBounds(360, 183, 99, 23);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("POSITION");
		lblNewLabel_5_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_5_1.setBounds(508, 186, 76, 17);
		contentPane.add(lblNewLabel_5_1);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setFont(new Font("Serif", Font.BOLD, 14));
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setBounds(32, 444, 85, 34);
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Home homePage = new Home();
		        homePage.setVisible(true);
		        dispose(); // Close current frame
		    }
		});

		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CONFIRM DEBOARD");
		btnNewButton_2.setFont(new Font("Serif", Font.BOLD, 14));
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.setBounds(384, 445, 200, 33);
		btnNewButton_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String empIdText = lblEmpIdValue.getText().trim();
 // ✅ Use correct input field

		        if (empIdText.isEmpty()) {
		            textField_1.setText("Please enter Employee ID.");
		            return;
		        }

		        int empId = Integer.parseInt(empIdText);

		        Configuration cfg = new Configuration();
		        cfg.configure("hibernate.cfg.xml");
		        SessionFactory factory = cfg.buildSessionFactory();
		        Session session = factory.openSession();

		        Transaction t = session.beginTransaction();
		        Employee e1 = session.get(Employee.class, empId);

		        if (e1 != null) {
		            session.remove(e1);
		            t.commit();
		            System.out.println("Deboarded Employee: " + e1);
		            showStatus("Employee deleted successfully.", Color.BLUE);
		            clearEmployeeDetails();

		        } else {
		            textField_1.setText("Employee Not Found.");
		        }

		        session.close();
		        factory.close();
		    }
		});


		contentPane.add(btnNewButton_2);
		
		lblStatusMessage_2 = new JLabel("");
		lblStatusMessage_2.setBackground(Color.BLACK);
		lblStatusMessage_2.setFont(new Font("Serif", Font.BOLD, 14));
		lblStatusMessage_2.setBounds(126, 508, 458, 27);
		contentPane.add(lblStatusMessage_2);
	}

	private void showStatus(String message, Color color) {
	    lblStatusMessage_2.setText(message);
	    lblStatusMessage_2.setForeground(color);
	}

	private void clearEmployeeDetails() {
	    lblEmpIdValue.setText("");
	    lblEmpNameValue.setText("");
	    lblEmpSalaryValue.setText("");
	    lblEmpDeptValue.setText("");
	    lblEmpPositionValue.setText("");
	    textField.setText("");
	}


}
