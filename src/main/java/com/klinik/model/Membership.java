/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klinik.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "membership")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membership.findAll", query = "SELECT m FROM Membership m")
    , @NamedQuery(name = "Membership.findByUserpatient", query = "SELECT m FROM Membership m WHERE m.userpatient = :userpatient")
    , @NamedQuery(name = "Membership.findByDatejoin", query = "SELECT m FROM Membership m WHERE m.datejoin = :datejoin")
    , @NamedQuery(name = "Membership.findByDateexpired", query = "SELECT m FROM Membership m WHERE m.dateexpired = :dateexpired")})
public class Membership implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERPATIENT")
    private String userpatient;
    @Column(name = "DATEJOIN")
    @Temporal(TemporalType.DATE)
    private Date datejoin;
    @Column(name = "DATEEXPIRED")
    @Temporal(TemporalType.DATE)
    private Date dateexpired;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "membership", fetch = FetchType.LAZY)
    private Patient patient;

    public Membership() {
    }

    public Membership(String userpatient) {
        this.userpatient = userpatient;
    }

    public String getUserpatient() {
        return userpatient;
    }

    public void setUserpatient(String userpatient) {
        this.userpatient = userpatient;
    }

    public Date getDatejoin() {
        return datejoin;
    }

    public void setDatejoin(Date datejoin) {
        this.datejoin = datejoin;
    }

    public Date getDateexpired() {
        return dateexpired;
    }

    public void setDateexpired(Date dateexpired) {
        this.dateexpired = dateexpired;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userpatient != null ? userpatient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membership)) {
            return false;
        }
        Membership other = (Membership) object;
        if ((this.userpatient == null && other.userpatient != null) || (this.userpatient != null && !this.userpatient.equals(other.userpatient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.Membership[ userpatient=" + userpatient + " ]";
    }
    
}
