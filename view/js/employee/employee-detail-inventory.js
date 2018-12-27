$(document).ready(function(){
    var urlString = window.location.href;
    var url = new URL(urlString);
    var id = url.searchParams.get("id");

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
                    "</div>"
                );
            },
            error: function(){
                alert("Error");
            }
        });
    });