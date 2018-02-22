package com.klinik.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Patient;
import com.klinik.model.Personalitytest;
import com.klinik.model.Useranswers;

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
		Date myDate=new Date();
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			test.setDatetest(new Date());	
			patient.setUserpatient("taufik");
			test.setUserpatient(patient);			
			List<Useranswers> sum = answerDAO.SumResult("taufik",new SimpleDateFormat("yyyy-MM-dd").format(myDate));
			int total = 0;
			for(int i = 0; i<sum.size();i++ ) {
				int jml=sum.get(i).getResulttemp();
				total = total + jml;				
			}
			
			
			System.out.println("total "+total);
			test.setResult(total);
			System.out.println("test2 "+test.getResult());			
			if (test==null) {
			    test = new Personalitytest();
			    em.persist(test);
			}
			test = em.merge(test);
//			 em.persist(test);

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
