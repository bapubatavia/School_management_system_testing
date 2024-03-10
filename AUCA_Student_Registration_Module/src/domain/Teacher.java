package domain;


import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int teacherId;
    @Column(name = "teacher_code")
    private String teacherCode;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "qualification")
    private EnumQualification qualification;
    @ManyToMany()
    @JoinTable(
    		name ="teacher_course",
    		joinColumns = @JoinColumn(name = "teacher_id"),
    		inverseJoinColumns = @JoinColumn(name = "course_id")
    		)
    private Set<Course> courses;

    public Teacher() {
    }

	public Teacher(int teacherId) {
		super();
		this.teacherId = teacherId;
	}

	

	public Teacher(int teacherId, String teacherCode, String firstName, String lastName,
			EnumQualification qualification, Set<Course> courses) {
		super();
		this.teacherId = teacherId;
		this.teacherCode = teacherCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.qualification = qualification;
		this.courses = courses;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public EnumQualification getQualification() {
		return qualification;
	}

	public void setQualification(EnumQualification qualification) {
		this.qualification = qualification;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	
}
