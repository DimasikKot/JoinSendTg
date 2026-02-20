
# JoinSendTg

**JoinSendTg** — это простой плагин для Minecraft-сервера, который отправляет уведомления в Telegram, когда игрок заходит на сервер, и показывает сообщение с ссылкой на Telegram прямо в игре.

---

## 🌍 Languages

* 🇷🇺 [Русская документация](#-документация-на-русском)
* 🇺🇸 [English documentation](#-english-documentation)

---

### 📌 Возможности

* 📩 Отправка сообщения в **Telegram** при входе игрока на сервер
* 💬 Отображение сообщения в чате сервера при входе
* 🔗 Поддержка ссылки на Telegram
* ⚙️ Простая настройка через `config.yml`

---

### ⚙️ Конфигурация (`config.yml`)

```yaml
# v1.1 - configuration version, update if you are using an older one

telegram:
  token: "BOT_TOKEN" # Получите токен у @BotFather
  chat-id: "CHAT_ID" # ID чата или пользователя (группа или личные сообщения)

message:
  telegram: "Player {player} joined the server" # {player} — ник игрока
  server: "📢 Our Telegram: "
  link: "https://t.me/link" # Оставьте пустым "", если не хотите показывать ссылку
```

---

### 🔑 Параметры

#### `telegram.token`

Токен Telegram-бота.
Получается через **@BotFather**.

#### `telegram.chat-id`

ID чата или пользователя:

* Личное сообщение: `123456789`
* Группа (бот должен быть добавлен): `-100XXXXXXXXXX`

#### `message.telegram`

Сообщение, которое отправляется в Telegram при входе игрока.
Доступные плейсхолдеры:

* `{player}` — никнейм игрока

#### `message.server`

Сообщение, которое показывается игроку в чате при входе.

#### `message.link`

Ссылка на Telegram:

* Если указана — будет показана в чате
* Если `""` — ссылка не отображается

---

### 📦 Установка

1. Скопируйте `JoinSendTg.jar` в папку `plugins`
2. Запустите сервер
3. Настройте `config.yml`
4. Перезапустите сервер
