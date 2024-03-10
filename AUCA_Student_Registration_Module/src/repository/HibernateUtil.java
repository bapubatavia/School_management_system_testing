package repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import domain.AcademicUnit;
import domain.Course;
import domain.CourseDefinition;
import domain.Semester;
import domain.Student;
import domain.StudentRegistration;
import domain.Teacher;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();
            //add annotated class
			configuration.addAnnotatedClass(Student.class);
			configuration.addAnnotatedClass(StudentRegistration.class);
			configuration.addAnnotatedClass(AcademicUnit.class);
			configuration.addAnnotatedClass(Semester.class);
			configuration.addAnnotatedClass(Course.class);
			configuration.addAnnotatedClass(CourseDefinition.class);
			configuration.addAnnotatedClass(Teacher.class);
			
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
;
			
        } catch (Throwable ex) {
            // Handle exception if any
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
