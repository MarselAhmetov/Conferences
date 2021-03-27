#Тестовый проект "Конференции"

##1. Модели
`Account, Presentation, Schedule, Room`

##2. API
`GET: /api/signup` - Регистрация с передачей `login` и `password` (Будет зарегистрирован пользователь с ролью `LISTENER`)

`GET: /api/signIn` - Аутентификация с передачей `login` и `password`

`GET: /api/account/current` - Получить информацию о текущем авторизированном аккаунте

`GET: /api/schedule/sorted` - Получение данных о презентациях с расписанием, отсортированных по аудиториям

`GET: /api/presentation` - Получение данных обо всех презентациях

`GET: /api/presentation/presenter/{id}` - Получить презентацию по id

###Доступно только PRESENTER'у

`POST: /api/presentation` - Сохранение презентации с передачей следующих параметров (Время не может пересекаться с уже существующими презентациями в аудитории)
```json
{
  "beginDate": "2021-03-27T20:18:56.046Z",
  "endDate": "2021-03-27T20:18:56.046Z",
  "id": 0,
  "presentation": {
    "id": 0,
    "presenters": [
      {
        "id": 0,
        "login": "string",
        "role": "string"
      }
    ],
    "text": "string",
    "title": "string"
  },
  "room": {
    "id": 0,
    "name": "string"
  }
}
```

`DELETE: /api/presentation` - Удаление презентаций

`GET: /api/presentation/presenter` - Получение презентаций в которых переданный аккаунт участвует

###Доступно только ADMIN'у
`POST: /api/account` - Изменить информацию о любом аккаунте (нельзя изменить пароль)

`GET: /api/account` - Получить информацию обо всех аккаунтах

**API доступно в Swagger (http://localhost:8080/swagger-ui.html)**