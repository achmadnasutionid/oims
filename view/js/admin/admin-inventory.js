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
                    "<a class='btn btn-primary' style='background-color:#000000;' href='admin-add-inventory.html?id="+ inventory.id +"'>Detail</a>" +
                    "<button id='delete-inventory' class='btn btn-primary' style='background-color:#000000;' value='" + inventory.id + "'>Delete</button>" +
                    "</td>" +
                    "<tr>"
                );
            });
        },
        error: function(){
            alert("Error");
        }
    });

    $(document).on("click", "#delete-inventory", function(){
        var id = $(this).val();
        $.ajax({
            type: "DELETE",
            url: "/api/inventory/" + id,
            contentType: "application/json",
            success: function(){
                alert("Inventory deleted");
                location.reload();
            },
            error: function(url){
                alert("Error");
                location.reload();
            }
        });
    });
});