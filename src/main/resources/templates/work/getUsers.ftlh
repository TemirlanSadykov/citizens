<#import "../partials/main.ftlh" as main />

<@main.renderWith scripts=["/js/validation.js"] styles=["https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <h3 class="mt-3">Все пользователи</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">ФИО</th>
            <th scope="col">ИНН</th>
            <th scope="col">Год рождения</th>
            <th scope="col">№ документа</th>
            <th scope="col">Удаление</th>
            <th scope="col">Редактирование</th>
        </tr>
        </thead>
        <tbody>
        <#list items as user>

                    <tr>
                        <th scope="row">${user?counter}</th>
                        <td><a href="/info/user/open/${user.id}">${user.name}</a></td>
                        <td>${user.inn}</td>
                        <td>${user.birth}</td>
                        <td>${user.documentDTO.number}</td>
                        <td><button onclick="sendFetch${user.id?string}()" style="color:red;">Удалить</button></td>
                            <script>
                             function sendFetch${user.id?string}() {
                                var href = window.location.href;
                                 fetch(href+'/delete/'+${user.id?string}, {
                                    method: 'DELETE',
                                })
                                    .then(location.replace(href))
                            }
                        </script>
                        <td><a href="/info/user/edit/${user.id}" style="color:green;">Редактировать</a></td>
                    </tr>
        </#list>

        </tbody>
    </table>
    <#include "../partials/pagination.ftlh">

</@main.renderWith>