import * as httpHandler from "./httpHandler.js";

const form = document.querySelector('#user-data form');

form.addEventListener('submit', event => {
    event.preventDefault();
    const userData = form.querySelectorAll('input');
    const wrongCredentialsLabel = form.querySelector('#wrong-admin-credentials');
    const user = {
        userName: userData[0].value,
        password: userData[1].value
    }

    httpHandler.sendHttpRequest('POST', httpHandler.urlPrefix + '/admin-page', 'json', user)
        .then(response => {
            if (response) {
                wrongCredentialsLabel.style.visibility = "hidden";
                httpHandler.redirect(httpHandler.urlPrefix + 'admin');
            } else {
                wrongCredentialsLabel.style.visibility = "visible";
                wrongCredentialsLabel.textContent = 'wrong admin credentials';
            }
        });
});

const backToMainButton = document.getElementById('back-to-main').firstElementChild;
backToMainButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'main'));