package be.civadis.gpdoc.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * TicketEnvoiEbox
 */
public class TicketEnvoiEbox {

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

    private List<FichierInfos> fichierInfosList = new ArrayList<>();

    public TicketEnvoiEbox() {
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

    public List<FichierInfos> getFichierInfosList() {
        return fichierInfosList;
    }

    public void setFichierInfosList(List<FichierInfos> fichierInfosList) {
        this.fichierInfosList = fichierInfosList;
    }

    public String getErreurEbox() {
        return this.erreurEbox;
    }

    public void setErreurEbox(String erreurEbox) {
        this.erreurEbox = erreurEbox;
    }


}
