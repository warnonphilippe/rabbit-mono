package be.civadis.gpdoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import be.civadis.gpdoc.amqp.AMQPConvertProducer;
import be.civadis.gpdoc.domain.TicketConversion;

@Component
public class Runner implements CommandLineRunner{

    @Autowired
    private AMQPConvertProducer producer;
    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        TicketConversion tc = new TicketConversion();
        tc.setSendingApplication("testapp");
        tc.setDestPath("/tmp/result.pdf");
        tc.setSourcePath("/tmp/input.odt");
        this.producer.sendTicketConversion(tc);
        //context.close();
    }

}