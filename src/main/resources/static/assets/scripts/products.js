import * as httpHandler from "./httpHandler.js";

const table = document.querySelector('#products-section table');
const saveProductForm = document.querySelector('#save-products-section form');
const deleteProductForm = document.querySelector('#delete-products-section form');
const adminPageButton = document.querySelector('#back-to-admin-section button');
const backToMainButton = document.getElementById('back-to-main').firstElementChild;

window.onload = () => {
    httpHandler.sendHttpRequest('GET', httpHandler.urlPrefix + 'get-all-products', 'json')
        .then(response => {
            for (const product of response)
                renderNewTableRow(product.name, product.price, product.quantity);
        });
}

class Product {
    constructor(name, price, quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

saveProductForm.addEventListener('submit', event => {
    event.preventDefault();
    const productData = event.currentTarget.querySelectorAll('input');
    const wrongProductInfoLabel = event.currentTarget.querySelector('#wrong-product-info');

    if (productData[0].value.length === 0 || productData[1].value.length === 0 || productData[2].value.length === 0) {
        wrongProductInfoLabel.style.visibility = 'visible';
        wrongProductInfoLabel.textContent = 'invalid product info';
    } else {
        wrongProductInfoLabel.style.visibility = 'hidden';
        const product = new Product(productData[0].value, productData[1].value, productData[2].value);
        httpHandler.sendHttpRequest('POST', httpHandler.urlPrefix + 'save-product', 'json', product)
            .then(response => {
                if (response) {
                    renderNewTableRow(productData[0].value, productData[1].value, productData[2].value)
                } else {
                    wrongProductInfoLabel.style.visibility = 'visible';
                    wrongProductInfoLabel.textContent = 'duplicate product name';
                }
            });
    }

})


const isProductPresent = (productName) => {
    if (productName.length === 0) return false;
    return Boolean[table.querySelector(`#${productName}`)];
}

deleteProductForm.addEventListener('submit', event => {
    event.preventDefault();
    const productName = event.currentTarget.querySelector('input');
    const wrongProductNameLabel = event.currentTarget.querySelector('#wrong-product-name');

    if (!isProductPresent(productName.value)) {
        wrongProductNameLabel.style.visibility = 'visible';
        wrongProductNameLabel.textContent = 'invalid product name';
    } else {
        wrongProductNameLabel.style.visibility = 'hidden';
        httpHandler.sendHttpRequest('DELETE', httpHandler.urlPrefix + 'delete-product?productName=' + productName.value, 'json')
            .then(() => deleteTableRowByName(productName.value));
    }

})

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

const deleteTableRowByName = (productName) => {
    const row = table.querySelector(`#${productName}`);
    if (row) row.remove();
    else console.log('product not found'); // error handling later --> output a label to user
}

adminPageButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'admin'));
backToMainButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'main'));