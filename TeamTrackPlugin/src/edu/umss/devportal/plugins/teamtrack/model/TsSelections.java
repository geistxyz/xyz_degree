/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 * @author July Camacho
 */
@Entity
@Table(name = "TS_SELECTIONS")
@NamedQueries({
    @NamedQuery(name = "TsSelections.findAll", query = "SELECT t FROM TsSelections t"),
    @NamedQuery(name = "TsSelections.findByTsId", query = "SELECT t FROM TsSelections t WHERE t.tsId = :tsId"),
    @NamedQuery(name = "TsSelections.findByTsFldid", query = "SELECT t FROM TsSelections t WHERE t.tsFldid = :tsFldid"),
    @NamedQuery(name = "TsSelections.findByTsName", query = "SELECT t FROM TsSelections t WHERE t.tsName = :tsName"),
    @NamedQuery(name = "TsSelections.findByTsValue", query = "SELECT t FROM TsSelections t WHERE t.tsValue = :tsValue"),
    @NamedQuery(name = "TsSelections.findByTsStatus", query = "SELECT t FROM TsSelections t WHERE t.tsStatus = :tsStatus"),
    @NamedQuery(name = "TsSelections.findByTsUserid", query = "SELECT t FROM TsSelections t WHERE t.tsUserid = :tsUserid"),
    @NamedQuery(name = "TsSelections.findByTsGroupid", query = "SELECT t FROM TsSelections t WHERE t.tsGroupid = :tsGroupid"),
    @NamedQuery(name = "TsSelections.findByTsPrefix", query = "SELECT t FROM TsSelections t WHERE t.tsPrefix = :tsPrefix"),
    @NamedQuery(name = "TsSelections.findByTsOrderindex", query = "SELECT t FROM TsSelections t WHERE t.tsOrderindex = :tsOrderindex"),
    @NamedQuery(name = "TsSelections.findByTsTableid", query = "SELECT t FROM TsSelections t WHERE t.tsTableid = :tsTableid")})
public class TsSelections implements Serializable ,edu.umss.devportal.plugins.teamtrack.model.common.Entity<Integer>{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TS_ID")
    private Integer tsId;
    @Column(name = "TS_FLDID")
    private Integer tsFldid;
    @Column(name = "TS_NAME")
    private String tsName;
    @Column(name = "TS_VALUE")
    private Integer tsValue;
    @Column(name = "TS_STATUS")
    private Integer tsStatus;
    @Column(name = "TS_USERID")
    private Integer tsUserid;
    @Column(name = "TS_GROUPID")
    private Integer tsGroupid;
    @Column(name = "TS_PREFIX")
    private String tsPrefix;
    @Column(name = "TS_ORDERINDEX")
    private Integer tsOrderindex;
    @Column(name = "TS_TABLEID")
    private Integer tsTableid;

    public TsSelections() {
    }

    public TsSelections(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getTsFldid() {
        return tsFldid;
    }

    public void setTsFldid(Integer tsFldid) {
        this.tsFldid = tsFldid;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public Integer getTsValue() {
        return tsValue;
    }

    public void setTsValue(Integer tsValue) {
        this.tsValue = tsValue;
    }

    public Integer getTsStatus() {
        return tsStatus;
    }

    public void setTsStatus(Integer tsStatus) {
        this.tsStatus = tsStatus;
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

    public String getTsPrefix() {
        return tsPrefix;
    }

    public void setTsPrefix(String tsPrefix) {
        this.tsPrefix = tsPrefix;
    }

    public Integer getTsOrderindex() {
        return tsOrderindex;
    }

    public void setTsOrderindex(Integer tsOrderindex) {
        this.tsOrderindex = tsOrderindex;
    }

    public Integer getTsTableid() {
        return tsTableid;
    }

    public void setTsTableid(Integer tsTableid) {
        this.tsTableid = tsTableid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsId != null ? tsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TsSelections)) {
            return false;
        }
        TsSelections other = (TsSelections) object;
        if ((this.tsId == null && other.tsId != null) || (this.tsId != null && !this.tsId.equals(other.tsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.umss.devportal.plugins.teamtrack.model.TsSelections[tsId=" + tsId + "]";
    }

}
