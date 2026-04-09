const API_URL = "https://jsonplaceholder.typicode.com/users";

async function fetchUsers() {
    try {
        const response = await fetch(API_URL);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log("Error:", error);
    }
}

function displayUsers(users) {
    const tableBody = document.getElementById("tableBody");

    tableBody.innerHTML = "";

    users.forEach(user => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.address.city}</td>
        `;

        tableBody.appendChild(row);
    });
}

async function loadUsers() {
    const users = await fetchUsers();
    displayUsers(users);
}