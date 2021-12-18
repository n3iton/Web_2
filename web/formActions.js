function sendRequest(xArr, y, r) {
    $.post('controller', {
        'xArr': xArr,
        'y': y,
        'r': r,
    }).done((data) => {
            let arrayOfResults = JSON.parse(data);
            arrayOfResults.forEach(result => {
                    let nRow = '<tr>';
                    for (let key in result) {
                        nRow += `<td>${result[key]}</td>`;
                    }
                    nRow += '</tr>';
                    $('#result-table').append(nRow);
            });
    });
}

function clickOnCanvas(canvas, event) {
    if (checkR()) {
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
}

function submitForm() {
    if (checkX() && checkY() && checkR()) {
        let xArr = document.querySelectorAll('input[name="xArr"]:checked');
        let y = document.getElementById('Y').value;
        let r = document.querySelector('input[name="r"]:checked').value;
        xArr = Object.values(xArr);
        xArr = xArr.map(element => element.value);
        xArr.forEach(x => drawPoint(x, y));
        sendRequest(xArr, y, r);
    }
}

function checkY() {
    let y = document.getElementById("Y");
    if (y.value.trim() === "") {
        wrongFieldY.textContent = "Поле Y должно быть заполнено";
        return false;
    }
    y.value = y.value.substring(0, 10).replace(',', '.');
    if (!(y.value && !isNaN(y.value))) {
        wrongFieldY.textContent = "Y должен быть числом!";
        return false;
    }
    if (y.value <= -5 || y.value >= 5) {
        wrongFieldY.textContent = "Y должен принадлежать промежутку: (-5; 5)!";
        return false;
    }
    return true;
}

function checkX() {
    let xButtons = document.getElementsByName("xArr");
    let counter = 0;
    xButtons.forEach(x => {
        if (x.checked)
            counter++
    });
    if (counter === 0) {
        wrongFieldX.textContent = "Вы должны выбрать минимум одно значение X";
        return false
    }
    return true;
}

function checkR() {
    let rButtons = document.getElementsByName("r");
    let counter = 0;
    rButtons.forEach(r => {
        if (r.checked)
            counter++
    });
    if (counter === 0) {
        wrongFieldR.textContent = "Вы должны выбрать одно значение R";
        return false
    }
    return true;
}

let wrongFieldX = document.getElementById("wrong_field_X");
let wrongFieldY = document.getElementById("wrong_field_Y");
let wrongFieldR = document.getElementById("wrong_field_R");

let rArray = document.getElementsByName("r");
rArray.forEach((radioButton) =>
    radioButton.addEventListener('click', refreshCanvas));

document.getElementById("submit").addEventListener('click', submitForm);
loadCanvas();

$.post('controller', {
    'reload': 'true',
}).done((data) => {
    let arrayOfResults = JSON.parse(data);
    $(".scroll-table tr:gt(0)").remove();
    arrayOfResults.forEach(result => {
        let nRow = '<tr>';
        for (let key in result) 
            nRow += `<td>${result[key]}</td>`;
        nRow += '</tr>';
        $('#result-table').append(nRow);
    });
});


