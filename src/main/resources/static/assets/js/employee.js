window.onload = function () {
    renderCurrentUser();
    retrieveEmployeeReimbs();
}

function renderCurrentUser() {
    console.log(document.cookie);
    fetch("/user/current", {
        method: "GET",
        credentials: "include",
    }).then(response => {
        console.log(response);
        if (response.status == 400) {
            window.location.href = "/";
        }
        return response.json();
    }).then(data => {
        console.log(data.userRoles.id)
        if (data.userRoles.id == 2) {
            window.location.href = "/manager.html"
        }
        let username = data.username;
        let userInfoElement = document.querySelector("#userEmp");
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

    fetch("/reimbursements", {
        method: "POST",
        credentials: "include",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(reimbData)
    })

}

function retrieveEmployeeReimbs() {
    fetch("/reimbursements", {
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
        const dateObject = new Date(item.reimbSubmitted);
        const formattedDate = dateObject.toLocaleString(); //change toLocalString() when full data + time is retrieved
        tdSubmit.innerHTML = formattedDate;

        let tdAmnt = document.createElement('td');
        tdAmnt.innerHTML = "$" + item.reimbAmount;

        let tdType = document.createElement('td');
        tdType.innerHTML = item.reimbType.reimbType;

        // let tdReceipt = document.createElement('td');
        // tdReceipt.innerHTML = item.receipt;
        let tdReceipt = document.createElement('td');
        tdReceipt.innerHTML =
            `<div>
                <button type="button" class="btn btn-sm btn-primary viewReceipt" value=${item.id}
                data-bs-toggle="modal" data-bs-target="#receiptModalEmp">View</button>
            </div>`;

        let tdStatus = document.createElement('td');
        tdStatus.innerHTML = item.reimbStatus.reimbStatus;

        let tdRslvdDate = document.createElement("td");
        rslvDateObject = new Date(item.reimbResolved);
        const formattedRslvDate = rslvDateObject.toLocaleString();
        tdRslvdDate.innerHTML = item.reimbSubmitted === item.reimbResolved ? "" : formattedRslvDate;
        //tdRslvdDate.innerHTML = item.reimbStatus.reimbStatus === "Pending" ? "" : formattedRslvDate;

        let tdRslvdBy = document.createElement('td');
        tdRslvdBy.innerHTML = item.resolver === null ? "" : item.resolver.firstName + " " + item.resolver.lastName;

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

}

document.getElementById("sign-out").addEventListener("click", logout);

function logout() {
    document.cookie = "JSESSIONID=; expires=Thu, 18 Dec 2013 12:00:00 UTC; path=/";
    fetch("/user/logout", {
        method: "POST",
        credentials: "include"
    })
}


