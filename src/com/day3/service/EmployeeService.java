package com.day3.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.day3.model.Department;
import com.day3.model.Designation;
import com.day3.model.EmpType;
import com.day3.model.Employee;
import com.day3.util.GenericFilter;

public class EmployeeService {

	private static List<Employee> employees = new ArrayList<>(Arrays.asList(
			new Employee(1, "ram", 20000, new Designation(1, "junior"), new Department(1, "backend"), EmpType.FULLTIME),
			new Employee(2, "rm", 30000, new Designation(2, "mid"), new Department(1, "backend"), EmpType.FULLTIME),
			new Employee(3, "am", 40000, new Designation(3, "senior"), new Department(1, "backend"), EmpType.FULLTIME),
			new Employee(4, "ram p", 20000, new Designation(1, "junior"), new Department(2, "Ai"), EmpType.FULLTIME),
			new Employee(5, "ram prasad", 20000, new Designation(3, "mid"), new Department(2, "AI"),
					EmpType.FULLTIME)));

	private GenericFilter<Employee> filter = new GenericFilter<>();
	public static Scanner sc = new Scanner(System.in);
	public static Logger log = Logger.getLogger(EmployeeService.class.getName());

	public EmployeeService() {
//		this.employees = new ArrayList<>();

	}

	public void addEmployee() {
		System.out.println("Enter Employee ID: ");
		int employeeId = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Employee Name: ");
		String employeeName = sc.nextLine();
		System.out.println("Enter Employee Salary: ");
		int employeeSalary = sc.nextInt();
		// displays all existing departments for user conventions

		getAlldepartments(employees);
		System.out.println("Enter Employee Department ID: ");
		int departmentId = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Employee Department Name: ");
		String departmentName = sc.nextLine();
		getAllDesignations(employees);
		System.out.println("Enter Employee Designation ID: ");
		int designationId = sc.nextInt();
		sc.nextLine(); // Consume the newline character
		System.out.println("Enter Employee Designation Name: ");
		String designationName = sc.nextLine();
		System.out.println("Enter Employee Type (FULLTIME, PARTTIME, INTERN): ");
		String empTypeInput = sc.nextLine().toUpperCase();

		try {
			boolean exist = employees.stream().anyMatch(e -> e.getEmployeeId() == employeeId);
			if (exist) {
				throw new IllegalArgumentException("Employee with ID " + employeeId + " already exists.");

			}
			Employee employee = new Employee(employeeId, employeeName, employeeSalary,
					new Designation(designationId, designationName), new Department(departmentId, departmentName),
					EmpType.valueOf(empTypeInput));
			employees.add(employee);
			System.out.println("Employee added successfully!");
		} catch (IllegalArgumentException e) {
			System.out.println("Error adding employee: " + e.getMessage());
		}

	}

	private void getAllDesignations(List<Employee> employees2) {
		// TODO Auto-generated method stub
		if (employees2.isEmpty()) {
			System.out.println("No Designation  is Present ---------------");
			return;
		}
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------");
		System.out.println("---------------------------------------");
		System.out.println("All Designation which are present ");
		List<Designation> ls = employees.stream().map(Employee::getDesignation).distinct().collect(Collectors.toList());
		for (Designation d : ls) {
			System.out.println(d);
		}

		System.out.println("---------------------------------------");
		System.out.println("---------------------------------------");
	}

	private void getAlldepartments(List<Employee> employees2) {

		if (employees2.isEmpty()) {
			System.out.println("No Department is Present ---------------");
			return;
		}
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------");
		System.out.println("---------------------------------------");
		System.out.println("All departments which are present ");
		List<Department> ls = employees.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
		for (Department d : ls) {
			System.out.println(d);
		}

		System.out.println("---------------------------------------");
		System.out.println("---------------------------------------");
	}

	public void viewEmployee() {
		System.out.println("Enter Employee ID to view: ");
		int employeeId = sc.nextInt();
		for (Employee employee : employees) {
			if (employee.getEmployeeId() == employeeId) {
				System.out.println("Employee ID: " + employee.getEmployeeId());
				System.out.println("Employee Name: " + employee.getEmployeeName());
				System.out.println("Employee Salary: " + employee.getSalary());
				System.out.println("Employee Department: " + employee.getDepartment().departmentName());
				System.out.println("Employee Designation: " + employee.getDesignation().designationName());
				System.out.println("Employee Type: " + employee.getEmpType());
				return;
			}
		}
		System.out.println("Employee with ID " + employeeId + " not found.");
	}

