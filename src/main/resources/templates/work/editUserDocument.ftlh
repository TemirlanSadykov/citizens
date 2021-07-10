<#import "../partials/main.ftlh" as main>
<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/.js"]>-->
    <div class="mx-auto mt-3 w-50">
        <form id="editUserDocForm">
            <h3 class="text-center">Редактирование документа</h3>

            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>

            <div class="form-group">
                <label>Изменить № документа:</label>
                <input type="text" name="number" class="form-control" value="${user.documentDTO.number}" autofocus>
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'number'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Изменить тип документа:</label>
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
                <label>Изменить орган выдачи:</label>
                <input type="text" name="giver" class="form-control" value="${user.documentDTO.giver}">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'giver'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Изменить Дату выдачи:</label>
                <input type="date" name="issue" class="form-control" value="${user.documentDTO.issue}">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'issue'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <label>Изменить Дату истечения срока:</label>
                <input type="date" name="expiration" class="form-control" value="${user.documentDTO.expiration}">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'expiration'>
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
        xhr.send(new FormData(document.getElementById('editUserDocForm')));
    }
</script>