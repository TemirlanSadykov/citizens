<#import "../partials/main.ftlh" as main>
<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/.js"]>-->
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Добро пожаловать в систему <b>${userName}</b></h3>
        <p><a class="btn btn-primary btn-block w-100" href="/info" role="button">Информация о себе</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/info/createUser" role="button">Создть пользователя</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/info/user" role="button">Получить всех пользователей</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/info/user/search" role="button">Поиск пользователей</a></p>
    </div>
</@main.renderWith>