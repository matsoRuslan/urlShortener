<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

    <title>Success!</title>
</head>

<body>
<div class="container">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">URL Shortener</h1>
                <p>Success!</p>
                <hr/>
            </div>
        </div>
        <div class="main-login main-center">
            <form class="form-horizontal" method="post">
                <div class="form-group">
                    <ul class="list-group">
                        <li class="list-group-item list-group-item-warning"><span class="label label-success">Inserted URL:</span> ${convertedURL}
                        </li>
                        <li class="list-group-item list-group-item-warning"><span class="label label-success">Original URL:</span> ${fullURL}
                        </li>
                        <li class="list-group-item list-group-item-warning"><span class="label label-success">Creation time:</span> ${createdON}
                        </li>
                    </ul>
                    <div class="cols-sm-10">
                    </div>
                </div>

                <div class="form-group">
                    <a href="/home" class="btn btn-primary btn-lg btn-block login-button">Back</a>
                </div>
            </form>

        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>