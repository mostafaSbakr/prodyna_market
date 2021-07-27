import * as httpHandler from "./httpHandler.js";

const viewProductsButton = document.getElementById('admin-views').firstElementChild;
const viewAccountsButton = document.getElementById('admin-views').lastElementChild;

viewProductsButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'products'));
viewAccountsButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'accounts'));

const backToMainButton = document.getElementById('back-to-main').firstElementChild;
backToMainButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'main'));