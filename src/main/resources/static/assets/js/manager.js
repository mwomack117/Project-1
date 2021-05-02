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
        let userInfoElement = document.querySelector("#user");
        userInfoElement.innerHTML = `Successfully logged in as: ${username}`;
    })
}

let radioButtons = document.querySelectorAll('input[name="btnRadio"]');
radioButtons.forEach(item => {
    item.addEventListener("click", function (event) {
        console.log(item);
        if (event.target.value == "All") {
            console.log("all function ran")
            clearTable();
            retrieveAllReimbs();
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

function retrieveAllReimbs() {
    fetch("/reimbursements/users", {
        method: "GET",
        credentials: "include"
    }).then((data) => {
        return data.json();
    }).then((response) => {
        console.log("all", response);
        populateData(response);
    })
}

function filterReimbs(filterValue) {
    let data = {
        reimbStatus: filterValue
    }
    fetch("/reimbursements/status", {
        method: "POST",
        credentials: "include",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        return response.json();

    }).then(jsonResponse => {
        populateData(jsonResponse);
        console.log("I'm filtered", jsonResponse);
    })
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
        const formattedDate = dateObject.toDateString(); //change toLocalString() when full data + time is retrieved
        tdSubmit.innerHTML = formattedDate;

        let tdReceipt = document.createElement('td');
        tdReceipt.innerHTML = item.receipt;

        let tdStatus = document.createElement('td');
        tdStatus.innerHTML = item.reimbStatus.reimbStatus;

        let tdChangeStatus = document.createElement('td');
        tdChangeStatus.innerHTML = `<div>
                                        <button type="button" class="btn btn-sm btn-success approveReimb" value=${item.id}>&check;</button>
                                        <button type="button" class="btn btn-sm btn-danger denyReimb" value=${item.id}>X</button>
                                    </div>`

        let tdRslvdDate = document.createElement("td");
        tdRslvdDate.innerHTML = item.reimbResolved;

        let tdRslvdBy = document.createElement('td');
        tdRslvdBy.innerHTML = item.resolver;

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

    // let approveBtn = document.getElementsByClassName("approveReimb");
    // console.log(approveBtn);
    // Array.from(approveBtn).forEach(function (btn) {
    //     btn.addEventListener('click', function (e) {
    //         console.log(e.target.value);
    //     });
    // });

    // let denyBtn = document.getElementsByClassName("denyReimb");
    // console.log(denyBtn);
    // Array.from(denyBtn).forEach(function (btn) {
    //     btn.addEventListener('click', function (e) {
    //         console.log(e.target.value);
    //     });
    // });

}


document.getElementById("sign-out").addEventListener("click", logout);

function logout() {
    document.cookie = "JSESSIONID=; expires=Thu, 18 Dec 2013 12:00:00 UTC; path=/";
    fetch("/user/logout", {
        method: "POST",
        credentials: "include"
    })
}

