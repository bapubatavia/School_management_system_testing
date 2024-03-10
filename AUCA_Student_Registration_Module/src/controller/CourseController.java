package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import domain.AcademicUnit;
import domain.Course;
import domain.Semester;
import domain.StudentRegistration;
import repository.HibernateUtil;


public class CourseController {

    @SuppressWarnings("deprecation")
	public Course registerCourse(Course CourseObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.save(CourseObj);
            trans.commit();
            ss.close();
            return CourseObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Course updateCourse(Course CourseObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.merge(CourseObj);
            trans.commit();
            ss.close();
            return CourseObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteCourse(Course CourseObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.remove(CourseObj);
            trans.commit();
            ss.close();         
        }catch(Exception ex){
            ex.printStackTrace();
        }
    } 
    
    public Course searchCourse(Course course){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Course theCourse= (Course) ss.get(Course.class, course.getCourseId());
            ss.beginTransaction().commit();
            ss.close();
            return theCourse;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Course searchCourseByCode(Course course){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Query<Course> query = ss.createQuery("FROM Course WHERE courseCode = :code", Course.class);
            query.setParameter("code", course.getCourseCode());
            Course theCourse = query.uniqueResult();
            ss.close();
            return theCourse;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }   
    

    public List<Course> allCourses() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Course> query = session.createQuery("FROM Course s", Course.class);
            List<Course> Courses = query.list();
            session.close();
            return Courses;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }   
    
    public List<Course> getCoursesByDepAndSemester(Semester semester, AcademicUnit aUnit) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Course> query = session.createQuery("SELECT DISTINCT c FROM Course c JOIN FETCH c.semesters sem JOIN c.department dep WHERE sem = :semester AND dep = :academicUnit", Course.class);
            query.setParameter("semester", semester);
            query.setParameter("academicUnit", aUnit);
            List<Course> courses = query.list();
            session.close();
            return courses;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Course> getCoursesByStudent(StudentRegistration student) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Course> query = session.createQuery("FROM Course c JOIN FETCH c.students student WHERE student = :student ", Course.class);
            query.setParameter("student", student);
            List<Course> courses = query.list();
            session.close();
            return courses;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
