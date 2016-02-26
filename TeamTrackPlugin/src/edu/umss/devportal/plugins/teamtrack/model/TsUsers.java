/*
 *  @(#)TsUsers.java   05-dic-2010
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
@Table(name = "TS_USERS")
@NamedQueries({
    @NamedQuery(name = "TsUsers.findAll", query = "SELECT t FROM TsUsers t"),
    @NamedQuery(name = "TsUsers.findByTsId", query = "SELECT t FROM TsUsers t WHERE t.tsId = :tsId"),
    @NamedQuery(name = "TsUsers.findByTsLoginid", query = "SELECT t FROM TsUsers t WHERE t.tsLoginid = :tsLoginid"),
    @NamedQuery(name = "TsUsers.findByTsPassword", query = "SELECT t FROM TsUsers t WHERE t.tsPassword = :tsPassword"),
    @NamedQuery(name = "TsUsers.findByTsName", query = "SELECT t FROM TsUsers t WHERE t.tsName = :tsName"),
    @NamedQuery(name = "TsUsers.findByTsTelephone", query = "SELECT t FROM TsUsers t WHERE t.tsTelephone = :tsTelephone"),
    @NamedQuery(name = "TsUsers.findByTsEmail", query = "SELECT t FROM TsUsers t WHERE t.tsEmail = :tsEmail"),
    @NamedQuery(name = "TsUsers.findByTsStatus", query = "SELECT t FROM TsUsers t WHERE t.tsStatus = :tsStatus"),
    @NamedQuery(name = "TsUsers.findByTsFieldsmask", query = "SELECT t FROM TsUsers t WHERE t.tsFieldsmask = :tsFieldsmask"),
    @NamedQuery(name = "TsUsers.findByTsNotesmask", query = "SELECT t FROM TsUsers t WHERE t.tsNotesmask = :tsNotesmask"),
    @NamedQuery(name = "TsUsers.findByTsNumnotes", query = "SELECT t FROM TsUsers t WHERE t.tsNumnotes = :tsNumnotes"),
    @NamedQuery(name = "TsUsers.findByTsChgmask", query = "SELECT t FROM TsUsers t WHERE t.tsChgmask = :tsChgmask"),
    @NamedQuery(name = "TsUsers.findByTsNumchgs", query = "SELECT t FROM TsUsers t WHERE t.tsNumchgs = :tsNumchgs"),
    @NamedQuery(name = "TsUsers.findByTsFilemask", query = "SELECT t FROM TsUsers t WHERE t.tsFilemask = :tsFilemask"),
    @NamedQuery(name = "TsUsers.findByTsNumfiles", query = "SELECT t FROM TsUsers t WHERE t.tsNumfiles = :tsNumfiles"),
    @NamedQuery(name = "TsUsers.findByTsBrowsermask", query = "SELECT t FROM TsUsers t WHERE t.tsBrowsermask = :tsBrowsermask"),
    @NamedQuery(name = "TsUsers.findByTsAccesstype", query = "SELECT t FROM TsUsers t WHERE t.tsAccesstype = :tsAccesstype"),
    @NamedQuery(name = "TsUsers.findByTsHomepagerpt", query = "SELECT t FROM TsUsers t WHERE t.tsHomepagerpt = :tsHomepagerpt"),
    @NamedQuery(name = "TsUsers.findByTsDatepreference", query = "SELECT t FROM TsUsers t WHERE t.tsDatepreference = :tsDatepreference"),
    @NamedQuery(name = "TsUsers.findByTsTimepreference", query = "SELECT t FROM TsUsers t WHERE t.tsTimepreference = :tsTimepreference"),
    @NamedQuery(name = "TsUsers.findByTsOtheruser", query = "SELECT t FROM TsUsers t WHERE t.tsOtheruser = :tsOtheruser"),
    @NamedQuery(name = "TsUsers.findByTsLastlogindate", query = "SELECT t FROM TsUsers t WHERE t.tsLastlogindate = :tsLastlogindate"),
    @NamedQuery(name = "TsUsers.findByTsStatechangehistory", query = "SELECT t FROM TsUsers t WHERE t.tsStatechangehistory = :tsStatechangehistory"),
    @NamedQuery(name = "TsUsers.findByTsManageincidentoptions", query = "SELECT t FROM TsUsers t WHERE t.tsManageincidentoptions = :tsManageincidentoptions"),
    @NamedQuery(name = "TsUsers.findByTsLicensing", query = "SELECT t FROM TsUsers t WHERE t.tsLicensing = :tsLicensing"),
    @NamedQuery(name = "TsUsers.findByTsPasswordprivilegeoptions", query = "SELECT t FROM TsUsers t WHERE t.tsPasswordprivilegeoptions = :tsPasswordprivilegeoptions"),
    @NamedQuery(name = "TsUsers.findByTsPreftableid", query = "SELECT t FROM TsUsers t WHERE t.tsPreftableid = :tsPreftableid"),
    @NamedQuery(name = "TsUsers.findByTsPasswordsetdate", query = "SELECT t FROM TsUsers t WHERE t.tsPasswordsetdate = :tsPasswordsetdate"),
    @NamedQuery(name = "TsUsers.findByTsPasswordlengthoption", query = "SELECT t FROM TsUsers t WHERE t.tsPasswordlengthoption = :tsPasswordlengthoption"),
    @NamedQuery(name = "TsUsers.findByTsGeneralmask", query = "SELECT t FROM TsUsers t WHERE t.tsGeneralmask = :tsGeneralmask"),
    @NamedQuery(name = "TsUsers.findByTsContactid", query = "SELECT t FROM TsUsers t WHERE t.tsContactid = :tsContactid"),
    @NamedQuery(name = "TsUsers.findByTsGmtoffset", query = "SELECT t FROM TsUsers t WHERE t.tsGmtoffset = :tsGmtoffset")})
public class TsUsers implements Serializable, edu.umss.devportal.plugins.teamtrack.model.common.Entity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "TS_ID")
    private Integer tsId;

    @Column(name = "TS_LOGINID")
    private String tsLoginid;

    @Column(name = "TS_PASSWORD")
    private String tsPassword;

    @Column(name = "TS_NAME")
    private String tsName;

    @Column(name = "TS_TELEPHONE")
    private String tsTelephone;

    @Column(name = "TS_EMAIL")
    private String tsEmail;

    @Column(name = "TS_STATUS")
    private Integer tsStatus;

    @Column(name = "TS_FIELDSMASK")
    private Integer tsFieldsmask;

    @Column(name = "TS_NOTESMASK")
    private Integer tsNotesmask;

    @Column(name = "TS_NUMNOTES")
    private Integer tsNumnotes;

    @Column(name = "TS_CHGMASK")
    private Integer tsChgmask;

    @Column(name = "TS_NUMCHGS")
    private Integer tsNumchgs;

    @Column(name = "TS_FILEMASK")
    private Integer tsFilemask;

    @Column(name = "TS_NUMFILES")
    private Integer tsNumfiles;

    @Column(name = "TS_BROWSERMASK")
    private Integer tsBrowsermask;

    @Column(name = "TS_ACCESSTYPE")
    private Integer tsAccesstype;

    @Lob
    @Column(name = "TS_MAILCC")
    private String tsMailcc;

    @Column(name = "TS_HOMEPAGERPT")
    private Integer tsHomepagerpt;

    @Column(name = "TS_DATEPREFERENCE")
    private Integer tsDatepreference;

    @Column(name = "TS_TIMEPREFERENCE")
    private Integer tsTimepreference;

    @Column(name = "TS_OTHERUSER")
    private Integer tsOtheruser;

    @Lob
    @Column(name = "TS_FOLDERPROFILE")
    private String tsFolderprofile;

    @Column(name = "TS_LASTLOGINDATE")
    private Integer tsLastlogindate;

    @Column(name = "TS_STATECHANGEHISTORY")
    private Integer tsStatechangehistory;

    @Column(name = "TS_MANAGEINCIDENTOPTIONS")
    private Integer tsManageincidentoptions;

    @Column(name = "TS_LICENSING")
    private Integer tsLicensing;

    @Column(name = "TS_PASSWORDPRIVILEGEOPTIONS")
    private Integer tsPasswordprivilegeoptions;

    @Column(name = "TS_PREFTABLEID")
    private Integer tsPreftableid;

    @Column(name = "TS_PASSWORDSETDATE")
    private Integer tsPasswordsetdate;

    @Column(name = "TS_PASSWORDLENGTHOPTION")
    private Integer tsPasswordlengthoption;

    @Column(name = "TS_GENERALMASK")
    private Integer tsGeneralmask;

    @Lob
    @Column(name = "TS_MEMO")
    private String tsMemo;

    @Column(name = "TS_CONTACTID")
    private Integer tsContactid;

    @Column(name = "TS_GMTOFFSET")
    private Integer tsGmtoffset;

    public TsUsers() {
    }

    public TsUsers(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }

    public String getTsLoginid() {
        return tsLoginid;
    }

    public void setTsLoginid(String tsLoginid) {
        this.tsLoginid = tsLoginid;
    }

    public String getTsPassword() {
        return tsPassword;
    }

    public void setTsPassword(String tsPassword) {
        this.tsPassword = tsPassword;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public String getTsTelephone() {
        return tsTelephone;
    }

    public void setTsTelephone(String tsTelephone) {
        this.tsTelephone = tsTelephone;
    }

    public String getTsEmail() {
        return tsEmail;
    }

    public void setTsEmail(String tsEmail) {
        this.tsEmail = tsEmail;
    }

    public Integer getTsStatus() {
        return tsStatus;
    }

    public void setTsStatus(Integer tsStatus) {
        this.tsStatus = tsStatus;
    }

    public Integer getTsFieldsmask() {
        return tsFieldsmask;
    }

    public void setTsFieldsmask(Integer tsFieldsmask) {
        this.tsFieldsmask = tsFieldsmask;
    }

    public Integer getTsNotesmask() {
        return tsNotesmask;
    }

    public void setTsNotesmask(Integer tsNotesmask) {
        this.tsNotesmask = tsNotesmask;
    }

    public Integer getTsNumnotes() {
        return tsNumnotes;
    }

    public void setTsNumnotes(Integer tsNumnotes) {
        this.tsNumnotes = tsNumnotes;
    }

    public Integer getTsChgmask() {
        return tsChgmask;
    }

    public void setTsChgmask(Integer tsChgmask) {
        this.tsChgmask = tsChgmask;
    }

    public Integer getTsNumchgs() {
        return tsNumchgs;
    }

    public void setTsNumchgs(Integer tsNumchgs) {
        this.tsNumchgs = tsNumchgs;
    }

    public Integer getTsFilemask() {
        return tsFilemask;
    }

    public void setTsFilemask(Integer tsFilemask) {
        this.tsFilemask = tsFilemask;
    }

    public Integer getTsNumfiles() {
        return tsNumfiles;
    }

    public void setTsNumfiles(Integer tsNumfiles) {
        this.tsNumfiles = tsNumfiles;
    }

    public Integer getTsBrowsermask() {
        return tsBrowsermask;
    }

    public void setTsBrowsermask(Integer tsBrowsermask) {
        this.tsBrowsermask = tsBrowsermask;
    }

    public Integer getTsAccesstype() {
        return tsAccesstype;
    }

    public void setTsAccesstype(Integer tsAccesstype) {
        this.tsAccesstype = tsAccesstype;
    }

    public String getTsMailcc() {
        return tsMailcc;
    }

    public void setTsMailcc(String tsMailcc) {
        this.tsMailcc = tsMailcc;
    }

    public Integer getTsHomepagerpt() {
        return tsHomepagerpt;
    }

    public void setTsHomepagerpt(Integer tsHomepagerpt) {
        this.tsHomepagerpt = tsHomepagerpt;
    }

    public Integer getTsDatepreference() {
        return tsDatepreference;
    }

    public void setTsDatepreference(Integer tsDatepreference) {
        this.tsDatepreference = tsDatepreference;
    }

    public Integer getTsTimepreference() {
        return tsTimepreference;
    }

    public void setTsTimepreference(Integer tsTimepreference) {
        this.tsTimepreference = tsTimepreference;
    }

    public Integer getTsOtheruser() {
        return tsOtheruser;
    }

    public void setTsOtheruser(Integer tsOtheruser) {
        this.tsOtheruser = tsOtheruser;
    }

    public String getTsFolderprofile() {
        return tsFolderprofile;
    }

    public void setTsFolderprofile(String tsFolderprofile) {
        this.tsFolderprofile = tsFolderprofile;
    }

    public Integer getTsLastlogindate() {
        return tsLastlogindate;
    }

    public void setTsLastlogindate(Integer tsLastlogindate) {
        this.tsLastlogindate = tsLastlogindate;
    }

    public Integer getTsStatechangehistory() {
        return tsStatechangehistory;
    }

    public void setTsStatechangehistory(Integer tsStatechangehistory) {
        this.tsStatechangehistory = tsStatechangehistory;
    }

    public Integer getTsManageincidentoptions() {
        return tsManageincidentoptions;
    }

    public void setTsManageincidentoptions(Integer tsManageincidentoptions) {
        this.tsManageincidentoptions = tsManageincidentoptions;
    }

    public Integer getTsLicensing() {
        return tsLicensing;
    }

    public void setTsLicensing(Integer tsLicensing) {
        this.tsLicensing = tsLicensing;
    }

    public Integer getTsPasswordprivilegeoptions() {
        return tsPasswordprivilegeoptions;
    }

    public void setTsPasswordprivilegeoptions(Integer tsPasswordprivilegeoptions) {
        this.tsPasswordprivilegeoptions = tsPasswordprivilegeoptions;
    }

    public Integer getTsPreftableid() {
        return tsPreftableid;
    }

    public void setTsPreftableid(Integer tsPreftableid) {
        this.tsPreftableid = tsPreftableid;
    }

    public Integer getTsPasswordsetdate() {
        return tsPasswordsetdate;
    }

    public void setTsPasswordsetdate(Integer tsPasswordsetdate) {
        this.tsPasswordsetdate = tsPasswordsetdate;
    }

    public Integer getTsPasswordlengthoption() {
        return tsPasswordlengthoption;
    }

    public void setTsPasswordlengthoption(Integer tsPasswordlengthoption) {
        this.tsPasswordlengthoption = tsPasswordlengthoption;
    }

    public Integer getTsGeneralmask() {
        return tsGeneralmask;
    }

    public void setTsGeneralmask(Integer tsGeneralmask) {
        this.tsGeneralmask = tsGeneralmask;
    }

    public String getTsMemo() {
        return tsMemo;
    }

    public void setTsMemo(String tsMemo) {
        this.tsMemo = tsMemo;
    }

    public Integer getTsContactid() {
        return tsContactid;
    }

    public void setTsContactid(Integer tsContactid) {
        this.tsContactid = tsContactid;
    }

    public Integer getTsGmtoffset() {
        return tsGmtoffset;
    }

    public void setTsGmtoffset(Integer tsGmtoffset) {
        this.tsGmtoffset = tsGmtoffset;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsId != null ? tsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TsUsers)) {
            return false;
        }
        TsUsers other = (TsUsers) object;
        if ((this.tsId == null && other.tsId != null) || (this.tsId != null && !this.tsId.equals(other.tsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.umss.devportal.plugins.teamtrack.model.TsUsers[tsId=" + tsId + "]";
    }
}
