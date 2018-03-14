package com.klinik;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.klinik.DAO.NutritionistDAO;
import com.klinik.DAO.PatientDAO;
import com.klinik.model.Admin;
import com.klinik.model.Nutritionist;
import com.klinik.model.Patient;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private EntityManagerFactory factory;
	@Autowired
	private PatientDAO patDAO;
	@Autowired
	private NutritionistDAO nutDAO;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/**").permitAll() //hasAnyRole("ADMIN")
				.antMatchers("/caloriesbible/**").permitAll() // ("NUT")
				.antMatchers("/patient/**").permitAll() // hasAnyRole("PAT")
				.antMatchers("/appointment/**").permitAll()
				.antMatchers("/caloriesbible/**").permitAll()
				.antMatchers("/correctanswer/**").permitAll()
				.antMatchers("/question/**").permitAll()
				.antMatchers("/test/**").permitAll()
				.anyRequest().authenticated()
                .and()
            .formLogin()
            	.defaultSuccessUrl("/default", true)
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
            	.logoutSuccessUrl("/login")
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	List<Patient> listPat = patDAO.getAllPatient();
    	List<Nutritionist> listNut = nutDAO.getAllNutritionist();
    	List<Admin> listAdmin = factory.createEntityManager().createQuery("from Admin").getResultList();
    	
    	for (Patient o : listPat) {
    		auth
            .inMemoryAuthentication()
                .withUser(o.getUserpatient()).password(o.getPassword()).roles("PAT");
    	}
    	
    	for (Nutritionist n : listNut) {
    		auth
            .inMemoryAuthentication()
                .withUser(n.getUsernutritionist()).password(n.getPassword()).roles("NUT");
    	}
    	
    	for (Admin a : listAdmin) {
    		auth
            .inMemoryAuthentication()
                .withUser(a.getUseradmin()).password(a.getPassword()).roles("ADMIN");
    	}
    }
}
