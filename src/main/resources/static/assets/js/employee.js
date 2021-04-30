window.onload = function () {
    renderCurrentUser();
    retrieveData();
}

function renderCurrentUser() {
    console.log(document.cookie);
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
        let username = data.username;
        let userInfoElement = document.querySelector("#user");
        userInfoElement.innerHTML = `Successfully logged in as: ${username}`;
    })
}

document.getElementById("submitReimb").addEventListener("click", addReimbursement);

function addReimbursement() {
    let reimbAmount = document.getElementById("reimbAmount").value;
    let reimbDescription = document.getElementById("reimbDescription").value;
    let type = document.getElementById("reimbType").value;
    //reimbPhoto = document.getElementById("uploadReceipt").value;
    let reimbData = {
        reimbAmount,
        reimbDescription,
        type
    }

    fetch("http://localhost:7001/reimbursement", {
        method: "POST",
        credentials: "include",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(reimbData)
    })

}

function retrieveData() {
    fetch("http://localhost:7001/allReimbursements", {
        method: "GET",
        credentials: "include"
    }).then((data) => {
        return data.json();
    }).then((response) => {
        console.log(response);
        populateData(response);
    })
}

function populateData(response) {
    let tBody = document.getElementById("tableBody")

    response.forEach(item => {
        let tr = document.createElement('tr')

        let tdDesc = document.createElement('td');
        tdDesc.innerHTML = item.reimbDescription;

        let tdSubmit = document.createElement('td');
        tdSubmit.innerHTML = item.reimbSubmitted;

        let tdAmnt = document.createElement('td');
        tdAmnt.innerHTML = item.reimbAmount;

        let tdType = document.createElement('td');
        tdType.innerHTML = item.reimbType.reimbType;

        let tdReceipt = document.createElement('td');
        tdReceipt.innerHTML = item.receipt;

        let tdStatus = document.createElement('td');
        tdStatus.innerHTML = item.reimbStatus.reimbStatus;

        let tdRslvdDate = document.createElement("td");
        tdRslvdDate.innerHTML = item.reimbResolved;

        let tdRslvdBy = document.createElement('td');
        tdRslvdBy.innerHTML = item.resolver;;

        tr.appendChild(tdDesc);
        tr.appendChild(tdSubmit);
        tr.appendChild(tdAmnt);
        tr.appendChild(tdType);
        tr.appendChild(tdReceipt);
        tr.appendChild(tdStatus);
        tr.appendChild(tdRslvdDate);
        tr.appendChild(tdRslvdBy);
        tBody.appendChild(tr);

    })
    // let reimbAmount = response.reimbAmount;
    // let reimbSubmitted = resonse.reimbSubmitted;
    // let reimbResolved = response.reimbResolved;
    // let reimbDescription = response.reimbDescription;
    // let reimbType = response.reimbType.reimbType;
    // let reimbStatus = response.reimbStatus.reimbStatus;
    // let receipt = response.receipt;

    // let tdDesc = document.createElement('td');
    // let tdSubmit = document.createElement('td');
    // let tdAmnt = document.createElement('td');
    // let tdType = document.createElement('td');
    // let tdReceipt = document.createElement('td');
    // let tdStatus = document.createElement('td');
    // let tdRlvdDate = document.createElement("td");
    // let tdRslvdBy = document.createElement('td');


}

document.getElementById("sign-out").addEventListener("click", logout);

function logout() {
    document.cookie = "JSESSIONID=; expires=Thu, 18 Dec 2013 12:00:00 UTC; path=/";
}