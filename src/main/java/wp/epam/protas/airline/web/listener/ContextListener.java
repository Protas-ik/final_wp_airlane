package wp.epam.protas.airline.web.listener;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by zhenya on 06.03.2016.
 */
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PropertyConfigurator.configure(sce.getServletContext().getRealPath("WEB-INF/log4j.properties"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
