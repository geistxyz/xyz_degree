/*
 *  @(#)TsMembers.java   05-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alex Arenas
 */
@Entity
@Table(name = "TS_MEMBERS")
@NamedQueries({
    @NamedQuery(name = "TsMembers.findAll", query = "SELECT t FROM TsMembers t"),
    @NamedQuery(name = "TsMembers.findByTsId", query = "SELECT t FROM TsMembers t WHERE t.tsId = :tsId"),
    @NamedQuery(name = "TsMembers.findByTsUserid", query = "SELECT t FROM TsMembers t WHERE t.tsUserid = :tsUserid"),
    @NamedQuery(name = "TsMembers.findByTsGroupid", query = "SELECT t FROM TsMembers t WHERE t.tsGroupid = :tsGroupid")})
public class TsMembers implements Serializable, edu.umss.devportal.plugins.teamtrack.model.common.Entity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "TS_ID")
    private Integer tsId;

    @Column(name = "TS_USERID")
    private Integer tsUserid;

    @Column(name = "TS_GROUPID")
    private Integer tsGroupid;

    public TsMembers() {
    }

    public TsMembers(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getTsUserid() {
        return tsUserid;
    }

    public void setTsUserid(Integer tsUserid) {
        this.tsUserid = tsUserid;
    }

    public Integer getTsGroupid() {
        return tsGroupid;
    }

    public void setTsGroupid(Integer tsGroupid) {
        this.tsGroupid = tsGroupid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsId != null ? tsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TsMembers)) {
            return false;
        }
        TsMembers other = (TsMembers) object;
        if ((this.tsId == null && other.tsId != null) || (this.tsId != null && !this.tsId.equals(other.tsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.umss.devportal.plugins.teamtrack.model.TsMembers[tsId=" + tsId + "]";
    }

}