	public void viewAllEmployees() {
		if (employees.isEmpty()) {
			System.out.println("No employees found.");
			return;
		}
		for (Employee employee : employees) {
			System.out.println("Employee ID: " + employee.getEmployeeId());
			System.out.println("Employee Name: " + employee.getEmployeeName());
			System.out.println("Employee Salary: " + employee.getSalary());
			System.out.println("Employee Department: " + employee.getDepartment().departmentName());
			System.out.println("Employee Designation: " + employee.getDesignation().designationName());
			System.out.println("Employee Type: " + employee.getEmpType());
			System.out.println("---------------------------");
		}
	}

	public void filterByDepartment() {

		getAlldepartments(employees);

		System.out.println("Enter Department Name:");
		String departmentName = sc.nextLine();

		List<Employee> result = filter.filter(employees,
				e -> e.getDepartment().departmentName().equalsIgnoreCase(departmentName));

		result.forEach(System.out::println);
	}

	public void filterBySalary() {

		System.out.println("Enter minimum salary:");
		int salary = sc.nextInt();

		List<Employee> result = filter.filter(employees, e -> e.getSalary() >= salary);

		result.forEach(System.out::println);
	}

	public void filterByType() {

		sc.nextLine();

		System.out.println("Enter Employee Type:");
		EmpType type = EmpType.valueOf(sc.nextLine().toUpperCase());

		List<Employee> result = filter.filter(employees, e -> e.getEmpType() == type);

		result.forEach(System.out::println);
	}

	public void sortBySalary() {

		List<Employee> result = filter.sort(employees, Comparator.comparingInt(Employee::getSalary));

		result.forEach(System.out::println);
	}

	public void groupByDepartment() {

		Map<String, List<Employee>> result = filter.groupBy(employees, e -> e.getDepartment().departmentName());

		result.forEach((department, employeeList) -> {

			System.out.println("\nDepartment: " + department);

			employeeList.forEach(System.out::println);
		});
	}

	public void calculateTotalSalary() {

		long total = filter.sum(employees, Employee::getSalary);

		System.out.println("Total Salary: " + total);
	}

	public void calculateAverageSalary() {

		double average = filter.average(employees, Employee::getSalary);

		System.out.println("Average Salary: " + average);
	}

	public void calculateDepartmentTotalSalary() {

		log.info("Depart wise total salaries :");
		Map<Department, Long> mp = filter.getTotalSalary(employees, Employee::getDepartment, Employee::getSalary);

		for (Department d : mp.keySet()) {
			System.out.println(d.departmentName() + "   " + mp.get(d));
		}

		log.info("Department wise Average salaries");
		Map<Department, Double> mp2 = filter.getAverageSalary(employees, Employee::getDepartment, Employee::getSalary);

		for (Department d : mp.keySet()) {
			System.out.println(d.departmentName() + "   " + mp2.get(d));
		}

		log.info("Department wise Max salaries");
		Map<Department, Optional<Employee>> mp3 = filter.getMaxSalary(employees, Employee::getDepartment,
				Comparator.comparingInt(Employee::getSalary));

		for (Department d : mp3.keySet()) {
			System.out.println(d.departmentName() + "   " + mp3.get(d).get());
		}

		log.info("Department wise Min salaries");
		Map<Department, Optional<Employee>> mp4 = filter.getMinSalary(employees, Employee::getDepartment,
				Comparator.comparingInt(Employee::getSalary));

		for (Department d : mp4.keySet()) {
			System.out.println(d.departmentName() + "   " + mp4.get(d).get());
		}

	}

