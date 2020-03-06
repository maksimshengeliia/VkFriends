# VkFriends
- при старте приложения пользователь попадает на страницу с информацией о приложении.
- нажимая кнопку "вход" пользователь попадает на страницу с аутентификацией, после прохождения её, приложение получает vk access_token пользователя и отправляет его на сервер, после чего сервер сохраняет vk access_token и отправляет собственный сгенерированный токен, который сохраняется на устройстве пользователя.
- После авторизации у пользователя открывается страница с пятью его друзьями. Любые запросы происходят через сервер с использованием сгенерированного токена. На сервере сгенерированный токен подменяется оригинальным vk access_token и делается запрос к vk api. Если vk access_token становиться недействительным сервер сообщает это приложению и приложение просит пользователя войти снова.
- После каждого запроса приложение кеширует информацию о друзьях и показывает эту информацию даже тогда, когда отсутствует интернет. Чтобы получить актуальную информацию о друзьях необходимо сделать свайп вниз.

(Может показаться, что сервер при первом запросе отвечает слегка долго, это связанно с тем, ему требуется некоторое время, чтобы выйти из спящего состояния.)

<a href="https://maksimshengeliia.azurewebsites.net/vkfriends/test.apk">Ссылка на apk</a>
## Скриншоты
<img src="https://maksimshengeliia.azurewebsites.net/vkfriends/examples/1.png" width="270" height="480"/>
<img src="https://maksimshengeliia.azurewebsites.net/vkfriends/examples/2.png" width="270" height="480"/>
<img src="https://maksimshengeliia.azurewebsites.net/vkfriends/examples/3.png" width="270" height="480"/>
<img src="https://maksimshengeliia.azurewebsites.net/vkfriends/examples/4.png" width="270" height="480"/>
<img src="https://maksimshengeliia.azurewebsites.net/vkfriends/examples/5.png" width="270" height="480"/>
