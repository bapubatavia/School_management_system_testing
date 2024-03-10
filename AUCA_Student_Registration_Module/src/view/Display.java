package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import controller.AcademicUnitController;
import controller.CourseController;
import controller.CourseDefinitionController;
import controller.SemesterController;
import controller.StudentController;
import controller.StudentRegistrationController;
import controller.TeacherController;
import domain.AcademicUnit;
import domain.Course;
import domain.CourseDefinition;
import domain.EnumAcademicUnit;
import domain.EnumQualification;
import domain.Semester;
import domain.Student;
import domain.StudentRegistration;
import domain.Teacher;

public class Display {

	public static void main(String[] args) {
		boolean condition = true;
	    // Student
	    String studentId;
	    @SuppressWarnings("unused")
		Date dateOfBirth;

	    // Semester
	    Date startDate;
	    Date endDate;



	    // Teacher
	    String teacherCode;

	    // StudentRegistration
	    Date regDate; 
	    
	    
	    String strgInput;

	    
	    //model initialisation
	    AcademicUnit aUnit = new AcademicUnit();
	    AcademicUnit aUnit2 = new AcademicUnit();
	    Course crs = new Course();
	    CourseDefinition crsDef = new CourseDefinition();
	    Semester sem = new Semester();
	    Student student = new Student();
	    StudentRegistration studentReg = new StudentRegistration();
	    Teacher tch = new Teacher();
	    
	    //dao initialisation
	    AcademicUnitController aUnitCtrl = new AcademicUnitController();
	    CourseController crsCtrl = new CourseController();
	    CourseDefinitionController crsDefCtrl = new CourseDefinitionController();
	    SemesterController semCtrl = new SemesterController();
	    StudentController studentCtrl = new StudentController();
	    StudentRegistrationController studentRegCtrl = new StudentRegistrationController();
	    TeacherController tchCtrl = new TeacherController();
	    
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
	    while(condition){
            System.out.println("==========================");
            System.out.println("AUCA MANAGEMENT SYSTEM");
            System.out.println("--------------------------");
            System.out.println("1. Insert Data ");
//            System.out.println("2. Delete Entity ");
            System.out.println("2. Retrieve Lists");
            System.out.println("0. Exit");
            System.out.println("---------------------");
            System.out.print("Choose: "); 
            try (Scanner input = new Scanner(System.in)) {
				int choice = input.nextInt();
				switch(choice){
				    case 1:
				    	boolean condition2 = true;
				    	while(condition2) {
				            System.out.println("==========================");
				            System.out.println("DATA INSERTION MANAGEMENT SYSTEM");
				            System.out.println("--------------------------");
				            System.out.println("Please selection which table to insert data: ");
				            System.out.println("1. Academic Unit ");
				            System.out.println("2. Course Definition ");
				            System.out.println("3. Course ");
				            System.out.println("4. Semester");                        
				            System.out.println("5. Student ");
				            System.out.println("6. Student Registration ");
				            System.out.println("7. Teacher");
				            System.out.println("0. Exit");
				            System.out.println("---------------------");
				            System.out.print("Choose: ");
				            int choice2 = input.nextInt();
				            switch(choice2) {
				                case 1:
				                    System.out.println("Enter Academic Unit details:");
				                    System.out.println("");
				                	do {
				                		System.out.print("Enter the Academic Unit Code:");
				                		aUnit.setAcademicUnitCode(input.next());
				                		if(aUnit.getAcademicUnitCode().length() == 7) {
				                			break;
				                		} else {
				                			System.out.println("Please enter a code of exactly 7 digits.");
				                		}
				                	}while(true);
				                	input.nextLine();
				                    System.out.print("Enter Academic Unit Name: ");	
				                    aUnit.setAcademicUnitName(input.nextLine());
				                	do {
				                        System.out.print("Enter Academic Unit Type (\"PROGRAM\", \"FACULTY\", \"DEPARTMENT\": ");
				                		strgInput = input.next();
				                		System.out.println(strgInput.toUpperCase());
				                		if(strgInput.equalsIgnoreCase("PROGRAM")) {
				                			aUnit.setAcademicUnitType(EnumAcademicUnit.PROGRAM);
				                			break;
				                		} else if(strgInput.equalsIgnoreCase("FACULTY")) {
				                			aUnit.setAcademicUnitType(EnumAcademicUnit.FACULTY);
				                			break;
				                		} else if(strgInput.equalsIgnoreCase("DEPARTMENT")) {
				                			aUnit.setAcademicUnitType(EnumAcademicUnit.DEPARTMENT);
				                			break;
				                		} else {
				                			System.out.println("Please pick one of the mentioned options above.");
				                		}
				                	}while(true);
				                	System.out.print("Does the unit have a parent unit?(Y/N)");
				                	strgInput = input.next();
				                	if(strgInput.equalsIgnoreCase("Y")) {
				                    	System.out.print("Enter Academic Unit Parent's Code:");
				                    	aUnit2.setAcademicUnitCode(input.next());
				                    	aUnit2 = aUnitCtrl.searchAcademicUnitByCode(aUnit2);
				                		aUnit.setParentId(aUnit2);	                        		
				                	}
				                	aUnitCtrl.registerAcademicUnit(aUnit);
				                    System.out.println("Academic Unit inserted successfully.");
				                	System.out.println("Do you wish to continue? Use Yes or No");
				                    strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition2 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition2 = false;
				                    }                                      
				                    break;	
				                    
				                case 2:
				                    System.out.println("Enter Course Definition details:");
				                    System.out.println("");
				                    System.out.print("Enter Course Definition Code: ");
				                    crsDef.setCourseDefCode(input.next());
				                    input.nextLine();
				                    System.out.print("Enter Course Definition Description: ");
				                    crsDef.setCourseDefDesc(input.nextLine());
				                    
				                    crsDefCtrl.registerCourseDefinition(crsDef);
				                    
				                    System.out.println("Course Definition inserted successfully.");
				                	System.out.println("Do you wish to continue? Use Yes or No");
				                	strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition2 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition2 = false;
				                    }                                      
				                    break;
				                    
				                case 3:
				                    System.out.println("Enter Course details:");
				                    System.out.println("");
				                    System.out.print("Enter Course Code: ");
				                    crs.setCourseCode(input.next());
				                    input.nextLine();
				                    System.out.print("Enter Course Name: ");
				                    crs.setCourseName(input.nextLine());
				                    System.out.print("Enter Course Definition Code: ");
				                    crsDef.setCourseDefCode(input.next());
				                    crsDef = crsDefCtrl.searchCourseDefinitionByCode(crsDef);
				                    crs.setCourseDefinition(crsDef);
				                    
				                    System.out.print("Enter Department Code where this course belongs to: ");
				                    strgInput = input.next();
				                	aUnit.setAcademicUnitCode(strgInput);
				                	aUnit2 = aUnitCtrl.searchAcademicUnitByCode(aUnit);
				                    crs.setDepartment(aUnit2);
				                    
				                    crsCtrl.registerCourse(crs);
				             
				                    System.out.println("Course inserted successfully.");
				                	System.out.println("Do you wish to continue? Use Yes or No");
				                    strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition2 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition2 = false;
				                    }
				                    break;
				                    
				                case 4:
				                    System.out.println("Enter Semester details:");
				                    System.out.println("");
				                    System.out.print("Enter Semester Name: ");
				                    sem.setSemName(input.next());
				                    System.out.print("Enter Start Date (dd/MM/yyyy): ");
				                    String startDateString = input.next();
				                    System.out.print("Enter End Date (dd/MM/yyyy): ");
				                    String endDateString = input.next();


				                    try {
				                        startDate = dateFormat.parse(startDateString);
				                        endDate = dateFormat.parse(endDateString);
				                        sem.setStartDate(startDate);
				                        sem.setEndDate(endDate);
				                    } catch (Exception ex) {
				                        System.out.println("Invalid date format. Please use dd/MM/yyyy format.");
				                    }
				                    
				                    List<Course> coursesSemester = new ArrayList<Course>();
				                    do {
				                        System.out.print("Please enter the course code of the course that will be available this semester: ");
				                        strgInput = input.next();
				                    	Course crs1 = new Course();
				                    	crs1.setCourseCode(strgInput);
				                    	crs = crsCtrl.searchCourseByCode(crs1);
				                    	coursesSemester.add(crs);
				                    	System.out.print("Add more courses? (Y/N): ");
				                    	strgInput = input.next();
				                    	if(strgInput.equalsIgnoreCase("N")) {
				                    		break;
				                    	}
				                    }while(true);
				                    sem.setCourses(coursesSemester);
				                    semCtrl.registerSemester(sem);
				                    System.out.println("Semester inserted successfully.");
				                	System.out.println("Do you wish to continue? Use Yes or No");
				                    strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition2 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition2 = false;
				                    }                                      
				                    break;
				                    
				                case 5:
				                	do {
				                        System.out.println("Enter Student details: ");
				                        System.out.println("");
				                        System.out.print("Enter Student ID: ");
				                		studentId = input.next();
				                		if(studentId.length() == 5) {
				                			student.setStudentId(Integer.parseInt(studentId));
				                			break;
				                		} else {
				                			System.out.println("Please enter an ID of exactly 5 digits.");
				                		}
				                	}while(true);
				                    input.nextLine(); 
				                    System.out.print("Enter First Name: ");
				                    student.setFirstName(input.nextLine());
				                    System.out.print("Enter Last Name: ");
				                    student.setLastName(input.nextLine());
				                    System.out.print("Enter Date of Birth (dd/MM/yyyy): ");
				                    String dobString = input.next();
				                    try {
				                    	dateOfBirth = dateFormat.parse(dobString);
				                    	student.setDateOfBirth(dateOfBirth);
				                    	studentCtrl.registerStudent(student);
				                        System.out.println("Student inserted successfully.");
				                    } catch (Exception ex) {
				                        System.out.println("Invalid date format. Please use dd/MM/yyyy format.");
				                    }
				                	System.out.println("Do you wish to continue? Use Yes or No");
				                	strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition2 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition2 = false;
				                    }   
				                    
				                    break;
				                    
				                case 6:
				                	System.out.println("Enter Student Registration details:");
				                    System.out.print("Enter Registration Code: ");
				                    studentReg.setRegCode(input.next());
				                    System.out.print("Enter Student ID: ");
				                    student.setStudentId(input.nextInt());
				                    studentReg.setStudent(student);
				                    System.out.print("Enter Semester(one,two,three,four,five,six,seven): ");
				                    strgInput = input.next();
				                    if(strgInput.equalsIgnoreCase("one") || strgInput.equalsIgnoreCase("two") || strgInput.equalsIgnoreCase("three") || strgInput.equalsIgnoreCase("four") ||
				                    		strgInput.equalsIgnoreCase("five") || strgInput.equalsIgnoreCase("six") || strgInput.equalsIgnoreCase("seven")) {
				                    	Semester sem1 = new Semester();
				                    	sem1.setSemName(strgInput);
				                    	sem = semCtrl.searchSemesterByName(sem1);
				                    	studentReg.setSemester(sem);
				                    	System.out.println("Semester selected: "+sem.getSemName());
				                    	
				                    }
				                    System.out.print("Enter Registration Date (dd/MM/yyyy): ");
				                    String regDateString = input.next();
				                    try {
				                        regDate = dateFormat.parse(regDateString);
				                        studentReg.setRegDate(regDate);

				                    } catch(Exception ex) {
				                    	System.out.println("Invalid date format. Please use dd/MM/yyyy format.");
				                    }
				                    System.out.print("Enter your Department Code: ");	                         
				                    aUnit2.setAcademicUnitCode(input.next());
				                    aUnit = aUnitCtrl.searchAcademicUnitByCode(aUnit2);
				                    studentReg.setDepId(aUnit);
				                    Set<Course> coursesStudent= new HashSet<>();
				                    do {
				                        System.out.print("Please enter the course code of the course you'll be taking this semester: ");
				                        strgInput = input.next();
				                    	Course crs1 = new Course();
				                    	crs1.setCourseCode(strgInput);
				                    	crs = crsCtrl.searchCourseByCode(crs1);
				                    	coursesStudent.add(crs);
				                    	System.out.print("Add more courses? (Y/N): ");
				                    	strgInput = input.next();
				                    	if(strgInput.equalsIgnoreCase("N")) {
				                    		break;
				                    	}
				                    }while(true);
				                    studentReg.setCourses(coursesStudent);
				                    
				                    studentRegCtrl.saveStudentRegistration(studentReg);
				                    System.out.println("Student Registration inserted successfully.");
				                	System.out.println("Do you wish to continue? Use Yes or No");
				                	strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition2 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition2 = false;
				                    }   

