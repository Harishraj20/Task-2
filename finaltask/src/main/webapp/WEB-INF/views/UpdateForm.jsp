<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="new.css" />
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
          <h3 id="modal-title">UPDATE USER</h3>
        </div>
        <div class="data-error" id="errorMessage">
          <p>User with this mail Id already exists!!!</p>
        </div>
        <div class="form-holder">
          <form
            id="userForm"
            class="addUserForm"
            action="/finaltask/addUser"
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
            </div>

            <div id="section-2" class="section">
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
                    <option value="" disabled ${user == null ? 'selected' : ''}>select</option>
                    <option value="Trainee" ${user.designation == 'Trainee' ? 'selected' : ''}>Trainee</option>
                    <option value="Flutter Developer" ${user.designation == 'Flutter Developer' ? 'selected' : ''}>Flutter Developer</option>
                    <option value="Java Developer" ${user.designation == 'Java Developer' ? 'selected' : ''}>Java Developer</option>
                    <option value="React Js Developer" ${user.designation == 'React Js Developer' ? 'selected' : ''}>React Js Developer</option>
                    <option value="NodeJs Developer" ${user.designation == 'NodeJs Developer' ? 'selected' : ''}>NodeJs Developer</option>
                    <option value="Python Developer" ${user.designation == 'Python Developer' ? 'selected' : ''}>Python Developer</option>
                    <option value="Manual Tester" ${user.designation == 'Manual Tester' ? 'selected' : ''}>Manual Tester</option>
                    <option value="Automation Tester" ${user.designation == 'Automation Tester' ? 'selected' : ''}>Automation Tester</option>
                    <option value="Support Engineer" ${user.designation == 'Support Engineer' ? 'selected' : ''}>Support Engineer</option>
                    <option value="Devops Engineer" ${user.designation == 'Devops Engineer' ? 'selected' : ''}>Devops Engineer</option>
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
    <script src="<c:url value='/javascript/update.js' />"></script>
  </body>
</html>
