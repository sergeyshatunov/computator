<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Computator</title>
    <link href="${pageContext.request.contextPath}/resources/css/main.css?2" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div class="container">
    <div class="header"><h1>Computator</h1></div>

    <div class="content">
        <div class="input" id="arithmetic">
            <span><span>Arithmetic expressions</span> or <a href="${pageContext.request.contextPath}/quadratic" id="toggle_quadratic">quadratic equation</a></span>
            <form action="${pageContext.request.contextPath}/arithmetic" method="post" name="compute">
                <input type="text" id="input_arithmetic" name="expression" value=""/><input type="submit" name="submit" id="submit_arithmetic" value="="/>
            </form>
        </div>

        <div class="input" id="quadratic">
            <span><a href="${pageContext.request.contextPath}/arithmetic" id="toggle_arithmetic">Arithmetic expressions</a> or <span>quadratic equation</span></span>
            <form action="${pageContext.request.contextPath}/quadratic" method="post" name="compute">
                <span id="quadratic_container"><input type="text" id="input_quadratic" name="a" placeholder="a" value=""/>x&#178; +<input type="text" name="b" placeholder="b" value=""/>x +<input type="text" name="c" placeholder="c" value=""/></span><input type="submit" name="submit" id="submit_quadratic" value="="/>
            </form>
        </div>
        <c:if test="${not empty expression}"><div class="output"><p class="output-field">${expression}</p></div></c:if>
        <c:if test="${not empty resultX1}"><div class="output"><p class="output-field">X1: ${resultX1}</p></div></c:if>
        <c:if test="${not empty resultX2}"><div class="output"><p class="output-field">X2: ${resultX2}</p></div></c:if>
    </div>

    <div class="footer"><p class="copyright">2017, made with &#9829;</p></div>
</div>

<script>
    $("#input_arithmetic").first().focus();

    $("#quadratic").hide();

    $("#toggle_quadratic").click(function (e) {
        $("#quadratic, #arithmetic").toggle();
        $("#input_quadratic").focus();
        e.preventDefault();
    });

    $("#toggle_arithmetic").click(function (e) {
        $("#quadratic, #arithmetic").toggle();
        $("#input_arithmetic").focus();
        e.preventDefault();
    });
</script>

</body>
</html>
