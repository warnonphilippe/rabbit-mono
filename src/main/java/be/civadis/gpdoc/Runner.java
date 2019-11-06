package be.civadis.gpdoc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import be.civadis.gpdoc.amqp.AMQPConvertProducer;
import be.civadis.gpdoc.dto.TicketConversionDto;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private AMQPConvertProducer producer;
    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {

        Arrays.asList(1, 2, 3, 4, 5).stream()
            .map(i -> createDto(i))
            .forEach(dto -> {
                try {
                    producer.sendTicketConversion(dto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        );
    
        //context.close();
    }

    private TicketConversionDto createDto(Integer idx){
        TicketConversionDto tc = new TicketConversionDto();
        tc.setSendingApplication("testapp");
        tc.setDestPath("/tmp/result"+ idx +".pdf");
        tc.setSourcePath("/tmp/input" + idx +".odt");
        return tc;
    }

}