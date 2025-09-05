import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.awt.event.ActionEvent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kodnest.EMS.Employee;
import com.kodnest.EMS.HibernateUtil;

public class DisplayAll extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayAll frame = new DisplayAll();
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
	public DisplayAll() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 750, 750);
		contentPane = new JPanel();
		contentPane.setBackground(Color.MAGENTA);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Table column headers
		String[] columns = {"ID", "Name", "Salary", "Department", "Position"};

		// Fetch data from database
		String[][] data = fetchEmployeeData();

		// fetch data from DB
		String[][] data1 = fetchEmployeeData();

		// starting Y position below the headings
		int startY = 100;

		// for each employee row, add new JLabels to the panel
		for (int i = 0; i < data1.length; i++) {
		    JLabel idLabel = new JLabel(data1[i][0]);
		    idLabel.setBounds(57, startY, 50, 25);
		    idLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		    contentPane.add(idLabel);

		    JLabel nameLabel = new JLabel(data1[i][1]);
		    nameLabel.setBounds(139, startY, 100, 25);
		    nameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		    contentPane.add(nameLabel);

		    JLabel salaryLabel = new JLabel(data1[i][2]);
		    salaryLabel.setBounds(272, startY, 100, 25);
		    salaryLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		    contentPane.add(salaryLabel);

		    JLabel deptLabel = new JLabel(data1[i][3]);
		    deptLabel.setBounds(402, startY, 120, 25);
		    deptLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		    contentPane.add(deptLabel);

		    JLabel posLabel = new JLabel(data1[i][4]);
		    posLabel.setBounds(591, startY, 100, 25);
		    posLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		    contentPane.add(posLabel);

		    startY += 30;  // move to next row
		}


		
		JLabel lblNewLabel = new JLabel("LIST OF ALL EMPLOYEES");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 26));
		lblNewLabel.setBounds(200, 10, 342, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 20));
		lblNewLabel_1.setBounds(57, 64, 36, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NAME");
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 20));
		lblNewLabel_2.setBounds(139, 62, 65, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("SALARY");
		lblNewLabel_3.setFont(new Font("Serif", Font.BOLD, 20));
		lblNewLabel_3.setBounds(272, 62, 87, 30);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("DEPARTMENT");
		lblNewLabel_4.setFont(new Font("Serif", Font.BOLD, 20));
		lblNewLabel_4.setBounds(402, 68, 148, 18);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("POSITION");
		lblNewLabel_5.setFont(new Font("Serif", Font.BOLD, 20));
		lblNewLabel_5.setBounds(591, 65, 99, 24);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 20));
		btnNewButton.setBounds(36, 614, 121, 58);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Home homePage = new Home();
		        homePage.setVisible(true);
		        dispose();  // close the current DisplayAll window
		    }
		});

		contentPane.add(btnNewButton);
	}
	private String[][] fetchEmployeeData() {
		try{Session session = HibernateUtil.getSessionFactory().openSession();
		List<Employee> list = session.createQuery("from Employee", Employee.class).list();
		session.close();

		String[][] data = new String[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
		    Employee e = list.get(i);
		    data[i][0] = String.valueOf(e.getId());
		    data[i][1] = e.getName();
		    data[i][2] = String.valueOf(e.getSalary());
		    data[i][3] = e.getDepartment();
		    data[i][4] = e.getPosition();
		}
		return data;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return new String[0][0];
	    }
	}


}
