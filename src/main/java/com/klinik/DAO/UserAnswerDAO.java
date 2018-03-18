package com.klinik.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.klinik.model.Correctanswers;
import com.klinik.model.Patient;
import com.klinik.model.Personalitytest;
import com.klinik.model.Question;
import com.klinik.model.Useranswers;
import com.klinik.model.UseranswersPK;

@Service
public class UserAnswerDAO {

	@Autowired
	private EntityManagerFactory factory;
	@Autowired
	CorrectAnswerDAO needCalorieDAO = new CorrectAnswerDAO();
	

	public List<Useranswers> getAllUserAnswer() {
		return factory.createEntityManager().createQuery("from Useranswers ").getResultList();
	}

	public List<Useranswers> SumResult(String user, String date) {
		return factory.createEntityManager()
				.createQuery("from Useranswers where userpatient like '%" + user + "%' and datetest = '" + date + "'")
				.getResultList();
	}

	@Transactional
	public boolean addUserAnswer(Useranswers useranswer) {
		EntityManager em = factory.createEntityManager();
		Patient patient = new Patient();
		Question question = new Question();
		UseranswersPK useranswersPK = new UseranswersPK();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			useranswer.setDatetest(new Date());
			// patient.setUserpatient("kaka");
			// useranswer.setPatient(patient);
			System.out.println(
					"result " + useranswer.getChoosenanswerid() + " " + useranswer.getUseranswersPK().getQuestionid());
			useranswersPK.setQuestionid(useranswer.getUseranswersPK().getQuestionid());
			question.setQuestionid(useranswer.getUseranswersPK().getQuestionid());
			useranswer.setQuestion(question);
			useranswersPK.setUserpatient("salman");
			useranswer.setUseranswersPK(useranswersPK);
			System.out.println("lolos");
			Correctanswers ca = needCalorieDAO.getCaloriesNeed(useranswer.getChoosenanswerid(),
					useranswer.getUseranswersPK().getQuestionid());
			System.out.println("test2 " + ca.getCaloriesneed());
			int cneed = ca.getCaloriesneed();

			useranswer.setResulttemp(cneed);
			System.out.println("result 2 " + useranswer.getResulttemp());
			if (useranswer == null) {
				useranswer = new Useranswers();
				em.persist(useranswer);
			}
			useranswer = em.merge(useranswer);
			transaksi.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			transaksi.rollback();
			isSuccess = false;
			System.out.println(ex.getMessage());
		}
		return isSuccess;
	}

	public boolean addTest(Personalitytest test) {
		EntityManager em = factory.createEntityManager();
		Patient patient = new Patient();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		Date myDate = new Date();
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			test.setDatetest(new Date());
			patient.setUserpatient("salman");
			test.setUserpatient(patient);
			List<Useranswers> sum = SumResult("salman", new SimpleDateFormat("yyyy-MM-dd").format(myDate));
			int total = 0;
			for (int i = 0; i < sum.size(); i++) {
				int jml = sum.get(i).getResulttemp();
				total = total + jml;
			}

			System.out.println("total " + total);
			test.setResult(total);
			System.out.println("test2 " + test.getResult());
			if (test == null) {
				test = new Personalitytest();
				em.persist(test);
			}
			test = em.merge(test);
			// em.persist(test);

			transaksi.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			transaksi.rollback();
			isSuccess = false;
			System.out.println(ex.getMessage());
		}
		return isSuccess;
	}

	public long Sum(String user) {
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("select sum(resulttemp) from Useranswers where userpatient like '%" + user
				+ "%'group by userpatient, datetest");
		long result = (long) q.getSingleResult();
		System.out.println("sum " + result);
		return result;
	}

	// select sum(resulttemp) from useranswers where userpatient="P001" group by
	// userpatient, datetest;
}
