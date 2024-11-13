<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored =
"false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Users Details</title>
    <link href="<c:url value='/css/Signup.css' />" rel="stylesheet" />

   
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
        <p>USER ID : 1001</p>
      </div>
      <div class="display-title">
        <p>LOGIN INFO</p>
      </div>

      <div class="logout-field">
        <P>LOGIN COUNTS : 6</P>
      </div>
    </div>
    <div class="table-container">
      <table border="1">
        <thead>
          <tr>
            <th style="width: 50%">LOGIN DATE</th>
            <th style="width: 50%">LOGIN TIME</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1001</td>
            <td>John Doe</td>
          </tr>
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
