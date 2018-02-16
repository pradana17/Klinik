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
@Table(name = "answers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answers.findAll", query = "SELECT a FROM Answers a")
    , @NamedQuery(name = "Answers.findByIdanswer", query = "SELECT a FROM Answers a WHERE a.idanswer = :idanswer")
    , @NamedQuery(name = "Answers.findByAnswer", query = "SELECT a FROM Answers a WHERE a.answer = :answer")})
public class Answers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDANSWER")
    private Integer idanswer;
    @Size(max = 50)
    @Column(name = "ANSWER")
    private String answer;

    public Answers() {
    }

    public Answers(Integer idanswer) {
        this.idanswer = idanswer;
    }

    public Integer getIdanswer() {
        return idanswer;
    }

    public void setIdanswer(Integer idanswer) {
        this.idanswer = idanswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idanswer != null ? idanswer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answers)) {
            return false;
        }
        Answers other = (Answers) object;
        if ((this.idanswer == null && other.idanswer != null) || (this.idanswer != null && !this.idanswer.equals(other.idanswer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "klinik.Answers[ idanswer=" + idanswer + " ]";
    }
    
}
