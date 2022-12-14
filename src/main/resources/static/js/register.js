function checkAuthor() {
    var checkBox = document.getElementById("nickname-checkbox");
    var text = document.getElementById("nickname-text");

    if (checkBox.checked == true) {
        text.type = "text";
    } else {
        text.type = "hidden";
    }
}