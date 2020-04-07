package be.civadis.gpdoc;

import java.time.Instant;
import java.util.Arrays;

import be.civadis.gpdoc.domain.FichierInfos;
import be.civadis.gpdoc.domain.TicketEnvoiEbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import be.civadis.gpdoc.federation.amqp.MessageService;
import be.civadis.gpdoc.domain.TicketConversion;
import be.civadis.gpdoc.multitenancy.TenantContext;

@Component
public class Runner implements CommandLineRunner {

    public static final String TEST_OK_CONST = "@@@###{TEST-ERREUR-GPDOC-OFF}###@@@";
    public static final String TEST_ERREUR_CONST = "@@@###{TEST-ERREUR-GPDOC-ON}###@@@";

    @Autowired
    private MessageService producer;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {

        testConversion();

        //testEbox();

        // dans l'application gpdoc, les messages seront envoyés suite aux appels rest (dans l'ancienne version, lancait des threads)
    }

    private void testConversion(){
        TenantContext.setCurrentApp("testapp");

        Arrays.asList(1, 2, 3, 4).stream()
            .map(i -> createTicketConversion(i))
            .forEach(dto -> {
                    try {
                        TenantContext.setCurrentTenant("jhipster");
                        // pour le test, on utilise le fichier local comme ficier temporaire contenant le fichier
                        producer.envoyerMessageConversion(dto, dto.getSourcePath(), null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            );

        // simulation d'envois de message pour le tenant jhipster2
        TenantContext.setCurrentTenant("jhipster2");
        TicketConversion dto5 = createTicketConversion(5);
        producer.envoyerMessageConversion(dto5, dto5.getSourcePath(), null);
    }

    private TicketConversion createTicketConversion(Integer idx){
        TicketConversion tc = new TicketConversion();
        tc.setSendingApplication("testapp");
        tc.setDestPath("/tmp/result"+ idx +".pdf");
        tc.setSourcePath("/tmp/input" + idx +".odt");
        tc.setStartDate(Instant.now());
        return tc;
    }

    private void testEbox(){
        TenantContext.setCurrentApp("testapp");
        /*
        TenantContext.setCurrentTenant("jhipster");
        producer.envoyerMessageEnvoi(createTicketEbox(1, true));
        producer.envoyerMessageEnvoi(createTicketEbox(2, true));
        producer.envoyerMessageEnvoi(createTicketEbox(3, false));
        producer.envoyerMessageEnvoi(createTicketEbox(4, false));
*/
        TenantContext.setCurrentTenant("jhipster2");
        producer.envoyerMessageEnvoi(createTicketEbox(5, false));
    }

    private TicketEnvoiEbox createTicketEbox(Integer idx, boolean ok){
        TicketEnvoiEbox ticket = new TicketEnvoiEbox();
        ticket.setApplicationAppelante("testapp");
        ticket.setTitreFr("Test envoi " + idx);
        ticket.setTitreEn((ok) ? TEST_OK_CONST : TEST_ERREUR_CONST);
        ticket.setConversion(true);
        ticket.setStockage(true);
        ticket.setUuid("uudi-" + idx);

        FichierInfos fi = new FichierInfos();
        fi.setAttachementId("attchId" + idx);
        fi.setPrincipal(true);
        fi.setNomFichierOriginal("/tmp/input" + idx +".odt");
        fi.setFichierTemp("/tmp/input" + idx +".odt"); // path du file temporaire
        ticket.getFichierInfosList().add(fi);

        return ticket;
    }

}
