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
import org.springframework.security.web.access.AccessDeniedHandler;

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
	
	 @Autowired
	private AccessDeniedHandler accessDeniedHandler;
	 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            	.antMatchers("/api/**").permitAll()
            	.antMatchers("/webjars/**").permitAll()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/**").permitAll() //hasAnyRole("ADMIN")
				.antMatchers("/caloriesbible/**").permitAll() // ("NUT")
				.antMatchers("/patient/**").permitAll() // hasAnyRole("PAT")
				.antMatchers("/appointment/**").permitAll()
				.antMatchers("/caloriesbible/**").hasAnyRole("NUT")
				.antMatchers("/correctanswer/**").permitAll()
				.antMatchers("/question/**").permitAll()
				.antMatchers("/branch/**").permitAll()
				.antMatchers("/test/**").permitAll()
				.anyRequest().authenticated()
                .and()
            .formLogin()
            	.loginPage("/login")
            	.defaultSuccessUrl("/default", true)                
                .permitAll()
                .and()
            .logout()
            	.logoutSuccessUrl("/login")
                .permitAll()
                .and()
        	.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
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
