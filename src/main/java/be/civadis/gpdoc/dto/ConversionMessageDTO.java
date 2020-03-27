package be.civadis.gpdoc.dto;

import java.util.List;

public class ConversionMessageDTO {

    private TicketConversionDTO ticketConversion;
    private String fichierAConvertirLocalPath;
    private String fichierAConvertirDocumentUuid;

    public ConversionMessageDTO(TicketConversionDTO ticketConversion, String fichierAConvertirLocalPath, String fichierAConvertirDocumentUuid) {
        this.ticketConversion = ticketConversion;
        this.fichierAConvertirLocalPath = fichierAConvertirLocalPath;
        this.fichierAConvertirDocumentUuid = fichierAConvertirDocumentUuid;
    }

    public TicketConversionDTO getTicketConversion() {
        return ticketConversion;
    }

    public void setTicketConversion(TicketConversionDTO ticketConversion) {
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
