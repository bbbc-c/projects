package com.example.statistics.listener;

import com.example.statistics.model.StatisticsClient;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;
import java.util.ResourceBundle;

@WebListener
public class SessionListener implements HttpSessionListener {

    public SessionListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("statisticsClient", new StatisticsClient());
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }
}
