package be.civadis.gpdoc.amqp.dto;

import be.civadis.gpdoc.domain.TicketConversion;

public class ConversionMessageDTO {

    private TicketConversion ticketConversion;
    private String fichierAConvertirLocalPath;
    private String fichierAConvertirDocumentUuid;

    public ConversionMessageDTO(TicketConversion ticketConversion, String fichierAConvertirLocalPath, String fichierAConvertirDocumentUuid) {
        this.ticketConversion = ticketConversion;
        this.fichierAConvertirLocalPath = fichierAConvertirLocalPath;
        this.fichierAConvertirDocumentUuid = fichierAConvertirDocumentUuid;
    }

    public TicketConversion getTicketConversion() {
        return ticketConversion;
    }

    public void setTicketConversion(TicketConversion ticketConversion) {
        this.ticketConversion = ticketConversion;
    }

    public String getFichierAConvertirLocalPath() {
        return fichierAConvertirLocalPath;
    }

    public void setFichierAConvertirLocalPath(String fichierAConvertirLocalPath) {
        this.fichierAConvertirLocalPath = fichierAConvertirLocalPath;
    }

    public String getFichierAConvertirDocumentUuid() {
        return fichierAConvertirDocumentUuid;
    }

    public void setFichierAConvertirDocumentUuid(String fichierAConvertirDocumentUuid) {
        this.fichierAConvertirDocumentUuid = fichierAConvertirDocumentUuid;
    }
}
