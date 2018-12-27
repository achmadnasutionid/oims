$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/api/employee",
        dataType: "json",
        contentType: "application/json",
        success: function(response){
            $.each(response.data, function (i, employee) {
                $('#table-content').append(
                    "<tr>" +
                    "<td align='center'>" + (i+1) + "</td>" +
                    "<td>" + employee.id + "</td>" +
                    "<td>" + employee.nama + "</td>" +
                    "<td align='center'>" + employee.hp + "</td>" +
                    "<td>" + employee.email + "</td>" +
                    "<td align='center'>" +
                    "<a class='btn btn-primary' style='background-color:#000000;' href='admin-add-employee.html?id="+ employee.id +"'>Detail</a>" +
                    "<button id='delete-employee' class='btn btn-primary' style='background-color:#000000;' value='" + employee.id + "'>Delete</button>" +
                    "</td>" +
                    "<tr>"
                );
            });
        },
        error: function(){
            alert("Error");
        }
    });

    $(document).on("click", "#delete-employee", function(){
        var id = $(this).val();
        $.ajax({
            type: "DELETE",
            url: "/api/employee/" + id,
            contentType: "application/json",
            success: function(){
                alert("Employee deleted");
                location.reload();
            },
            error: function(url){
                alert("Error");
                location.reload();
            }
        });
    });
});