				                    break;
				                    
				                case 7:
				                	System.out.println("Enter Teacher details:");
				                	System.out.println("");
				                	do {
				                        System.out.print("Enter Teacher Code: ");
				                		teacherCode = input.next();
				                		if(teacherCode.length() == 7) {
				                			tch.setTeacherCode(teacherCode);
				                			break;
				                		} else {
				                			System.out.println("Please enter an ID of exactly 7 digits.");
				                		}
				                	}while(true);
				                    System.out.print("Enter First Name: ");
				                    tch.setFirstName(input.next());
				                    System.out.print("Enter Last Name: ");
				                    tch.setLastName(input.next());
				                	do {
				                        System.out.print("Enter Qualification (\"MASTER\", \"PHD\", \"PROFESSOR\": ");
				                		strgInput = input.next();
				                		if(strgInput.equalsIgnoreCase("MASTER")) {
				                			tch.setQualification(EnumQualification.MASTER);
				                			break;
				                		} else if(strgInput.equalsIgnoreCase("PHD")) {
				                			tch.setQualification(EnumQualification.PHD);
				                			break;
				                		} else if(strgInput.equalsIgnoreCase("PROFESSOR")) {
				                			tch.setQualification(EnumQualification.PROFESSOR);
				                			break;
				                		} else {
				                			System.out.println("Please pick one of the options mentioned above.");
				                		}
				                	}while(true);
				                	
