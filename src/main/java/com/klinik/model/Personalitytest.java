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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MuhammadTaufik
 */
@Entity
@Table(name = "personalitytest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personalitytest.findAll", query = "SELECT p FROM Personalitytest p")
    , @NamedQuery(name = "Personalitytest.findByIdpertest", query = "SELECT p FROM Personalitytest p WHERE p.idpertest = :idpertest")
    , @NamedQuery(name = "Personalitytest.findByResult", query = "SELECT p FROM Personalitytest p WHERE p.result = :result")
    , @NamedQuery(name = "Personalitytest.findByDatetest", query = "SELECT p FROM Personalitytest p WHERE p.datetest = :datetest")})
public class Personalitytest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPERTEST")
    private Integer idpertest;
    @Column(name = "RESULT")
    private int result;
    @Column(name = "DATETEST")
    @Temporal(TemporalType.DATE)
    private Date datetest;
    @JoinColumn(name = "USERPATIENT", referencedColumnName = "USERPATIENT")
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient userpatient;

    public Personalitytest() {
    }

    public Personalitytest(Integer idpertest) {
        this.idpertest = idpertest;
    }

    public Integer getIdpertest() {
        return idpertest;
    }

    public void setIdpertest(Integer idpertest) {
        this.idpertest = idpertest;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Date getDatetest() {
        return datetest;
    }

    public void setDatetest(Date datetest) {
        this.datetest = datetest;
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
        hash += (idpertest != null ? idpertest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personalitytest)) {
            return false;
        }
        Personalitytest other = (Personalitytest) object;
        if ((this.idpertest == null && other.idpertest != null) || (this.idpertest != null && !this.idpertest.equals(other.idpertest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.Personalitytest[ idpertest=" + idpertest + " ]";
    }
    
}
