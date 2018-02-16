/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klinik.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author MuhammadTaufik
 */
@Embeddable
public class CorrectanswersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDANSWER")
    private int idanswer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUESTIONID")
    private int questionid;

    public CorrectanswersPK() {
    }

    public CorrectanswersPK(int idanswer, int questionid) {
        this.idanswer = idanswer;
        this.questionid = questionid;
    }

    public int getIdanswer() {
        return idanswer;
    }

    public void setIdanswer(int idanswer) {
        this.idanswer = idanswer;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idanswer;
        hash += (int) questionid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorrectanswersPK)) {
            return false;
        }
        CorrectanswersPK other = (CorrectanswersPK) object;
        if (this.idanswer != other.idanswer) {
            return false;
        }
        if (this.questionid != other.questionid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.CorrectanswersPK[ idanswer=" + idanswer + ", questionid=" + questionid + " ]";
    }
    
}
