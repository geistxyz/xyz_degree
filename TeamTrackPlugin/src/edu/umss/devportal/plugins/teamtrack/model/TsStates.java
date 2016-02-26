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
@Table(name = "TS_STATES")
@NamedQueries({
    @NamedQuery(name = "TsStates.findAll", query = "SELECT t FROM TsStates t"),
    @NamedQuery(name = "TsStates.findByTsId", query = "SELECT t FROM TsStates t WHERE t.tsId = :tsId"),
    @NamedQuery(name = "TsStates.findByTsName", query = "SELECT t FROM TsStates t WHERE t.tsName = :tsName"),
    @NamedQuery(name = "TsStates.findByTsStatus", query = "SELECT t FROM TsStates t WHERE t.tsStatus = :tsStatus"),
    @NamedQuery(name = "TsStates.findByTsOpenclosed", query = "SELECT t FROM TsStates t WHERE t.tsOpenclosed = :tsOpenclosed"),
    @NamedQuery(name = "TsStates.findByTsOwner", query = "SELECT t FROM TsStates t WHERE t.tsOwner = :tsOwner"),
    @NamedQuery(name = "TsStates.findByTsProjectid", query = "SELECT t FROM TsStates t WHERE t.tsProjectid = :tsProjectid"),
    @NamedQuery(name = "TsStates.findByTsTransitionid", query = "SELECT t FROM TsStates t WHERE t.tsTransitionid = :tsTransitionid"),
    @NamedQuery(name = "TsStates.findByTsPrescriptid", query = "SELECT t FROM TsStates t WHERE t.tsPrescriptid = :tsPrescriptid"),
    @NamedQuery(name = "TsStates.findByTsPostscriptid", query = "SELECT t FROM TsStates t WHERE t.tsPostscriptid = :tsPostscriptid")})
public class TsStates implements Serializable,edu.umss.devportal.plugins.teamtrack.model.common.Entity<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TS_ID")
    private Integer tsId;
    @Column(name = "TS_NAME")
    private String tsName;
    @Column(name = "TS_STATUS")
    private Integer tsStatus;
    @Column(name = "TS_OPENCLOSED")
    private Integer tsOpenclosed;
    @Column(name = "TS_OWNER")
    private Integer tsOwner;
    @Column(name = "TS_PROJECTID")
    private Integer tsProjectid;
    @Column(name = "TS_TRANSITIONID")
    private Integer tsTransitionid;
    @Column(name = "TS_PRESCRIPTID")
    private Integer tsPrescriptid;
    @Column(name = "TS_POSTSCRIPTID")
    private Integer tsPostscriptid;

    public TsStates() {
    }

    public TsStates(Integer tsId) {
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

    public Integer getTsOpenclosed() {
        return tsOpenclosed;
    }

    public void setTsOpenclosed(Integer tsOpenclosed) {
        this.tsOpenclosed = tsOpenclosed;
    }

    public Integer getTsOwner() {
        return tsOwner;
    }

    public void setTsOwner(Integer tsOwner) {
        this.tsOwner = tsOwner;
    }

    public Integer getTsProjectid() {
        return tsProjectid;
    }

    public void setTsProjectid(Integer tsProjectid) {
        this.tsProjectid = tsProjectid;
    }

    public Integer getTsTransitionid() {
        return tsTransitionid;
    }

    public void setTsTransitionid(Integer tsTransitionid) {
        this.tsTransitionid = tsTransitionid;
    }

    public Integer getTsPrescriptid() {
        return tsPrescriptid;
    }

    public void setTsPrescriptid(Integer tsPrescriptid) {
        this.tsPrescriptid = tsPrescriptid;
    }

    public Integer getTsPostscriptid() {
        return tsPostscriptid;
    }

    public void setTsPostscriptid(Integer tsPostscriptid) {
        this.tsPostscriptid = tsPostscriptid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsId != null ? tsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TsStates)) {
            return false;
        }
        TsStates other = (TsStates) object;
        if ((this.tsId == null && other.tsId != null) || (this.tsId != null && !this.tsId.equals(other.tsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.umss.devportal.plugins.teamtrack.model.TsStates[tsId=" + tsId + "]";
    }

}
