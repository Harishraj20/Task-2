<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home</title>
    <link href="<c:url value='/css/Signup.css' />" rel="stylesheet" />
  </head>
  <body>
    <div class="holder">

      <div class="userModal" id="add-user-modal">
        <div class="modal-content">
          <div class="heading">
            <h3 id="modal-title">ADD USERS</h3>
          </div>
          <div class="data-error">
            <p>
                Username already exists
            </p>
          </div>
          <form
            id="userForm"
            class="addUserForm"
            action="/finaltask/addUser"
            onsubmit="return validateFields()"
            method="post"
          >
            <input type="hidden" name="customerId" id="customerId" />
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
                  <option value="admin" >Admin</option>
                  <option value="viewer">Viewer</option>
                </select>
              </div>
              
            <div id="roleError" class="error"></div>

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

          
          </form>
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
      </div>

    <div class="back-button">
        <form action="" class="button-form">

           <button class="back-but"> Back</button>


        </form>
    </div>

</div>
</div>
<script src="<c:url value='/javascript/Signup.js' />"></script>
</body>
</html>
