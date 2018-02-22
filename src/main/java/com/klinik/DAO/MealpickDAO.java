package com.klinik.DAO;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Mealpick;


@Service
public class MealpickDAO {
	@Autowired
	private EntityManagerFactory managerFactory;
	
	public List<Mealpick> getAllMealpick(){
		return (List<Mealpick>) managerFactory.createEntityManager().createQuery("from Mealpick").getResultList();
	}
	
	public Mealpick getMealpick(int id) {
		return (Mealpick) managerFactory.createEntityManager().createQuery("from Mealpick where idpick = " + id).getSingleResult();
	}
}
