import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kodnest.EMS.Employee;
import com.kodnest.EMS.HibernateUtil;


public class UpdateEmployee extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5;
    private JLabel lblStatusMessage_3;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateEmployee frame = new UpdateEmployee();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UpdateEmployee() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 650, 600);
        contentPane = new JPanel();
        contentPane.setBackground(Color.MAGENTA);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("UPDATE EMPLOYEE DETAILS");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 26));
        lblTitle.setBounds(114, 10, 397, 33);
        contentPane.add(lblTitle);

        JLabel lblEmpIdSearch = new JLabel("Employee Id:");
        lblEmpIdSearch.setFont(new Font("Serif", Font.BOLD, 14));
        lblEmpIdSearch.setBounds(32, 104, 98, 27);
        contentPane.add(lblEmpIdSearch);

        textField = new JTextField();
        textField.setFont(new Font("Serif", Font.BOLD, 14));
        textField.setBounds(140, 104, 146, 27);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Serif", Font.BOLD, 16));
        btnSubmit.setBackground(Color.BLUE);
        btnSubmit.setBounds(346, 97, 120, 45);
        contentPane.add(btnSubmit);

        // Labels and Input Fields
        addLabel("ID:", 178);
        textField_1 = addTextField(180);

        addLabel("Name:", 223);
        textField_2 = addTextField(225);

        addLabel("Salary:", 268);
        textField_3 = addTextField(270);

        addLabel("Department:", 316);
        textField_4 = addTextField(315);

        addLabel("Position:", 360);
        textField_5 = addTextField(360);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Serif", Font.BOLD, 16));
        btnUpdate.setBackground(Color.BLUE);
        btnUpdate.setBounds(262, 417, 120, 45);
        contentPane.add(btnUpdate);

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(Color.PINK);
        btnBack.setFont(new Font("Serif", Font.BOLD, 14));
        btnBack.setBounds(23, 429, 73, 33);
        contentPane.add(btnBack);

        lblStatusMessage_3 = new JLabel("");
        lblStatusMessage_3.setFont(new Font("Serif", Font.BOLD, 14));
        lblStatusMessage_3.setBounds(134, 498, 417, 27);
        contentPane.add(lblStatusMessage_3);

        // 🧠 ACTIONS

        btnSubmit.addActionListener(e -> {
            String empId = textField.getText().trim();
            if (empId.isEmpty()) {
                showStatus("Please enter Employee ID.", Color.RED);
                return;
            }

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                int id = Integer.parseInt(empId);
                Employee emp = session.get(Employee.class, id);
                System.out.println("Deboarded Employee: " + emp);

                if (emp != null) {
                    textField_1.setText(String.valueOf(emp.getId()));
                    textField_2.setText(emp.getName());
                    textField_3.setText(String.valueOf(emp.getSalary()));
                    textField_4.setText(emp.getDepartment());
                    textField_5.setText(emp.getPosition());

                    showStatus("Employee data loaded successfully.", Color.GREEN);
                } else {
                    showStatus("Employee not found.", Color.BLUE);
                }
            } catch (Exception ex) {
                showStatus("Hibernate Error: " + ex.getMessage(), Color.RED);
                ex.printStackTrace();
            }
        });

        btnUpdate.addActionListener(e -> {
            String empId = textField_1.getText().trim();
            String name = textField_2.getText().trim();
            String salaryStr = textField_3.getText().trim();
            String dept = textField_4.getText().trim();
            String pos = textField_5.getText().trim();

            if (empId.isEmpty() || name.isEmpty() || salaryStr.isEmpty() || dept.isEmpty() || pos.isEmpty()) {
                showStatus("Please fill all fields.", Color.RED);
                return;
            }

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                int id = Integer.parseInt(empId);
                double salary = Double.parseDouble(salaryStr);

                Transaction tx = session.beginTransaction();
                Employee emp = session.get(Employee.class, id);

                if (emp != null) {
                    emp.setName(name);
                    emp.setSalary(salary);
                    emp.setDepartment(dept);
                    emp.setPosition(pos);

                    session.persist(emp);
                    tx.commit();
                    showStatus("Employee updated successfully.", Color.GREEN);
                } else {
                    showStatus("Employee not found for update.", Color.RED);
                }
            } catch (Exception ex) {
                showStatus("Update error: " + ex.getMessage(), Color.RED);
                ex.printStackTrace();
            }
        });

        btnBack.addActionListener(e -> {
            new Home().setVisible(true);
            dispose();
        });
    }

    private void addLabel(String text, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Serif", Font.BOLD, 14));
        lbl.setBounds(10, y, 88, 29);
        contentPane.add(lbl);
    }

    private JTextField addTextField(int y) {
        JTextField tf = new JTextField();
        tf.setBounds(92, y, 507, 35);
        contentPane.add(tf);
        return tf;
    }

    private void showStatus(String message, Color color) {
        lblStatusMessage_3.setForeground(color);
        lblStatusMessage_3.setText(message);
    }
}
