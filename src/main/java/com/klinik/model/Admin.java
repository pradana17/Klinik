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
@Table(name = "admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a")
    , @NamedQuery(name = "Admin.findByUseradmin", query = "SELECT a FROM Admin a WHERE a.useradmin = :useradmin")
    , @NamedQuery(name = "Admin.findByPassword", query = "SELECT a FROM Admin a WHERE a.password = :password")})
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERADMIN")
    private String useradmin;
    @Size(max = 50)
    @Column(name = "PASSWORD")
    private String password;

    public Admin() {
    }

    public Admin(String useradmin) {
        this.useradmin = useradmin;
    }

    public String getUseradmin() {
        return useradmin;
    }

    public void setUseradmin(String useradmin) {
        this.useradmin = useradmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (useradmin != null ? useradmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.useradmin == null && other.useradmin != null) || (this.useradmin != null && !this.useradmin.equals(other.useradmin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "klinik.Admin[ useradmin=" + useradmin + " ]";
    }
    
}
