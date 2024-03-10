package domain;


import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId;
    @Column(name = "course_code")
    private String courseCode;
    @Column(name = "course_name")
    private String courseName;
    @ManyToMany(mappedBy = "courses")
    private List<Semester> semesters;
    @ManyToOne
    @JoinColumn(name = "academic_unit_id")
    private AcademicUnit department;
    @OneToOne
    @JoinColumn(name = "course_definition_id")
    private CourseDefinition courseDefinition;
    
    @ManyToMany(mappedBy = "courses")
    private Set<Teacher> teachers;

    @ManyToMany(mappedBy = "courses")
    private Set<StudentRegistration> students;
    
    
    public Course() {
    }

	public Course(int courseId) {
		super();
		this.courseId = courseId;
	}


	public Course(int courseId, String courseCode, String courseName, List<Semester> semesters, AcademicUnit department,
			CourseDefinition courseDefinition, Set<Teacher> teachers, Set<StudentRegistration> students) {
		super();
		this.courseId = courseId;
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.semesters = semesters;
		this.department = department;
		this.courseDefinition = courseDefinition;
		this.teachers = teachers;
		this.students = students;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Semester> getSemesters() {
		return semesters;
	}

	public void setSemesters(List<Semester> semesters) {
		this.semesters = semesters;
	}

	public AcademicUnit getDepartment() {
		return department;
	}

	public void setDepartment(AcademicUnit department) {
		this.department = department;
	}

	public CourseDefinition getCourseDefinition() {
		return courseDefinition;
	}

	public void setCourseDefinition(CourseDefinition courseDefinition) {
		this.courseDefinition = courseDefinition;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Set<StudentRegistration> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentRegistration> students) {
		this.students = students;
	}

		
}
