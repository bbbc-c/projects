package com.example.statistics.model;

import java.util.HashMap;

public class StatisticsClient {

    private int countRequestInSession;                  // кол-во запросов в одной сессии
    private HashMap<String,Integer> countPageVisit;     // кол-во посешение страниц
    private StatisticsSite statisticsSite;

    public StatisticsClient(){
        statisticsSite = StatisticsSite.getStaticsSite();
        countRequestInSession = 0;
        countPageVisit = new HashMap<>();
        countPageVisit.put("Main",0);
        countPageVisit.put("StatisticsAllClient",0);
        countPageVisit.put("StatisticsClient",0);
        newClient();
    }

    public void newClient(){
        statisticsSite.newClient();
    }
    public void сlientToPage(String page){
        if(countPageVisit.get(page) <= 0)
            statisticsSite.newClientToPage(page);
        if(countPageVisit.containsKey(page))
            countPageVisit.replace(page,countPageVisit.get(page)+1);
        else
            throw new IllegalArgumentException("Такой страницы нет!");
    }
    public void requestClient(){
        countRequestInSession++;
        statisticsSite.requestClients();
    }
    public int getCountPageVisit(String page){
        return countPageVisit.get(page);
    }
    public int getCountRequestInSession(){
        return countRequestInSession;
    }
}
