function sendRequest(x, y, r) {
    $.post('controller', {
        'x': x,
        'y': y,
        'r': r,
    }).done((data) => console.log(data));
}

function clickOnCanvas(canvas, event) {
    let rect = canvas.getBoundingClientRect()
    let width = canvas.width;
    let height = canvas.height;
    let x = (event.clientX - rect.left - width / 2) / step; // - 0.45;
    let y = (height / 2 - event.clientY + rect.top) / step; // + 0.48;
    let R = document.querySelector('input[name="R"]:checked');
    let r = R.value;
    x = x.toFixed(2).replace(".00", "");
    y = y.toFixed(2).replace(".00", "");
    console.log(x, y, r);
    drawPoint(x, y);
    sendRequest(x, y, r);
}

let errorX = document.getElementById("errorX");
let errorY = document.getElementById("errorY");
let errorC = document.getElementById("errorC");
let errorV = document.getElementById("errorV");

let pointsXArray = [];
let pointsYArray = [];
let pointsRArray = [];


let text_field = document.getElementById("paramX");

