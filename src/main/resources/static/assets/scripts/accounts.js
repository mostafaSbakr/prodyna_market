import * as httpHandler from "./httpHandler.js";

const deleteUserForm = document.querySelector('#delete-user-section form');
const table = document.querySelector('#accounts-section table');
const adminPageButton = document.querySelector('#back-to-admin-section button');

window.onload = () => {
    httpHandler.sendHttpRequest('GET', httpHandler.urlPrefix + 'get-all-users', 'json')
        .then(response => {
            for (const user of response)
                renderNewTableRow(user.userId, user.userName, user.address, user.phone, user.email, user.creditCard);
        });
}

const renderNewTableRow = (userId, userName, address, phone, email, creditCard) => {
    const tableRow = document.createElement('tr');
    tableRow.id = 'id' + userId;
    tableRow.innerHTML = `<tr>
    <td>${userId}</td>
    <td>${userName}</td>
    <td>${address}</td>
    <td>${phone}</td>
    <td>${email}</td>
    <td>${creditCard}</td>
</tr>
    `;
    table.append(tableRow);
}

const deleteTableRow = (userId) => {
    const row = table.querySelector(`#id${userId}`);
    if (row) row.remove();
    else console.log('user not in table');
}

const isRowPresent = (userId) => {
    return Boolean(table.querySelector(`#id${userId}`));
}

deleteUserForm.addEventListener('submit', event => {
    event.preventDefault();
    const userId = event.currentTarget.querySelector('input');
    const warningLabel = event.currentTarget.querySelector('#wrong-user-id-label');
    console.log(isRowPresent(userId.value));
    if (!isRowPresent(userId.value)) {
        warningLabel.style.visibility = "visible";
        warningLabel.textContent = 'User not in database';
    } else {
        warningLabel.style.visibility = "hidden";
        warningLabel.textContent = '';
        httpHandler.sendHttpRequest('DELETE', httpHandler.urlPrefix + 'delete-user?userId=' + userId.value, 'json')
            .then(response => {
                console.log(response);
                deleteTableRow(userId.value)
            });
    }
});

adminPageButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'admin'));

const backToMainButton = document.getElementById('back-to-main').firstElementChild;
backToMainButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'main'));