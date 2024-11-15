<%@ page contentType="text/html;charset=UTF-8" language="java"
isELIgnored="false" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
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
      <div class="display-user">
        <p>USER ID: ${loggedUser.employeeId}</p>
      </div>
      <div class="display-title">
        <p>MANAGE USERS</p>
      </div>

      <div class="logout-field">
        <form action="/finaltask/logout" method="get">
          <input type="hidden" name="viewUser" value="${loggedUser.userId}" />
          <button class="logout-btn">Log-out</button>
        </form>
      </div>
    </div>

    <c:if test="${loggedUser.role eq 'admin'}">
      <div class="addUser-btn">
        <form action="/finaltask/users/inactiveUsers" method="get">
          <button class="add-btn">Inactive Users</button>
        </form>
  
        <div class="message-content">
          <p style="font-size: 25px">User Created successfully!</p>
        </div>
  
        <form action="/finaltask/users/form" method="get">
          <input type="hidden" name="userId" value=''>
          <button class="add-btn">Add User</button>
        </form>
      </div>
    </c:if>

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
            <th style="width: 20%">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>${loggedUser.employeeId}</td>
            <td>${loggedUser.userName}</td>
            <td>${loggedUser.emailId}</td>
            <td>${loggedUser.designation}</td>
            <td>${loggedUser.role}</td>
            <td>${loggedUser.dob}</td>
            <td>
              <div class="action-holder">
                <form action="/finaltask/users/viewInfo" method="get">
                  <input type="hidden" name="userId" value="${loggedUser.userId}">
                  <input type="hidden" name="employeeId" value="${loggedUser.employeeId}">
                  <button class="btn view-btn">View</button>
                </form>
                <form action="/finaltask/users/form" method="get">
                  <input type="hidden" name="userId" value="${loggedUser.userId}">
                  <input type="hidden" name="employeeId" value="${loggedUser.employeeId}">
                  <button class="btn edit-btn">Edit</button>
                </form>
              </div>
            </td>
          </tr>

          <c:forEach var="user" items="${UserList}">
            <c:if test="${user.userId != loggedUser.userId}">
              <tr>
                <td>${user.employeeId}</td>
                <td>${user.userName}</td>
                <td>${user.emailId}</td>
                <td>${user.designation}</td>
                <td>${user.role}</td>
                <td>${user.dob}</td>
                <td>
                  <div class="action-holder">
                    <form action="/finaltask/users/viewInfo" method="get">
                      <input type="hidden" name="userId" value="${user.userId}">
                      <input type="hidden" name="employeeId" value="${user.employeeId}">


                      <button class="btn view-btn">View</button>
                    </form>
                    <c:choose>
                      <c:when test="${loggedUser.role eq 'admin'}">
                        <form action="/finaltask/users/form" method="get">
                          <input type="hidden" name="userId" value="${user.userId}">
                          <input type="hidden" name="employeeId" value="${user.employeeId}">
                          <button class="btn edit-btn">Edit</button>
                        </form>
                        <form
                          action="/finaltask/users/delete"
                          method="post"
                          onsubmit="return confirm('Are you sure you want to the employee with id: ${user.employeeId}?');"
                        >
                        <input type="hidden" name="userId" value="${user.userId}">
                        <input type="hidden" name="employeeId" value="${user.employeeId}">
                          <button class="btn delete-btn">Delete</button>
                        </form>
                      </c:when>
                    </c:choose>
                  </div>
                </td>
              </tr>
            </c:if>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </body>
</html>
