package com.klinik.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinik.model.Appointment;
import com.klinik.model.Nutritionist;


@Service
public class AppointmentDAO {

	private static final Logger Log = LoggerFactory.getLogger(AppointmentDAO.class);

	@Autowired
	private EntityManagerFactory factory;

	public List<Appointment> getAllAppointments() {
		return factory.createEntityManager().createQuery("from Appointment").getResultList();
	}

	public Appointment getAppointmentID(int Id) {
		return (Appointment) factory.createEntityManager().createQuery("from Appointment where IDAPPOINTMENT = " + Id)
				.getSingleResult();
	}

	public boolean addAppointment(Appointment appointment) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSucces = true;
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			appointment.setApprovedby("admin");		
			appointment.setApproved(0);	
			System.out.println("aaaa");
			System.out.println(appointment.getApprovedby());
			System.out.println(appointment.getApproved());
			System.out.println(appointment.getDateappointment());
			System.out.println(appointment.getUsernutritionist().getUsernutritionist());
			em.persist(appointment);
			transaksi.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			transaksi.rollback();
			isSucces = false;
		}
		return isSucces;
	}

	public boolean ValidateAppointment(Appointment updateApproved) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaksi = null;
		boolean isSucces = true;
		System.out.println("yes");
		try {
			transaksi = em.getTransaction();
			transaksi.begin();
			System.out.println(updateApproved.getUserpatient()+" cc "+updateApproved.getApproved());
			Appointment setApproved = (Appointment) em.find(Appointment.class, updateApproved.getIdappointment());
			setApproved.setUserpatient(updateApproved.getUserpatient());
			setApproved.setUsernutritionist(updateApproved.getUsernutritionist());
			setApproved.setApproved(updateApproved.getApproved());
			setApproved.setApprovedby(updateApproved.getApprovedby());
			setApproved.setDateappointment(updateApproved.getDateappointment());
			System.out.println("nanana");
			System.out.println(setApproved.getUsernutritionist().getUsernutritionist());
			System.out.println(setApproved.getUserpatient().getUserpatient());
			System.out.println(setApproved.getApprovedby());
			System.out.println(setApproved.getApproved());
			System.out.println(setApproved.getDateappointment());
			transaksi.commit();
		} catch (Exception ex) {
			transaksi.rollback();
			isSucces = false;
		}
		return isSucces;
	}
}
