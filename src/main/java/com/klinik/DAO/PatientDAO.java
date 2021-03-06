package com.klinik.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klinik.model.Patient;


@Service
public class PatientDAO {

	@Autowired
	private EntityManagerFactory factory;
	@Autowired
	private PasswordEncoder pEncoder;
	
	public List<Patient> getAllPatient() {
		return (List<Patient>) factory.createEntityManager().createQuery("from Patient where isactive = 1").getResultList();
	}
	
	public Patient getPatientUser(String user) {
		return (Patient) factory.createEntityManager().createQuery("from Patient where userpatient = '" + user + "'").getSingleResult();
	}
	
	public List<String> getAllUserPatientName(){
		StringBuilder builder;
		List<String> patName = new ArrayList<>();
		List<Patient> pat = factory.createEntityManager().createQuery("from Patient where isactive = 1").getResultList();
		
		for (Patient p : pat) {
			builder = new StringBuilder();
			builder.append(p.getUserpatient());
			patName.add(builder.toString());
		}
		return patName;
	}
	
	public boolean addPatient(Patient patient) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			patient.setIsactive(1);
			patient.setPassword(pEncoder.encode(patient.getPassword()));
			em.persist(patient);
			transaksi.commit();
		} catch (Exception ex) {
			transaksi.rollback();
			isSuccess = false;
//			log.error("DAO Error", ex.getMessage());
			//level logging 1 to 5: trace -> debug -> info -> warning -> error
		}
		return isSuccess;
	}
	
	public boolean editPatient(Patient patient) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			
			Patient existingPatient = (Patient) em.find(Patient.class, patient.getUserpatient());
			
			existingPatient.setFullname(patient.getFullname());
			existingPatient.setPassword(patient.getPassword());
			existingPatient.setEmail(patient.getEmail());
			existingPatient.setHeight(patient.getHeight());
			existingPatient.setWeight(patient.getWeight());
			existingPatient.setIsactive(patient.getIsactive());
			transaksi.commit();
			
		} catch (Exception ex) {
			transaksi.rollback();
			isSuccess = false;
			System.out.println(ex.getMessage());
		}
		return isSuccess;
	}
}
