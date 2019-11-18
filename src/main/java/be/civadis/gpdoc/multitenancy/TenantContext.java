package be.civadis.gpdoc.multitenancy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadLocal contenant le tenant courant
 */
public class TenantContext {

    private static Logger logger = LoggerFactory.getLogger(TenantContext.class.getName());

    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();
    private static ThreadLocal<String> currentApp = new ThreadLocal<>();

    public static void setCurrentTenant(String tenant) {
        //logger.warn("Setting tenant to " + tenant);
        currentTenant.set(tenant);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentApp(String app) {
        //logger.warn("Setting app to " + app);
        currentApp.set(app);
    }

    public static String getCurrentApp() {
        return currentApp.get();
    }

    public static void clear() {
        //logger.warn("Clear tenant & app");
        currentTenant.set(null);
        currentApp.set(null);
    }
}
