# Система регистрации и обработки пользовательских заявок.

<h5>В качестве документации доступен Swagger: http://localhost:8080/swagger-ui.html пользователь <i>user</i> пароль <i>123</i></h5>
<h5>База данных - PostgreSQL</h5>
<h5>Миграции в БД с тестовыми данными - FlyWay</h5>
<h5>BasicAuth в Spring Security и NoOpPasswordEncoder, чтобы не шифровать пароли перед записью т.к. не было указано каких-либо требований к аутентификации.<h5>

Примеры Curl запросов:

Первая страница списка заявок по порядку от старых к новым (с правами user)
`curl -X 'GET' \
'http://localhost:8080/user/requests?pageNumber=0&fromOldToNew=true' \
-H 'accept: */*' \
-H 'Authorization: Basic dXNlcjoxMjM='`

Отправить заявку на рассмотрение (с правами user)
`curl -X 'POST' \
'http://localhost:8080/user/requests/4' \
-H 'accept: */*' \
-H 'Authorization: Basic dXNlcjoxMjM=' \
-d ''`

Первая страница списка заявок по порядку от старых к новым с отбором по части имени (c правами operator)
`curl -X 'GET' \
'http://localhost:8080/operator/requests?pageNumber=0&fromOldToNew=true&partOfName=user2' \
-H 'accept: */*' \
-H 'Authorization: Basic b3BlcmF0b3I6MTIz'`

Список пользователей с отбором по части имени (с правами admin)
`curl -X 'GET' \
'http://localhost:8080/admin/users?partOfName=user' \
-H 'accept: */*' \
-H 'Authorization: Basic YWRtaW46MTIz'`

Описание задания: в файле task.md

