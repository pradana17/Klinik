/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klinik.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "useranswers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Useranswers.findAll", query = "SELECT u FROM Useranswers u")
    , @NamedQuery(name = "Useranswers.findByQuestionid", query = "SELECT u FROM Useranswers u WHERE u.useranswersPK.questionid = :questionid")
    , @NamedQuery(name = "Useranswers.findByUserpatient", query = "SELECT u FROM Useranswers u WHERE u.useranswersPK.userpatient = :userpatient")
    , @NamedQuery(name = "Useranswers.findByChoosenanswerid", query = "SELECT u FROM Useranswers u WHERE u.choosenanswerid = :choosenanswerid")
    , @NamedQuery(name = "Useranswers.findByDatetest", query = "SELECT u FROM Useranswers u WHERE u.datetest = :datetest")
    , @NamedQuery(name = "Useranswers.findByResulttemp", query = "SELECT u FROM Useranswers u WHERE u.resulttemp = :resulttemp")})
public class Useranswers implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UseranswersPK useranswersPK;
    @Column(name = "CHOOSENANSWERID")
    private Integer choosenanswerid;
    @Column(name = "DATETEST")
    @Temporal(TemporalType.DATE)
    private Date datetest;
    @Column(name = "RESULTTEMP")
    private Integer resulttemp;
    @JoinColumn(name = "USERPATIENT", referencedColumnName = "USERPATIENT", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Patient patient;
    @JoinColumn(name = "QUESTIONID", referencedColumnName = "QUESTIONID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Question question;

    public Useranswers() {
    }

    public Useranswers(UseranswersPK useranswersPK) {
        this.useranswersPK = useranswersPK;
    }

    public Useranswers(int questionid, String userpatient) {
        this.useranswersPK = new UseranswersPK(questionid, userpatient);
    }

    public UseranswersPK getUseranswersPK() {
        return useranswersPK;
    }

    public void setUseranswersPK(UseranswersPK useranswersPK) {
        this.useranswersPK = useranswersPK;
    }

    public Integer getChoosenanswerid() {
        return choosenanswerid;
    }

    public void setChoosenanswerid(Integer choosenanswerid) {
        this.choosenanswerid = choosenanswerid;
    }

    public Date getDatetest() {
        return datetest;
    }

    public void setDatetest(Date datetest) {
        this.datetest = datetest;
    }

    public Integer getResulttemp() {
        return resulttemp;
    }

    public void setResulttemp(Integer resulttemp) {
        this.resulttemp = resulttemp;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (useranswersPK != null ? useranswersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useranswers)) {
            return false;
        }
        Useranswers other = (Useranswers) object;
        if ((this.useranswersPK == null && other.useranswersPK != null) || (this.useranswersPK != null && !this.useranswersPK.equals(other.useranswersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.Useranswers[ useranswersPK=" + useranswersPK + " ]";
    }
    
}
