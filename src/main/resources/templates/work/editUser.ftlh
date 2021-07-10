<#import "../partials/main.ftlh" as main>
<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/.js"]>-->
    <div class="mx-auto mt-3 w-50">
        <p><a class="btn btn-primary btn-block w-100" href="/info/user/edit/info/${id}" role="button">Изменить общую информацию</a></p>
        <p><a class="btn btn-primary btn-block w-100" href="/info/user/edit/document/${id}" role="button">Изменить документ</a></p>
    </div>
</@main.renderWith>