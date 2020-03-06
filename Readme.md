# VkFriends
- при старте приложения пользователь попадает страницу с информацией о приложении
- нажимая кнопку "вход" пользователь попадает на страницу с аутентификацией, после прохождения её, приложение получает vk access_token пользователя и отправляет его на сервер, после чего сервер сохраняет vk access_token и отправляет собственный сгенерированный токен, который сохраняется на устройстве пользователя.
- После авторизации у пользователя открывается страница с пятью его друзьями. Любые запросы происходят через сервер с использованием сгенерированного токена. На сервере сгенерированный токен подменяется оригинальным vk access_token и делается запрос к vk api. Если vk access_token становиться недействительным сервер сообщает это приложению и приложение просит пользователя войти снова.

<a href="https://maksimshengeliia.azurewebsites.net/vkfriends/test.apk">Ссылка на apk</a>
## Скриншоты
<img src="https://maksimshengeliia.azurewebsites.net/vkfriends/examples/1.png"/>
<img src="https://maksimshengeliia.azurewebsites.net/vkfriends/examples/2.png"/>
<img src="https://maksimshengeliia.azurewebsites.net/vkfriends/examples/3.png"/>
<img src="https://maksimshengeliia.azurewebsites.net/vkfriends/examples/4.png"/>
<img src="https://maksimshengeliia.azurewebsites.net/vkfriends/examples/5.png"/>
