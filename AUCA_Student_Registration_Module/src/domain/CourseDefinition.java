package domain;


import jakarta.persistence.*;

@Entity
@Table(name = "course_definition")
public class CourseDefinition {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_definition_id")
    private int courseDefId;
    @Column(name = "course_definition_code")
    private String courseDefCode;
    @Column(name = "course_definition_description")
    private String courseDefDesc;
    @OneToOne(mappedBy = "courseDefinition")
    private Course course;

    public CourseDefinition() {
    }

	public CourseDefinition(int courseDefId) {
		super();
		this.courseDefId = courseDefId;
	}


	public CourseDefinition(int courseDefId, String courseDefCode, String courseDefDesc, Course course) {
		super();
		this.courseDefId = courseDefId;
		this.courseDefCode = courseDefCode;
		this.courseDefDesc = courseDefDesc;
		this.course = course;
	}

	public int getCourseDefId() {
		return courseDefId;
	}

	public void setCourseDefId(int courseDefId) {
		this.courseDefId = courseDefId;
	}

	public String getCourseDefCode() {
		return courseDefCode;
	}

	public void setCourseDefCode(String courseDefCode) {
		this.courseDefCode = courseDefCode;
	}

	public String getCourseDefDesc() {
		return courseDefDesc;
	}

	public void setCourseDefDesc(String courseDefDesc) {
		this.courseDefDesc = courseDefDesc;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	
}
