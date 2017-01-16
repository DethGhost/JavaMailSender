/**
 * Created by DethGhost on 09.01.2017.
 */
var passwordF;
var show_button;
var form;
var deleteGroup;
var whatToDo;
function go() {

    passwordF = document.getElementById('password');
    show_button = document.getElementById('show-button');
    form = document.getElementById('form');
    deleteGroup = document.getElementById('deleteGroup');
    whatToDo = document.getElementById('whatToDo');
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
function checkThat() {
    var action = "/setting/subscriber-group-list";
    form.method = "get";
    whatToDo.setAttribute('name', "whatToDo");
    if (confirm("Do you really want to delete")) {
        whatToDo.click();
        action = "/setting/delete-group";
        form.method = "post";
    }
    form.action = action;
}