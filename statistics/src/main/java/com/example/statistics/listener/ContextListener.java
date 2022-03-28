package com.example.statistics.listener;

import com.example.statistics.model.StatisticsSite;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Locale;
import java.util.ResourceBundle;

@WebListener
public class ContextListener implements ServletContextListener {

    public ContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("statisticsAllClients",StatisticsSite.getStaticsSite());
        sce.getServletContext().setAttribute("localization", ResourceBundle.getBundle("Localization",new Locale("ru")));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }
}
