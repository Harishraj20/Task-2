<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored =
"false" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Users Details</title>
    <link href="<c:url value='/css/LoginInfo.css' />" rel="stylesheet" />

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
      integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
  </head>
  <body>
    <div class="user-details">
      <div class="display-user">
        <p>USER ID : ${empId}</p>
      </div>
      <div class="display-title">
        <p>LOGIN INFO</p>
      </div>

      <div class="logout-field">
        <P>LOGIN COUNTS : ${Loggedinfo.size()}</P>
      </div>
    </div>
    <div class="table-container">
      <table border="1">
        <thead>
          <tr>
            <th style="width: 25%;">S.NO</th>
            <th style="width: 40%">LOGIN DATE</th>
            <th style="width: 45%">LOGIN TIME</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty Loggedinfo}">
              <c:forEach var="login" items="${Loggedinfo}" varStatus="status">
                <tr>
                  <td>${status.index + 1}</td>
                  <td>${login.formattedDate}</td>
                  <td>${login.formattedTime}</td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="2" style="text-align: center">
                  No Logins
                </td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>
    <div class="button-container">
      <form action="/finaltask/back" method="get">
        <button class="back-button">
          <i class="fa-solid fa-arrow-left fa-xs"></i> Back
        </button>
      </form>
    </div>
  </body>
</html>
