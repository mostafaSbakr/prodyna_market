import * as httpHandler from './httpHandler.js';

const user = JSON.parse(sessionStorage.getItem('user'));

const userSection = document.getElementById('user-details-section');

// console.log(user);

const firstNameLabel = userSection.querySelector('#first-name-label');
firstNameLabel.innerHTML = `${user.firstName}`;
const lastNameLabel = userSection.querySelector('#last-name-label');
lastNameLabel.innerHTML = `${user.lastName}`;
const birthDateLabel = userSection.querySelector('#birth-date-label');
birthDateLabel.innerHTML = `${user.dateOfBirth}`;
const emailLabel = userSection.querySelector('#email-label');
emailLabel.innerHTML = `${user.email}`;
const phoneLabel = userSection.querySelector('#phone-label');
phoneLabel.innerHTML = `${user.phone}`;
const addressLabel = userSection.querySelector('#address-label');
addressLabel.innerHTML = `${user.address}`;
const creditCardLabel = userSection.querySelector('#credit-card-label');
creditCardLabel.innerHTML = `${user.creditCard}`;


const editProfileButton = document.getElementById('edit-profile-section').firstElementChild;
editProfileButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'sign-up'));

const backToMainButton = document.getElementById('back-to-main').firstElementChild;
backToMainButton.addEventListener('click', () => httpHandler.redirect(httpHandler.urlPrefix + 'main'));