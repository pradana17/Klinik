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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author dony pradana
 */
@Entity
@Table(name = "patient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p")
    , @NamedQuery(name = "Patient.findByUserpatient", query = "SELECT p FROM Patient p WHERE p.userpatient = :userpatient")
    , @NamedQuery(name = "Patient.findByPassword", query = "SELECT p FROM Patient p WHERE p.password = :password")
    , @NamedQuery(name = "Patient.findByEmail", query = "SELECT p FROM Patient p WHERE p.email = :email")
    , @NamedQuery(name = "Patient.findByFullname", query = "SELECT p FROM Patient p WHERE p.fullname = :fullname")
    , @NamedQuery(name = "Patient.findByWeight", query = "SELECT p FROM Patient p WHERE p.weight = :weight")
    , @NamedQuery(name = "Patient.findByHeight", query = "SELECT p FROM Patient p WHERE p.height = :height")})
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERPATIENT")
    private String userpatient;
    @Size(max = 50)
    @Column(name = "PASSWORD")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 50)
    @Column(name = "FULLNAME")
    private String fullname;
    @Column(name = "WEIGHT")
    private Integer weight;
    @Column(name = "HEIGHT")
    private Integer height;
    @OneToMany(mappedBy = "userpatient", fetch = FetchType.LAZY)
    private List<Mealpick> mealpickList;
    @OneToMany(mappedBy = "receiverId", fetch = FetchType.LAZY)
    private List<Chat> chatList;
    @JoinColumn(name = "USERNUTRITIONIST", referencedColumnName = "USERNUTRITIONIST")
    @ManyToOne(fetch = FetchType.LAZY)
    private Nutritionist usernutritionist;
    @JoinColumn(name = "IDBRANCH", referencedColumnName = "IDBRANCH")
    @ManyToOne(fetch = FetchType.LAZY)
    private Branch idbranch;
    @OneToMany(mappedBy = "userpatient", fetch = FetchType.LAZY)
    private List<Appointment> appointmentList;
    @OneToMany(mappedBy = "userpatient", fetch = FetchType.LAZY)
    private List<Personalitytest> personalitytestList;

    public Patient() {
    }

    public Patient(String userpatient) {
        this.userpatient = userpatient;
    }

    public String getUserpatient() {
        return userpatient;
    }

    public void setUserpatient(String userpatient) {
        this.userpatient = userpatient;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @XmlTransient
    public List<Mealpick> getMealpickList() {
        return mealpickList;
    }

    public void setMealpickList(List<Mealpick> mealpickList) {
        this.mealpickList = mealpickList;
    }

    @XmlTransient
    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    public Nutritionist getUsernutritionist() {
        return usernutritionist;
    }

    public void setUsernutritionist(Nutritionist usernutritionist) {
        this.usernutritionist = usernutritionist;
    }

    public Branch getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(Branch idbranch) {
        this.idbranch = idbranch;
    }

    @XmlTransient
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @XmlTransient
    public List<Personalitytest> getPersonalitytestList() {
        return personalitytestList;
    }

    public void setPersonalitytestList(List<Personalitytest> personalitytestList) {
        this.personalitytestList = personalitytestList;
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
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.userpatient == null && other.userpatient != null) || (this.userpatient != null && !this.userpatient.equals(other.userpatient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "klinik.Patient[ userpatient=" + userpatient + " ]";
    }
    
}
