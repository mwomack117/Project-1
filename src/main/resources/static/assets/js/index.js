document.querySelector("#login").addEventListener("click", login);
console.log("script loaded");
function login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let data = {
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
        body: JSON.stringify(data)
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
