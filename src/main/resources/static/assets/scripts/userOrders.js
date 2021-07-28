import * as httpHandler from "./httpHandler.js";

console.log(sessionStorage.getItem('userName'));

const table = document.querySelector('#orders-table-section table');

window.onload = () => {
    httpHandler.sendHttpRequest('GET', httpHandler.urlPrefix + 'get-user-orders?userName=' +
            sessionStorage.getItem('userName'), 'json')
        .then(response => {
            console.log(response);
            for (const order of response)
                renderNewTableRow(order.orderId, order.product.name, order.price, order.orderDate)

        });
}

const renderNewTableRow = (orderId, product, price, orderDate) => {
    const tableRow = document.createElement('tr');
    tableRow.id = orderId;
    tableRow.innerHTML = `
    <td>${orderId}</td>
    <td>${product}</td>
    <td>${price}</td>
    <td>${orderDate}</td>
    `;
    table.append(tableRow);
}

const backToMainButton = document.getElementById('back-to-main').firstElementChild;
backToMainButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'main'));