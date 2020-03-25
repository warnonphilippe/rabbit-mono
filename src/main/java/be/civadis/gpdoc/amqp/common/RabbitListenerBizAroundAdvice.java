package be.civadis.gpdoc.amqp.common;

import be.civadis.gpdoc.multitenancy.TenantContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.amqp.core.Message;

public class RabbitListenerBizAroundAdvice implements MethodInterceptor {

    /**
     * place the "AroundAdvice" around each new message being processed.
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Message message = (Message) invocation.getArguments()[1];

        String tenant = (String) message.getMessageProperties().getHeaders().get("tenant");
        String application = (String) message.getMessageProperties().getHeaders().get("application");
        TenantContext.setCurrentApp(application);
        TenantContext.setCurrentTenant(tenant);

        return invocation.proceed();
    }

}
