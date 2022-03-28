<%--
  Created by IntelliJ IDEA.
  User: EFIM
  Date: 27.09.2021
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>${applicationScope.localization.getString("statisticsAllClient")}</title>
    </head>
    <body>
    ${applicationScope.localization.getString("countUniqueClients")}:${applicationScope.statisticsAllClients.countUniqueClients}
    <br>${applicationScope.localization.getString("countUniqueClientInPage")}
    <ul>
        <li>${applicationScope.localization.getString("home")}: ${applicationScope.statisticsAllClients.getCountNewClientInPage('Main')} </li>
        <li>${applicationScope.localization.getString("statisticsAllClient")}: ${applicationScope.statisticsAllClients.getCountNewClientInPage("StatisticsAllClient")}</li>
        <li>${applicationScope.localization.getString("statisticsCurrentClient")}: ${applicationScope.statisticsAllClients.getCountNewClientInPage("StatisticsClient")}</li>
    </ul>
    ${applicationScope.localization.getString("countRequest")}: ${applicationScope.statisticsAllClients.countRequest}
    <br> ${applicationScope.localization.getString("countRequestInSession")}: ${applicationScope.statisticsAllClients.countRequestInSession}
    <br> <a href="/">${applicationScope.localization.getString("back")}</a>
    </body>
</html>
