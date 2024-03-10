package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "semester")
public class Semester {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semester_id")
    private int semId;
    @Column(name = "semester_name")
    private String semName;
    @Column(name = "starting_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @OneToMany(mappedBy = "semester")
	private List<StudentRegistration> students = new ArrayList<>();
    @ManyToMany()
    @JoinTable(
    		name ="semester_course",
    		joinColumns = @JoinColumn(name = "semester_id"),
    		inverseJoinColumns = @JoinColumn(name = "course_id")
    		)
    private List<Course> courses;
    
    public Semester() {
    }

    public Semester(int semId) {
		this.semId = semId;;
    }

	public Semester(int semId, String semName, Date startDate, Date endDate, List<StudentRegistration> students,
			List<Course> courses) {
		super();
		this.semId = semId;
		this.semName = semName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.students = students;
		this.courses = courses;
	}

	public int getSemId() {
		return semId;
	}

	public void setSemId(int semId) {
		this.semId = semId;
	}

	public String getSemName() {
		return semName;
	}

	public void setSemName(String semName) {
		this.semName = semName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<StudentRegistration> getStudents() {
		return students;
	}

	public void setStudents(List<StudentRegistration> students) {
		this.students = students;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
	    return "Semester " + semName;
	}

	
}
