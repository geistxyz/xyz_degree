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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author July Camacho
 */
@Entity
@Table(name = "TTT_ISSUES")
@NamedQueries({
    @NamedQuery(name = "TttIssues.findAll", query = "SELECT t FROM TttIssues t"),
    @NamedQuery(name = "TttIssues.findByTsId", query = "SELECT t FROM TttIssues t WHERE t.tsId = :tsId"),
    @NamedQuery(name = "TttIssues.findByTsIssueid", query = "SELECT t FROM TttIssues t WHERE t.tsIssueid = :tsIssueid"),
    @NamedQuery(name = "TttIssues.findByTsTitle", query = "SELECT t FROM TttIssues t WHERE t.tsTitle = :tsTitle"),
    @NamedQuery(name = "TttIssues.findByTsIssuetype", query = "SELECT t FROM TttIssues t WHERE t.tsIssuetype = :tsIssuetype"),
    @NamedQuery(name = "TttIssues.findByTsClosedate", query = "SELECT t FROM TttIssues t WHERE t.tsClosedate = :tsClosedate"),
    @NamedQuery(name = "TttIssues.findByTsSubmitdate", query = "SELECT t FROM TttIssues t WHERE t.tsSubmitdate = :tsSubmitdate"),
    @NamedQuery(name = "TttIssues.findByTsSubmitter", query = "SELECT t FROM TttIssues t WHERE t.tsSubmitter = :tsSubmitter"),
    @NamedQuery(name = "TttIssues.findByTsActiveinactive", query = "SELECT t FROM TttIssues t WHERE t.tsActiveinactive = :tsActiveinactive"),
    @NamedQuery(name = "TttIssues.findByTsOwner", query = "SELECT t FROM TttIssues t WHERE t.tsOwner = :tsOwner"),
    @NamedQuery(name = "TttIssues.findByTsProjectid", query = "SELECT t FROM TttIssues t WHERE t.tsProjectid = :tsProjectid"),
    @NamedQuery(name = "TttIssues.findByTsState", query = "SELECT t FROM TttIssues t WHERE t.tsState = :tsState"),
    @NamedQuery(name = "TttIssues.findByTsLastmodifieddate", query = "SELECT t FROM TttIssues t WHERE t.tsLastmodifieddate = :tsLastmodifieddate"),
    @NamedQuery(name = "TttIssues.findByTsLastmodifier", query = "SELECT t FROM TttIssues t WHERE t.tsLastmodifier = :tsLastmodifier"),
    @NamedQuery(name = "TttIssues.findByTsLaststatechangedate", query = "SELECT t FROM TttIssues t WHERE t.tsLaststatechangedate = :tsLaststatechangedate"),
    @NamedQuery(name = "TttIssues.findByTsLaststatechanger", query = "SELECT t FROM TttIssues t WHERE t.tsLaststatechanger = :tsLaststatechanger"),
    @NamedQuery(name = "TttIssues.findByTsManager", query = "SELECT t FROM TttIssues t WHERE t.tsManager = :tsManager"),
    @NamedQuery(name = "TttIssues.findByTsActtimetofix", query = "SELECT t FROM TttIssues t WHERE t.tsActtimetofix = :tsActtimetofix"),
    @NamedQuery(name = "TttIssues.findByTsEngineer", query = "SELECT t FROM TttIssues t WHERE t.tsEngineer = :tsEngineer"),
    @NamedQuery(name = "TttIssues.findByTsEsttimetofix", query = "SELECT t FROM TttIssues t WHERE t.tsEsttimetofix = :tsEsttimetofix"),
    @NamedQuery(name = "TttIssues.findByTsHowfound", query = "SELECT t FROM TttIssues t WHERE t.tsHowfound = :tsHowfound"),
    @NamedQuery(name = "TttIssues.findByTsPriority", query = "SELECT t FROM TttIssues t WHERE t.tsPriority = :tsPriority"),
    @NamedQuery(name = "TttIssues.findByTsResolution", query = "SELECT t FROM TttIssues t WHERE t.tsResolution = :tsResolution"),
    @NamedQuery(name = "TttIssues.findByTsSeverity", query = "SELECT t FROM TttIssues t WHERE t.tsSeverity = :tsSeverity"),
    @NamedQuery(name = "TttIssues.findByTsTester", query = "SELECT t FROM TttIssues t WHERE t.tsTester = :tsTester"),
    @NamedQuery(name = "TttIssues.findByTsVersion", query = "SELECT t FROM TttIssues t WHERE t.tsVersion = :tsVersion")})
