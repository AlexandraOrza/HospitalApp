package dao;

import com.hospital.model.MedicalEmployee;
import com.hospital.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GenericDao<X> {
    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    //era "private" dar il facem "protected" ca sa fie vazut de clasele copii


    public void save(X someObject) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction(); // deschide tranzactia

        session.save(someObject);

        transaction.commit();       // inchide tranzactia
        session.close();
    }

    public void update(X someObject) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction(); // deschide tranzactia

        session.update(someObject);

        transaction.commit();       // inchide tranzactia
        session.close();
    }

    public void delete(X someObject) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction(); // deschide tranzactia

        session.delete(someObject);

        transaction.commit();       // inchide tranzactia
        session.close();
    }

    public List<X> findByFieldName(String className, String fieldName, String parameterValue) {
        Session session = sessionFactory.openSession();
        Query<X> query = session.createQuery("from " + className + " x where x." + fieldName + " = :parameter");
        query.setParameter("parameter", parameterValue);
        return query.list();
    }

    public X findById(Class<X> className, Integer id) {
        Session session = sessionFactory.openSession();
        return session.find(className, id);
    }
}
