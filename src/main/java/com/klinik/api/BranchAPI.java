package com.klinik.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klinik.DAO.BranchDAO;
import com.klinik.model.Branch;

@RestController
@RequestMapping("/api/branch")

public class BranchAPI {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	
	@Autowired
	private BranchDAO branchDAO;

	@GetMapping("/changebranchnama")
	public boolean changeBranchnama(
			@RequestParam("id") String id,
			@RequestParam("nama") String nama) {

		Branch branch = branchDAO.getBranchId(id);
		branch.setNamabranch(nama);
		return branchDAO.editBranch(branch);
	}
	
	@GetMapping("/changebranchalamat")
	public boolean changeBranchalamat(
			@RequestParam("id") String id,
			@RequestParam("alamat") String alamat) {

		Branch branch = branchDAO.getBranchId(id);
		branch.setAlamat(alamat);
		return branchDAO.editBranch(branch);
	}
	
	@GetMapping("/branchname")
	public List<String> getAllBranchName() {
		StringBuilder builder;
		List<String> hasil = new ArrayList<>();
		List<Branch> listBranch = entityManagerFactory.createEntityManager().createQuery("from Branch where isactive = 1").getResultList();
		
		for (Branch branch : listBranch) {
			builder = new StringBuilder();
			builder.append(branch.getNamabranch());
			hasil.add(builder.toString());
		}
		
		return hasil;
	}
	
	@GetMapping("/branchid")
	public List<String> getAllBranchId() {
		StringBuilder builder;
		List<String> hasil = new ArrayList<>();
		List<Branch> listBranch = entityManagerFactory.createEntityManager().createQuery("from Branch where isactive = 1").getResultList();
		
		for (Branch branch : listBranch) {
			builder = new StringBuilder();
			builder.append(branch.getIdbranch());
			hasil.add(builder.toString());
		}
		
		return hasil;
	}
	
	@GetMapping("/deleteBranch")
	public boolean deleteBranch(@RequestParam("idbr") String id) {
		Branch branch = branchDAO.getBranchId(id);
		branch.setIsactive(0);
		return branchDAO.editBranch(branch);
	}
	
//	@GetMapping("/changebranchkode")
//	public boolean changeBranchkode(
//			@RequestParam("id") Integer id,
//			@RequestParam("kode") String kdbranch) {
//
//		Branch branch = branchDAO.getBranchId(id);
//		branch.set(kdbranch);
//		return branchDAO.editBranch(branch);
//	}
	
	
}
