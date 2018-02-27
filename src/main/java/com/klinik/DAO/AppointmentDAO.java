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
<<<<<<< HEAD
=======
<<<<<<< HEAD
			appointment.setApprovedby("admin");
			appointment.setApproved(0);
=======
<<<<<<< HEAD
			appointment.setApprovedby("admin");
=======
>>>>>>> 1907d4124536b850d8fb891a4049e8c7b0c70b79
			appointment.setApprovedby("admin");		
			appointment.setApproved(0);	
>>>>>>> 5a796bf5059a468fc2972b3a033192edf2ee059d
			System.out.println("aaaa");
			System.out.println(appointment.getApprovedby());
			System.out.println(appointment.getApproved());
			System.out.println(appointment.getDateappointment());
			System.out.println(appointment.getUsernutritionist().getUsernutritionist());
<<<<<<< HEAD
=======
<<<<<<< HEAD
			System.out.println(appointment.getUserpatient().getUserpatient());
			appointment.setApproved(0);
			if (appointment == null) {
				em.persist(appointment);
			} else {
				em.merge(appointment);
			}
=======
<<<<<<< HEAD
			System.out.println(appointment.getUserpatient().getUserpatient());
=======
			System.out.println(appointment.getUserpatient().getUserpatient());			
>>>>>>> c9e0820067f92d3af838de39821ae75a7f25ce6f
>>>>>>> 1907d4124536b850d8fb891a4049e8c7b0c70b79
			em.persist(appointment);
>>>>>>> 5a796bf5059a468fc2972b3a033192edf2ee059d
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
