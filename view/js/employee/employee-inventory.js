$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/api/inventory",
        dataType: "json",
        contentType: "application/json",
        success: function(response){
            $.each(response.data, function (i, inventory) { 
                $('#table-content').append(
                    "<tr>" +
                    "<td align='center'>" + (i+1) + "</td>" +
                    "<td>" + inventory.id + "</td>" +
                    "<td>" + inventory.nama + "</td>" +
                    "<td align='center'>" + inventory.harga + "</td>" +
                    "<td>" + inventory.jumlah + "</td>" +
                    "<td align='center'>" +
                    "<a class='btn btn-primary' style='background-color:#000000;' href='employee-detail-inventory.html?id="+ inventory.id +"'>Detail</a>" +
                    "<a class='btn btn-primary' style='background-color:#000000;' href='employee-request-inventory.html?id="+ inventory.id +"'>Request</a>" +
                    "</td>" +
                    "<tr>"
                );
            });
        },
        error: function(){
            alert("Error");
        }
    });
});