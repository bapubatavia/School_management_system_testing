package view;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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

public class ListsTests {

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//After running this class once, you can comment it out as it's just to enter data into the DB.
		
		//Date formatter
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
		//Academic Unit
		AcademicUnitController aUnitCtrl = new AcademicUnitController();
		
		AcademicUnit program = new AcademicUnit();
		program.setAcademicUnitCode("UGRAD01");
		program.setAcademicUnitName("Undergraduate");
		program.setAcademicUnitType(EnumAcademicUnit.PROGRAM);
		aUnitCtrl.registerAcademicUnit(program);

		AcademicUnit faculty = new AcademicUnit();
		faculty.setAcademicUnitCode("FACUL01");
		faculty.setAcademicUnitName("IT");
		faculty.setAcademicUnitType(EnumAcademicUnit.FACULTY);
		faculty.setParentId(program);
		aUnitCtrl.registerAcademicUnit(faculty);

		AcademicUnit depSoft = new AcademicUnit();
		depSoft.setAcademicUnitCode("SENG101");
		depSoft.setAcademicUnitName("Software Engineering");
		depSoft.setAcademicUnitType(EnumAcademicUnit.DEPARTMENT);
		depSoft.setParentId(faculty);
		aUnitCtrl.registerAcademicUnit(depSoft);
		
		AcademicUnit depNetw = new AcademicUnit();
		depNetw.setAcademicUnitCode("NETW101");
		depNetw.setAcademicUnitName("Networking");
		depNetw.setAcademicUnitType(EnumAcademicUnit.DEPARTMENT);
		depNetw.setParentId(faculty);
		aUnitCtrl.registerAcademicUnit(depNetw);
		
		
		//Course Definition
		CourseDefinitionController crsDefCtrl = new CourseDefinitionController();
		
		CourseDefinition webDesignDef = new CourseDefinition();
		webDesignDef.setCourseDefCode("CRSDEF001");
		webDesignDef.setCourseDefDesc("Learn to design and code websites' front-end.");
		crsDefCtrl.registerCourseDefinition(webDesignDef);
		
		CourseDefinition spmDef = new CourseDefinition();
		spmDef.setCourseDefCode("CRSDEF002");
		spmDef.setCourseDefDesc("Project Management course.");
		crsDefCtrl.registerCourseDefinition(spmDef);
		
		CourseDefinition compNetDef = new CourseDefinition();
		compNetDef.setCourseDefCode("CRSDEF003");
		compNetDef.setCourseDefDesc("Learn networking in computers.");
		crsDefCtrl.registerCourseDefinition(compNetDef);
		
		CourseDefinition linuxDef = new CourseDefinition();
		linuxDef.setCourseDefCode("CRSDEF004");
		linuxDef.setCourseDefDesc("Learn to use the operating system Linux.");
		crsDefCtrl.registerCourseDefinition(linuxDef);
		
		
		//Course
		CourseController crsCtrl = new CourseController();
		
		Course webDesign = new Course();
		webDesign.setCourseCode("COSC419");
		webDesign.setCourseName("Web Design");
		webDesign.setCourseDefinition(webDesignDef);
		webDesign.setDepartment(depSoft);
		crsCtrl.registerCourse(webDesign);
		
		Course linux = new Course();
		linux.setCourseCode("COSC327");
		linux.setCourseName("Introduction to Linux");
		linux.setCourseDefinition(linuxDef);
		linux.setDepartment(depNetw);
		crsCtrl.registerCourse(linux);
		
		Course spm = new Course();
		spm.setCourseCode("SENG412");
		spm.setCourseName("Software Project Management");
		spm.setCourseDefinition(spmDef);
		spm.setDepartment(depSoft);
		crsCtrl.registerCourse(spm);
		
		Course compNetwork = new Course();
		compNetwork.setCourseCode("INSY411");
		compNetwork.setCourseName("Computer Networks");
		compNetwork.setCourseDefinition(compNetDef);
		compNetwork.setDepartment(depNetw);
		crsCtrl.registerCourse(compNetwork);
		
		//Semester
		SemesterController semCtrl = new SemesterController();
		
