$(document).ready(function () {
    const usersBodyTable = document.querySelector("#users .table tbody");
    const userIdBodyTable = document.querySelector("#userId .table tbody");

    const userName = document.querySelector("#userNameSpan").innerText;

    $.ajax(`api/users/${userName}`, {
        method: "get",
        contentType: "application/json; charset=utf-8",
        success: function (user) {
            const userRow = $('<tr></tr>').appendTo(userIdBodyTable)[0];
            $('<td></td>').text(user.id).appendTo(userRow);
            $('<td></td>').text(user.firstName).appendTo(userRow);
            $('<td></td>').text(user.lastName).appendTo(userRow);
            $('<td></td>').text(user.password).appendTo(userRow);
            $('<td></td>').text(user.roles.map(role => '[' + role + ']').join(' ')).appendTo(userRow);
        }
    })

    const fetchUsers = () => {
        $.ajax("api/users", {
            dataType: "json",
            success: function (users) {
                if (!usersBodyTable) return;
                users.forEach(function (user) {
                    const userRow = $('<tr></tr>').appendTo(usersBodyTable)[0];
                    $('<td></td>').text(user.id).appendTo(userRow);
                    $('<td></td>').text(user.firstName).appendTo(userRow);
                    $('<td></td>').text(user.lastName).appendTo(userRow);
                    $('<td></td>').text(user.password).appendTo(userRow);
                    $('<td></td>').text(user.roles.map(role => '[' + role + ']').join(' ')).appendTo(userRow);
                    $('<td><button class="btn btn-info btn-sm showEditModalBtn">Edit</button></td>').appendTo(userRow);
                    userRow.querySelector('.showEditModalBtn').addEventListener('click', function () {
                        $('#idEdit').val(user.id);
                        $('#firstnameEdit').val(user.firstName);
                        $('#lastnameEdit').val(user.lastName);
                        $('#passwordEdit').val('');
                        $('#editModal').modal();
                    })
                    $('<td><button class="btn btn-danger btn-sm showDeleteModalBtn">Delete</button></td>').appendTo(userRow);
                    userRow.querySelector('.showDeleteModalBtn').addEventListener('click', function () {
                        $('#idDelete').val(user.id);
                        $('#firstnameDelete').val(user.firstName);
                        $('#lastnameDelete').val(user.lastName);
                        $('#passwordDelete').val(user.password);
                        $('#deleteModal').modal();
                    })
                })
            }
        })
    }

    const clearTable = () => {
        usersBodyTable.innerHTML = '';
    }
    fetchUsers();
    document.querySelector('#editForm') && document.querySelector('#editForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const editData = {
            id: $(this).find("#idEdit")[0].value,
            firstName: $(this).find("#firstnameEdit")[0].value,
            lastName: $(this).find("#lastnameEdit")[0].value,
            password: $(this).find("#passwordEdit")[0].value,
            roles: $(".addFormSelectEdit").val()
        };
        $.ajax("/api/users", {
            method: "put",
            data: JSON.stringify(editData),
            contentType: "application/json; charset=utf-8",
            success: function (msg) {
                $('#editModal').modal('hide');
                clearTable();
                fetchUsers();
            }
        })
    })
    document.querySelector('#deleteForm') && document.querySelector('#deleteForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const userId = $(this).find("#idDelete")[0].value;
        $.ajax(`api/users/${userId}`, {
            method: "delete",
            contentType: "application/json; charset=utf-8",
            success: function (msg) {
                $('#deleteModal').modal('hide');
                clearTable();
                fetchUsers();
            }
        })
    })
    document.querySelector('#addUserForm') && document.querySelector('#addUserForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const addData = {
            firstName: $(this).find("#addFirstName")[0].value,
            lastName: $(this).find("#addLastName")[0].value,
            password: $(this).find("#addPassword")[0].value,
            roles: $("#addRolesSelect").val()
        };
        $.ajax("/api/users", {
            method: "post",
            data: JSON.stringify(addData),
            contentType: "application/json; charset=utf-8",
            success: function (msg) {
                clearTable();
                fetchUsers();
                $('[href="#nav-home"]').tab('show');
            }
        })
    })
});






