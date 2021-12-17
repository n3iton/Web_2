function sendRequest(xArr, y, r) {
    $.post('controller', {
        'xArr': xArr,
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
    let R = document.querySelector('input[name="r"]:checked');
    let r = R.value;
    x = x.toFixed(2).replace(".00", "");
    y = y.toFixed(2).replace(".00", "");
    console.log(x, y, r);
    drawPoint(x, y);
    sendRequest([x], y, r);
}

function submitForm() {
    let xArr = document.querySelectorAll('input[name="xArr"]:checked');
    let y = document.getElementById('Y').value;
    let r = document.querySelector('input[name="r"]:checked').value;
    xArr = Object.values(xArr);
    xArr = xArr.map(element => element.value);
    xArr.forEach(x => drawPoint(x, y));
    sendRequest(xArr, y, r);
}



// let errorX = document.getElementById("errorX");
// let errorY = document.getElementById("errorY");
// let errorC = document.getElementById("errorC");
// let errorV = document.getElementById("errorV");

// let pointsXArray = [];
// let pointsYArray = [];
// let pointsRArray = [];

let rArray = document.getElementsByName("r");
rArray.forEach((radioButton) =>
    radioButton.addEventListener('click', refreshCanvas));


loadCanvas();
document.getElementById("submit").addEventListener('click', submitForm);


$.post('controller', {
    'reload': 'true',
}).done((data) => console.log(data));


