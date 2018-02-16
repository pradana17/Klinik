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
@Table(name = "nutritionist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nutritionist.findAll", query = "SELECT n FROM Nutritionist n")
    , @NamedQuery(name = "Nutritionist.findByUsernutritionist", query = "SELECT n FROM Nutritionist n WHERE n.usernutritionist = :usernutritionist")
    , @NamedQuery(name = "Nutritionist.findByPassword", query = "SELECT n FROM Nutritionist n WHERE n.password = :password")
    , @NamedQuery(name = "Nutritionist.findByEmail", query = "SELECT n FROM Nutritionist n WHERE n.email = :email")
    , @NamedQuery(name = "Nutritionist.findByFullname", query = "SELECT n FROM Nutritionist n WHERE n.fullname = :fullname")})
public class Nutritionist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERNUTRITIONIST")
    private String usernutritionist;
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
    @JoinColumn(name = "IDBRANCH", referencedColumnName = "IDBRANCH")
    @ManyToOne(fetch = FetchType.LAZY)
    private Branch idbranch;
    @OneToMany(mappedBy = "senderId", fetch = FetchType.LAZY)
    private List<Chat> chatList;
    @OneToMany(mappedBy = "usernutritionist", fetch = FetchType.LAZY)
    private List<Patient> patientList;
    @OneToMany(mappedBy = "usernutritionist", fetch = FetchType.LAZY)
    private List<Appointment> appointmentList;

    public Nutritionist() {
    }

    public Nutritionist(String usernutritionist) {
        this.usernutritionist = usernutritionist;
    }

    public String getUsernutritionist() {
        return usernutritionist;
    }

    public void setUsernutritionist(String usernutritionist) {
        this.usernutritionist = usernutritionist;
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

    public Branch getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(Branch idbranch) {
        this.idbranch = idbranch;
    }

    @XmlTransient
    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    @XmlTransient
    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @XmlTransient
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usernutritionist != null ? usernutritionist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nutritionist)) {
            return false;
        }
        Nutritionist other = (Nutritionist) object;
        if ((this.usernutritionist == null && other.usernutritionist != null) || (this.usernutritionist != null && !this.usernutritionist.equals(other.usernutritionist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "klinik.Nutritionist[ usernutritionist=" + usernutritionist + " ]";
    }
    
}
