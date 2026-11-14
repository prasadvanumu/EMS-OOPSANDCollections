package com.day3.model;

public class Employee {
    private int employeeId;
    private String employeeName;
    private int salary;
    private Designation designation;
    private Department department;
    private EmpType empType;
    


    public Employee(int employeeId, String employeeName, int salary, Designation designation, Department department, EmpType empType) {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("Employee ID must be greater than 0");
        }
        if (employeeName == null || employeeName.isBlank()) {
            throw new IllegalArgumentException("Employee name cannot be null or blank");
        }
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary must be a positive value");
        }
        if (designation == null) {
            throw new IllegalArgumentException("Designation cannot be null");
        }
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }
        if (empType == null) {
            throw new IllegalArgumentException("Employee type cannot be null");
        }

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
        this.department = department;
        this.empType = empType;
        this.salary=salary;
    }

    
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Designation getDesignation() {
        return designation;
    }

    public Department getDepartment() {
        return department;
    }

    public EmpType getEmpType() {
        return empType;
    }

    public int getSalary() {
        return salary;

    }
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", designation=" + designation.toString() +
                ", department=" + department.toString() +
                ", empType=" + empType.toString() +
                '}';
    }


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public void setDesignation(Designation designation) {
		this.designation = designation;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public void setEmpType(EmpType empType) {
		this.empType = empType;
	}

}
