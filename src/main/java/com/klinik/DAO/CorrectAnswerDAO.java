package com.klinik.DAO;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.klinik.model.Correctanswers;

@Service
public class CorrectAnswerDAO {
	
	@Autowired
	private EntityManagerFactory factory;
	
	public List<Correctanswers> getAllCorrectAnswer(){
		return factory.createEntityManager().createQuery("from Correctanswers").getResultList();
	}
	
	public  Correctanswers getCaloriesNeed(int idanswer, int idquestion) {
		return (Correctanswers)	factory.createEntityManager().createQuery
				("from Correctanswers where questionid = " + idquestion 
						+" AND idanswer = "+idanswer).getSingleResult();
	}
	
	public List<Correctanswers> detailQuiz(int Id) {
		return factory.createEntityManager()
				.createQuery("from Correctanswers where questionid = "+Id)
				.getResultList();
	}
}
