package com.klinik.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Question;


@Service
public class QuestionDAO {
	
	@Autowired
	private EntityManagerFactory factory;
	
	public List<Question> getAllQuestion()
	{
		return factory.createEntityManager().createQuery("from Question").getResultList();
	}
	
	public Question getQuestion(int id) {
		return (Question) factory.createEntityManager().createQuery("from Question where questionid = " +id).getSingleResult();
	}
	
}
