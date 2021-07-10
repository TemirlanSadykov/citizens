<#import "../partials/main.ftlh" as main>
<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/.js"]>-->
    <div class="mx-auto mt-3 w-50">
        <form id="searchUserForm" action="/info/user/search" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <h3 class="text-center">Поиск пользователей</h3>
            <div class="form-group">
                <label>ФИО (необязательно):</label>
                <input type="text" name="name" class="form-control" autofocus>
            </div>
            <div class="form-group">
                <label>Дата рождения (необязательно):</label>
                <input type="date" name="birth" class="form-control">
            </div>
            <div class="form-group">
                <label>Номер документа (необязательно):</label>
                <input type="text" name="number" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Найти</button>
        </form>
    </div>
</@main.renderWith>