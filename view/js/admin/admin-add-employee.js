$(document).ready(function(){
    var urlString = window.location.href;
    var url = new URL(urlString);
    var id = url.searchParams.get("id");

    if(id != null) {
        $.ajax({
            type: "GET",
            url: "/api/employee/" + id,
            dataType: "json",
            contentType: "application/json",
            success: function(response){
                var employee = response.data;
                $('#form-content').append(
                    "<input id='id' type='hidden' value='" + employee.id + "'>" +
                    "<div class='form-group'>" +
                    "<label>Nama</label>" +
                    "<input id='nama' type='text' value='" + employee.nama + "' class='form-control' required/>" +
                    "</div>" +
                    "<div class='form-group'>" +
                    "<label>Hp/Telp</label>" +
                    "<input id='hp' type='text' value='" + employee.hp + "' class='form-control' required/>" +
                    "</div>" +
                    "<div class='form-group'>" +
                    "<label>Email</label>" +
                    "<input id='email' type='text' value='" + employee.email + "' class='form-control' required/>" +
                    "</div>" +
                    "<div style='margin-top:50px;'>" +
                    "<button id='update-employee' value='" + employee.id + "' class='btn btn-primary' type='submit' style='background-color:#000000;'>Save</button>" +
                    "</div>"
                );
            },
            error: function(){
                alert("Error");
            }
        });
    }
    else {
        $('#form-content').append(
            "<input id='id' type='hidden'>" +
            "<div class='form-group'>" +
            "<label>Nama</label>" +
            "<input id='nama' type='text' class='form-control' required/>" +
            "</div>" +
            "<div class='form-group'>" +
            "<label>Hp/Telp</label>" +
            "<input id='hp' type='text' class='form-control' required/>" +
            "</div>" +
            "<div class='form-group'>" +
            "<label>Email</label>" +
            "<input id='email' type='text' class='form-control' required/>" +
            "</div>" +
            "<div style='margin-top:50px;'>" +
            "<button id='save-employee' class='btn btn-primary' type='submit' style='background-color:#000000;'>Save</button>" +
            "</div>"
        );
    }
    $(document).on("click", "#update-employee", function () {
        var  id = $(this).val();
        var data = {
            nama: $('#nama').val(),
            hp: $('#hp').val(),
            email: $('#email').val()
        };
        $.ajax({
            type: "PUT",
            url: "/api/employee/" + id,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                alert("Employee saved");
                location.href="admin-employee.html";
            },
            error: function (url) {
                alert("Error");
                location.href = "admin-employee.html";
            }
        });
    });
    $(document).on("click", "#save-employee", function () {
        var data = {
            nama: $('#nama').val(),
            hp: $('#hp').val(),
            email: $('#email').val()
        };
        $.ajax({
            type: "POST",
            url: "/api/employee",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                alert("Employee saved");
                location.href="admin-employee.html";
            },
            error: function (url) {
                alert("Error");
                location.href = "admin-employee.html";
            }
        });
    });
});