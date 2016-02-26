/*
 *  @(#)TsLastids.java   05-dic-2010
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
@Table(name = "TS_LASTIDS")
@NamedQueries({
    @NamedQuery(name = "TsLastids.findAll", query = "SELECT t FROM TsLastids t"),
    @NamedQuery(name = "TsLastids.findByTsId", query = "SELECT t FROM TsLastids t WHERE t.tsId = :tsId"),
    @NamedQuery(name = "TsLastids.findByTsName", query = "SELECT t FROM TsLastids t WHERE t.tsName = :tsName"),
    @NamedQuery(name = "TsLastids.findByTsTableid", query = "SELECT t FROM TsLastids t WHERE t.tsTableid = :tsTableid"),
    @NamedQuery(name = "TsLastids.findByTsLastid", query = "SELECT t FROM TsLastids t WHERE t.tsLastid = :tsLastid")})
public class TsLastids implements Serializable, edu.umss.devportal.plugins.teamtrack.model.common.Entity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "TS_ID")
    private Integer tsId;

    @Basic(optional = false)
    @Column(name = "TS_NAME")
    private String tsName;

    @Basic(optional = false)
    @Column(name = "TS_TABLEID")
    private int tsTableid;

    @Column(name = "TS_LASTID")
    private Integer tsLastid;

    public TsLastids() {
    }

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }

    public TsLastids(Integer tsId) {
        this.tsId = tsId;
    }

    public TsLastids(Integer tsId, String tsName, int tsTableid) {
        this.tsId = tsId;
        this.tsName = tsName;
        this.tsTableid = tsTableid;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public int getTsTableid() {
        return tsTableid;
    }

    public void setTsTableid(int tsTableid) {
        this.tsTableid = tsTableid;
    }

    public Integer getTsLastid() {
        return tsLastid;
    }

    public void setTsLastid(Integer tsLastid) {
        this.tsLastid = tsLastid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsId != null ? tsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TsLastids)) {
            return false;
        }
        TsLastids other = (TsLastids) object;
        if ((this.tsId == null && other.tsId != null) || (this.tsId != null && !this.tsId.equals(other.tsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.umss.devportal.plugins.teamtrack.model.TsLastids[tsId=" + tsId + "]";
    }

}
