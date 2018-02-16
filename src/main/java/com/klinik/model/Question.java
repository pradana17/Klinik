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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dony pradana
 */
@Entity
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
    , @NamedQuery(name = "Question.findByQuestionid", query = "SELECT q FROM Question q WHERE q.questionid = :questionid")
    , @NamedQuery(name = "Question.findByQuestions", query = "SELECT q FROM Question q WHERE q.questions = :questions")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUESTIONID")
    private Integer questionid;
    @Size(max = 100)
    @Column(name = "QUESTIONS")
    private String questions;

    public Question() {
    }

    public Question(Integer questionid) {
        this.questionid = questionid;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionid != null ? questionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.questionid == null && other.questionid != null) || (this.questionid != null && !this.questionid.equals(other.questionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "klinik.Question[ questionid=" + questionid + " ]";
    }
    
}
