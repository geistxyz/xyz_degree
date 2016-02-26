/*
 *  @(#)TtsProducts.java   05-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alex Arenas
 */
@Entity
@Table(name = "TTS_PRODUCTS")
@NamedQueries({
    @NamedQuery(name = "TtsProducts.findAll", query = "SELECT t FROM TtsProducts t"),
    @NamedQuery(name = "TtsProducts.findByTsId", query = "SELECT t FROM TtsProducts t WHERE t.tsId = :tsId"),
    @NamedQuery(name = "TtsProducts.findByTsName", query = "SELECT t FROM TtsProducts t WHERE t.tsName = :tsName"),
    @NamedQuery(name = "TtsProducts.findByTsModelnum", query = "SELECT t FROM TtsProducts t WHERE t.tsModelnum = :tsModelnum"),
    @NamedQuery(name = "TtsProducts.findByTsVersionnum", query = "SELECT t FROM TtsProducts t WHERE t.tsVersionnum = :tsVersionnum")})
public class TtsProducts implements Serializable, edu.umss.devportal.plugins.teamtrack.model.common.Entity<Integer>{

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "TS_ID")
    private Integer tsId;

    @Column(name = "TS_NAME")
    private String tsName;

    @Lob
    @Column(name = "TS_DESCRIPTION")
    private String tsDescription;

    @Column(name = "TS_MODELNUM")
    private String tsModelnum;

    @Column(name = "TS_VERSIONNUM")
    private String tsVersionnum;

    public TtsProducts() {
    }

    public TtsProducts(Integer tsId) {
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

    public String getTsDescription() {
        return tsDescription;
    }

    public void setTsDescription(String tsDescription) {
        this.tsDescription = tsDescription;
    }

    public String getTsModelnum() {
        return tsModelnum;
    }

    public void setTsModelnum(String tsModelnum) {
        this.tsModelnum = tsModelnum;
    }

    public String getTsVersionnum() {
        return tsVersionnum;
    }

    public void setTsVersionnum(String tsVersionnum) {
        this.tsVersionnum = tsVersionnum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsId != null ? tsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TtsProducts)) {
            return false;
        }
        TtsProducts other = (TtsProducts) object;
        if ((this.tsId == null && other.tsId != null) || (this.tsId != null && !this.tsId.equals(other.tsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.umss.devportal.plugins.teamtrack.model.TtsProducts[tsId=" + tsId + "]";
    }
}
