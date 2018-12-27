function validate(form){
    if(form.txtUsername.value=="admin" && form.txtPassword.value=="admin"){
        location.assign('../admin/admin-inventory.html');
    } else if(form.txtUsername.value=="employee" && form.txtPassword.value=="employee"){
        location.assign('../employee/employee-inventory.html');
    } else if(form.txtUsername.value=="supervisor" && form.txtPassword.value=="supervisor"){
        location.assign('../supervisor/supervisor-form-request.html');
    }
}