<%@ page contentType="text/html;charset=UTF-8" language="java"
isELIgnored="false" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Inactive Users</title>
    <link href="<c:url value='/css/table.css' />" rel="stylesheet" />
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
      <div class="display-user"></div>
      <div class="display-title">
        <p>INACTIVE USERS</p>
      </div>
    </div>

    <div class="table-container">
      <table border="1">
        <thead>
          <tr>
            <th style="width: 10%">User Id</th>
            <th style="width: 20%">Name</th>
            <th>Email Id</th>
            <th>Designation</th>
            <th>Role</th>
            <th>Date of Birth</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${empty UserList}">
              <tr>
                <td colspan="6" style="text-align: center">No Users!</td>
              </tr>
            </c:when>
            <c:otherwise>
              <c:set var="inactiveUserFound" value="false" />
              <c:forEach var="user" items="${UserList}">
                <c:if test="${user.loginStatus == 0}">
                  <c:set var="inactiveUserFound" value="true" />
                  <tr>
                    <td>${user.employeeId}</td>
                    <td>${user.userName}</td>
                    <td>${user.emailId}</td>
                    <td>${user.designation}</td>
                    <td>${user.role}</td>
                    <td>${user.dob}</td>
                  </tr>
                </c:if>
              </c:forEach>
              <c:if test="${!inactiveUserFound}">
                <tr>
                  <td colspan="6" style="text-align: center">No Inactive Users</td>
                </tr>
              </c:if>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>

    <div class="button-container">
      <form action="/finaltask/users" method="get">
        <button class="back-button">
          <i class="fa-solid fa-arrow-left fa-xs"></i> Back
        </button>
      </form>
    </div>
  </body>
</html>
