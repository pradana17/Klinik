/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klinik.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MuhammadTaufik
 */
@Entity
@Table(name = "caloriesbible")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caloriesbible.findAll", query = "SELECT c FROM Caloriesbible c")
    , @NamedQuery(name = "Caloriesbible.findByIdcal", query = "SELECT c FROM Caloriesbible c WHERE c.idcal = :idcal")
    , @NamedQuery(name = "Caloriesbible.findByFoodname", query = "SELECT c FROM Caloriesbible c WHERE c.foodname = :foodname")
    , @NamedQuery(name = "Caloriesbible.findByCalorie", query = "SELECT c FROM Caloriesbible c WHERE c.calorie = :calorie")
    , @NamedQuery(name = "Caloriesbible.findByProtein", query = "SELECT c FROM Caloriesbible c WHERE c.protein = :protein")
    , @NamedQuery(name = "Caloriesbible.findByFats", query = "SELECT c FROM Caloriesbible c WHERE c.fats = :fats")
    , @NamedQuery(name = "Caloriesbible.findByCarbhdrt", query = "SELECT c FROM Caloriesbible c WHERE c.carbhdrt = :carbhdrt")
    , @NamedQuery(name = "Caloriesbible.findByCalcium", query = "SELECT c FROM Caloriesbible c WHERE c.calcium = :calcium")
    , @NamedQuery(name = "Caloriesbible.findByPhosphor", query = "SELECT c FROM Caloriesbible c WHERE c.phosphor = :phosphor")
    , @NamedQuery(name = "Caloriesbible.findByIron", query = "SELECT c FROM Caloriesbible c WHERE c.iron = :iron")
    , @NamedQuery(name = "Caloriesbible.findByVita", query = "SELECT c FROM Caloriesbible c WHERE c.vita = :vita")
    , @NamedQuery(name = "Caloriesbible.findByVitb1", query = "SELECT c FROM Caloriesbible c WHERE c.vitb1 = :vitb1")
    , @NamedQuery(name = "Caloriesbible.findByVitc", query = "SELECT c FROM Caloriesbible c WHERE c.vitc = :vitc")
    , @NamedQuery(name = "Caloriesbible.findByAmount", query = "SELECT c FROM Caloriesbible c WHERE c.amount = :amount")
    , @NamedQuery(name = "Caloriesbible.findByFweight", query = "SELECT c FROM Caloriesbible c WHERE c.fweight = :fweight")
    , @NamedQuery(name = "Caloriesbible.findByAdded", query = "SELECT c FROM Caloriesbible c WHERE c.added = :added")
    , @NamedQuery(name = "Caloriesbible.findByIsactive", query = "SELECT c FROM Caloriesbible c WHERE c.isactive = :isactive")})
public class Caloriesbible implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCAL")
    private Integer idcal;
    @Size(max = 50)
    @Column(name = "FOODNAME")
    private String foodname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CALORIE")
    private Float calorie;
    @Column(name = "PROTEIN")
    private Float protein;
    @Column(name = "FATS")
    private Float fats;
    @Column(name = "CARBHDRT")
    private Float carbhdrt;
    @Column(name = "CALCIUM")
    private Float calcium;
    @Column(name = "PHOSPHOR")
    private Float phosphor;
    @Column(name = "IRON")
    private Float iron;
    @Column(name = "VITA")
    private Float vita;
    @Column(name = "VITB1")
    private Float vitb1;
    @Column(name = "VITC")
    private Float vitc;
    @Column(name = "amount")
    private Float amount;
    @Column(name = "FWEIGHT")
    private Integer fweight;
    @Size(max = 10)
    @Column(name = "added")
    private String added;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISACTIVE")
    private int isactive;

    public Caloriesbible() {
    }

    public Caloriesbible(Integer idcal) {
        this.idcal = idcal;
    }

    public Caloriesbible(Integer idcal, int isactive) {
        this.idcal = idcal;
        this.isactive = isactive;
    }

    public Integer getIdcal() {
        return idcal;
    }

    public void setIdcal(Integer idcal) {
        this.idcal = idcal;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public Float getCalorie() {
        return calorie;
    }

    public void setCalorie(Float calorie) {
        this.calorie = calorie;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getFats() {
        return fats;
    }

    public void setFats(Float fats) {
        this.fats = fats;
    }

    public Float getCarbhdrt() {
        return carbhdrt;
    }

    public void setCarbhdrt(Float carbhdrt) {
        this.carbhdrt = carbhdrt;
    }

    public Float getCalcium() {
        return calcium;
    }

    public void setCalcium(Float calcium) {
        this.calcium = calcium;
    }

    public Float getPhosphor() {
        return phosphor;
    }

    public void setPhosphor(Float phosphor) {
        this.phosphor = phosphor;
    }

    public Float getIron() {
        return iron;
    }

    public void setIron(Float iron) {
        this.iron = iron;
    }

    public Float getVita() {
        return vita;
    }

    public void setVita(Float vita) {
        this.vita = vita;
    }

    public Float getVitb1() {
        return vitb1;
    }

    public void setVitb1(Float vitb1) {
        this.vitb1 = vitb1;
    }

    public Float getVitc() {
        return vitc;
    }

    public void setVitc(Float vitc) {
        this.vitc = vitc;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getFweight() {
        return fweight;
    }

    public void setFweight(Integer fweight) {
        this.fweight = fweight;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcal != null ? idcal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caloriesbible)) {
            return false;
        }
        Caloriesbible other = (Caloriesbible) object;
        if ((this.idcal == null && other.idcal != null) || (this.idcal != null && !this.idcal.equals(other.idcal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.Caloriesbible[ idcal=" + idcal + " ]";
    }
    
}
