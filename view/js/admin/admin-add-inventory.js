$(document).ready(function(){
    var urlString = window.location.href;
    var url = new URL(urlString);
    var id = url.searchParams.get("id");

    if(id != null) {
        $.ajax({
            type: "GET",
            url: "/api/inventory/" + id,
            dataType: "json",
            contentType: "application/json",
            success: function(response){
                var inventory = response.data;
                $('#form-content').append(
                    "<input id='id' type='hidden' value='" + inventory.id + "'>" +
                    "<div class='form-group'>" +
                    "<label>Nama</label>" +
                    "<input id='nama' type='text' value='" + inventory.nama + "' class='form-control' required/>" +
                    "</div>" +
                    "<div class='form-group'>" +
                    "<label>Harga</label>" +
                    "<input id='harga' type='text' value='" + inventory.harga + "' class='form-control' required/>" +
                    "</div>" +
                    "<div class='form-group'>" +
                    "<label>Jumlah</label>" +
                    "<input id='jumlah' type='text' value='" + inventory.jumlah + "' class='form-control' required/>" +
                    "</div>" +
                    "<div class='form-group'>" +
                    "<label>Deskripsi</label>" +
                    "<textarea id='deskripsi'  class='form-control' rows='5'>" + inventory.deskripsi + "</textarea>" +
                    "</div>" +
                    "<div style='margin-top:50px;'>" +
                    "<button id='update-inventory' value='" + inventory.id + "' class='btn btn-primary' type='submit' style='background-color:#000000;'>Save</button>" +
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
            "<label>Harga</label>" +
            "<input id='harga' type='text' class='form-control' required/>" +
            "</div>" +
            "<div class='form-group'>" +
            "<label>Jumlah</label>" +
            "<input id='jumlah' type='text' class='form-control' required/>" +
            "</div>" +
            "<div class='form-group'>" +
            "<label>Deskripsi</label>" +
            "<textarea id='deskripsi' class='form-control' rows='5'></textarea>" +
            "</div>" +
            "<div style='margin-top:50px;'>" +
            "<button id='save-inventory' class='btn btn-primary' type='submit' style='background-color:#000000;'>Save</button>" +
            "</div>"
        );
    }
    $(document).on("click", "#update-inventory", function () {
        var  id = $(this).val();
        var data = {
            nama: $('#nama').val(),
            harga: $('#harga').val(),
            jumlah: $('#jumlah').val(),
            deskripsi: $('#deskripsi').val()
        };
        $.ajax({
            type: "PUT",
            url: "/api/inventory/" + id,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                alert("Inventory saved");
                location.href="admin-inventory.html";
            },
            error: function (url) {
                alert("Error");
                location.href = "admin-inventory.html";
            }
        });
    });
    $(document).on("click", "#save-inventory", function () {
        var data = {
            nama: $('#nama').val(),
            harga: $('#harga').val(),
            jumlah: $('#jumlah').val(),
            deskripsi: $('#deskripsi').val()
        };
        $.ajax({
            type: "POST",
            url: "/api/inventory",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                alert("Inventory saved");
                location.href="admin-inventory.html";
            },
            error: function (url) {
                alert("Error");
                location.href = "admin-inventory.html";
            }
        });
    });
});