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
@Table(name = "TS_FIELDS")
@NamedQueries({
    @NamedQuery(name = "TsFields.findAll", query = "SELECT t FROM TsFields t"),
    @NamedQuery(name = "TsFields.findByTsId", query = "SELECT t FROM TsFields t WHERE t.tsId = :tsId"),
    @NamedQuery(name = "TsFields.findByTsTableid", query = "SELECT t FROM TsFields t WHERE t.tsTableid = :tsTableid"),
    @NamedQuery(name = "TsFields.findByTsProjectid", query = "SELECT t FROM TsFields t WHERE t.tsProjectid = :tsProjectid"),
    @NamedQuery(name = "TsFields.findByTsName", query = "SELECT t FROM TsFields t WHERE t.tsName = :tsName"),
    @NamedQuery(name = "TsFields.findByTsDbname", query = "SELECT t FROM TsFields t WHERE t.tsDbname = :tsDbname"),
    @NamedQuery(name = "TsFields.findByTsFldtype", query = "SELECT t FROM TsFields t WHERE t.tsFldtype = :tsFldtype"),
    @NamedQuery(name = "TsFields.findByTsLen", query = "SELECT t FROM TsFields t WHERE t.tsLen = :tsLen"),
    @NamedQuery(name = "TsFields.findByTsAttributes", query = "SELECT t FROM TsFields t WHERE t.tsAttributes = :tsAttributes"),
    @NamedQuery(name = "TsFields.findByTsStatus", query = "SELECT t FROM TsFields t WHERE t.tsStatus = :tsStatus"),
    @NamedQuery(name = "TsFields.findByTsProperty", query = "SELECT t FROM TsFields t WHERE t.tsProperty = :tsProperty"),
    @NamedQuery(name = "TsFields.findByTsDefaultint", query = "SELECT t FROM TsFields t WHERE t.tsDefaultint = :tsDefaultint"),
    @NamedQuery(name = "TsFields.findByTsDefaultreal", query = "SELECT t FROM TsFields t WHERE t.tsDefaultreal = :tsDefaultreal"),
    @NamedQuery(name = "TsFields.findByTsDefaultchar", query = "SELECT t FROM TsFields t WHERE t.tsDefaultchar = :tsDefaultchar"),
    @NamedQuery(name = "TsFields.findByTsAction", query = "SELECT t FROM TsFields t WHERE t.tsAction = :tsAction"),
    @NamedQuery(name = "TsFields.findByTsRequired", query = "SELECT t FROM TsFields t WHERE t.tsRequired = :tsRequired"),
    @NamedQuery(name = "TsFields.findByTsSyscode", query = "SELECT t FROM TsFields t WHERE t.tsSyscode = :tsSyscode"),
    @NamedQuery(name = "TsFields.findByTsLabel1", query = "SELECT t FROM TsFields t WHERE t.tsLabel1 = :tsLabel1"),
    @NamedQuery(name = "TsFields.findByTsLabel2", query = "SELECT t FROM TsFields t WHERE t.tsLabel2 = :tsLabel2"),
    @NamedQuery(name = "TsFields.findByTsOptions", query = "SELECT t FROM TsFields t WHERE t.tsOptions = :tsOptions"),
    @NamedQuery(name = "TsFields.findByTsMasstransedit", query = "SELECT t FROM TsFields t WHERE t.tsMasstransedit = :tsMasstransedit"),
    @NamedQuery(name = "TsFields.findByTsQueryable", query = "SELECT t FROM TsFields t WHERE t.tsQueryable = :tsQueryable"),
    @NamedQuery(name = "TsFields.findByTsDescription", query = "SELECT t FROM TsFields t WHERE t.tsDescription = :tsDescription"),
    @NamedQuery(name = "TsFields.findByTsDisplayprefix", query = "SELECT t FROM TsFields t WHERE t.tsDisplayprefix = :tsDisplayprefix"),
    @NamedQuery(name = "TsFields.findByTsDisplaysuffix", query = "SELECT t FROM TsFields t WHERE t.tsDisplaysuffix = :tsDisplaysuffix"),
    @NamedQuery(name = "TsFields.findByTsMasterid", query = "SELECT t FROM TsFields t WHERE t.tsMasterid = :tsMasterid"),
    @NamedQuery(name = "TsFields.findByTsRelationid", query = "SELECT t FROM TsFields t WHERE t.tsRelationid = :tsRelationid"),
    @NamedQuery(name = "TsFields.findByTsFieldid", query = "SELECT t FROM TsFields t WHERE t.tsFieldid = :tsFieldid")})
