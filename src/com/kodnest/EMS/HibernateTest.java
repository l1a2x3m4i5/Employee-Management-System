package com.kodnest.EMS;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateTest {
    public static void main(String[] args) {
        // Open Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Create a dummy Employee to test
            Employee emp = new Employee();
            emp.setId(1);  // make sure it's unique if you're testing again
            emp.setName("Test User");
            emp.setSalary(50000);

            // Save employee
            session.save(emp);

            transaction.commit();
            System.out.println("✅ Hibernate setup is working! Employee inserted successfully.");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
