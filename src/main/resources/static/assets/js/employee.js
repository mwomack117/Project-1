window.onload = function () {
    renderCurrentUser();
}

function renderCurrentUser() {
    fetch("http://localhost:7001/current_user", {
        method: "GET",
        credentials: "include",
    }).then(response => {
        console.log(response);
        if (response.status == 400) {
            window.location.href = "/";
        }
        return response.json();
    }).then(data => {
        if (data.userRoles.id == 2) {
            window.location.href = "/manager.html"
        }
        let id = data.id;
        let username = data.username;

        let userInfoElement = document.querySelector("#user");
        userInfoElement.innerHTML = `User id: ${id}, username: ${username}`;
    })
}

document.getElementById("sign-out").addEventListener("click", logout);

function logout() {
    fetch("http://localhost:7001/current_user", {
        method: "POST"
    })
}