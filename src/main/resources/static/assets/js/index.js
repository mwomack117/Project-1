// ------- HANDLE LOGN START ------ //
document.querySelector("#login").addEventListener("click", login);
console.log("script loaded");
function login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let loginData = {
        username,
        password
    }

    fetch('http://localhost:7001/login', {
        method: 'POST',
        credentials: 'include', // this specifies that when receive cookies, you should include them in future
        // requests. So in our case, it's important so that the backend can identify if we are logged in or not.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    }).then(response => {
        if (response.status == 200) {
            window.location.href = "/employee.html"
        } else if (response.status == 401) {
            displayInvalidLogin();
        }
    })
}

function displayInvalidLogin() {
    let bodyElement = document.querySelector("form");

    let pElement = document.createElement('p');
    pElement.style.color = 'red';
    pElement.innerHTML = 'Invalid login!';

    bodyElement.appendChild(pElement);

}

// ------- HANDLE LOGN END ------ //

// ------- HANDLE REGISTER START ------ //
document.querySelector("#submitRegistration").addEventListener("click", register);
function register() {
    let firstName = document.getElementById("inputFirstname").value;
    let lastName = document.getElementById("inputLastname").value;
    let email = document.getElementById("inputEmail").value;
    let userName = document.getElementById("inputUsername").value;
    let password = document.getElementById("inputPassword").value;

    let registerForm = document.getElementById("register-form");

    let registerData = {
        userName,
        password,
        firstName,
        lastName,
        email,
    }

    fetch("http://localhost:7001/register", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerData)
    }).then(response => {
        if (response.status == 201) {
            registerForm.reset();
            window.location.reload();
            alert("successful registration!")
        } else if (response.status == 400) {
            displayErrorMessage(response);
        }
    })
}

function displayErrorMessage(data) {
    let errorElement = document.getElementById("error-message");
    errorElement.style.color = "red";
    errorElement.innerHTML = `Error: ${data.message}`;
}