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
			if (appointment == null) {
				em.persist(appointment);
				transaksi.commit();
			}
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
			System.out.println(updateApproved.getUserpatient() + " cc " + updateApproved.getApproved());
			Appointment setApproved = (Appointment) em.find(Appointment.class, updateApproved.getIdappointment());
			setApproved.setUserpatient(updateApproved.getUserpatient());
			setApproved.setUsernutritionist(updateApproved.getUsernutritionist());
			setApproved.setApproved(updateApproved.getApproved());
			setApproved.setApprovedby(updateApproved.getApprovedby());
			setApproved.setDateappointment(updateApproved.getDateappointment());
			transaksi.commit();
		} catch (Exception ex) {
			transaksi.rollback();
			isSucces = false;
		}
		return isSucces;
	}
}
