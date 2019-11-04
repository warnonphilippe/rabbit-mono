package be.civadis.gpdoc.dto;

import java.util.Objects;

/**
 * A TicketConversion.
 */
public class TicketConversionDto {

    private Long id;

    private String sourceRepo;

    private String sourcePath;

    private String sourceId;

    private String sourceMimeType;

    private String sourceExt;

    private String destRepo;

    private String destPath;

    private String destId;

    private String destMimeType;

    private String destDescription;

    //private ZonedDateTime startDate;

    //private ZonedDateTime endDate;

    private Boolean ok;

    private String errorMsg;

    private String sendingApplication;

    public TicketConversionDto() {
    }

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

    public TicketConversionDto sourceRepo(String sourceRepo) {
        this.sourceRepo = sourceRepo;
        return this;
    }

    public void setSourceRepo(String sourceRepo) {
        this.sourceRepo = sourceRepo;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public TicketConversionDto sourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
        return this;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getSourceId() {
        return sourceId;
    }

    public TicketConversionDto sourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceMimeType() {
        return sourceMimeType;
    }

    public TicketConversionDto sourceMimeType(String sourceMimeType) {
        this.sourceMimeType = sourceMimeType;
        return this;
    }

    public void setSourceMimeType(String sourceMimeType) {
        this.sourceMimeType = sourceMimeType;
    }

    public String getSourceExt() {
        return sourceExt;
    }

    public TicketConversionDto sourceExt(String sourceExt) {
        this.sourceExt = sourceExt;
        return this;
    }

    public void setSourceExt(String sourceExt) {
        this.sourceExt = sourceExt;
    }

    public String getDestRepo() {
        return destRepo;
    }

    public TicketConversionDto destRepo(String destRepo) {
        this.destRepo = destRepo;
        return this;
    }

    public void setDestRepo(String destRepo) {
        this.destRepo = destRepo;
    }

    public String getDestPath() {
        return destPath;
    }

    public TicketConversionDto destPath(String destPath) {
        this.destPath = destPath;
        return this;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public String getDestId() {
        return destId;
    }

    public TicketConversionDto destId(String destId) {
        this.destId = destId;
        return this;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getDestMimeType() {
        return destMimeType;
    }

    public TicketConversionDto destMimeType(String destMimeType) {
        this.destMimeType = destMimeType;
        return this;
    }

    public void setDestMimeType(String destMimeType) {
        this.destMimeType = destMimeType;
    }

    public String getDestDescription() {
        return destDescription;
    }

    public TicketConversionDto destDescription(String destDescription) {
        this.destDescription = destDescription;
        return this;
    }

    public void setDestDescription(String destDescription) {
        this.destDescription = destDescription;
    }

    public Boolean isOk() {
        return ok;
    }

    public TicketConversionDto ok(Boolean ok) {
        this.ok = ok;
        return this;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public TicketConversionDto errorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSendingApplication() {
        return sendingApplication;
    }

    public TicketConversionDto sendingApplication(String sendingApplication) {
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
        TicketConversionDto ticketConversion = (TicketConversionDto) o;
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
            ", ok='" + isOk() + "'" +
            ", errorMsg='" + getErrorMsg() + "'" +
            ", sendingApplication='" + getSendingApplication() + "'" +
            "}";
    }
}
