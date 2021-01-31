<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>
<script type="text/javascript">
    $(document).ready(function () {
        alert("33 afterSuccess")
        $.ajax({
            type: 'Post',
            url: 'http://localhost:8080/td_job4j/info',
            dataType: 'json',
        }).done(function (data) {
            console.log(data + ": 38 ready Function")
            let table = $('#table')
            data.forEach(function (item, i) {
                table = $('#table')
                table.append('<tr>')
                table.append('<td>' + item.id + '</td>')
                table.append('<td>' + item.description + '</td>')
                table.append('<td>' + item.created + '</td>')
                console.log(item.dataType + "ItemDataType")
                table.append('<td>' + item.done + '</td>')
                table.append('</tr>');
            });
        }).fail(function (err) {
            alert("Fail 76 " + err)
        })
    });

    function addNewTask() {
        alert("57")
        let description = $("#text").val()
        alert("Descrption 59 :" + description)
        console.log(description)
        if (description !== '') {
            $.ajax({
                type: 'Get',
                data: {description: description},
                url: 'http://localhost:8080/td_job4j/add'
            }).done(function () {
                return true;
            });
        } else {
            alert("Пожалуйста, заполниете поле \'описание задачи\'")
            return false;
        }
    };
</script>

<div class="container">
    <div class="row pt-3">
        <table class="table table-bordered" style="background-color: azure">
            <thead>
            <tr>
                <h5>
                    Добавить новую задачу
                </h5>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <form>
                        Ведите описание задачи: <label for="text"></label><input type="text" id="text"
                                                                                 style="width: 600px"/><br/>
                        <button type="reset" onclick="return addNewTask()" id="add" class="btn btn-success">
                            Добавить задачу
                        </button>
                    </form>
                </td>
                <td>
                    <form method="get" action="<%=request.getContextPath()%>/info">
                        <p>Выберете все ли задания показывать</p>
                        <p><input type="checkbox" id="0" name="turn" value="0"> ONLY DONE TASK</p>
                        <p><input type="checkbox" id="1" name="turn" value="1"> All TASK </p>
                        <p><input type="submit" value="Отправить"></p>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>

    </div>
</div>

<div class="container">
    <div class="row pt-3">
        <h1>
            TASK LIST.
        </h1>

        <table class="table table-bordered" style="background-color: azure">
            <thead>
            <tr>
                <th>ID</th>
                <th style="width: 400px">Task</th>
                <th>Date of Creation</th>
                <th>Result</th>
            </tr>
            </thead>
            <tbody id="table"></tbody>
        </table>
    </div>
</div>
</body>
</html>