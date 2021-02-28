<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 14.02.2021
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <title>LOGIN</title>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Авторизация
                <label>
                    <ul class="nav">
                        <li class="nav-task">
                            <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Регистрация</a>
                        </li>
                    </ul>
                </label>
            </div>
            <div class="card-body">
                <form id="myForm" class="form" action="<%=request.getContextPath()%>/auth" method="post">
                    <div class="form-group">
                        <label>Почта</label>
                        <input type="text" class="form-control" id="email" title="почта" name="email">
                    </div>
                    <div class="form-group">
                        <label>Пароль</label>
                        <input type="text" class="form-control" id="password" title="пароль" name="password">
                    </div>
                    <button type="submit" class="btn btn-primary">Войти</button>
                </form>

            </div>
        </div>
    </div>
</div>
<script>
    const ell = $('#myForm')
    ellDomDoc = document.getElementById('myForm');
    function validateFilling(event) {
        console.log("validateFiling 2")
        alert("29 Login JSP Method validateFilling with  val.addEventListener");
        const msg = "please fill in the field ";
        if ($('#email').val() === '') {
            event.preventDefault();
            alert(msg + $('#email').attr('title'));
            return;
        }
        if ($('#password').val() === '') {
            event.preventDefault();
            return;
        }
    }
    ellDomDoc.addEventListener('submit', validateFilling);
</script>
</body>
</html>
