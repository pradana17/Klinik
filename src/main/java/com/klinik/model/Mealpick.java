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
 * @author dony pradana
 */
@Entity
@Table(name = "mealpick")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mealpick.findAll", query = "SELECT m FROM Mealpick m")
    , @NamedQuery(name = "Mealpick.findByIdpick", query = "SELECT m FROM Mealpick m WHERE m.idpick = :idpick")
    , @NamedQuery(name = "Mealpick.findByPickedby", query = "SELECT m FROM Mealpick m WHERE m.pickedby = :pickedby")
    , @NamedQuery(name = "Mealpick.findByDatepick", query = "SELECT m FROM Mealpick m WHERE m.datepick = :datepick")})
public class Mealpick implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPICK")
    private Integer idpick;
    @Size(max = 50)
    @Column(name = "PICKEDBY")
    private String pickedby;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEPICK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datepick;
    @JoinColumn(name = "USERPATIENT", referencedColumnName = "USERPATIENT")
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient userpatient;
    @JoinColumn(name = "IDMEALPLAN_", referencedColumnName = "IDMEALPLAN_")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mealplan idmealplan;

    public Mealpick() {
    }

    public Mealpick(Integer idpick) {
        this.idpick = idpick;
    }

    public Mealpick(Integer idpick, Date datepick) {
        this.idpick = idpick;
        this.datepick = datepick;
    }

    public Integer getIdpick() {
        return idpick;
    }

    public void setIdpick(Integer idpick) {
        this.idpick = idpick;
    }

    public String getPickedby() {
        return pickedby;
    }

    public void setPickedby(String pickedby) {
        this.pickedby = pickedby;
    }

    public Date getDatepick() {
        return datepick;
    }

    public void setDatepick(Date datepick) {
        this.datepick = datepick;
    }

    public Patient getUserpatient() {
        return userpatient;
    }

    public void setUserpatient(Patient userpatient) {
        this.userpatient = userpatient;
    }

    public Mealplan getIdmealplan() {
        return idmealplan;
    }

    public void setIdmealplan(Mealplan idmealplan) {
        this.idmealplan = idmealplan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpick != null ? idpick.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mealpick)) {
            return false;
        }
        Mealpick other = (Mealpick) object;
        if ((this.idpick == null && other.idpick != null) || (this.idpick != null && !this.idpick.equals(other.idpick))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "klinik.Mealpick[ idpick=" + idpick + " ]";
    }
    
}
