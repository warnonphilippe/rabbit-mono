package be.civadis.gpdoc;

import java.util.Arrays;

import be.civadis.gpdoc.amqp.producer.MessageService;
import be.civadis.gpdoc.multitenancy.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import be.civadis.gpdoc.dto.TicketConversionDto;

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
                    producer.envoyerMessageConversion(dto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        );

        // simulation d'envois de message pour le tenant jhipster2

        TenantContext.setCurrentTenant("jhipster2");
        producer.envoyerMessageConversion(createDto(5));

        //context.close();

        // dans l'application gpdoc, les messages seront envoyés suite aux appels rest (dans l'ancienne version, lancait des threads)
        //  -> création de ticket
        //  -> envoi d'un message
        //  -> retour du ticket
    }

    private TicketConversionDto createDto(Integer idx){
        TicketConversionDto tc = new TicketConversionDto();
        tc.setSendingApplication("testapp");
        tc.setDestPath("/tmp/result"+ idx +".pdf");
        tc.setSourcePath("/tmp/input" + idx +".odt");
        return tc;
    }

}
