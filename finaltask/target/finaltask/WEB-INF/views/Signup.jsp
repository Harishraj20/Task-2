<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored =
"false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="<c:url value='/css/Signup.css' />" rel="stylesheet" />
    <title>create user</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
      integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
  </head>
  <body>
    <div class="container">
      <div class="holder">
        <div class="heading">
          <h3 id="modal-title">ADD USERS</h3>
        </div>
        <div class="data-error" id="errorMessage">
          <p>${message}</p>
        </div>
        <div class="form-holder">
          <form
            id="userForm"
            class="addUserForm"
            action="/finaltask/Details/Signup/addUser"
            onsubmit="return validateFields()"
            method="post"
          >
            <div id="section-1" class="section">
              <div class="form-elements">
                <label for="name">User Name:</label>
                <input
                  type="text"
                  name="userName"
                  id="name"
                  placeholder="Enter User Name"
                  maxlength="30"
                />
              </div>
              <div id="nameError" class="error"></div>

              <div class="form-elements">
                <label for="userPassword">Password:</label>
                <input
                  type="password"
                  name="password"
                  id="userPassword"
                  class="password"
                  placeholder="Enter Password"
                />
              </div>
              <div id="passwordError" class="error"></div>

              <div class="form-elements">
                <label for="confirm-password">Confirm Password:</label>
                <input
                  type="password"
                  name="confirmPassword"
                  id="confirm-password"
                  class="password"
                  placeholder="Re Enter the Password"
                />
              </div>
              <div id="confirmPasswordError" class="error"></div>

              <div class="form-elements">
                <label for="email-field">Mail Id:</label>
                <input
                  type="text"
                  name="emailId"
                  id="email-field"
                  class="password"
                  placeholder="Enter Mail Id"
                />
              </div>
              <div id="emailerror" class="error"></div>
            </div>

            <div id="section-2" class="section">
              <div class="form-elements">
                <label for="dob-field">Date of Birth:</label>
                <input
                  type="date"
                  name="dob"
                  id="dob-field"
                  class="password"
                  placeholder="Enter Date of Birth"
                />
              </div>
              <div id="dobError" class="error"></div>
              <div class="form-elements">
                <label for="gender-field">Gender:</label>
                <select name="gender" id="gender-field">
                  <option value="" disabled selected>select</option>
                  <option value="Male">Male</option>
                  <option value="Female">Female</option>
                  <option value="Others">Others</option>
                </select>
              </div>
              <div id="genderError" class="error"></div>

              <div class="form-elements">
                <label for="designation-field">Designation:</label>
                <select name="designation" id="designation-field">
                  <option value="" disabled selected>select</option>
                  <option value="trainee">Trainee</option>
                  <option value="junior">Junior Developer</option>
                  <option value="senior">Java Developer</option>
                  <option value="lead">React Js Developer</option>
                  <option value="manager">NodeJs Developer</option>
                  <option value="trainee">Python Developer</option>
                  <option value="junior">Manual Tester</option>
                  <option value="senior">Automation Tester</option>
                  <option value="lead">Support Engineer</option>
                  <option value="manager">Devops Engineer</option>
                </select>
              </div>

              <div id="designationError" class="error"></div>

              <div class="form-elements">
                <label for="role-field">Role:</label>
                <select name="role" id="role-field">
                  <option value="" disabled selected>select</option>
                  <option value="admin">Admin</option>
                  <option value="viewer">Viewer</option>
                </select>
              </div>

              <div id="roleError" class="error"></div>
            </div>
          </form>
        </div>

        <div class="form-button">
          <input class="reset-button" type="reset" value="Reset" />
          <input
            class="submit-button"
            type="submit"
            value="Submit"
            id="form-submit"
          />
        </div>
      </div>
      <div class="back-button">
        <form action="" class="button-form">
          <button class="back-but">
            <i class="fa-solid fa-arrow-left fa-xs"></i>Back
          </button>
        </form>
      </div>
    </div>
    <script src="<c:url value='/javascript/Signup.js' />"></script>
  </body>
</html>