	public void calculateDesignationTotalSalary() {
		// TODO Auto-generated method stub
		log.info("Designation wise Total salaries");
		Map<Designation, Long> mp = filter.getTotalSalary(employees, Employee::getDesignation, Employee::getSalary);

		for (Designation d : mp.keySet()) {
			System.out.println(d.designationName() + " " + mp.get(d));
		}
		log.info("Designation wise Average salaries");
		Map<Designation, Double> mp2 = filter.getAverageSalary(employees, Employee::getDesignation,
				Employee::getSalary);

		for (Designation d : mp.keySet()) {
			System.out.println(d.designationName() + " " + mp2.get(d));
		}

		log.info("Designation wise Max salaries");
		Map<Designation, Optional<Employee>> mp3 = filter.getMaxSalary(employees, Employee::getDesignation,
				Comparator.comparingInt(Employee::getSalary));

		for (Designation d : mp3.keySet()) {
			System.out.println(d.designationName() + "   " + mp3.get(d).get());
		}

		log.info("Designation wise Min salaries");
		Map<Designation, Optional<Employee>> mp4 = filter.getMinSalary(employees, Employee::getDesignation,
				Comparator.comparingInt(Employee::getSalary));

		for (Designation d : mp4.keySet()) {
			System.out.println(d.designationName() + "   " + mp4.get(d).get());
		}

	}

	public void DeleteById() {
		// TODO Auto-generated method stub
		viewAllEmployees();

		System.out.println("Enter ID of Employee to Delete :");
		int id = sc.nextInt();
		if (id <= 0) {
			throw new IllegalArgumentException("id must be positive and greater than Zero");
		}
		Optional<Employee> e = employees.stream().filter(em -> em.getEmployeeId() == id).findFirst();

		if (e.isEmpty()) {
			System.out.println("you entered a wrong id . no employee record is found ");
		} else {
			employees.remove(e.get());
			System.out.println("Deleted Employee record SuccessFully");

		}

	}

	public void UpdateDetailsById() {
		// TODO Auto-generated method stub
		viewAllEmployees();

		System.out.println("Enter a id of employee to update his details :");
		int id = sc.nextInt();
		Optional<Employee> em = employees.stream().filter(e -> e.getEmployeeId() == id).findFirst();
		if (em.isEmpty()) {
			System.out.println("you entered a wrong ID please check once again");
		} else {
			employees.remove(em.get());
			System.out.println("---------------------------");
			System.out.println("---------------------------");
			System.out.println("1.Enter for personal changes ");
			System.out.println("2.Enter for Department Related Chenges ");
			System.out.println("3.Enter for Designation Related Changes ");
			System.out.println("4.Enter for Employee Type");
			System.out.println("0. Exit");
			System.out.println("---------------------------");
			int ch = sc.nextInt();
			boolean flag=false;
			
			while (ch >= 1 && ch <= 4) {
				flag=true;
				if (ch == 1) {

					System.out.println("---------------------------");
					System.out.println("1.enter to change new name");
					System.out.println("2. enter to change salary");
					System.out.println("---------------------------");
					int inChoice=sc.nextInt();
					if(inChoice==1) {
						sc.nextLine();
						System.out.println("Enter name of employee :");
						String name = sc.nextLine();
						em.get().setEmployeeName(name);
					}
					else if(inChoice ==2) {
						System.out.println("Enter salary :");
						int sal = sc.nextInt();
						em.get().setSalary(sal);
					}
					else {
						log.info("******* you have failed to update details please try again");
					}
					
					
					
				}
				else if( ch==2) {
					log.info("list of all the departments");
					getAlldepartments(employees);
					
					System.out.println("---------------------------");
					System.out.println("1.enter to change department name ");
					System.out.println("2. enter to create a new department ");
					System.out.println("---------------------------");
					int inChoice=sc.nextInt();
					
					if(inChoice==1) {
						sc.nextLine();
						System.out.println("Enter department name : ");
						String deptName=sc.nextLine();
						int deptId=getDeptId(deptName);
						if(deptId==-1) {
							log.info("wrong depatment entered try again ");
						}
						else {
							em.get().setDepartment(new Department(deptId,deptName));
						}
					}
					else if( inChoice==2) {
						System.out.println("Enter the id of the department ");
						int deptId=sc.nextInt();
						sc.nextLine();
						System.out.println("Enter the department Name");
						String deptName=sc.nextLine();
						em.get().setDepartment(new Department(deptId,deptName));
					}
					else {
						log.info("*****wrong choice selected ");
					}
					
				}
				else if( ch==3) {
					log.info("list of all the designations");
					getAllDesignations(employees);
					
					System.out.println("---------------------------");
					System.out.println("1.enter to change designation name ");
					System.out.println("2. enter to create a new designation ");
					System.out.println("---------------------------");
					int inChoice=sc.nextInt();
					
					if(inChoice==1) {
						sc.nextLine();
						System.out.println("Enter designation name : ");
						String deptName=sc.nextLine();
						int deptId=getDesId(deptName);
						if(deptId==-1) {
							log.info("wrong designation entered try again ");
						}
						else {
							em.get().setDesignation(new Designation(deptId,deptName));
						}
					}
					else if( inChoice==2) {
						System.out.println("Enter the id of the designation ");
						int deptId=sc.nextInt();
						sc.nextLine();
						System.out.println("Enter the designation Name");
						String deptName=sc.nextLine();
						em.get().setDesignation(new Designation(deptId,deptName));
					}
					else {
						log.info("*****wrong choice selected ");
					}
					
				}
				else{
					System.out.println(" enter one of the following TYpe 1. FULLTIME \n 2.PARTTIME \n 3.INTERN");
					sc.nextLine();
					EmpType emT=EmpType.valueOf(sc.nextLine().toUpperCase());
					em.get().setEmpType(emT);
				}
				employees.add(em.get());
				log.info("****** updated successfully*****");
				
				System.out.println("---------------------------");
				System.out.println("---------------------------");
				System.out.println("1.Enter for personal changes ");
				System.out.println("2.Enter for Department Related Chenges ");
				System.out.println("3.Enter for Designation Related Changes ");
				System.out.println("4.Enter for Employee Type");
				System.out.println("0. Exit");
				System.out.println("---------------------------");
				 ch = sc.nextInt();
				
			}
			if(!flag) {
				log.info("you entered a wrong choice");
			}
			
		}

	}

