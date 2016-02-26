/*
 *  @(#)TsProjects.java   05-dic-2010
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
@Table(name = "TS_PROJECTS")
@NamedQueries({
    @NamedQuery(name = "TsProjects.findAll", query = "SELECT t FROM TsProjects t"),
    @NamedQuery(name = "TsProjects.findByTsId", query = "SELECT t FROM TsProjects t WHERE t.tsId = :tsId"),
    @NamedQuery(name = "TsProjects.findByTsName", query = "SELECT t FROM TsProjects t WHERE t.tsName = :tsName"),
    @NamedQuery(name = "TsProjects.findByTsParentid", query = "SELECT t FROM TsProjects t WHERE t.tsParentid = :tsParentid"),
    @NamedQuery(name = "TsProjects.findByTsAllowchgreqs", query = "SELECT t FROM TsProjects t WHERE t.tsAllowchgreqs = :tsAllowchgreqs"),
    @NamedQuery(name = "TsProjects.findByTsUseparent", query = "SELECT t FROM TsProjects t WHERE t.tsUseparent = :tsUseparent"),
    @NamedQuery(name = "TsProjects.findByTsPrefix", query = "SELECT t FROM TsProjects t WHERE t.tsPrefix = :tsPrefix"),
    @NamedQuery(name = "TsProjects.findByTsUseparentseq", query = "SELECT t FROM TsProjects t WHERE t.tsUseparentseq = :tsUseparentseq"),
    @NamedQuery(name = "TsProjects.findByTsLastid", query = "SELECT t FROM TsProjects t WHERE t.tsLastid = :tsLastid"),
    @NamedQuery(name = "TsProjects.findByTsZerofillto", query = "SELECT t FROM TsProjects t WHERE t.tsZerofillto = :tsZerofillto"),
    @NamedQuery(name = "TsProjects.findByTsSequence", query = "SELECT t FROM TsProjects t WHERE t.tsSequence = :tsSequence"),
    @NamedQuery(name = "TsProjects.findByTsWorkflowid", query = "SELECT t FROM TsProjects t WHERE t.tsWorkflowid = :tsWorkflowid"),
    @NamedQuery(name = "TsProjects.findByTsUseparentworkflow", query = "SELECT t FROM TsProjects t WHERE t.tsUseparentworkflow = :tsUseparentworkflow"),
    @NamedQuery(name = "TsProjects.findMaxTsProjectSequence", query = "SELECT max(p.tsSequence) FROM TsProjects p")})
    public class TsProjects implements Serializable, edu.umss.devportal.plugins.teamtrack.model.common.Entity<Integer>{

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "TS_ID")
    private Integer tsId;

    @Column(name = "TS_NAME")
    private String tsName;

    @Column(name = "TS_PARENTID")
    private Integer tsParentid;

    @Column(name = "TS_ALLOWCHGREQS")
    private Integer tsAllowchgreqs;

    @Column(name = "TS_USEPARENT")
    private Integer tsUseparent;

    @Column(name = "TS_PREFIX")
    private String tsPrefix;

    @Column(name = "TS_USEPARENTSEQ")
    private Integer tsUseparentseq;

    @Column(name = "TS_LASTID")
    private Integer tsLastid;

    @Column(name = "TS_ZEROFILLTO")
    private Integer tsZerofillto;

    @Column(name = "TS_SEQUENCE")
    private Integer tsSequence;

    @Column(name = "TS_WORKFLOWID")
    private Integer tsWorkflowid;

    @Column(name = "TS_USEPARENTWORKFLOW")
    private Integer tsUseparentworkflow;

    @Lob
    @Column(name = "TS_DESCRIPTION")
    private String tsDescription;

    public TsProjects() {
    }

    public TsProjects(Integer tsId) {
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

    public Integer getTsParentid() {
        return tsParentid;
    }

    public void setTsParentid(Integer tsParentid) {
        this.tsParentid = tsParentid;
    }

    public Integer getTsAllowchgreqs() {
        return tsAllowchgreqs;
    }

    public void setTsAllowchgreqs(Integer tsAllowchgreqs) {
        this.tsAllowchgreqs = tsAllowchgreqs;
    }

    public Integer getTsUseparent() {
        return tsUseparent;
    }

    public void setTsUseparent(Integer tsUseparent) {
        this.tsUseparent = tsUseparent;
    }

    public String getTsPrefix() {
        return tsPrefix;
    }

    public void setTsPrefix(String tsPrefix) {
        this.tsPrefix = tsPrefix;
    }

    public Integer getTsUseparentseq() {
        return tsUseparentseq;
    }

    public void setTsUseparentseq(Integer tsUseparentseq) {
        this.tsUseparentseq = tsUseparentseq;
    }

    public Integer getTsLastid() {
        return tsLastid;
    }

    public void setTsLastid(Integer tsLastid) {
        this.tsLastid = tsLastid;
    }

    public Integer getTsZerofillto() {
        return tsZerofillto;
    }

    public void setTsZerofillto(Integer tsZerofillto) {
        this.tsZerofillto = tsZerofillto;
    }

    public Integer getTsSequence() {
        return tsSequence;
    }

    public void setTsSequence(Integer tsSequence) {
        this.tsSequence = tsSequence;
    }

    public Integer getTsWorkflowid() {
        return tsWorkflowid;
    }

    public void setTsWorkflowid(Integer tsWorkflowid) {
        this.tsWorkflowid = tsWorkflowid;
    }

    public Integer getTsUseparentworkflow() {
        return tsUseparentworkflow;
    }

    public void setTsUseparentworkflow(Integer tsUseparentworkflow) {
        this.tsUseparentworkflow = tsUseparentworkflow;
    }

    public String getTsDescription() {
        return tsDescription;
    }

    public void setTsDescription(String tsDescription) {
        this.tsDescription = tsDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tsId != null ? tsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TsProjects)) {
            return false;
        }
        TsProjects other = (TsProjects) object;
        if ((this.tsId == null && other.tsId != null) || (this.tsId != null && !this.tsId.equals(other.tsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.umss.devportal.plugins.teamtrack.model.TsProjects[tsId=" + tsId + "]";
    }

}
