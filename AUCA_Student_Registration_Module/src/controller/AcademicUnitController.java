package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import domain.AcademicUnit;
import repository.HibernateUtil;


public class AcademicUnitController {

    public AcademicUnit registerAcademicUnit(AcademicUnit AcademicUnitObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.persist(AcademicUnitObj);
            trans.commit();
            ss.close();
            return AcademicUnitObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public AcademicUnit updateAcademicUnit(AcademicUnit AcademicUnitObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.merge(AcademicUnitObj);
            trans.commit();
            ss.close();
            return AcademicUnitObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteAcademicUnit(AcademicUnit AcademicUnitObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.remove(AcademicUnitObj);
            trans.commit();
            ss.close();         
        }catch(Exception ex){
            ex.printStackTrace();
        }
    } 
    
    public AcademicUnit searchAcademicUnitByCode(AcademicUnit unit){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Query<AcademicUnit> query = ss.createQuery("FROM AcademicUnit WHERE academicUnitCode = :code", AcademicUnit.class);
            query.setParameter("code", unit.getAcademicUnitCode());
            AcademicUnit theUnit = query.uniqueResult();
            ss.close();
            return theUnit;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    

    public List<AcademicUnit> allAcademicUnits() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<AcademicUnit> query = session.createQuery("FROM AcademicUnit s", AcademicUnit.class);
            List<AcademicUnit> AcademicUnits = query.list();
            session.close();
            return AcademicUnits;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }   
}