	private int getDesId(String deptName) {
		// TODO Auto-generated method stub
		for(Employee e:employees) {
			if(e.getDesignation().designationName().equals(deptName)) {
				return e.getDesignation().designationId();
			}
		}
		return -1;
	}

	private int getDeptId(String deptName) {
		// TODO Auto-generated method stub
		for(Employee e:employees) {
			if(e.getDepartment().departmentName().equals(deptName)) {
				return e.getDepartment().departmentId();
			}
		}
		return -1;
	}

	public void calculateEmployeeTypeInsights() {
		// TODO Auto-generated method stub

		log.info("EmpType wise Total salaries");
		Map<EmpType, Long> mp = filter.getTotalSalary(employees, Employee::getEmpType, Employee::getSalary);

		for (EmpType d : mp.keySet()) {
			System.out.println(d.name() + " " + mp.get(d));
		}
		log.info("EmpType wise Average salaries");
		Map<EmpType, Double> mp2 = filter.getAverageSalary(employees, Employee::getEmpType, Employee::getSalary);

		for (EmpType d : mp.keySet()) {
			System.out.println(d.name() + " " + mp2.get(d));
		}

		log.info("EmpType wise Max salaries");
		Map<EmpType, Optional<Employee>> mp3 = filter.getMaxSalary(employees, Employee::getEmpType,
				Comparator.comparingInt(Employee::getSalary));

		for (EmpType d : mp3.keySet()) {
			System.out.println(d.name() + "   " + mp3.get(d).get());
		}

		log.info("EmpType wise Min salaries");
		Map<EmpType, Optional<Employee>> mp4 = filter.getMinSalary(employees, Employee::getEmpType,
				Comparator.comparingInt(Employee::getSalary));

		for (EmpType d : mp4.keySet()) {
			System.out.println(d.name() + "   " + mp4.get(d).get());
		}

	}

	public void getTotalInsights() {
		// TODO Auto-generated method stub
		log.info("Total Employees Count");
		System.out.println("Total count " + employees.size());
		
		log.info("Department wise  Count :");
		Map<Department,Long>mp=filter.getGroupCount(employees,Employee::getDepartment );
		
		for(Department d:mp.keySet()) {
			System.out.println(d.departmentName()+" no of employees "+mp.get(d));
		}
		
		log.info("Designation wise  Count :");
		Map<Designation,Long>mp2=filter.getGroupCount(employees,Employee::getDesignation );
		
		for(Designation d:mp2.keySet()) {
			System.out.println(d.designationName()+" no of employees "+mp2.get(d));
		}
		
		log.info("EmpType wise  Count :");
		Map<EmpType,Long>mp3=filter.getGroupCount(employees,Employee::getEmpType );
		
		for(EmpType d:mp3.keySet()) {
			System.out.println(d.name()+" no of employees "+mp3.get(d));
		}
		

	}

}
