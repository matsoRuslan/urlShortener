$(document).ready(function () {

    $.ajax({
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: window.location.href,
        // data: "" + productId.data.param1,
        data:window.location.href,
        success: function () {
            location.reload();
        }
    });




});