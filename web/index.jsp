<%--
  Created by IntelliJ IDEA.
  User: neiton
  Date: 12/2/21
  Time: 1:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Web2</title>
</head>
<body>
<div class="header">
    <h1 class="header__FIO">Ермаков Никита Сергеевич</h1>
    <h2 class="header__groupNumber">Группа P3212</h2>
    <h3 class="header__variant">Вариант 12038</h3>
</div>

<div class="main">
    <div class="main__input">
        <form action="/controller" method="post">
        <div id="chooseX">
            <legend id="wrong_field_X" >Choose X</legend>
            <input type="checkbox" class="custom-checkbox" name="X" id = "1" value="-5"><label for="1">-5</label><br>
            <input type="checkbox" class="custom-checkbox" name="X" id = "2" value="-4"><label for="2">-4</label><br>
            <input type="checkbox" class="custom-checkbox" name="X" id = "3" value="-3"><label for="3">-3</label><br>
            <input type="checkbox" class="custom-checkbox" name="X" id = "4" value="-2"><label for="4">-2</label><br>
            <input type="checkbox" class="custom-checkbox" name="X" id = "5" value="-1"><label for="5">-1</label><br>
            <input type="checkbox" class="custom-checkbox" name="X" id = "6" value="0"><label for="6">0</label><br>
            <input type="checkbox" class="custom-checkbox" name="X" id = "7" value="1"><label for="7">1</label><br>
            <input type="checkbox" class="custom-checkbox" name="X" id = "8" value="2"><label for="8">2</label><br>
            <input type="checkbox" class="custom-checkbox" name="X" id = "9" value="3"><label for="9">3</label><br>
        </div>
        <div id="chooseY">
            <div>
                <legend id="wrong_field_Y">Choose Y</legend>
                <input type="text" name="Y" id = "Y" placeholder="Choose Y from -5 to 5">
            </div>
            <button type="submit" id="submit">Отправить</button>
        </div>
        <div id=chooseR>
            <legend id="wrong_field_R">Choose R</legend>
            <input class="custom-radio" type="radio" name="R" id="10" value="1"><label for="10">1</label>
            <input class="custom-radio" type="radio" name="R" id="11" value="1.5"><label for="11">1.5</label>
            <input class="custom-radio" type="radio" name="R" id="12" value="2"><label for="12">2</label>
            <input class="custom-radio" type="radio" name="R" id="13" value="2.5"><label for="13">2.5</label>
            <input class="custom-radio" type="radio" name="R" id="14" value="3"><label for="14">3</label>
        </div>
        </form>
    </div>
    <div class="main__area">
        <canvas id="canvas"></canvas>
    </div>
</div>

<jsp:include page="hitsTable.jsp"/>
<div>
    <button id="clear">Очистить</button>
</div>

<div class="footer">
    <div>
        <a href="https://github.com/n3iton/Web_1">
            <img class="github" src="GitHub-Mark-64px.png" width="50" height="50" alt="github">
        </a>
    </div>
</div>

<script src="formActions.js"></script>
<script src="canvasTest.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>
