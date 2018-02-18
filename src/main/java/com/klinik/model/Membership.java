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
    , @NamedQuery(name = "Membership.findByIDmember", query = "SELECT m FROM Membership m WHERE m.iDmember = :iDmember")
    , @NamedQuery(name = "Membership.findByDatejoin", query = "SELECT m FROM Membership m WHERE m.datejoin = :datejoin")
    , @NamedQuery(name = "Membership.findByDateexpired", query = "SELECT m FROM Membership m WHERE m.dateexpired = :dateexpired")})
public class Membership implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDmember")
    private Integer iDmember;
    @Column(name = "DATEJOIN")
    @Temporal(TemporalType.DATE)
    private Date datejoin;
    @Column(name = "DATEEXPIRED")
    @Temporal(TemporalType.DATE)
    private Date dateexpired;
    @JoinColumn(name = "USERPATIENT", referencedColumnName = "USERPATIENT")
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient userpatient;

    public Membership() {
    }

    public Membership(Integer iDmember) {
        this.iDmember = iDmember;
    }

    public Integer getIDmember() {
        return iDmember;
    }

    public void setIDmember(Integer iDmember) {
        this.iDmember = iDmember;
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

    public Patient getUserpatient() {
        return userpatient;
    }

    public void setUserpatient(Patient userpatient) {
        this.userpatient = userpatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDmember != null ? iDmember.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membership)) {
            return false;
        }
        Membership other = (Membership) object;
        if ((this.iDmember == null && other.iDmember != null) || (this.iDmember != null && !this.iDmember.equals(other.iDmember))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.Membership[ iDmember=" + iDmember + " ]";
    }
    
}
