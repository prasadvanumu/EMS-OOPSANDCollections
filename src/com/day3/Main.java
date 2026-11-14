package com.day3;

import java.util.Scanner;

import com.day3.service.EmployeeService;

public class Main {
	public static void getmenu() {
		System.out.println("---------------------------------------");
		System.out.println("---------------------------------------");
	    System.out.println("1. Add Employee");
	    System.out.println("2. View Employee");
	    System.out.println("3. View All Employees");
	    System.out.println("4. Filter By Department");
	    System.out.println("5. Filter By Salary");
	    System.out.println("6. Filter By Employee Type");
	    System.out.println("7. Sort By Salary");
	    System.out.println("8. Group By Department");
	   
	    System.out.println("9. Calculate Total Salary");
	    System.out.println("10. Calculate Average Salary");
	    System.out.println("11. Department wise insights");
	    
	    System.out.println("12. Designation wise insights");
	    System.out.println("13. Employee Type Wise insights");
	    System.out.println("14. Delete A Employee By ID");
	    System.out.println("15. Update Details of employee .");
	    System.out.println("16. Over All Insignts");
	    
	    
	    
	    System.out.println("0. Exit");
	    System.out.println("---------------------------------------");
	    System.out.println("---------------------------------------");
	}
	    public static void main(String[] args) {
	        System.out.println("---Welcome to Employee Management System--");
	        EmployeeService employeeService = new EmployeeService();

	        Scanner sc=new Scanner(System.in);
	        System.out.println("Enter your choice: ");
	        getmenu();
	        int ch=sc.nextInt();
	        while(ch>0&&ch<=16){
	            switch(ch){
	                case 1->employeeService.addEmployee();   
	                case 2->
	                    employeeService.viewEmployee();
	                    
	                case 3->
	                    employeeService.viewAllEmployees();

	                 case 4 -> employeeService.filterByDepartment();
	                    case 5 -> employeeService.filterBySalary();
	                    case 6 -> employeeService.filterByType();
	                    case 7 -> employeeService.sortBySalary();
	                    case 8 -> employeeService.groupByDepartment();
	                    case 9 -> employeeService.calculateTotalSalary();
	                    case 10 -> employeeService.calculateAverageSalary();
	                    case 11->employeeService.calculateDepartmentTotalSalary();
	                    case 12->employeeService.calculateDesignationTotalSalary();
	                    case 13->employeeService.calculateEmployeeTypeInsights();
	                    case 14->employeeService.DeleteById();
	                    case 15->employeeService.UpdateDetailsById();
	                    case 16->employeeService.getTotalInsights();
	                    
	                
	            }
	            System.out.println("Enter your choice: ");
	            getmenu();
	            ch=sc.nextInt();

	        }
	        sc.close();
	        System.out.println("Thank you for using Employee Management System");
	    }

}