				                	Set<Course> courses = new HashSet<Course>();
				                    System.out.print("Enter Course Code for this lecturer: ");
				                    strgInput = input.next();
				                    Course crs1 = new Course();
				                    Course crs2 = new Course();
				                    crs1.setCourseCode(strgInput);
				                    crs = crsCtrl.searchCourseByCode(crs1);
				                    courses.add(crs);
				                    System.out.print("Is there another course for this lecturer?(Y/N)");
				                    strgInput = input.next();
				                    if(strgInput.equalsIgnoreCase("Y")) {
				                        System.out.print("Enter Course Code for this lecturer: ");
				                        strgInput = input.next();
				                        crs2.setCourseCode(strgInput);
				                        crs = crsCtrl.searchCourseByCode(crs2);
				                        courses.add(crs);
				                    }
				                    tch.setCourses(courses);
				                	
				                	tchCtrl.registerTeacher(tch);
				                	
				                    System.out.println("Teacher inserted successfully.");
				                	System.out.println("Do you wish to continue? Use Yes or No");
				                	strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition2 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition2 = false;
				                    }   
				                    
				                    break;
				                    
				                case 0:
				                    System.out.println("Thank you for using the system.");
				                    System.exit(0);
				                    break;
				                default:
				                    
				                    break;
				            }
				    	}
				    case 2:
				        boolean condition3 = true;
				        while(condition3) {
				            System.out.println("==========================");
				            System.out.println("DATA RETRIEVAL MANAGEMENT SYSTEM");
				            System.out.println("--------------------------");
				            System.out.println("Please select which table to retrieve data from: ");
				            System.out.println("1. List of Students per Semester ");
				            System.out.println("2. List of Students per Semester and Department");
				            System.out.println("3. List of Students per Semester and Course");
				            System.out.println("4. List of Courses per Semester and Department ");
				            System.out.println("5. List of Courses per Student ");
				            System.out.println("0. Exit");
				            System.out.println("---------------------");
				            System.out.print("Choose: ");
				            int choice4 = input.nextInt();
				            switch(choice4) {
				                case 1:
				                    System.out.print("Please enter Semester Name to get Student list:");
				                    sem.setSemName(input.next());
				                    sem = semCtrl.searchSemesterByName(sem);
				            		List<StudentRegistration> allStudents = studentRegCtrl.getStudentsBySemester(sem);
				            		if(allStudents != null) {
				            			System.out.println("====================================================================");
				            			System.out.println("STUDENT REG CODE       FIRST NAME       SEMESTER");
				            			System.out.println("====================================================================");
					            		for(StudentRegistration stuReg: allStudents) {
					            			
					            		    System.out.println(stuReg.getRegCode() + "		" + stuReg.getStudent().getFirstName() + "		" + stuReg.getSemester().getSemName());
					            		}
				            			System.out.println("====================================================================");
				            			System.out.println("");
					            		System.out.println("List printed successfully.");
					            	} else {
				            			System.out.println("No students with those details");
				            		}	
				                    System.out.println("Do you wish to continue? Use Yes or No");
				                    strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition3 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition3 = false;
				                    } 
				                    break;
				                case 2:
				                    System.out.print("Please enter Semester Name:");
				                    sem.setSemName(input.next());
				                    sem = semCtrl.searchSemesterByName(sem);
				                    System.out.print("Now please enter Department Code to get Student list:");
				                    aUnit.setAcademicUnitCode(input.next());
				                    aUnit = aUnitCtrl.searchAcademicUnitByCode(aUnit);
				            		List<StudentRegistration> allStudentsSemDep = studentRegCtrl.getStudentsBySemesterAndDepartment(sem, aUnit);
				            		if(allStudentsSemDep != null ) {
				            			System.out.println("====================================================================");
				            			System.out.println("STUDENT REG CODE       FIRST NAME       SEMESTER       DEPARTMENT");
				            			System.out.println("====================================================================");
					            		for(StudentRegistration stuReg: allStudentsSemDep) {
					            		    System.out.println(stuReg.getRegCode() + "		" + stuReg.getStudent().getFirstName() + "		" + stuReg.getSemester().getSemName() + "		" + stuReg.getDepId().getAcademicUnitName());
					            		}
				            			System.out.println("====================================================================");
				            			System.out.println("");
					            		System.out.println("List printed successfully.");
					            	} else {
				            			System.out.println("No students with those details");
				            		}				                    
				            		System.out.println("Do you wish to continue? Use Yes or No");
				                    strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition3 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition3 = false;
				                    } 
				                    break;
				                case 3:
				                    System.out.print("Please enter Semester Name:");
				                    sem.setSemName(input.next());
				                    sem = semCtrl.searchSemesterByName(sem);
				                    System.out.print("Now please enter Course Code to get Student list:");
				                    crs.setCourseCode(input.next());
				                    crs = crsCtrl.searchCourseByCode(crs);
				            		List<StudentRegistration> allStudentsSemCrs = studentRegCtrl.getStudentsByCourseAndSemester(sem, crs);
				            		if(allStudentsSemCrs !=null) {
				            			System.out.println("=====================================================");
				            			System.out.println("STUDENT REG CODE       FIRST NAME       SEMESTER");
				            			System.out.println("=====================================================");
					            		for(StudentRegistration stuReg: allStudentsSemCrs) {
					            		    System.out.println(stuReg.getRegCode() + "		" + stuReg.getStudent().getFirstName() + "		" + stuReg.getDepId().getAcademicUnitName());
					            		}
				            			System.out.println("=====================================================");
				            			System.out.println("");
					            		System.out.println("List printed successfully.");
					            	} else {
				            			System.out.println("No students with those details");
				            		}
				                    System.out.println("Do you wish to continue? Use Yes or No");
				                    strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition3 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition3 = false;
				                    } 
				                    break;
				                case 4:
				                    System.out.print("Please enter Semester Name:");
				                    sem.setSemName(input.next());
				                    sem = semCtrl.searchSemesterByName(sem);
				                    System.out.print("Now please enter Department Code to get Course list:");
				                    aUnit.setAcademicUnitCode(input.next());
				                    aUnit = aUnitCtrl.searchAcademicUnitByCode(aUnit);
				            		List<Course> allCoursesSemDep = crsCtrl.getCoursesByDepAndSemester(sem, aUnit);
				            		if(allCoursesSemDep != null) {
				            			System.out.println("==============================================================================================");
				            			System.out.println("COURSE CODE       DEPART NAME       		SEMESTERS       	COURSE NAME");
				            			System.out.println("==============================================================================================");
				            			for(Course course : allCoursesSemDep) {
					            		    System.out.println(course.getCourseCode()+ "		" + course.getDepartment().getAcademicUnitName() + "		" + course.getSemesters().toArray()[0] + "		" + course.getCourseName());
					            		}
				            			System.out.println("==============================================================================================");
				            			System.out.println("");
					            		System.out.println("List printed successfully.");
				            		}else {
				            			System.out.println("No courses with those details.");
				            		}
				                    System.out.println("Do you wish to continue? Use Yes or No");
				                    strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition3 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition3 = false;
				                    } 
				                    break;
				                case 5:
				                    System.out.print("Please enter Student ID:");
				                    student.setStudentId(input.nextInt());
				                    student = studentCtrl.searchStudent(student);
				                    studentReg = studentRegCtrl.searchStudentRegistrationByStudentId(student);
				            		List<Course> allCoursesStudent= crsCtrl.getCoursesByStudent(studentReg);
				            		if(allCoursesStudent != null) {
				            			System.out.println("============================================================");
				            			System.out.println("COURSE CODE       DEPART NAME       COURSE NAME");
				            			System.out.println("============================================================");
					            		for(Course course : allCoursesStudent) {
					            		    System.out.println(course.getCourseCode()+ "           " + course.getDepartment().getAcademicUnitCode() + "           " + course.getCourseName());
					            		}
				            			System.out.println("============================================================");
				            			System.out.println("");
					                	System.out.println("List printed successfully.");
				            		}else {
				            			System.out.println("No courses with those details.");
				            		}
				                    System.out.println("Do you wish to continue? Use Yes or No");
				                    strgInput = input.next();
				                    if (strgInput.equalsIgnoreCase("yes")){
				                        condition3 = true;
				                    }else{
				                        System.out.println("Thank you for using the system.");
				                        condition3 = false;
				                    } 
				                    break;
				                case 0:
				                    System.out.println("Thank you for using the system.");
				                    System.exit(0);
				                    break;
				                default:
				                    
				                    break;
				            }
				        }
				        break;
				    case 0:
				        System.out.println("Thank you for using the system.");
				        System.exit(0);
				        break;
				    default:
				        
				        break;                    
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
        }
	}

}