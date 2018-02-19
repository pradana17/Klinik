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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MuhammadTaufik
 */
@Entity
@Table(name = "branch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branch b")
    , @NamedQuery(name = "Branch.findByIdbranch", query = "SELECT b FROM Branch b WHERE b.idbranch = :idbranch")
    , @NamedQuery(name = "Branch.findByAlamat", query = "SELECT b FROM Branch b WHERE b.alamat = :alamat")
    , @NamedQuery(name = "Branch.findByNamabranch", query = "SELECT b FROM Branch b WHERE b.namabranch = :namabranch")
    , @NamedQuery(name = "Branch.findByIsactive", query = "SELECT b FROM Branch b WHERE b.isactive = :isactive")})
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "IDBRANCH")
    private String idbranch;
<<<<<<< HEAD
    @Size(max = 80)
=======
<<<<<<< HEAD
    @Size(max = 80)
=======
<<<<<<< HEAD
    @Size(max = 50)
=======
    @Size(max = 80)
>>>>>>> b992fe75a5822bdb2b24ef082bd53e9f80fd626b
>>>>>>> bc297ee392a50fffd562b975fbcf82806528f8af
>>>>>>> 3fbd23c7ae97f3f83a9d6374aef0f3a2d16cd368
    @Column(name = "ALAMAT")
    private String alamat;
    @Size(max = 45)
    @Column(name = "NAMABRANCH")
    private String namabranch;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISACTIVE")
    private int isactive;
    @OneToMany(mappedBy = "idbranch", fetch = FetchType.LAZY)
    private List<Nutritionist> nutritionistList;
    @OneToMany(mappedBy = "idbranch", fetch = FetchType.LAZY)
    private List<Patient> patientList;

    public Branch() {
    }

    public Branch(String idbranch) {
        this.idbranch = idbranch;
    }

    public Branch(String idbranch, int isactive) {
        this.idbranch = idbranch;
        this.isactive = isactive;
    }

    public String getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(String idbranch) {
        this.idbranch = idbranch;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamabranch() {
        return namabranch;
    }

    public void setNamabranch(String namabranch) {
        this.namabranch = namabranch;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    @XmlTransient
    public List<Nutritionist> getNutritionistList() {
        return nutritionistList;
    }

    public void setNutritionistList(List<Nutritionist> nutritionistList) {
        this.nutritionistList = nutritionistList;
    }

    @XmlTransient
    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbranch != null ? idbranch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Branch)) {
            return false;
        }
        Branch other = (Branch) object;
        if ((this.idbranch == null && other.idbranch != null) || (this.idbranch != null && !this.idbranch.equals(other.idbranch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.Branch[ idbranch=" + idbranch + " ]";
    }
    
}
