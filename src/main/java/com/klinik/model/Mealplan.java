/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klinik.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MuhammadTaufik
 */
@Entity
@Table(name = "mealplan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mealplan.findAll", query = "SELECT m FROM Mealplan m")
    , @NamedQuery(name = "Mealplan.findByIdmealplan", query = "SELECT m FROM Mealplan m WHERE m.idmealplan = :idmealplan")
    , @NamedQuery(name = "Mealplan.findByCalories", query = "SELECT m FROM Mealplan m WHERE m.calories = :calories")
    , @NamedQuery(name = "Mealplan.findByCreatedby", query = "SELECT m FROM Mealplan m WHERE m.createdby = :createdby")
    , @NamedQuery(name = "Mealplan.findByFase", query = "SELECT m FROM Mealplan m WHERE m.fase = :fase")
    , @NamedQuery(name = "Mealplan.findByKodemealplan", query = "SELECT m FROM Mealplan m WHERE m.kodemealplan = :kodemealplan")
    , @NamedQuery(name = "Mealplan.findByFilename", query = "SELECT m FROM Mealplan m WHERE m.filename = :filename")})
public class Mealplan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMEALPLAN_")
    private Integer idmealplan;
    @Column(name = "CALORIES")
    private Integer calories;
    @Lob
    @Column(name = "FILES")
    private byte[] files;
    @Size(max = 50)
    @Column(name = "CREATEDBY")
    private String createdby;
    @Size(max = 10)
    @Column(name = "FASE")
    private String fase;
    @Size(max = 10)
    @Column(name = "KODEMEALPLAN")
    private String kodemealplan;
    @Size(max = 45)
    @Column(name = "FILENAME")
    private String filename;
    @OneToMany(mappedBy = "idmealplan", fetch = FetchType.LAZY)
    private List<Mealpick> mealpickList;

    public Mealplan() {
    }

    public Mealplan(Integer idmealplan) {
        this.idmealplan = idmealplan;
    }

    public Integer getIdmealplan() {
        return idmealplan;
    }

    public void setIdmealplan(Integer idmealplan) {
        this.idmealplan = idmealplan;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public byte[] getFiles() {
        return files;
    }

    public void setFiles(byte[] files) {
        this.files = files;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getKodemealplan() {
        return kodemealplan;
    }

    public void setKodemealplan(String kodemealplan) {
        this.kodemealplan = kodemealplan;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @XmlTransient
    public List<Mealpick> getMealpickList() {
        return mealpickList;
    }

    public void setMealpickList(List<Mealpick> mealpickList) {
        this.mealpickList = mealpickList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmealplan != null ? idmealplan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mealplan)) {
            return false;
        }
        Mealplan other = (Mealplan) object;
        if ((this.idmealplan == null && other.idmealplan != null) || (this.idmealplan != null && !this.idmealplan.equals(other.idmealplan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.Mealplan[ idmealplan=" + idmealplan + " ]";
    }
    
}
