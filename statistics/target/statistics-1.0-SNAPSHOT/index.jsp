<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>${applicationScope.localization.getString("home")}</title>
</head>
    <body>
        <h1>${applicationScope.localization.getString("statistics")}</h1>
        <form action="/statisticsAllClients" method="GET">
            ${applicationScope.localization.getString("allClient")}:
            <input type="submit" value="${applicationScope.localization.getString("goOver")}">
        </form>
        <form action="/statisticsClient" method="GET">
            <p>${applicationScope.localization.getString("currentClient")}:
                <input type="submit" value="${applicationScope.localization.getString("goOver")}">
        </form>
    </body>
</html>