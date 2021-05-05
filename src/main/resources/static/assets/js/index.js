// ------- HANDLE LOGN START ------ //
document.querySelector("#login").addEventListener("click", login);
console.log("script loaded");
function login(event) {
    event.preventDefault();
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let loginData = {
        username,
        password
    }

    fetch('/user/login', {
        method: 'POST',
        credentials: 'include', // this specifies that when receive cookies, you should include them in future
        // requests. So in our case, it's important so that the backend can identify if we are logged in or not.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData),
    }).then(response => {
        if (response.status == 200) {
            window.location.href = "/employee.html"
        } else if (response.status == 400) {
            return response.json();
        }
    }).then(jsonResponse => {
        console.log(jsonResponse);
        displayInvalidLogin(jsonResponse.message);

    })
}

function displayInvalidLogin(message) {
    let bodyElement = document.querySelector("form");

    let pElement = document.createElement('p');
    pElement.style.color = 'red';
    pElement.innerHTML = message;

    bodyElement.appendChild(pElement);

    setTimeout(function () {
        pElement.innerHTML = "";
    }, 2000);

}

// ------- HANDLE LOGN END ------ //

// ------- HANDLE REGISTER START ------ //
document.querySelector("#submitRegistration").addEventListener("click", register);
function register(e) {
    e.preventDefault();

    let userName = document.getElementById("inputUsername").value;
    let password = document.getElementById("inputPassword").value;
    let firstName = document.getElementById("inputFirstname").value;
    let lastName = document.getElementById("inputLastname").value;
    let email = document.getElementById("inputEmail").value;

    let registerForm = document.getElementById("register-form");

    let registerData = {
        userName,
        password,
        firstName,
        lastName,
        email,
    }

    fetch("user/register", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerData)
    }).then(response => {
        if (response.status == 201) {
            displaySuccessfulRegister();
            registerForm.reset();
        } else if (response.status == 400) {
            return response.json();
        }
    }).then(jsonResponse => {
        if (!jsonResponse.message) {
            return;
        }
        displayInvalidRegister(jsonResponse.message);

    })
}

function displayInvalidRegister(message) {
    let errorElement = document.getElementById("errorMessage");
    console.log(errorElement);
    console.log(message);
    errorElement.textContent = message;

    setTimeout(function () {
        errorElement.textContent = "";
    }, 2000);
}

function displaySuccessfulRegister() {
    let successElement = document.getElementById("successMessage");

    successElement.textContent = "Successfully Registered!";

    setTimeout(function () {
        successElement.textContent = "";
        window.location.reload();
    }, 1500);
}

(function () {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()