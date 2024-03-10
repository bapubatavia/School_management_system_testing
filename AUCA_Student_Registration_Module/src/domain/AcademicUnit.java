package domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="academic_unit")
public class AcademicUnit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="academic_unit_id")
	private int academicUnitId;
	@Column(name="academic_unit_code")
	private String academicUnitCode;
	@Column(name = "academic_unit_name")
	private String academicUnitName;
	@Column(name = "academic_unit_type")
	private EnumAcademicUnit academicUnitType;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "parent_id", referencedColumnName = "academic_unit_id")
	private AcademicUnit parentId;	
	
	@OneToMany(mappedBy = "depId")
	private List<StudentRegistration> students = new ArrayList<>();
	
	@OneToMany(mappedBy = "department")
	private List<Course> course = new ArrayList<>();

	
	public AcademicUnit() {
		super();
	}



	public AcademicUnit(int academicUnitId) {
		super();
		this.academicUnitId = academicUnitId;
	}


	public AcademicUnit(int academicUnitId, String academicUnitCode, String academicUnitName,
			EnumAcademicUnit academicUnitType, AcademicUnit parentId, List<StudentRegistration> students,
			List<Course> course) {
		super();
		this.academicUnitId = academicUnitId;
		this.academicUnitCode = academicUnitCode;
		this.academicUnitName = academicUnitName;
		this.academicUnitType = academicUnitType;
		this.parentId = parentId;
		this.students = students;
		this.course = course;
	}



	public int getAcademicUnitId() {
		return academicUnitId;
	}



	public void setAcademicUnitId(int academicUnitId) {
		this.academicUnitId = academicUnitId;
	}



	public String getAcademicUnitCode() {
		return academicUnitCode;
	}



	public void setAcademicUnitCode(String academicUnitCode) {
		this.academicUnitCode = academicUnitCode;
	}



	public String getAcademicUnitName() {
		return academicUnitName;
	}



	public void setAcademicUnitName(String academicUnitName) {
		this.academicUnitName = academicUnitName;
	}



	public EnumAcademicUnit getAcademicUnitType() {
		return academicUnitType;
	}



	public void setAcademicUnitType(EnumAcademicUnit academicUnitType) {
		this.academicUnitType = academicUnitType;
	}



	public AcademicUnit getParentId() {
		return parentId;
	}



	public void setParentId(AcademicUnit parentId) {
		this.parentId = parentId;
	}



	public List<StudentRegistration> getStudents() {
		return students;
	}



	public void setStudents(List<StudentRegistration> students) {
		this.students = students;
	}



	public List<Course> getCourse() {
		return course;
	}



	public void setCourse(List<Course> course) {
		this.course = course;
	}

	
	
}