public class TttIssues implements Serializable,edu.umss.devportal.plugins.teamtrack.model.common.Entity<Integer>{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TS_ID")
    private Integer tsId;
    @Column(name = "TS_ISSUEID")
    private String tsIssueid;
    @Column(name = "TS_TITLE")
    private String tsTitle;
    @Lob
    @Column(name = "TS_DESCRIPTION")
    private String tsDescription;
    @Column(name = "TS_ISSUETYPE")
    private Integer tsIssuetype;
    @Column(name = "TS_CLOSEDATE")
    private Integer tsClosedate;
    @Column(name = "TS_SUBMITDATE")
    private Integer tsSubmitdate;
    @Column(name = "TS_SUBMITTER")
    private Integer tsSubmitter;
    @Column(name = "TS_ACTIVEINACTIVE")
    private Integer tsActiveinactive;
    @Column(name = "TS_OWNER")
    private Integer tsOwner;
    @Column(name = "TS_PROJECTID")
    private Integer tsProjectid;
    @Column(name = "TS_STATE")
    private Integer tsState;
    @Column(name = "TS_LASTMODIFIEDDATE")
    private Integer tsLastmodifieddate;
    @Column(name = "TS_LASTMODIFIER")
    private Integer tsLastmodifier;
    @Column(name = "TS_LASTSTATECHANGEDATE")
    private Integer tsLaststatechangedate;
    @Column(name = "TS_LASTSTATECHANGER")
    private Integer tsLaststatechanger;
    @Column(name = "TS_MANAGER")
    private Integer tsManager;
    @Column(name = "TS_ACTTIMETOFIX")
    private Integer tsActtimetofix;
    @Lob
    @Column(name = "TS_ADDITIONALNOTES")
    private String tsAdditionalnotes;
    @Column(name = "TS_ENGINEER")
    private Integer tsEngineer;
    @Column(name = "TS_ESTTIMETOFIX")
    private Integer tsEsttimetofix;
    @Lob
    @Column(name = "TS_FUNCTIONALAREA")
    private String tsFunctionalarea;
    @Column(name = "TS_HOWFOUND")
    private Integer tsHowfound;
    @Column(name = "TS_PRIORITY")
    private Integer tsPriority;
    @Column(name = "TS_RESOLUTION")
    private Integer tsResolution;
    @Column(name = "TS_SEVERITY")
    private Integer tsSeverity;
    @Column(name = "TS_TESTER")
    private Integer tsTester;
    @Column(name = "TS_VERSION")
    private Integer tsVersion;
    @Lob
    @Column(name = "TS_WORKAROUND")
    private String tsWorkaround;

    public TttIssues() {
    }

    public TttIssues(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }

    public String getTsIssueid() {
        return tsIssueid;
    }

    public void setTsIssueid(String tsIssueid) {
        this.tsIssueid = tsIssueid;
    }

    public String getTsTitle() {
        return tsTitle;
    }

    public void setTsTitle(String tsTitle) {
        this.tsTitle = tsTitle;
    }

    public String getTsDescription() {
        return tsDescription;
    }

    public void setTsDescription(String tsDescription) {
        this.tsDescription = tsDescription;
    }

    public Integer getTsIssuetype() {
        return tsIssuetype;
    }

    public void setTsIssuetype(Integer tsIssuetype) {
        this.tsIssuetype = tsIssuetype;
    }

    public Integer getTsClosedate() {
        return tsClosedate;
    }

    public void setTsClosedate(Integer tsClosedate) {
        this.tsClosedate = tsClosedate;
    }

    public Integer getTsSubmitdate() {
        return tsSubmitdate;
    }

    public void setTsSubmitdate(Integer tsSubmitdate) {
        this.tsSubmitdate = tsSubmitdate;
    }

    public Integer getTsSubmitter() {
        return tsSubmitter;
    }

