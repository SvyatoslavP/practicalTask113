/**
 *
 */

$('document').ready(function (){

    $('.table .showEditModalBtn').on('click', function (){

        const userId = +this.getAttribute('data-user-id');
        $.get('/users/getOne', {userId}, function (user){
            $('#idEdit').val(user.id);
            $('#firstnameEdit').val(user.name);
            $('#lastnameEdit').val(user.lastName);
            $('#passwordEdit').val(user.password);
            $('#roleEdit').val(user.roles.join(' '));
        });

        $('#editModal').modal();

    });

    $('.table .showDeleteModalBtn').on('click', function (){

        const userId = +this.getAttribute('data-user-id');

        $.get('/users/getOne', {userId}, function (user){
            $('#idDelete').val(user.id);
            $('#firstnameDelete').val(user.name);
            $('#lastnameDelete').val(user.lastName);
            $('#passwordDelete').val(user.password);
            $('#roleDelete').val(user.roles.join(' '));
        });

        $('#deleteModal').modal();

    });

    $('.addFormSelect').on('change', function (){
        console.log(this.value);
    });
});



