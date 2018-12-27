$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/api/supervisor",
        dataType: "json",
        contentType: "application/json",
        success: function(response){
            $.each(response.data, function (i, supervisor) { 
                $('#table-content').append(
                    "<tr>" +
                    "<td align='center'>" + (i+1) + "</td>" +
                    "<td>" + supervisor.id + "</td>" +
                    "<td>" + supervisor.nama + "</td>" +
                    "<td align='center'>" + supervisor.hp + "</td>" +
                    "<td>" + supervisor.email + "</td>" +
                    "<td align='center'>" +
                    "<a class='btn btn-primary' style='background-color:#000000;' href='admin-add-supervisor.html?id="+ supervisor.id +"'>Detail</a>" +
                    "<button id='delete-supervisor' class='btn btn-primary' style='background-color:#000000;' value='" + supervisor.id + "'>Delete</button>" +
                    "</td>" +
                    "<tr>"
                );
            });
        },
        error: function(){
            alert("Error");
        }
    });

    $(document).on("click", "#delete-supervisor", function(){
        var id = $(this).val();
        $.ajax({
            type: "DELETE",
            url: "/api/supervisor/" + id,
            contentType: "application/json",
            success: function(){
                alert("Supervisor deleted");
                location.reload();
            },
            error: function(url){
                alert("Error");
                location.reload();
            }
        });
    });
});