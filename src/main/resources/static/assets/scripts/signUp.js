import * as httpHandler from "./httpHandler.js";

const user = JSON.parse(sessionStorage.getItem('user'));

console.log(user);


class User {
    constructor(userId, firstName, lastName, dateOfBirth,
        phone, address, email, userName, password, creditCard) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.creditCard = creditCard;
    }
}

const form = document.querySelector('#new-user form');

const inputs = form.querySelectorAll('input');
console.log(inputs);

if (user) {
    inputs[0].value = user.userId;
    inputs[1].value = user.firstName;
    inputs[2].value = user.lastName;
    inputs[3].value = user.dateOfBirth;
    inputs[4].value = user.phone;
    inputs[5].value = user.address;
    inputs[6].value = user.email;
    inputs[7].value = user.userName;
    inputs[8].value = user.password;
    inputs[9].value = user.creditCard;
}

// Save and Login Button
form.addEventListener('submit', event => {
    event.preventDefault();
    const newUser = new User(inputs[0].value, inputs[1].value, inputs[2].value,
        inputs[3].value, inputs[4].value, inputs[5].value,
        inputs[6].value, inputs[7].value, inputs[8].value, inputs[9].value);
    console.log(newUser);
    httpHandler.sendHttpRequest('POST', httpHandler.urlPrefix + '/new-user', 'json', newUser)
        .then(() => {
            httpHandler.redirect(httpHandler.urlPrefix + 'sign-in')
            sessionStorage.setItem('newUserName', newUser.userName);
        });
})

const backToMainButton = document.getElementById('back-to-main').firstElementChild;
backToMainButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'main'));