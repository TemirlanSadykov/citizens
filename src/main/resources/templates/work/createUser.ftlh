<#import "../partials/main.ftlh" as main>
<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/.js"]>-->
    <div class="mx-auto mt-3 w-50">
        <form id="login-form" action="/info/createUser" method="post">

            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>

            <h3 class="text-center">Создание пользователя</h3>
            <div class="form-group">
                <label>Введите ФИО:</label>
                <input type="text" name="name" class="form-control" placeholder="Фамилия Имя Отчество" autofocus>
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'name'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Введите ИНН:</label>
                <input type="number" name="inn" class="form-control" placeholder="ИНН">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'inn'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Введите дату рождения:</label>
                <input type="date" name="birth" class="form-control">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'birth'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Введите адрес проживания:</label>
                <input type="text" name="place" class="form-control" placeholder="Адрес проживания">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'place'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Выберите должность:</label>
                <select id="roleId" name="roleId">
                    <#list roles as rl>
                        <option value="${rl.id}">${rl.name}</option>
                    </#list>
                </select>
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'roleId'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Введите Логин:</label>
                <input type="text" name="login" class="form-control" placeholder="Логин">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'login'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Введите Пароль:</label>
                <input type="password" name="password" class="form-control" placeholder="Пароль">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'password'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <button name="register" class="btn btn-primary btn-block">Создать</button>
            <div>
                <#if error?? >
                    <div class="alert alert-warning mt-1" style="color: red" role="alert">Данный пользователь уже зарегистрирован</div>
                </#if>
            </div>
        </form>
    </div>
</@main.renderWith>