		Semester semOne = new Semester();
		semOne.setSemName("one");
		try {
			semOne.setStartDate(dateFormat.parse("15/09/2023"));
			semOne.setEndDate(dateFormat.parse("23/12/2023"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Course> coursesSemesterOne = new ArrayList<Course>();
		coursesSemesterOne.add(webDesign);
		coursesSemesterOne.add(linux);
		coursesSemesterOne.add(spm);
		coursesSemesterOne.add(compNetwork);
		semOne.setCourses(coursesSemesterOne);
		semCtrl.registerSemester(semOne);
		
		
		Semester semTwo= new Semester();
		semTwo.setSemName("two");
		try {
			semTwo.setStartDate(dateFormat.parse("23/01/2024"));
			semTwo.setEndDate(dateFormat.parse("05/05/2024"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Course> coursesSemesterTwo = new ArrayList<Course>();
		coursesSemesterTwo.add(spm);
		coursesSemesterTwo.add(compNetwork);
		coursesSemesterTwo.add(webDesign);
		semTwo.setCourses(coursesSemesterTwo);
		semCtrl.registerSemester(semTwo);
		
		Semester semThree= new Semester();
		semThree.setSemName("three");
		try {
			semThree.setStartDate(dateFormat.parse("14/06/2024"));
			semThree.setEndDate(dateFormat.parse("25/08/2024"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Course> coursesSemesterThree = new ArrayList<Course>();
		coursesSemesterThree.add(webDesign);
		coursesSemesterThree.add(compNetwork);
		semThree.setCourses(coursesSemesterThree);
		semCtrl.registerSemester(semThree);
		
		//Student
		StudentController stuCtrl = new StudentController();
		
		Student stu1 = new Student();
		stu1.setStudentId(20000);
		stu1.setFirstName("Bob");
		stu1.setLastName("Clark");
		try {
			stu1.setDateOfBirth(dateFormat.parse("03/05/1999"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuCtrl.registerStudent(stu1);
		
		Student stu2 = new Student();
		stu2.setStudentId(21234);
		stu2.setFirstName("Emily");
		stu2.setLastName("Johnson");
		try {
			stu2.setDateOfBirth(dateFormat.parse("22/03/1998"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuCtrl.registerStudent(stu2);
		
		Student stu3 = new Student();
		stu3.setStudentId(25890);
		stu3.setFirstName("Michael");
		stu3.setLastName("Brown");
		try {
			stu3.setDateOfBirth(dateFormat.parse("10/11/2001"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		stuCtrl.registerStudent(stu3);
		
		Student stu4 = new Student();
		stu4.setStudentId(22987);
		stu4.setFirstName("Sarah");
		stu4.setLastName("Uwase");
		try {
			stu4.setDateOfBirth(dateFormat.parse("23/10/1998"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuCtrl.registerStudent(stu4);
		
		Student stu5 = new Student();
		stu5.setStudentId(21111);
		stu5.setFirstName("Gilbert");
		stu5.setLastName("Ishimwe");
		try {
			stu5.setDateOfBirth(dateFormat.parse("01/01/1998"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuCtrl.registerStudent(stu5);
		
		Student stu6 = new Student();
		stu6.setStudentId(23452);
		stu6.setFirstName("John");
		stu6.setLastName("Smith");
		try {
			stu6.setDateOfBirth(dateFormat.parse("01/01/2000"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuCtrl.registerStudent(stu6);
		
		Student stu7 = new Student();
		stu7.setStudentId(22314);
		stu7.setFirstName("Elise");
		stu7.setLastName("Uwamahoro");
		try {
			stu7.setDateOfBirth(dateFormat.parse("01/01/1999"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuCtrl.registerStudent(stu7);
		
		Student stu8 = new Student();
		stu8.setStudentId(20495);
		stu8.setFirstName("Kevin");
		stu8.setLastName("Karekezi");
		try {
			stu8.setDateOfBirth(dateFormat.parse("25/12/1998"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuCtrl.registerStudent(stu8);
		
		Student stu9 = new Student();
		stu9.setStudentId(22982);
		stu9.setFirstName("Naomie");
		stu9.setLastName("Dupond");
		try {
			stu9.setDateOfBirth(dateFormat.parse("06/02/1999"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuCtrl.registerStudent(stu9);
		
		Student stu10 = new Student();
		stu10.setStudentId(24986);
		stu10.setFirstName("Sam");
		stu10.setLastName("Sebera");
		try {
			stu4.setDateOfBirth(dateFormat.parse("02/11/2002"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuCtrl.registerStudent(stu10);
		
		//Student Registration
		StudentRegistrationController stuRegCtrl = new StudentRegistrationController();
		
		StudentRegistration stuRegOne = new StudentRegistration();
		stuRegOne.setRegCode("REG20000");
		stuRegOne.setStudent(stu1);
		stuRegOne.setSemester(semOne);
		try {
			stuRegOne.setRegDate(dateFormat.parse("05/09/2023"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuRegOne.setDepId(depSoft);
		Set<Course> coursesStudentOne = new HashSet<Course>();
		coursesStudentOne.add(spm);
		coursesStudentOne.add(compNetwork);
		coursesStudentOne.add(linux);
		stuRegOne.setCourses(coursesStudentOne);
		stuRegCtrl.saveStudentRegistration(stuRegOne);
		
		StudentRegistration stuRegTwo = new StudentRegistration();
		stuRegTwo.setRegCode("REG21234");
		stuRegTwo.setStudent(stu2);
		stuRegTwo.setSemester(semTwo);
		try {
			stuRegTwo.setRegDate(dateFormat.parse("13/01/2024"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuRegTwo.setDepId(depNetw);
		Set<Course> coursesStudentTwo = new HashSet<Course>();
		coursesStudentTwo.add(webDesign);
		coursesStudentTwo.add(spm);
		stuRegTwo.setCourses(coursesStudentTwo);
		stuRegCtrl.saveStudentRegistration(stuRegTwo);
		
		StudentRegistration stuRegThree = new StudentRegistration();
		stuRegThree.setRegCode("REG25890");
		stuRegThree.setStudent(stu3);
		stuRegThree.setSemester(semTwo);
		try {
			stuRegThree.setRegDate(dateFormat.parse("14/01/2024"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuRegThree.setDepId(depNetw);
		Set<Course> coursesStudentThree = new HashSet<Course>();
		coursesStudentThree.add(webDesign);
		coursesStudentThree.add(compNetwork);
		stuRegThree.setCourses(coursesStudentThree);
		stuRegCtrl.saveStudentRegistration(stuRegThree);
		
		StudentRegistration stuRegFour = new StudentRegistration();
		stuRegFour.setRegCode("REG22987");
		stuRegFour.setStudent(stu4);
		stuRegFour.setSemester(semThree);
		try {
			stuRegFour.setRegDate(dateFormat.parse("09/06/2024"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuRegFour.setDepId(depSoft);
		Set<Course> coursesStudentFour = new HashSet<Course>();
		coursesStudentFour.add(webDesign);
		coursesStudentFour.add(compNetwork);
		stuRegFour.setCourses(coursesStudentFour);
		stuRegCtrl.saveStudentRegistration(stuRegFour);
		
		StudentRegistration stuRegFive = new StudentRegistration();
		stuRegFive.setRegCode("REG21111");
		stuRegFive.setStudent(stu5);
		stuRegFive.setSemester(semTwo);
		try {
			stuRegFive.setRegDate(dateFormat.parse("09/01/2024"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuRegFive.setDepId(depSoft);
		Set<Course> coursesStudentFive = new HashSet<Course>();
		coursesStudentFive.add(webDesign);
		coursesStudentFive.add(spm);
		coursesStudentFive.add(linux);
		stuRegFive.setCourses(coursesStudentFive);
		stuRegCtrl.saveStudentRegistration(stuRegFive);
		
		StudentRegistration stuRegSix = new StudentRegistration();
		stuRegSix.setRegCode("REG23452");
		stuRegSix.setStudent(stu6);
		stuRegSix.setSemester(semThree);
		try {
			stuRegSix.setRegDate(dateFormat.parse("12/06/2024"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuRegSix.setDepId(depNetw);
		Set<Course> coursesStudentSix= new HashSet<Course>();
		coursesStudentSix.add(webDesign);
		coursesStudentSix.add(compNetwork);
		stuRegSix.setCourses(coursesStudentSix);
		stuRegCtrl.saveStudentRegistration(stuRegSix);
		
		StudentRegistration stuRegSeven = new StudentRegistration();
		stuRegSeven.setRegCode("REG22314");
		stuRegSeven.setStudent(stu7);
		stuRegSeven.setSemester(semOne);
		try {
			stuRegSeven.setRegDate(dateFormat.parse("07/09/2023"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuRegSeven.setDepId(depSoft);
		Set<Course> coursesStudentSeven= new HashSet<Course>();
		coursesStudentSeven.add(spm);
		coursesStudentSeven.add(linux);
		coursesStudentSeven.add(webDesign);
		stuRegSeven.setCourses(coursesStudentSeven);
		stuRegCtrl.saveStudentRegistration(stuRegSeven);
		
		StudentRegistration stuRegEight = new StudentRegistration();
		stuRegEight.setRegCode("REG20495");
		stuRegEight.setStudent(stu8);
		stuRegEight.setSemester(semTwo);
		try {
			stuRegEight.setRegDate(dateFormat.parse("20/01/2024"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuRegEight.setDepId(depNetw);
		Set<Course> coursesStudentEight= new HashSet<Course>();
		coursesStudentEight.add(compNetwork);
		coursesStudentEight.add(webDesign);
		stuRegEight.setCourses(coursesStudentEight);
		stuRegCtrl.saveStudentRegistration(stuRegEight);
		
		StudentRegistration stuRegNine = new StudentRegistration();
		stuRegNine.setRegCode("REG22982");
		stuRegNine.setStudent(stu9);
		stuRegNine.setSemester(semThree);
		try {
			stuRegNine.setRegDate(dateFormat.parse("06/06/2024"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuRegNine.setDepId(depNetw);
		Set<Course> coursesStudentNine= new HashSet<Course>();
		coursesStudentNine.add(compNetwork);
		stuRegNine.setCourses(coursesStudentNine);
		stuRegCtrl.saveStudentRegistration(stuRegNine);
		
		StudentRegistration stuRegTen = new StudentRegistration();
		stuRegTen.setRegCode("REG24986");
		stuRegTen.setStudent(stu10);
		stuRegTen.setSemester(semOne);
		try {
			stuRegTen.setRegDate(dateFormat.parse("12/09/2023"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stuRegTen.setDepId(depSoft);
		Set<Course> coursesStudentTen= new HashSet<Course>();
		coursesStudentTen.add(spm);
		coursesStudentTen.add(linux);
		stuRegTen.setCourses(coursesStudentTen);
		stuRegCtrl.saveStudentRegistration(stuRegTen);
		
		
		//Teacher
		TeacherController tchCtrl = new TeacherController();
		
		Teacher tch1 = new Teacher();
		tch1.setTeacherCode("TCH001");
		tch1.setFirstName("Prince");
		tch1.setLastName("Ishimwe");
		tch1.setQualification(EnumQualification.MASTER);
		Set<Course> coursesTeacherOne = new HashSet<Course>();
		coursesTeacherOne.add(spm);
		coursesTeacherOne.add(webDesign);
		tch1.setCourses(coursesTeacherOne);
		tchCtrl.registerTeacher(tch1);
		
		Teacher tch2 = new Teacher();
		tch2.setTeacherCode("TCH002");
		tch2.setFirstName("Jason");
		tch2.setLastName("Sibomana");
		tch2.setQualification(EnumQualification.PHD);
		Set<Course> coursesTeacherTwo = new HashSet<Course>();
		coursesTeacherTwo.add(compNetwork);
		coursesTeacherTwo.add(linux);
		tch2.setCourses(coursesTeacherTwo);
		tchCtrl.registerTeacher(tch2);	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void listStudentsPerSemester() {
		SemesterController semCtrl = new SemesterController();
		StudentRegistrationController stuCtrl = new StudentRegistrationController();
		List<Integer> studentIdInDb = new ArrayList<Integer>();
		Semester semOne = new Semester();
		
		
		semOne.setSemName("one");
		semOne = semCtrl.searchSemesterByName(semOne);
		List<StudentRegistration> students = stuCtrl.getStudentsBySemester(semOne);
		for(StudentRegistration stu: students) {
			studentIdInDb.add(stu.getStudent().getStudentId());
		}
		int[] expectedArray = {20000,22314,24986};
	    int[] returnedArray = new int[studentIdInDb.size()];
	    for (int i = 0; i < studentIdInDb.size(); i++) {
	        returnedArray[i] = studentIdInDb.get(i);
	    }
	    Arrays.sort(expectedArray);
	    Arrays.sort(returnedArray);
	    
	    assertArrayEquals(expectedArray, returnedArray);
	}
	
	@Test
	public void listStudentsPerSemesterAndDep() {
		SemesterController semCtrl = new SemesterController();
		StudentRegistrationController stuCtrl = new StudentRegistrationController();
		AcademicUnitController aUnitCtrl = new AcademicUnitController();
		List<Integer> studentIdInDb = new ArrayList<Integer>();
		Semester semOne = new Semester();
		AcademicUnit aUnit = new AcademicUnit();
		
		semOne.setSemName("three");
		semOne = semCtrl.searchSemesterByName(semOne);
		aUnit.setAcademicUnitCode("NETW101");
		aUnit = aUnitCtrl.searchAcademicUnitByCode(aUnit);
		List<StudentRegistration> students = stuCtrl.getStudentsBySemesterAndDepartment(semOne, aUnit);
		for(StudentRegistration stu: students) {
			studentIdInDb.add(stu.getStudent().getStudentId());
		}

		int[] expectedArray = {23452, 22982};
	    int[] returnedArray = new int[studentIdInDb.size()];
	    for (int i = 0; i < studentIdInDb.size(); i++) {
	        returnedArray[i] = studentIdInDb.get(i);
	    }
	    Arrays.sort(expectedArray);
	    Arrays.sort(returnedArray);
	    
	    assertArrayEquals(expectedArray, returnedArray);
	}
	
	@Test
	public void listStudentsPerSemesterAndCourse() {
		SemesterController semCtrl = new SemesterController();
		StudentRegistrationController stuCtrl = new StudentRegistrationController();
		CourseController crsCtrl = new CourseController();
		List<Integer> studentIdInDb = new ArrayList<Integer>();
		Semester semOne = new Semester();
		Course crs = new Course();
		
		semOne.setSemName("two");
		semOne = semCtrl.searchSemesterByName(semOne);
		crs.setCourseCode("INSY411");
		crs = crsCtrl.searchCourseByCode(crs);
		List<StudentRegistration> students = stuCtrl.getStudentsByCourseAndSemester(semOne, crs);
		for(StudentRegistration stu: students) {
			studentIdInDb.add(stu.getStudent().getStudentId());
		}
		int[] expectedArray = {25890, 20495};
	    int[] returnedArray = new int[studentIdInDb.size()];
	    for (int i = 0; i < studentIdInDb.size(); i++) {
	        returnedArray[i] = studentIdInDb.get(i);
	    }
	    Arrays.sort(expectedArray);
	    Arrays.sort(returnedArray);
	    
	    assertArrayEquals(expectedArray, returnedArray);
	}
	
	@Test
	public void listCoursesPerSemesterAndDep() {
		SemesterController semCtrl = new SemesterController();
		AcademicUnitController aUnitCtrl = new AcademicUnitController();
		CourseController crsCtrl = new CourseController();
		List<String> courseCodesInDb = new ArrayList<String>();
		Semester semOne = new Semester();
		AcademicUnit aUnit = new AcademicUnit();
		
		semOne.setSemName("two");
		semOne = semCtrl.searchSemesterByName(semOne);
		aUnit.setAcademicUnitCode("SENG101");
		aUnit = aUnitCtrl.searchAcademicUnitByCode(aUnit);
		List<Course> courses = crsCtrl.getCoursesByDepAndSemester(semOne, aUnit);
		for(Course course: courses) {
			courseCodesInDb.add(course.getCourseCode());
		}
		String[] expectedArray = {"COSC419","SENG412"};
	    String[] returnedArray = new String[courseCodesInDb.size()];
	    for (int i = 0; i < courseCodesInDb.size(); i++) {
	        returnedArray[i] = courseCodesInDb.get(i);
	    }	   
	    Arrays.sort(expectedArray);
	    Arrays.sort(returnedArray);
	    
	    assertArrayEquals(expectedArray, returnedArray);
	}
	
	@Test
	public void listCoursesPerStudent() {
		StudentRegistrationController stuRegCtrl = new StudentRegistrationController();
		StudentController stuCtrl = new StudentController();
		CourseController crsCtrl = new CourseController();
		List<String> courseCodesInDb = new ArrayList<String>();
		StudentRegistration stuReg = new StudentRegistration();
		Student stu = new Student();

		stu.setStudentId(21111);
		stu = stuCtrl.searchStudent(stu);
		stuReg = stuRegCtrl.searchStudentRegistrationByStudentId(stu);
		List<Course> courses = crsCtrl.getCoursesByStudent(stuReg);
		for(Course course: courses) {
			courseCodesInDb.add(course.getCourseCode());
		}
		String[] expectedArray = {"COSC419","SENG412","COSC327"};
	    String[] returnedArray = new String[courseCodesInDb.size()];
	    for (int i = 0; i < courseCodesInDb.size(); i++) {
	        returnedArray[i] = courseCodesInDb.get(i);
	    }	   
	    Arrays.sort(expectedArray);
	    Arrays.sort(returnedArray);
	    
	    assertArrayEquals(expectedArray, returnedArray);
	}

}
