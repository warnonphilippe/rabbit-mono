package be.civadis.gpdoc.dto;

import java.time.Instant;
import java.util.Objects;

/**
 * TicketEboxDTO
 */
public class TicketEboxDTO {

    private String uuid;
    private String titreFr;
    private String titreNl;
    private String titreDe;
    private String titreEn;

    private String messageType;
    private String eboxMessageId;
    private Instant dateDebut;
    private Instant dateFin;
    private String statut; //TODO utiliser enum
    private Integer essais;
    private Boolean stockage;
    private Boolean conversion;
    private String moteurConversion; //TODO utiliser enum
    private String applicationAppelante;
    private String erreurGed;
    private String erreurEbox;

    public TicketEboxDTO() {
    }


    public TicketEboxDTO(String uuid, String titreFr, String titreNl, String titreDe, String titreEn, String messageType, String eboxMessageId, Instant dateDebut, Instant dateFin, String statut, Integer essais, Boolean stockage, Boolean conversion, String moteurConversion, String applicationAppelante, String erreurGed, String erreurEbox) {
        this.uuid = uuid;
        this.titreFr = titreFr;
        this.titreNl = titreNl;
        this.titreDe = titreDe;
        this.titreEn = titreEn;
        this.messageType = messageType;
        this.eboxMessageId = eboxMessageId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.essais = essais;
        this.stockage = stockage;
        this.conversion = conversion;
        this.moteurConversion = moteurConversion;
        this.applicationAppelante = applicationAppelante;
        this.erreurGed = erreurGed;
        this.erreurEbox = erreurEbox;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitreFr() {
        return this.titreFr;
    }

    public void setTitreFr(String titreFr) {
        this.titreFr = titreFr;
    }

    public String getTitreNl() {
        return this.titreNl;
    }

    public void setTitreNl(String titreNl) {
        this.titreNl = titreNl;
    }

    public String getTitreDe() {
        return this.titreDe;
    }

    public void setTitreDe(String titreDe) {
        this.titreDe = titreDe;
    }

    public String getTitreEn() {
        return this.titreEn;
    }

    public void setTitreEn(String titreEn) {
        this.titreEn = titreEn;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getEboxMessageId() {
        return this.eboxMessageId;
    }

    public void setEboxMessageId(String eboxMessageId) {
        this.eboxMessageId = eboxMessageId;
    }

    public Instant getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Instant getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public String getStatut() {
        return this.statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getEssais() {
        return this.essais;
    }

    public void setEssais(Integer essais) {
        this.essais = essais;
    }

    public Boolean isStockage() {
        return this.stockage;
    }

    public Boolean getStockage() {
        return this.stockage;
    }

    public void setStockage(Boolean stockage) {
        this.stockage = stockage;
    }

    public Boolean isConversion() {
        return this.conversion;
    }

    public Boolean getConversion() {
        return this.conversion;
    }

    public void setConversion(Boolean conversion) {
        this.conversion = conversion;
    }

    public String getMoteurConversion() {
        return this.moteurConversion;
    }

    public void setMoteurConversion(String moteurConversion) {
        this.moteurConversion = moteurConversion;
    }

    public String getApplicationAppelante() {
        return this.applicationAppelante;
    }

    public void setApplicationAppelante(String applicationAppelante) {
        this.applicationAppelante = applicationAppelante;
    }

    public String getErreurGed() {
        return this.erreurGed;
    }

    public void setErreurGed(String erreurGed) {
        this.erreurGed = erreurGed;
    }

    public String getErreurEbox() {
        return this.erreurEbox;
    }

    public void setErreurEbox(String erreurEbox) {
        this.erreurEbox = erreurEbox;
    }

    public TicketEboxDTO uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public TicketEboxDTO titreFr(String titreFr) {
        this.titreFr = titreFr;
        return this;
    }

    public TicketEboxDTO titreNl(String titreNl) {
        this.titreNl = titreNl;
        return this;
    }

    public TicketEboxDTO titreDe(String titreDe) {
        this.titreDe = titreDe;
        return this;
    }

    public TicketEboxDTO titreEn(String titreEn) {
        this.titreEn = titreEn;
        return this;
    }

    public TicketEboxDTO messageType(String messageType) {
        this.messageType = messageType;
        return this;
    }

    public TicketEboxDTO eboxMessageId(String eboxMessageId) {
        this.eboxMessageId = eboxMessageId;
        return this;
    }

    public TicketEboxDTO dateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public TicketEboxDTO dateFin(Instant dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public TicketEboxDTO statut(String statut) {
        this.statut = statut;
        return this;
    }

    public TicketEboxDTO essais(Integer essais) {
        this.essais = essais;
        return this;
    }

    public TicketEboxDTO stockage(Boolean stockage) {
        this.stockage = stockage;
        return this;
    }

    public TicketEboxDTO conversion(Boolean conversion) {
        this.conversion = conversion;
        return this;
    }

    public TicketEboxDTO moteurConversion(String moteurConversion) {
        this.moteurConversion = moteurConversion;
        return this;
    }

    public TicketEboxDTO applicationAppelante(String applicationAppelante) {
        this.applicationAppelante = applicationAppelante;
        return this;
    }

    public TicketEboxDTO erreurGed(String erreurGed) {
        this.erreurGed = erreurGed;
        return this;
    }

    public TicketEboxDTO erreurEbox(String erreurEbox) {
        this.erreurEbox = erreurEbox;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TicketEboxDTO)) {
            return false;
        }
        TicketEboxDTO ticketEboxDTO = (TicketEboxDTO) o;
        return Objects.equals(uuid, ticketEboxDTO.uuid) && Objects.equals(titreFr, ticketEboxDTO.titreFr) && Objects.equals(titreNl, ticketEboxDTO.titreNl) && Objects.equals(titreDe, ticketEboxDTO.titreDe) && Objects.equals(titreEn, ticketEboxDTO.titreEn) && Objects.equals(messageType, ticketEboxDTO.messageType) && Objects.equals(eboxMessageId, ticketEboxDTO.eboxMessageId) && Objects.equals(dateDebut, ticketEboxDTO.dateDebut) && Objects.equals(dateFin, ticketEboxDTO.dateFin) && Objects.equals(statut, ticketEboxDTO.statut) && Objects.equals(essais, ticketEboxDTO.essais) && Objects.equals(stockage, ticketEboxDTO.stockage) && Objects.equals(conversion, ticketEboxDTO.conversion) && Objects.equals(moteurConversion, ticketEboxDTO.moteurConversion) && Objects.equals(applicationAppelante, ticketEboxDTO.applicationAppelante) && Objects.equals(erreurGed, ticketEboxDTO.erreurGed) && Objects.equals(erreurEbox, ticketEboxDTO.erreurEbox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, titreFr, titreNl, titreDe, titreEn, messageType, eboxMessageId, dateDebut, dateFin, statut, essais, stockage, conversion, moteurConversion, applicationAppelante, erreurGed, erreurEbox);
    }

    @Override
    public String toString() {
        return "{" +
            " uuid='" + getUuid() + "'" +
            ", titreFr='" + getTitreFr() + "'" +
            ", titreNl='" + getTitreNl() + "'" +
            ", titreDe='" + getTitreDe() + "'" +
            ", titreEn='" + getTitreEn() + "'" +
            ", messageType='" + getMessageType() + "'" +
            ", eboxMessageId='" + getEboxMessageId() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", statut='" + getStatut() + "'" +
            ", essais='" + getEssais() + "'" +
            ", stockage='" + isStockage() + "'" +
            ", conversion='" + isConversion() + "'" +
            ", moteurConversion='" + getMoteurConversion() + "'" +
            ", applicationAppelante='" + getApplicationAppelante() + "'" +
            ", erreurGed='" + getErreurGed() + "'" +
            ", erreurEbox='" + getErreurEbox() + "'" +
            "}";
    }




}
