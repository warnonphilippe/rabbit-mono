package be.civadis.gpdoc.dto;

/**
 * ConversionGedMessageDTO
 */
public class ConversionGedMessageDTO {

    private TicketConversionDTO ticketConversion;
    private String fichierGedId;

    public ConversionGedMessageDTO() {
    }

    public ConversionGedMessageDTO(TicketConversionDTO ticketConversion, String fichierGedId) {
        this.ticketConversion = ticketConversion;
        this.fichierGedId = fichierGedId;
    }

    public TicketConversionDTO getTicketConversion() {
        return ticketConversion;
    }

    public void setTicketConversion(TicketConversionDTO ticketConversion) {
        this.ticketConversion = ticketConversion;
    }

    public String getFichierGedId() {
        return fichierGedId;
    }

    public void setFichierGedId(String fichierGedId) {
        this.fichierGedId = fichierGedId;
    }
}
