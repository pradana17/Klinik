/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klinik.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MuhammadTaufik
 */
@Entity
@Table(name = "chat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chat.findAll", query = "SELECT c FROM Chat c")
    , @NamedQuery(name = "Chat.findByIdchat", query = "SELECT c FROM Chat c WHERE c.idchat = :idchat")
    , @NamedQuery(name = "Chat.findBySenderId", query = "SELECT c FROM Chat c WHERE c.senderId = :senderId")
    , @NamedQuery(name = "Chat.findByReceiverId", query = "SELECT c FROM Chat c WHERE c.receiverId = :receiverId")
    , @NamedQuery(name = "Chat.findByDatesending", query = "SELECT c FROM Chat c WHERE c.datesending = :datesending")
    , @NamedQuery(name = "Chat.findByDatereceiver", query = "SELECT c FROM Chat c WHERE c.datereceiver = :datereceiver")})
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCHAT")
    private Integer idchat;
    @Size(max = 50)
    @Column(name = "SENDER_ID")
    private String senderId;
    @Size(max = 50)
    @Column(name = "RECEIVER_ID")
    private String receiverId;
    @Column(name = "DATESENDING")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datesending;
    @Column(name = "DATERECEIVER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datereceiver;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "MESSAGE")
    private String message;

    public Chat() {
    }

    public Chat(Integer idchat) {
        this.idchat = idchat;
    }

    public Integer getIdchat() {
        return idchat;
    }

    public void setIdchat(Integer idchat) {
        this.idchat = idchat;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Date getDatesending() {
        return datesending;
    }

    public void setDatesending(Date datesending) {
        this.datesending = datesending;
    }

    public Date getDatereceiver() {
        return datereceiver;
    }

    public void setDatereceiver(Date datereceiver) {
        this.datereceiver = datereceiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idchat != null ? idchat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chat)) {
            return false;
        }
        Chat other = (Chat) object;
        if ((this.idchat == null && other.idchat != null) || (this.idchat != null && !this.idchat.equals(other.idchat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.klinik.model.Chat[ idchat=" + idchat + " ]";
    }
    
}
