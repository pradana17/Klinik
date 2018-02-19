package com.klinik.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Branch;


@Service
public class BranchDAO {

	@Autowired
	private EntityManagerFactory factory;
	
	public List<Branch> getAllBranch() {
		return (List<Branch>) factory.createEntityManager().createQuery("from Branch").getResultList();
	}
	
	public Branch getBranchId(String id) {
		return (Branch) factory.createEntityManager().createQuery("from Branch where idbranch = '" + id + "'").getSingleResult();
	}
	
	public boolean addBranch(Branch branch) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			em.persist(branch);
			transaksi.commit();
		} catch (Exception ex) {
			transaksi.rollback();
			isSuccess = false;
//			log.error("DAO Error", ex.getMessage());
			//level logging 1 to 5: trace -> debug -> info -> warning -> error
		}
		return isSuccess;
	}
	
	public boolean editBranch(Branch branch) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSuccess = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			
			Branch existingBranch = (Branch) em.find(Branch.class, branch.getIdbranch());
			
			existingBranch.setNamabranch(branch.getNamabranch());
			existingBranch.setAlamat(branch.getAlamat());
			existingBranch.setIsactive(branch.getIsactive());
			transaksi.commit();
			
		} catch (Exception ex) {
			transaksi.rollback();
			isSuccess = false;
			System.out.println(ex.getMessage());
		}
		return isSuccess;
	}
}
