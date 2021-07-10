<#import "../partials/main.ftlh" as main>
<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/.js"]>-->
    <div class="mx-auto mt-3 w-50">
        <form id="editUserForm">

            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>

            <h3 class="text-center">Изменение пользователя</h3>
            <div class="form-group">
                <label>Изменить ФИО:</label>
                <input type="text" name="name" class="form-control" value="${user.name}" autofocus>
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'name'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Изменить ИНН:</label>
                <input type="number" name="inn" class="form-control" value="${user.inn}">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'inn'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Изменить дату рождения:</label>
                <input type="date" name="birth" class="form-control" value="${user.birth}">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'birth'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Изменить адрес проживания:</label>
                <input type="text" name="place" class="form-control" value="${user.place}">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'place'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Изменить Логин:</label>
                <input type="text" name="login" class="form-control" value="${user.login}">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'login'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Ввести Пароль:</label>
                <input type="password" name="password" class="form-control" placeholder="Пароль">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'password'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <button class="btn btn-primary btn-block" onclick="sendForm()">Сохранить</button>
        </form>
    </div>
</@main.renderWith>
<script>
    function sendForm() {
        var xhr = new XMLHttpRequest();
        xhr.open('PUT', window.location.href, true);
        xhr.send(new FormData(document.getElementById('editUserForm')));
    }
</script>