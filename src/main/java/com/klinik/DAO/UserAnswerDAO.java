package com.klinik.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.klinik.model.Correctanswers;
import com.klinik.model.Patient;
import com.klinik.model.Question;
import com.klinik.model.Useranswers;
import com.klinik.model.UseranswersPK;


@Service
public class UserAnswerDAO {
	
	@Autowired
	private EntityManagerFactory factory;
	@Autowired
	CorrectAnswerDAO needCalorieDAO = new CorrectAnswerDAO();

	public List<Useranswers> getAllUserAnswer(){
		return factory.createEntityManager().createQuery("from Useranswers").getResultList();
	}
	
	@Transactional
	public boolean addUserAnswer(Useranswers useranswer) {
		EntityManager em = factory.createEntityManager();
		Patient patient=new Patient();
		Question question = new Question();
		UseranswersPK useranswersPK= new UseranswersPK();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			useranswer.setDatetest(new Date());
			patient.setUserpatient("P001");
			useranswer.setPatient(patient);	
			
//			question.setQuestionid(5);
//			useranswer.setQuestion(question);
//			useranswersPK.setQuestionid(5);
			
			System.out.println("result " +useranswer.getChoosenanswerid()+" "+useranswer.getUseranswersPK().getQuestionid());
			
			useranswersPK.setQuestionid(useranswer.getUseranswersPK().getQuestionid());
			question.setQuestionid(useranswer.getUseranswersPK().getQuestionid());
			useranswer.setQuestion(question);
			useranswersPK.setUserpatient("P001");
			useranswer.setUseranswersPK(useranswersPK);
			System.out.println("lolos");
			Correctanswers ca  = needCalorieDAO.getCaloriesNeed(useranswer.getChoosenanswerid(), useranswer.getUseranswersPK().getQuestionid());
			System.out.println("test2 "+ca.getCaloriesneed());
			int cneed = ca.getCaloriesneed();			
			useranswer.setResulttemp(cneed);
			System.out.println("result 2 " +useranswer.getResulttemp());
			em.persist(useranswer);
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
