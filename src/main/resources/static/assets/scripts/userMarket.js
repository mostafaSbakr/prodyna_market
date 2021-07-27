import * as httpHandler from "./httpHandler.js";

const userName = sessionStorage.getItem('userName');

const h1Element = document.querySelector('header h1');
h1Element.textContent = `Hello ${userName}`;

const table = document.getElementById('products-section').firstElementChild;

window.onload = () => {
    httpHandler.sendHttpRequest('GET', httpHandler.urlPrefix + 'get-all-products', 'json')
        .then(response => {
            for (const product of response)
                renderNewTableRow(product.name, product.price, product.quantity);
        });

    httpHandler.sendHttpRequest('GET', httpHandler.urlPrefix + 'get-user?userName=' + userName, 'json')
        .then(response => {
            sessionStorage.setItem('user', JSON.stringify(response))
        });
}

const renderNewTableRow = (productName, price, quantity) => {
    const tableRow = document.createElement('tr');
    tableRow.id = productName;
    tableRow.innerHTML = `
    <td>${productName}</td>
    <td>${price}</td>
    <td>${quantity}</td>
    `;
    table.append(tableRow);
}

const getTableRow = (productName) => {
    return table.querySelector(`#${productName}`);
}

const isOrderValid = (productName, quantity) => {
    try {
        const productRow = table.querySelector(`#${productName}`);
        const productQuantity = productRow.lastElementChild.textContent;
        return [Boolean(productRow) && parseInt(quantity) < parseInt(productQuantity), parseInt(productQuantity) - parseInt(quantity)];
    } catch (error) {
        return [false, 0];
    }
}

const updateTableRow = (productName, updatedQuantity) => {
    const updatedTableRow = table.querySelector(`#${productName}`);
    updatedTableRow.lastElementChild.innerHTML = `
    <td>${updatedQuantity}</td>`;
}

let order = {};

const ordersListSection = document.querySelector('#cart');
const ordersList = ordersListSection.firstElementChild;
const confirmButton = ordersListSection.querySelector('button');
const confirmLabel = ordersListSection.querySelector('#confirmed-label');
confirmButton.addEventListener('click', () => {
    console.log(ordersList);
    if (order.ready) {
        console.log('ready');
        httpHandler.sendHttpRequest('POST', httpHandler.urlPrefix +
                'new-order?name=' + order.name + '&quantity=' + order.quantity +
                '&userName=' + sessionStorage.getItem('userName'), 'json')
            .then(() => updateTableRow(order.name, order.updatedQuantity));
        order.ready = false;
        confirmLabel.style.visibility = 'visible';
        ordersList.innerHTML = '';
        confirmButton.disabled = true;
    }
})

const renderNewListElement = (name, quantity, price) => {
    const li = document.createElement('li');
    li.id = name;
    li.innerHTML = `${quantity} ${name}. Price: $${price * quantity}`;
    ordersList.append(li);
}

const addToCartForm = document.querySelector('#buy-product form');
addToCartForm.addEventListener('submit', event => {
    event.preventDefault();
    const orderDetails = event.currentTarget.querySelectorAll('input');
    const noProductLabel = event.currentTarget.querySelector('#no-product-label');
    const orderValidation = isOrderValid(orderDetails[0].value, orderDetails[1].value);
    if (!orderValidation[0]) {
        noProductLabel.style.visibility = 'visible';
        noProductLabel.textContent = 'invalid order';
    } else {
        noProductLabel.style.visibility = 'hidden';
        ordersListSection.style.visibility = 'visible';
        const price = getTableRow(orderDetails[0].value).firstElementChild.nextElementSibling.innerHTML;
        renderNewListElement(orderDetails[0].value, orderDetails[1].value, price);
        order = {
            name: orderDetails[0].value,
            quantity: orderDetails[1].value,
            price: parseFloat(price),
            updatedQuantity: orderValidation[1],
            ready: true
        };
        confirmLabel.style.visibility = 'hidden';
        confirmButton.disabled = false;
    }
})

const viewMyOrdersButton = document.getElementById('user-views').firstElementChild;
viewMyOrdersButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'user-orders'));

const viewMyProfileButton = document.getElementById('user-views').lastElementChild;
viewMyProfileButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'user-profile'));

const backToMainButton = document.getElementById('back-to-main').firstElementChild;
backToMainButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'main'));