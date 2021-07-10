<#import "../partials/main.ftlh" as main>
<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/.js"]>-->
    <div class="mx-auto mt-3 w-50">
        <#if error??>
            <h3 class="text-center">${error}</h3>
            <#else >
                <form id="login-form" action="/info/document" method="post">
                    <h3 class="text-center">Создание документа</h3>

                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>

                    <input type="hidden" name="login" value="${login}"/>
                    <p>${login} - Логин пользователя</p>

                    <div class="form-group">
                        <label>Введите № документа:</label>
                        <input type="text" name="number" class="form-control" placeholder="№ документа" autofocus>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'number'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <label>Выберите тип документа:</label>
                        <select id="doctypeName" name="doctypeName">
                            <#list doctype as dt>
                                <option value="${dt.name()}">${dt.name()}</option>
                            </#list>
                        </select>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'doctypeName'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <label>Кем был выдан:</label>
                        <input type="text" name="giver" class="form-control" placeholder="Орган выдачи">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'giver'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <label>Введите Дату выдачи:</label>
                        <input type="date" name="issue" class="form-control">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'issue'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <label>Введите Дату истечения срока:</label>
                        <input type="date" name="expiration" class="form-control">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'expiration'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <button name="register" class="btn btn-primary btn-block">Создать</button>
                    <div>
                        <#if error?? >
                            <div class="alert alert-warning mt-1" style="color: red" role="alert">${error}</div>
                        </#if>
                    </div>
                </form>
        </#if>
    </div>
</@main.renderWith>