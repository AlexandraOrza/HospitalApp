package dao;

import com.hospital.model.Patient;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PatientDao extends GenericDao<Patient>{
    public List<Patient> findAll() {
        Session session = sessionFactory.openSession();

        Query<Patient> query = session.createQuery("from Patient ");

        return query.list();
    }

    public List<Patient> findByLastName(String lastName) {
        Session session = sessionFactory.openSession();

        Query<Patient> query = session.createQuery("select p from Patient p where p.lastName = :parameter");
        query.setParameter("parameter", lastName);
        return query.list();
    }

    public List<Patient> findPatientsFor(Integer medicalEmployeeId) {
        Session session = sessionFactory.openSession();
        Query<Patient> query = session.createQuery("select m.patientList from MedicalEmployee m where m.medicalEmployeeId = :parameter");
        query.setParameter("parameter", medicalEmployeeId);
        return query.list();
    }

    public Patient findByCnp(String cnp) {
        Session session = sessionFactory.openSession();
        Query<Patient> query = session.createQuery("select p from Patient p where p.cnp = :parameter");
        query.setParameter("parameter",cnp);
        return query.uniqueResult();

    }
}
