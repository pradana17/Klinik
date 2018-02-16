/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klinik.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MuhammadTaufik
 */
@Entity
@Table(name = "appointment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a")
    , @NamedQuery(name = "Appointment.findByIdappointment", query = "SELECT a FROM Appointment a WHERE a.idappointment = :idappointment")
    , @NamedQuery(name = "Appointment.findByDateappointment", query = "SELECT a FROM Appointment a WHERE a.dateappointment = :dateappointment")
    , @NamedQuery(name = "Appointment.findByApprovedby", query = "SELECT a FROM Appointment a WHERE a.approvedby = :approvedby")
    , @NamedQuery(name = "Appointment.findByApproved", query = "SELECT a FROM Appointment a WHERE a.approved = :approved")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDAPPOINTMENT")
    private Integer idappointment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEAPPOINTMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateappointment;
    @Size(max = 50)
    @Column(name = "APPROVEDBY")
    private String approvedby;
    @Column(name = "APPROVED")
    private Integer approved;
    @JoinColumn(name = "USERNUTRITIONIST", referencedColumnName = "USERNUTRITIONIST")
    @ManyToOne(fetch = FetchType.LAZY)
    private Nutritionist usernutritionist;
    @JoinColumn(name = "USERPATIENT", referencedColumnName = "USERPATIENT")
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient userpatient;

    public Appointment() {
    }

    public Appointment(Integer idappointment) {
        this.idappointment = idappointment;
    }

    public Appointment(Integer idappointment, Date dateappointment) {
        this.idappointment = idappointment;
        this.dateappointment = dateappointment;
    }

    public Integer getIdappointment() {
        return idappointment;
    }

    public void setIdappointment(Integer idappointment) {
        this.idappointment = idappointment;
    }

    public Date getDateappointment() {
        return dateappointment;
    }

    public void setDateappointment(Date dateappointment) {
        this.dateappointment = dateappointment;
    }

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public Nutritionist getUsernutritionist() {
        return usernutritionist;
    }

    public void setUsernutritionist(Nutritionist usernutritionist) {
        this.usernutritionist = usernutritionist;
    }

    public Patient getUserpatient() {
        return userpatient;
    }

    public void setUserpatient(Patient userpatient) {
        this.userpatient = userpatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idappointment != null ? idappointment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.idappointment == null && other.idappointment != null) || (this.idappointment != null && !this.idappointment.equals(other.idappointment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.Appointment[ idappointment=" + idappointment + " ]";
    }
    
}
