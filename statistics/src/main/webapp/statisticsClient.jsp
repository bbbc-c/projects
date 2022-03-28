<%--
  Created by IntelliJ IDEA.
  User: EFIM
  Date: 27.09.2021
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${applicationScope.localization.getString("statisticsCurrentClient")}</title>
</head>
    <body>
    ${applicationScope.localization.getString("countVisit")}
    <ul>
        <li>${applicationScope.localization.getString("home")}: ${sessionScope.statisticsClient.getCountPageVisit("Main")}</li>
        <li>${applicationScope.localization.getString("statisticsAllClient")}: ${sessionScope.statisticsClient.getCountPageVisit("StatisticsAllClient")}</li>
        <li>${applicationScope.localization.getString("statisticsCurrentClient")}: ${sessionScope.statisticsClient.getCountPageVisit("StatisticsClient")}</li>
    </ul>
    <br> ${applicationScope.localization.getString("countRequestInSession")}: ${sessionScope.statisticsClient.countRequestInSession}
    <br> <a href="/">${applicationScope.localization.getString("back")}</a>
    </body>
</html>

