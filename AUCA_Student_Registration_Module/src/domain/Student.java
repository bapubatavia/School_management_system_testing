package domain;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
	
    @Id
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @OneToOne(mappedBy = "student")
    private StudentRegistration studentRegistration;

    public Student() {
    }

    public Student(int studentId) {
        this.studentId = studentId;
    }

	public Student(int studentId, String firstName, String lastName, Date dateOfBirth) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	
}
