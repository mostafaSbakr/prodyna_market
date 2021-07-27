import * as httpHandler from './httpHandler.js';

sessionStorage.clear();

const signUpButton = document.getElementById('sign-up-button');
const signInButton = document.getElementById('sign-in-button');
const adminButton = document.getElementById('admin-button');

const redirectHandler = url => {
    httpHandler.redirect(url);
}

signUpButton.addEventListener('click',
    redirectHandler.bind(null, httpHandler.urlPrefix + 'sign-up'));

// signUpButton.addEventListener('click',
//     sendHttpRequest.bind(null, 'GET', httpHandler.urlPrefix + 'sign-up'));

signInButton.addEventListener('click',
    redirectHandler.bind(null, httpHandler.urlPrefix + 'sign-in'));

adminButton.addEventListener('click',
    redirectHandler.bind(null, httpHandler.urlPrefix + 'admin-sign-in'));