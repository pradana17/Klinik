package com.klinik.DAO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Membership;
import com.klinik.model.Patient;

@Service
public class MembershipDAO {

	@Autowired
	private EntityManagerFactory factory;
	
	public List<Membership> getAllMember() {
		return (List<Membership>) factory.createEntityManager().createQuery("from Membership").getResultList();
	}
	
	public Membership getMemberId(Integer id) {
		return (Membership) factory.createEntityManager().createQuery("from Membership where iDmember = " + id).getSingleResult();
	}
	
	public boolean addMember(Membership membership) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 2);
		Date next = cal.getTime();
		Patient pat = new Patient();
		pat.setIsactive(1);
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			membership.setDatejoin(new Date());
			membership.setDateexpired(next);
			//membership.setUserpatient(pat);
			em.persist(membership);
			transaksi.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			transaksi.rollback();
			isSuccess = false;
//			log.error("DAO Error", ex.getMessage());
			//level logging 1 to 5: trace -> debug -> info -> warning -> error
		}
		return isSuccess;
	}
}
