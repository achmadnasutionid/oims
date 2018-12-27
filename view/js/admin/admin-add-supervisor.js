$(document).ready(function(){
    var urlString = window.location.href;
    var url = new URL(urlString);
    var id = url.searchParams.get("id");

    if(id != null) {
        $.ajax({
            type: "GET",
            url: "/api/supervisor/" + id,
            dataType: "json",
            contentType: "application/json",
            success: function(response){
                var supervisor = response.data;
                $('#form-content').append(
                    "<input id='id' type='hidden' value='" + supervisor.id + "'>" +
                    "<div class='form-group'>" +
                    "<label>Nama</label>" +
                    "<input id='nama' type='text' value='" + supervisor.nama + "' class='form-control' required/>" +
                    "</div>" +
                    "<div class='form-group'>" +
                    "<label>Hp/Telp</label>" +
                    "<input id='hp' type='text' value='" + supervisor.hp + "' class='form-control' required/>" +
                    "</div>" +
                    "<div class='form-group'>" +
                    "<label>Email</label>" +
                    "<input id='email' type='text' value='" + supervisor.email + "' class='form-control' required/>" +
                    "</div>" +
                    "<div style='margin-top:50px;'>" +
                    "<button id='update-supervisor' value='" + supervisor.id + "' class='btn btn-primary' type='submit' style='background-color:#000000;'>Save</button>" +
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
            "<button id='save-supervisor' class='btn btn-primary' type='submit' style='background-color:#000000;'>Save</button>" +
            "</div>"
        );
    }
    $(document).on("click", "#update-supervisor", function () {
        var  id = $(this).val();
        var data = {
            nama: $('#nama').val(),
            hp: $('#hp').val(),
            email: $('#email').val()
        };
        $.ajax({
            type: "PUT",
            url: "/api/supervisor/" + id,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                alert("Supervisor saved");
                location.href="admin-supervisor.html";
            },
            error: function (url) {
                alert("Error");
                location.href = "admin-supervisor.html";
            }
        });
    });
    $(document).on("click", "#save-supervisor", function () {
        var data = {
            nama: $('#nama').val(),
            hp: $('#hp').val(),
            email: $('#email').val()
        };
        $.ajax({
            type: "POST",
            url: "/api/supervisor",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                alert("Supervisor saved");
                location.href="admin-supervisor.html";
            },
            error: function (url) {
                alert("Error");
                location.href = "admin-supervisor.html";
            }
        });
    });
});