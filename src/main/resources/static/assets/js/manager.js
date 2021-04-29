window.onload = function () {
    renderCurrentManager();
}

function renderCurrentUser() {
    fetch("http://localhost:7001/current_user", {
        method: "GET",
        credentials: "include",
    }).then(response => {
        if (response.status == 400 || response.userRoles.id != 2) {
            window.location.href = "/";
        }
        return response.json();
    }).then(data => {
        let id = data.id;
        let username = data.username;

        let userInfoElement = document.querySelector("#user");
        userInfoElement.innerHTML = `User id: ${id}, username: ${username}`;
    })
}