package com.kodnest.EMS;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")

public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@Column
	private String name;
	
	@Column
    private double salary;
	
	@Column
    private String department;
	
	@Column
	private String position;
    
    public Employee() {
		super();
	}
	public Employee(int id, String name, double salary, String department, String position) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.department = department;
		this.position = position;
	}
	public Employee(String name, double salary, String department, String position) {
		super();
		this.name = name;
		this.salary = salary;
		this.department = department;
		this.position = position;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", department=" + department
				+ ", position=" + position + "]";
	}
	
}
