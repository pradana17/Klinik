package com.klinik;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klinik.model.Caloriesbible;



@RestController
public class SearchAPI {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
		
	@GetMapping("/foodlist")
	public List<String> getBible() {
		String data [][]= new String[1000][1000];

		StringBuilder builder;
		List<String> hasil = new ArrayList<>();
		
		List<Caloriesbible> bibleList = entityManagerFactory 
				.createEntityManager()
				.createQuery("from Caloriesbible")
				.getResultList();
		
		for (Caloriesbible bible : bibleList) {
			builder = new StringBuilder();
			builder.append(bible.getFoodname());		
			hasil.add(builder.toString());
		}
		return hasil;
		
	}
}
