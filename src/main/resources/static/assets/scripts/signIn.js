import * as httpHandler from "./httpHandler.js";

const form = document.querySelector('#user-data form');

form.addEventListener('submit', event => {
    event.preventDefault();
    const userData = form.querySelectorAll('input');
    const wrongCredentialsLabel = form.querySelector('#wrong-login-credentials');
    sessionStorage.setItem('userName', userData[0].value);
    httpHandler.sendHttpRequest('POST', httpHandler.urlPrefix + '/user-page?userName=' + userData[0].value +
            '&password=' + userData[1].value, 'json')
        .then(response => {
            if (response) {
                wrongCredentialsLabel.style.visibility = "hidden";
                httpHandler.redirect(httpHandler.urlPrefix + 'user-market');
            } else {
                wrongCredentialsLabel.style.visibility = "visible";
                wrongCredentialsLabel.textContent = 'wrong credentials';
            }
        });
});


const backToMainChild = document.getElementById('back-to-main').firstElementChild;
backToMainChild.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'main'));

const userNameTextInput = form.querySelector('#user-name-input');
userNameTextInput.value = sessionStorage.getItem('newUserName');