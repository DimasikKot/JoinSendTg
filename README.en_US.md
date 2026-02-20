
[Русский](/README.md) | **English**

---

# JoinSendTg

**JoinSendTg** is a simple Minecraft server plugin that sends Telegram notifications when a player joins the server and displays a message with a Telegram link directly in-game.

---

### 📌 Features

* 📩 Sends a **Telegram message** when a player joins the server
* 💬 Displays a message in the server chat on join
* 🔗 Optional Telegram link support
* ⚙️ Easy configuration via `config.yml`

---

### ⚙️ Configuration (`config.yml`)

```yaml
# v1.1 - configuration version, update if you are using an older one

telegram:
  token: "BOT_TOKEN" # Get it from @BotFather
  chat-id: "CHAT_ID" # Chat ID or user ID (group or private messages)

message:
  send-join: "true"
  join: "{player} join the game" # {player} - nickname
  send-quit: "true"
  quit: "{player} leave the game" # {player} - nickname
  send-start: "true"
  start: "Server on"
  send-disable: "true"
  disable: "Server off"
  server: "📢 Our Telegram: "
  link: "https://t.me/link" # Leave empty "" to disable the link
```

---

### 🔑 Configuration Options

#### `telegram.token`

Your Telegram bot token.
Get it from **@BotFather**.

#### `telegram.chat-id`

Target chat or user ID:

* Private chat: `123456789`
* Group chat (bot must be added): `-100XXXXXXXXXX`

#### `message.telegram`

Message sent to Telegram when a player joins.
Available placeholders:

* `{player}` — player nickname

#### `message.server`

Message displayed in the in-game chat when a player joins.

#### `message.link`

Telegram link:

* If set — shown in chat
* If empty (`""`) — link will not be shown

---

### 📦 Installation

1. Put `JoinSendTg.jar` into the `plugins` folder
2. Start the server
3. Edit `config.yml`
4. Restart the server

---

### 📄 License

This project is licensed under the **MIT License**