public class TsFields implements Serializable, edu.umss.devportal.plugins.teamtrack.model.common.Entity<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TS_ID")
    private Integer tsId;
    @Column(name = "TS_TABLEID")
    private Integer tsTableid;
    @Column(name = "TS_PROJECTID")
    private Integer tsProjectid;
    @Column(name = "TS_NAME")
    private String tsName;
    @Column(name = "TS_DBNAME")
    private String tsDbname;
    @Column(name = "TS_FLDTYPE")
    private Integer tsFldtype;
    @Column(name = "TS_LEN")
    private Integer tsLen;
    @Column(name = "TS_ATTRIBUTES")
    private Integer tsAttributes;
    @Column(name = "TS_STATUS")
    private Integer tsStatus;
    @Column(name = "TS_PROPERTY")
    private Integer tsProperty;
    @Column(name = "TS_DEFAULTINT")
    private Integer tsDefaultint;
    @Column(name = "TS_DEFAULTREAL")
    private Double tsDefaultreal;
    @Column(name = "TS_DEFAULTCHAR")
    private String tsDefaultchar;
    @Column(name = "TS_ACTION")
    private Integer tsAction;
    @Column(name = "TS_REQUIRED")
    private Integer tsRequired;
    @Column(name = "TS_SYSCODE")
    private Integer tsSyscode;
    @Column(name = "TS_LABEL1")
    private String tsLabel1;
    @Column(name = "TS_LABEL2")
    private String tsLabel2;
    @Column(name = "TS_OPTIONS")
    private Integer tsOptions;
    @Column(name = "TS_MASSTRANSEDIT")
    private Integer tsMasstransedit;
    @Column(name = "TS_QUERYABLE")
    private Integer tsQueryable;
    @Column(name = "TS_DESCRIPTION")
    private String tsDescription;
    @Column(name = "TS_DISPLAYPREFIX")
    private String tsDisplayprefix;
    @Column(name = "TS_DISPLAYSUFFIX")
    private String tsDisplaysuffix;
    @Column(name = "TS_MASTERID")
    private Integer tsMasterid;
    @Column(name = "TS_RELATIONID")
    private Integer tsRelationid;
    @Column(name = "TS_FIELDID")
    private Integer tsFieldid;

    public TsFields() {
    }

    public TsFields(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getTsTableid() {
        return tsTableid;
    }

    public void setTsTableid(Integer tsTableid) {
        this.tsTableid = tsTableid;
    }

    public Integer getTsProjectid() {
        return tsProjectid;
    }

    public void setTsProjectid(Integer tsProjectid) {
        this.tsProjectid = tsProjectid;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public String getTsDbname() {
        return tsDbname;
    }

    public void setTsDbname(String tsDbname) {
        this.tsDbname = tsDbname;
    }

    public Integer getTsFldtype() {
        return tsFldtype;
    }

    public void setTsFldtype(Integer tsFldtype) {
        this.tsFldtype = tsFldtype;
    }

    public Integer getTsLen() {
        return tsLen;
    }

    public void setTsLen(Integer tsLen) {
        this.tsLen = tsLen;
    }

    public Integer getTsAttributes() {
        return tsAttributes;
    }

    public void setTsAttributes(Integer tsAttributes) {
        this.tsAttributes = tsAttributes;
    }

    public Integer getTsStatus() {
        return tsStatus;
    }

    public void setTsStatus(Integer tsStatus) {
        this.tsStatus = tsStatus;
    }

    public Integer getTsProperty() {
        return tsProperty;
    }

    public void setTsProperty(Integer tsProperty) {
        this.tsProperty = tsProperty;
    }

    public Integer getTsDefaultint() {
        return tsDefaultint;
    }

    public void setTsDefaultint(Integer tsDefaultint) {
        this.tsDefaultint = tsDefaultint;
    }

    public Double getTsDefaultreal() {
        return tsDefaultreal;
    }

    public void setTsDefaultreal(Double tsDefaultreal) {
        this.tsDefaultreal = tsDefaultreal;
    }

    public String getTsDefaultchar() {
        return tsDefaultchar;
    }

    public void setTsDefaultchar(String tsDefaultchar) {
        this.tsDefaultchar = tsDefaultchar;
    }

    public Integer getTsAction() {
        return tsAction;
    }

    public void setTsAction(Integer tsAction) {
        this.tsAction = tsAction;
    }

    public Integer getTsRequired() {
        return tsRequired;
    }

    public void setTsRequired(Integer tsRequired) {
        this.tsRequired = tsRequired;
    }

    public Integer getTsSyscode() {
        return tsSyscode;
    }

    public void setTsSyscode(Integer tsSyscode) {
        this.tsSyscode = tsSyscode;
    }

    public String getTsLabel1() {
        return tsLabel1;
    }

    public void setTsLabel1(String tsLabel1) {
        this.tsLabel1 = tsLabel1;
    }

    public String getTsLabel2() {
        return tsLabel2;
    }

    public void setTsLabel2(String tsLabel2) {
        this.tsLabel2 = tsLabel2;
    }

    public Integer getTsOptions() {
        return tsOptions;
    }

    public void setTsOptions(Integer tsOptions) {
        this.tsOptions = tsOptions;
    }

    public Integer getTsMasstransedit() {
        return tsMasstransedit;
    }

    public void setTsMasstransedit(Integer tsMasstransedit) {
        this.tsMasstransedit = tsMasstransedit;
    }

    public Integer getTsQueryable() {
        return tsQueryable;
    }

    public void setTsQueryable(Integer tsQueryable) {
        this.tsQueryable = tsQueryable;
    }

    public String getTsDescription() {
        return tsDescription;
    }

    public void setTsDescription(String tsDescription) {
        this.tsDescription = tsDescription;
    }

    public String getTsDisplayprefix() {
        return tsDisplayprefix;
    }

    public void setTsDisplayprefix(String tsDisplayprefix) {
        this.tsDisplayprefix = tsDisplayprefix;
    }

    public String getTsDisplaysuffix() {
        return tsDisplaysuffix;
    }

    public void setTsDisplaysuffix(String tsDisplaysuffix) {
        this.tsDisplaysuffix = tsDisplaysuffix;
    }

    public Integer getTsMasterid() {
        return tsMasterid;
    }

    public void setTsMasterid(Integer tsMasterid) {
        this.tsMasterid = tsMasterid;
    }

    public Integer getTsRelationid() {
        return tsRelationid;
    }

    public void setTsRelationid(Integer tsRelationid) {
        this.tsRelationid = tsRelationid;
    }

    public Integer getTsFieldid() {
        return tsFieldid;
    }

    public void setTsFieldid(Integer tsFieldid) {
        this.tsFieldid = tsFieldid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsId != null ? tsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TsFields)) {
            return false;
        }
        TsFields other = (TsFields) object;
        if ((this.tsId == null && other.tsId != null) || (this.tsId != null && !this.tsId.equals(other.tsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.umss.devportal.plugins.teamtrack.model.TsFields[tsId=" + tsId + "]";
    }

}
