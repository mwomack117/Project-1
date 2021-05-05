window.onload = function () {
    renderCurrentUser();
    retrieveAllReimbs();
}

function renderCurrentUser() {
    fetch("/user/current", {
        method: "GET",
        credentials: "include",
    }).then(response => {
        if (response.status == 400) {
            window.location.href = "/";
        }
        return response.json();
    }).then(data => {
        let username = data.username;
        let userInfoElement = document.querySelector("#userMngr");
        userInfoElement.innerHTML = `Successfully logged in as: ${username}`;
    })
}

let radioButtons = document.querySelectorAll('input[name="btnRadio"]');
radioButtons.forEach(item => {
    item.addEventListener("click", function (event) {
        console.log(item);
        if (event.target.value == "All") {
            console.log("all function ran")
            console.log(jsonData)
            clearTable();
            populateData(jsonData);
        } else {
            console.log("filter function ran")
            clearTable();
            filterReimbs(event.target.value);
        }
    })
})

function clearTable() {
    let table = document.querySelector("table");

    while (table.rows.length > 1) {
        table.deleteRow(1);
    }
}

async function retrieveAllReimbs() {
    let response = await fetch("/reimbursements/users", {
        method: "GET",
        credentials: "include"
    });
    let data = await response.json();
    jsonData = data;
    return populateData(jsonData);
}

function filterReimbs(filterValue) {
    console.log(jsonData);
    let data = jsonData.filter(item => item.reimbStatus.reimbStatus === filterValue);
    populateData(data);
    // let data = {
    //     reimbStatus: filterValue
    // }
    // fetch("/reimbursements/status", {
    //     method: "POST",
    //     credentials: "include",
    //     headers: {
    //         'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify(data)
    // }).then(response => {
    //     return response.json();

    // }).then(jsonResponse => {
    //     populateData(jsonResponse);
    //     console.log("I'm filtered", jsonResponse);
    // })
}

function populateData(response) {
    let tBody = document.getElementById("tableBody");

    response.forEach(item => {
        let tr = document.createElement('tr')

        let tdAuthor = document.createElement('td');
        tdAuthor.innerHTML = item.author.firstName + " " + item.author.lastName;

        let tdAmnt = document.createElement('td');
        tdAmnt.innerHTML = "$" + item.reimbAmount;

        let tdType = document.createElement('td');
        tdType.innerHTML = item.reimbType.reimbType;

        let tdDesc = document.createElement('td');
        tdDesc.innerHTML = item.reimbDescription;

        let tdSubmit = document.createElement('td');
        const dateObject = new Date(item.reimbSubmitted);
        const formattedDate = dateObject.toLocaleString(); //change toLocalString() when full data + time is retrieved
        tdSubmit.innerHTML = formattedDate;

        let tdReceipt = document.createElement('td');
        tdReceipt.innerHTML =
            `<div>
                <button type="button" class="btn btn-sm btn-primary viewReceipt" value=${item.id}
                data-bs-toggle="modal" data-bs-target="#receiptModal">View</button>
            </div>`;

        let tdStatus = document.createElement('td');
        tdStatus.innerHTML = item.reimbStatus.reimbStatus;

        let tdChangeStatus = document.createElement('td');
        tdChangeStatus.innerHTML =
            `<div>
                <button type="button" class="btn btn-sm btn-success approveReimb" onclick=value=${item.id}>&check;</button>
                <button type="button" class="btn btn-sm btn-danger denyReimb" value=${item.id}>X</button>
            </div>`;

        let tdRslvdDate = document.createElement("td");
        const RsvlDateObject = new Date(item.reimbResolved);
        const rsvlFormattedDate = RsvlDateObject.toLocaleString(); //change toLocalString() when full data + time is retrieved
        //tdRslvdDate.innerHTML = rsvlFormattedDate;
        tdRslvdDate.innerHTML = item.reimbSubmitted === item.reimbResolved ? "" : rsvlFormattedDate;

        let tdRslvdBy = document.createElement('td');
        tdRslvdBy.innerHTML = item.resolver === null ? "" : item.resolver.firstName + " " + item.resolver.lastName;

        tr.appendChild(tdAuthor);
        tr.appendChild(tdAmnt);
        tr.appendChild(tdType);
        tr.appendChild(tdDesc);
        tr.appendChild(tdSubmit);
        tr.appendChild(tdReceipt);
        tr.appendChild(tdStatus);
        tr.appendChild(tdChangeStatus);
        tr.appendChild(tdRslvdDate);
        tr.appendChild(tdRslvdBy);

        tBody.appendChild(tr);
    })

    function addApproveBtnClass() {
        let approveBtn = document.getElementsByClassName("approveReimb");
        console.log(approveBtn);
        Array.from(approveBtn).forEach(function (btn) {
            btn.addEventListener('click', function (e) {
                console.log(e.target.value);
                approveRequest(e.target.value);
                window.location.reload();
            });
        });
    };

    function addDenyBtnClass() {
        let denyBtn = document.getElementsByClassName("denyReimb");
        console.log(denyBtn);
        Array.from(denyBtn).forEach(function (btn) {
            btn.addEventListener('click', function (e) {
                console.log(e.target.value);
                denyRequest(e.target.value);
                window.location.reload();
            });
        });
    };

    addApproveBtnClass();
    addDenyBtnClass();

}

// function addApproveBtnClass() {
//     let approveBtn = document.getElementsByClassName("approveReimb");
//     console.log(approveBtn);
//     Array.from(approveBtn).forEach(function (btn) {
//         btn.addEventListener('click', function (e) {
//             console.log(e.target.value);
//             approveRequest(e.target.value);
//         });
//     });
// }

// function addDenyBtnClass() {
//     let denyBtn = document.getElementsByClassName("denyReimb");
//     console.log(denyBtn);
//     Array.from(denyBtn).forEach(function (btn) {
//         btn.addEventListener('click', function (e) {
//             console.log(e.target.value);
//             denyRequest(e.target.value);
//         });
//     });
// }

function denyRequest(value) {
    let updateData = {
        newStatus: "Denied",
        reimbId: value
    }

    fetch("/reimbursements/users/update", {
        method: "PUT",
        credentials: "include",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updateData)
    })
}

function approveRequest(value) {
    let updateData = {
        newStatus: "Approved",
        reimbId: value
    }

    fetch("/reimbursements/users/update", {
        method: "PUT",
        credentials: "include",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updateData)
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

