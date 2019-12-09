package be.civadis.gpdoc.amqp;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.amqp.core.Message;

import be.civadis.gpdoc.multitenancy.TenantContext;

/**
 * AOP Around advice wrapper. Every time a message comes in we can do
 * pre/post processing by using this advice by implementing the before/after methods.
 * https://stackoverflow.com/questions/34513662/how-do-we-hook-into-before-after-message-processing-using-rabbitlistener
 * @author phw
 */
public class CustomRabbitListenerAroundAdvice implements MethodInterceptor {

    /**
     * place the "AroundAdvice" around each new message being processed.
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Message message = (Message) invocation.getArguments()[1];

        //System.out.println("CustomRabbitListenerAroundAdvice executed on message !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        String tenant = (String) message.getMessageProperties().getHeaders().get("tenant");
        String application = (String) message.getMessageProperties().getHeaders().get("application");
        //System.out.println("Tenant " + tenant + " - application " + application);
        TenantContext.setCurrentApp(application);
        TenantContext.setCurrentTenant(tenant);

        Object result = invocation.proceed();
        return  result;
    }

}
