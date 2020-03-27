package be.civadis.gpdoc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import be.civadis.gpdoc.amqp.producer.MessageService;
import be.civadis.gpdoc.domain.TicketConversion;
import be.civadis.gpdoc.multitenancy.TenantContext;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private MessageService producer;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {

        // simulation d'envois de messages pour le tenant jhipster

        TenantContext.setCurrentApp("testapp");


        Arrays.asList(1, 2, 3, 4).stream()
            .map(i -> createDto(i))
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
        TicketConversion dto5 = createDto(5);
        producer.envoyerMessageConversion(createDto(5), dto5.getSourcePath(), null);

        //context.close();

        // dans l'application gpdoc, les messages seront envoyés suite aux appels rest (dans l'ancienne version, lancait des threads)
        //  -> création de ticket
        //  -> envoi d'un message
        //  -> retour du ticket
    }

    private TicketConversion createDto(Integer idx){
        TicketConversion tc = new TicketConversion();
        tc.setSendingApplication("testapp");
        tc.setDestPath("/tmp/result"+ idx +".pdf");
        tc.setSourcePath("/tmp/input" + idx +".odt");
        return tc;
    }

}
