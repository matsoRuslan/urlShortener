<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <title>URL shortener</title>
</head>

<body>
<div class="container">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">URL Shortener</h1>
                <p>Short it man!</p>
                <hr/>
            </div>
        </div>
        <div class="main-login main-center">
            <form class="form-horizontal" method="post" action="/convert">
                <div class="form-group">
                    <label for="search_item" class="cols-sm-2 control-label">URL input</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="fullURL" id="search_item"
                                   placeholder="Enter your URL"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit"
                            class="btn btn-primary btn-lg btn-block login-button">Shorten URL</button>
                </div>

            </form>
            <form class="form-horizontal" method="post" action="/reverse">
                <div class="form-group">
                    <label for="search_item" class="cols-sm-2 control-label">Shortened URL input</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa" aria-hidden="true"></i></span>

                            <input type="text" class="form-control" name="convertedURL"
                                   placeholder="Enter your shortened URL"/>

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit"
                            class="btn btn-danger btn-lg btn-block login-button">Find original URL</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>

</html>