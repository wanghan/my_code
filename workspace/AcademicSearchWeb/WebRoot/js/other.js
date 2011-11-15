
function setbg1(temp) {
    var obj = temp;
    if (obj.className == "Cstyle") {
        obj.className = "Cstyle";
    } else {
        obj.className = "";
    }
}
function setbg2(temp) {
    var obj = temp;
    if (obj.className == "Cstyle") {
        obj.className = "Cstyle";
    } else {
        obj.className = "Ovstyle";
    }
}
function invalidateNumberKey(event) {
    var temp = window.event || event;
    var keyCode = temp.keyCode || temp.which;
    if (!KeyIsNumber(keyCode)) {
        return false;
    }
}
function KeyIsNumber(KeyCode) {
    if (((KeyCode > 47) && (KeyCode < 58)) || (KeyCode == 8)) {
        return true;
    } else {
        return false;
    }
}
function selectAllItem() {
    var items = document.getElementsByName("items");
    if (items != null) {
        for (counter = 0; counter < items.length; counter++) {
            items[counter].checked = "checked";
        }
    }
}
function selectNoneItem() {
    var items = document.getElementsByName("items");
    if (items != null) {
        for (counter = 0; counter < items.length; counter++) {
            items[counter].checked = "";
        }
    }
}

