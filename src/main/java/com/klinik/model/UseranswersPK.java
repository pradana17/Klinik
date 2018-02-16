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
import javax.validation.constraints.Size;

/**
 *
 * @author MuhammadTaufik
 */
@Embeddable
public class UseranswersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "QUESTIONID")
    private int questionid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERPATIENT")
    private String userpatient;

    public UseranswersPK() {
    }

    public UseranswersPK(int questionid, String userpatient) {
        this.questionid = questionid;
        this.userpatient = userpatient;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getUserpatient() {
        return userpatient;
    }

    public void setUserpatient(String userpatient) {
        this.userpatient = userpatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) questionid;
        hash += (userpatient != null ? userpatient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UseranswersPK)) {
            return false;
        }
        UseranswersPK other = (UseranswersPK) object;
        if (this.questionid != other.questionid) {
            return false;
        }
        if ((this.userpatient == null && other.userpatient != null) || (this.userpatient != null && !this.userpatient.equals(other.userpatient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.UseranswersPK[ questionid=" + questionid + ", userpatient=" + userpatient + " ]";
    }
    
}
