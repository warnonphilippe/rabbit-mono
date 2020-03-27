package be.civadis.gpdoc.domain;

public class FichierInfos {

    private String attachementId;
    private Boolean principal;
    private String nomFichierOriginal;
    private String fichierTemp;

    public String getAttachementId() {
        return attachementId;
    }

    public void setAttachementId(String attachementId) {
        this.attachementId = attachementId;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getNomFichierOriginal() {
        return nomFichierOriginal;
    }

    public void setNomFichierOriginal(String nomFichierOriginal) {
        this.nomFichierOriginal = nomFichierOriginal;
    }

    public String getFichierTemp() {
        return fichierTemp;
    }

    public void setFichierTemp(String fichierTemp) {
        this.fichierTemp = fichierTemp;
    }


}
