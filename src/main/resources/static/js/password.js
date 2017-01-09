/**
 * Created by DethGhost on 09.01.2017.
 */
var passwordF;
var show_button;
var title;
function go() {

    passwordF = document.getElementById('password');
    show_button = document.getElementById('show-button');
}

function show() {
    if (passwordF.getAttribute('type') == "password") {
        passwordF.setAttribute('type', "text");
        show_button.innerText = "Hide password";

    } else {
        passwordF.setAttribute('type', "password");
        show_button.innerText = "Show password";

    }
}