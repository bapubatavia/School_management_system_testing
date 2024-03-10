package domain;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "student_registration")
public class StudentRegistration {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private int regId;
    @Column(name = "registration_code")
    private String regCode;
    @Column(name = "registration_date")
    private Date regDate;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
	@ManyToOne
	@JoinColumn(name="academic_unit_id")
    private AcademicUnit depId;
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_course",
    		joinColumns = @JoinColumn(name = "registration_id"),
    		inverseJoinColumns = @JoinColumn(name = "course_id")
    		)
	private Set<Course> courses;
	
	
    public StudentRegistration() {
    }

    public StudentRegistration(int regId) {
        this.regId = regId;
    }



	public StudentRegistration(int regId, String regCode, Date regDate, Student student, Semester semester,
			AcademicUnit depId, Set<Course> courses) {
		super();
		this.regId = regId;
		this.regCode = regCode;
		this.regDate = regDate;
		this.student = student;
		this.semester = semester;
		this.depId = depId;
		this.courses = courses;
	}

	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public AcademicUnit getDepId() {
		return depId;
	}

	public void setDepId(AcademicUnit depId) {
		this.depId = depId;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	@Override
	public String toString() {
	    return "Student: " + student.getStudentId();
	}


		
	
}
