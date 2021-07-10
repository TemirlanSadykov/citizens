<#import "../partials/main.ftlh" as main>
<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/.js"]>-->
    <#if error??>
        <h3>${error}</h3>
        <a href="/default" class="btn btn-primary btn-block">Выйти</a>
        <#else >
            <div class="mx-auto mt-3 w-50">

                <h3 class="text-center">Ваша информация:</h3>
                <div class="form-group">
                    <label><h3>ФИО:</h3></label>
                    <p>${user.name}</p>
                </div>
                <div class="form-group">
                    <label><h3>ИНН:</h3></label>
                    <p>${user.inn}</p>
                </div>
                <div class="form-group">
                    <label><h3>Дата Рождения:</h3></label>
                    <p>${user.birth}</p>
                </div>
                <div class="form-group">
                    <label><h3>Адрес проживания:</h3></label>
                    <p>${user.place}</p>
                </div>
                <div class="form-group">
                    <label><h3>Логин:</h3></label>
                    <p>${user.login}</p>
                </div>
                <div class="form-group">
                    <label><h3>Должность:</h3></label>
                    <p>${user.roleDTO.name}</p>
                </div>
                <div class="form-group">
                    <label><h3>№ документа:</h3></label>
                    <p>${user.documentDTO.number}</p>
                </div>
                <div class="form-group">
                    <label><h3>Вид документа:</h3></label>
                    <p>${user.documentDTO.doctype.name()}</p>
                </div>
                <div class="form-group">
                    <label><h3>Орган выдачи:</h3></label>
                    <p>${user.documentDTO.giver}</p>
                </div>
                <div class="form-group">
                    <label><h3>Дата выдачи:</h3></label>
                    <p>${user.documentDTO.issue}</p>
                </div>
                <div class="form-group">
                    <label><h3>Дата окончания срока:</h3></label>
                    <p>${user.documentDTO.expiration}</p>
                </div>
                <a href="/default" class="btn btn-primary btn-block">Выйти</a>

            </div>
    </#if>
</@main.renderWith>