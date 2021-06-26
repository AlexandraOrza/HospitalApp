package dao;

import com.hospital.model.MedicalEmployee;

import java.util.List;

public class MedicalEmployeeDao extends GenericDao<MedicalEmployee>{

    public List<MedicalEmployee> findByUsername(String username){
        return findByFieldName("MedicalEmployee","userName", username);

    }
}
