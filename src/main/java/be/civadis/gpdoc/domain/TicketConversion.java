package be.civadis.gpdoc.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A TicketConversion.
 */
@Entity
@Table(name = "ticket_conversion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TicketConversion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_repo")
    private String sourceRepo;

    @Column(name = "source_path")
    private String sourcePath;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "source_mime_type")
    private String sourceMimeType;

    @Column(name = "source_ext")
    private String sourceExt;

    @Column(name = "dest_repo")
    private String destRepo;

    @Column(name = "dest_path")
    private String destPath;

    @Column(name = "dest_id")
    private String destId;

    @Column(name = "dest_mime_type")
    private String destMimeType;

    @Column(name = "dest_description")
    private String destDescription;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Column(name = "ok")
    private Boolean ok;

    @Column(name = "error_msg")
    private String errorMsg;

    @Column(name = "sending_application")
    private String sendingApplication;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceRepo() {
        return sourceRepo;
    }

    public TicketConversion sourceRepo(String sourceRepo) {
        this.sourceRepo = sourceRepo;
        return this;
    }

    public void setSourceRepo(String sourceRepo) {
        this.sourceRepo = sourceRepo;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public TicketConversion sourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
        return this;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getSourceId() {
        return sourceId;
    }

    public TicketConversion sourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceMimeType() {
        return sourceMimeType;
    }

    public TicketConversion sourceMimeType(String sourceMimeType) {
        this.sourceMimeType = sourceMimeType;
        return this;
    }

    public void setSourceMimeType(String sourceMimeType) {
        this.sourceMimeType = sourceMimeType;
    }

    public String getSourceExt() {
        return sourceExt;
    }

    public TicketConversion sourceExt(String sourceExt) {
        this.sourceExt = sourceExt;
        return this;
    }

    public void setSourceExt(String sourceExt) {
        this.sourceExt = sourceExt;
    }

    public String getDestRepo() {
        return destRepo;
    }

    public TicketConversion destRepo(String destRepo) {
        this.destRepo = destRepo;
        return this;
    }

    public void setDestRepo(String destRepo) {
        this.destRepo = destRepo;
    }

    public String getDestPath() {
        return destPath;
    }

    public TicketConversion destPath(String destPath) {
        this.destPath = destPath;
        return this;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public String getDestId() {
        return destId;
    }

    public TicketConversion destId(String destId) {
        this.destId = destId;
        return this;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getDestMimeType() {
        return destMimeType;
    }

    public TicketConversion destMimeType(String destMimeType) {
        this.destMimeType = destMimeType;
        return this;
    }

    public void setDestMimeType(String destMimeType) {
        this.destMimeType = destMimeType;
    }

    public String getDestDescription() {
        return destDescription;
    }

    public TicketConversion destDescription(String destDescription) {
        this.destDescription = destDescription;
        return this;
    }

    public void setDestDescription(String destDescription) {
        this.destDescription = destDescription;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public TicketConversion startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public TicketConversion endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean isOk() {
        return ok;
    }

    public TicketConversion ok(Boolean ok) {
        this.ok = ok;
        return this;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public TicketConversion errorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSendingApplication() {
        return sendingApplication;
    }

    public TicketConversion sendingApplication(String sendingApplication) {
        this.sendingApplication = sendingApplication;
        return this;
    }

    public void setSendingApplication(String sendingApplication) {
        this.sendingApplication = sendingApplication;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TicketConversion ticketConversion = (TicketConversion) o;
        if (ticketConversion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ticketConversion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TicketConversion{" +
            "id=" + getId() +
            ", sourceRepo='" + getSourceRepo() + "'" +
            ", sourcePath='" + getSourcePath() + "'" +
            ", sourceId='" + getSourceId() + "'" +
            ", sourceMimeType='" + getSourceMimeType() + "'" +
            ", sourceExt='" + getSourceExt() + "'" +
            ", destRepo='" + getDestRepo() + "'" +
            ", destPath='" + getDestPath() + "'" +
            ", destId='" + getDestId() + "'" +
            ", destMimeType='" + getDestMimeType() + "'" +
            ", destDescription='" + getDestDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", ok='" + isOk() + "'" +
            ", errorMsg='" + getErrorMsg() + "'" +
            ", sendingApplication='" + getSendingApplication() + "'" +
            "}";
    }
}
