<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored =
"false" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Users Details</title>
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
      <p>User Details</p>
    </div>
    <div class="table-container">
      <table border="1">
        <thead>
          <tr>
            <th style="width: 20%">User Id</th>
            <th style="width: 20%">Name</th>      
            <th style="width: 20%">Actions</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty userList}">
              <c:forEach var="user" items="${userList}">
                <tr>
                  <td>${user.id}</td>
                  <td>${user.userName}</td>
                  <td>
                    <form action="/finaltask/login/viewInfo" method="post">
                      <input type="hidden" name="viewUser" value="${user.id}">
                      <button>View</button>
                    </form>
                    <form action="/finaltask/login/editUser">
                      <input type="hidden" name="editUserID" value="${user.id}">
                      <button>Edit</button>
                    </form>

                    <form action="/finaltask/login/deleteUser">
                      <input type="hidden" name="deleteUserID" value="${user.id}">
                      <button>Delete</button>
                    </form>
                  </td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="4" style="text-align: center">
                  No data available
                </td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>
    <div class="button-container">
      <form class="button-container" action="/finaltask/back" method="get">
        <button class="back-button">
          <i class="fa-solid fa-arrow-left fa-xs"></i> Back
        </button>
      </form>
    </div>
  </body>
</html>