    public void setTsSubmitter(Integer tsSubmitter) {
        this.tsSubmitter = tsSubmitter;
    }

    public Integer getTsActiveinactive() {
        return tsActiveinactive;
    }

    public void setTsActiveinactive(Integer tsActiveinactive) {
        this.tsActiveinactive = tsActiveinactive;
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

    public Integer getTsState() {
        return tsState;
    }

    public void setTsState(Integer tsState) {
        this.tsState = tsState;
    }

    public Integer getTsLastmodifieddate() {
        return tsLastmodifieddate;
    }

    public void setTsLastmodifieddate(Integer tsLastmodifieddate) {
        this.tsLastmodifieddate = tsLastmodifieddate;
    }

    public Integer getTsLastmodifier() {
        return tsLastmodifier;
    }

    public void setTsLastmodifier(Integer tsLastmodifier) {
        this.tsLastmodifier = tsLastmodifier;
    }

    public Integer getTsLaststatechangedate() {
        return tsLaststatechangedate;
    }

    public void setTsLaststatechangedate(Integer tsLaststatechangedate) {
        this.tsLaststatechangedate = tsLaststatechangedate;
    }

    public Integer getTsLaststatechanger() {
        return tsLaststatechanger;
    }

    public void setTsLaststatechanger(Integer tsLaststatechanger) {
        this.tsLaststatechanger = tsLaststatechanger;
    }

    public Integer getTsManager() {
        return tsManager;
    }

    public void setTsManager(Integer tsManager) {
        this.tsManager = tsManager;
    }

    public Integer getTsActtimetofix() {
        return tsActtimetofix;
    }

    public void setTsActtimetofix(Integer tsActtimetofix) {
        this.tsActtimetofix = tsActtimetofix;
    }

    public String getTsAdditionalnotes() {
        return tsAdditionalnotes;
    }

    public void setTsAdditionalnotes(String tsAdditionalnotes) {
        this.tsAdditionalnotes = tsAdditionalnotes;
    }

    public Integer getTsEngineer() {
        return tsEngineer;
    }

    public void setTsEngineer(Integer tsEngineer) {
        this.tsEngineer = tsEngineer;
    }

    public Integer getTsEsttimetofix() {
        return tsEsttimetofix;
    }

    public void setTsEsttimetofix(Integer tsEsttimetofix) {
        this.tsEsttimetofix = tsEsttimetofix;
    }

    public String getTsFunctionalarea() {
        return tsFunctionalarea;
    }

    public void setTsFunctionalarea(String tsFunctionalarea) {
        this.tsFunctionalarea = tsFunctionalarea;
    }

    public Integer getTsHowfound() {
        return tsHowfound;
    }

    public void setTsHowfound(Integer tsHowfound) {
        this.tsHowfound = tsHowfound;
    }

    public Integer getTsPriority() {
        return tsPriority;
    }

    public void setTsPriority(Integer tsPriority) {
        this.tsPriority = tsPriority;
    }

    public Integer getTsResolution() {
        return tsResolution;
    }

    public void setTsResolution(Integer tsResolution) {
        this.tsResolution = tsResolution;
    }

    public Integer getTsSeverity() {
        return tsSeverity;
    }

    public void setTsSeverity(Integer tsSeverity) {
        this.tsSeverity = tsSeverity;
    }

    public Integer getTsTester() {
        return tsTester;
    }

    public void setTsTester(Integer tsTester) {
        this.tsTester = tsTester;
    }

    public Integer getTsVersion() {
        return tsVersion;
    }

    public void setTsVersion(Integer tsVersion) {
        this.tsVersion = tsVersion;
    }

    public String getTsWorkaround() {
        return tsWorkaround;
    }

    public void setTsWorkaround(String tsWorkaround) {
        this.tsWorkaround = tsWorkaround;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsId != null ? tsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TttIssues)) {
            return false;
        }
        TttIssues other = (TttIssues) object;
        if ((this.tsId == null && other.tsId != null) || (this.tsId != null && !this.tsId.equals(other.tsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.umss.devportal.plugins.teamtrack.model.TttIssues[tsId=" + tsId + "]";
    }

}
