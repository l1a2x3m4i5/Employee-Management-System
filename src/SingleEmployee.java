import java.awt.EventQueue;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kodnest.EMS.Employee;
import com.kodnest.EMS.HibernateUtil;


public class SingleEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblIdValue, lblNameValue, lblSalaryValue, lblDeptValue, lblPositionValue;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				SingleEmployee frame = new SingleEmployee();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public SingleEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 550, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("EMPLOYEE DETAILS");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 26));
		lblNewLabel.setBounds(110, 10, 292, 34);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("EMPLOYEE ID:");
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 16));
		lblNewLabel_1.setBounds(35, 76, 120, 34);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(160, 76, 165, 34);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Serif", Font.BOLD, 14));
		btnSearch.setBounds(340, 76, 100, 34);
		contentPane.add(btnSearch);

		// Labels for headers
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_2.setBounds(45, 134, 26, 17);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("NAME");
		lblNewLabel_3.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_3.setBounds(92, 136, 45, 13);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("SALARY");
		lblNewLabel_4.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_4.setBounds(193, 131, 65, 23);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("DEPARTMENT");
		lblNewLabel_5.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_5.setBounds(303, 134, 99, 17);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("POSITION");
		lblNewLabel_5_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_5_1.setBounds(427, 134, 76, 17);
		contentPane.add(lblNewLabel_5_1);

		// Labels for values (initially empty)
		lblIdValue = new JLabel("");
		lblIdValue.setBounds(45, 165, 40, 17);
		contentPane.add(lblIdValue);

		lblNameValue = new JLabel("");
		lblNameValue.setBounds(92, 165, 90, 17);
		contentPane.add(lblNameValue);

		lblSalaryValue = new JLabel("");
		lblSalaryValue.setBounds(193, 165, 80, 17);
		contentPane.add(lblSalaryValue);

		lblDeptValue = new JLabel("");
		lblDeptValue.setBounds(303, 165, 100, 17);
		contentPane.add(lblDeptValue);

		lblPositionValue = new JLabel("");
		lblPositionValue.setBounds(427, 165, 90, 17);
		contentPane.add(lblPositionValue);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 14));
		btnNewButton.setBounds(35, 407, 85, 34);
		contentPane.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home homePage = new Home();
				homePage.setVisible(true);
				dispose();
			}
		});

		// Action for Search button
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empId = textField.getText().trim();
				if (empId.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter employee ID!");
					return;
				}
				fetchEmployeeDetails(empId);
			}
		});
	}

	private void fetchEmployeeDetails(String empId) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        int id = Integer.parseInt(empId);
	        Employee emp = session.get(Employee.class, id);
	        System.out.println("Deboarded Employee: " + emp);

	        if (emp != null) {
	            lblIdValue.setText(String.valueOf(emp.getId()));
	            lblNameValue.setText(emp.getName());
	            lblSalaryValue.setText(String.valueOf(emp.getSalary()));
	            lblDeptValue.setText(emp.getDepartment());
	            lblPositionValue.setText("-"); // No position field in entity, so use placeholder
	        } else {
	            JOptionPane.showMessageDialog(null, "No employee found with ID: " + empId);
	            clearLabels();
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid ID format.");
	        clearLabels();
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error fetching data.");
	        clearLabels();
	    }
	}


	private void clearLabels() {
		lblIdValue.setText("");
		lblNameValue.setText("");
		lblSalaryValue.setText("");
		lblDeptValue.setText("");
		lblPositionValue.setText("");
	}
}
