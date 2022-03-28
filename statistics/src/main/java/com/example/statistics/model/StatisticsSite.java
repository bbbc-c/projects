package com.example.statistics.model;

import java.util.HashMap;

public class StatisticsSite {

    private int countUniqueClients;                                     // Кол-во уникальных пользователей
    private int countRequest;                                           // Кол-во запросов всех пользователей
    private int countRequestInSession;                                  // Кол-во запросов всех пользователей в одной сессии
    HashMap<String,Integer> countUniqueClientInPage = new HashMap<>();  // Кол-во уникальных пользователей каждой страницы
    static StatisticsSite statisticsSite = null;

    private StatisticsSite() {
        countUniqueClients = 0;
        countRequest = 0;
        countRequestInSession = 0;
        countUniqueClientInPage.put("Main",0);
        countUniqueClientInPage.put("StatisticsAllClient",0);
        countUniqueClientInPage.put("StatisticsClient",0);
    }

    public static StatisticsSite getStaticsSite(){
        if (statisticsSite == null)
            statisticsSite = new StatisticsSite();
        return statisticsSite;
    }
    public void newClient(){
        countUniqueClients++;
    }
    public void newClientToPage(String page){
        if(countUniqueClientInPage.containsKey(page))
            countUniqueClientInPage.replace(page,countUniqueClientInPage.get(page)+1);
        else
            throw new IllegalArgumentException("Такой страницы нет!");
    }
    public void requestClients(){
        countRequest++;
        countRequestInSession++;
    }
    public int getCountNewClientInPage(String page){
        return countUniqueClientInPage.get(page);
    }
    public int getCountUniqueClients(){
        return countUniqueClients;
    }
    public int getCountRequestInSession(){
        return countRequestInSession;
    }
    public int getCountRequest(){
        return countRequest;
    }

}
