/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klinik.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MuhammadTaufik
 */
@Entity
@Table(name = "correctanswers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Correctanswers.findAll", query = "SELECT c FROM Correctanswers c")
    , @NamedQuery(name = "Correctanswers.findByIdanswer", query = "SELECT c FROM Correctanswers c WHERE c.correctanswersPK.idanswer = :idanswer")
    , @NamedQuery(name = "Correctanswers.findByQuestionid", query = "SELECT c FROM Correctanswers c WHERE c.correctanswersPK.questionid = :questionid")
    , @NamedQuery(name = "Correctanswers.findByCaloriesneed", query = "SELECT c FROM Correctanswers c WHERE c.caloriesneed = :caloriesneed")})
public class Correctanswers implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CorrectanswersPK correctanswersPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CALORIESNEED")
    private Double caloriesneed;
    @JoinColumn(name = "IDANSWER", referencedColumnName = "IDANSWER", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Answers answers;
    @JoinColumn(name = "QUESTIONID", referencedColumnName = "QUESTIONID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Question question;

    public Correctanswers() {
    }

    public Correctanswers(CorrectanswersPK correctanswersPK) {
        this.correctanswersPK = correctanswersPK;
    }

    public Correctanswers(int idanswer, int questionid) {
        this.correctanswersPK = new CorrectanswersPK(idanswer, questionid);
    }

    public CorrectanswersPK getCorrectanswersPK() {
        return correctanswersPK;
    }

    public void setCorrectanswersPK(CorrectanswersPK correctanswersPK) {
        this.correctanswersPK = correctanswersPK;
    }

    public Double getCaloriesneed() {
        return caloriesneed;
    }

    public void setCaloriesneed(Double caloriesneed) {
        this.caloriesneed = caloriesneed;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
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
        hash += (correctanswersPK != null ? correctanswersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correctanswers)) {
            return false;
        }
        Correctanswers other = (Correctanswers) object;
        if ((this.correctanswersPK == null && other.correctanswersPK != null) || (this.correctanswersPK != null && !this.correctanswersPK.equals(other.correctanswersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.Correctanswers[ correctanswersPK=" + correctanswersPK + " ]";
    }
    
}
