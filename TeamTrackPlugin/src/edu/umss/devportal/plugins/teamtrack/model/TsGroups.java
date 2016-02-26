/*
 *  @(#)TsGroups.java   05-dic-2010
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
@Table(name = "TS_GROUPS")
@NamedQueries({
    @NamedQuery(name = "TsGroups.findAll", query = "SELECT t FROM TsGroups t"),
    @NamedQuery(name = "TsGroups.findByTsId", query = "SELECT t FROM TsGroups t WHERE t.tsId = :tsId"),
    @NamedQuery(name = "TsGroups.findByTsName", query = "SELECT t FROM TsGroups t WHERE t.tsName = :tsName"),
    @NamedQuery(name = "TsGroups.findByTsStatus", query = "SELECT t FROM TsGroups t WHERE t.tsStatus = :tsStatus"),
    @NamedQuery(name = "TsGroups.findByTsAccesstype", query = "SELECT t FROM TsGroups t WHERE t.tsAccesstype = :tsAccesstype"),
    @NamedQuery(name = "TsGroups.findByTsType", query = "SELECT t FROM TsGroups t WHERE t.tsType = :tsType")})
public class TsGroups implements Serializable, edu.umss.devportal.plugins.teamtrack.model.common.Entity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "TS_ID")
    private Integer tsId;

    @Column(name = "TS_NAME")
    private String tsName;

    @Column(name = "TS_STATUS")
    private Integer tsStatus;

    @Column(name = "TS_ACCESSTYPE")
    private Integer tsAccesstype;

    @Column(name = "TS_TYPE")
    private Integer tsType;

    public TsGroups() {
    }

    public TsGroups(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public Integer getTsStatus() {
        return tsStatus;
    }

    public void setTsStatus(Integer tsStatus) {
        this.tsStatus = tsStatus;
    }

    public Integer getTsAccesstype() {
        return tsAccesstype;
    }

    public void setTsAccesstype(Integer tsAccesstype) {
        this.tsAccesstype = tsAccesstype;
    }

    public Integer getTsType() {
        return tsType;
    }

    public void setTsType(Integer tsType) {
        this.tsType = tsType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsId != null ? tsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TsGroups)) {
            return false;
        }
        TsGroups other = (TsGroups) object;
        if ((this.tsId == null && other.tsId != null) || (this.tsId != null && !this.tsId.equals(other.tsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.umss.devportal.plugins.teamtrack.model.TsGroups[tsId=" + tsId + "]";
    }

}
