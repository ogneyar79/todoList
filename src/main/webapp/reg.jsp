<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 21.02.2021
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>REGISTRATION IN LIST OF TASK</title>
    <script>
        function validate() {
            alert('function validate')
            let email = $('#email').val();
            let password = $('#password').val();
            let password2 = $('#password2').val();
            let reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
            let regPasword;
            if (!reg.test(email)) {
                console.log(email)
                alert('Введите корректный e-mail');
                return false;
            }
            if (!email.match(reg)) {
                console.log(email)
                alert('EMAIL Match INCORRECT ');
                return false
            }
            if (!regPasword.test(password)) {
                console.log(password);
                alert('Password  incorrect ');
                return false
            }
            if (!password === password2) {
                alert('Password  and Repeating Password do not natch  incorrect ');
                return false;
            }
            return true;
        }
        valid.addEventListener("click", validate());
    </script>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Регистрация
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/reg" method="post">
                    <div class="form-group">
                        <label>Имя</label>
                        <input type="text" class="form-control" id="name" title="имя" name="name">
                    </div>
                    <div class="form-group">
                        <label>Почта</label>
                        <input type="text" class="form-control" id="email" title="почта" name="email">
                    </div>
                    <div class="form-group">
                        <label>Пароль</label>
                        <input type="text" class="form-control" id="password" title="пароль" name="password">
                    </div>
                    <div class="form-group">
                        <label>Повторите пароль</label>
                        <<input type="password" name="password2" id="password2"/>>
                    </div>
                    <button id="valid" type="submit" class="btn btn-primary" >Зарегистрироваться</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
