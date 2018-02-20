package com.klinik.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Patient;
import com.klinik.model.Personalitytest;

@Service
public class TestDAO {
	@Autowired
	private EntityManagerFactory factory;
	@Autowired
	private UserAnswerDAO answerDAO;
	
	
	public List<Personalitytest> getAllTest()
	{
		return factory.createEntityManager().createQuery("from Personalitytest").getResultList();
	}
	
	public boolean addTest(Personalitytest test) {
		EntityManager em = factory.createEntityManager();
		Patient patient=new Patient();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			test.setDatetest(new Date());
			patient.setUserpatient("P001");
			test.setUserpatient(patient);			
			long sum = answerDAO.Sum("P001");
			test.setResult((int) sum);
			System.out.println("test2 "+test.getResult());			
			em.persist(test);
			if (test==null) {
			    test = new Personalitytest();
			    em.persist(test);
			}
			test = em.merge(test);
			transaksi.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			transaksi.rollback();
			isSuccess = false;
			System.out.println(ex.getMessage());
		}
		return isSuccess;
	}	
	
}
