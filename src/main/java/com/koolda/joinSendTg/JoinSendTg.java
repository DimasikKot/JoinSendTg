package com.koolda.joinSendTg;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class JoinSendTg extends JavaPlugin implements Listener {

    private String token;
    private String chatId;
    private boolean sendJoinMessage;
    private String joinMessage;
    private boolean sendQuitMessage;
    private String quitMessage;
    private boolean sendDisableMessage;
    private String disableMessage;
    private String serverMessage;
    private String linkMessage;


    @Override
    public void onEnable() {
        saveDefaultConfig();

        token = getConfig().getString("telegram.token");
        chatId = getConfig().getString("telegram.chat-id");
        sendJoinMessage = getConfig().getBoolean("message.send-join", false);
        joinMessage = getConfig().getString("message.join");
        sendQuitMessage = getConfig().getBoolean("message.send-quit", false);
        quitMessage = getConfig().getString("message.quit");
        boolean sendStartMessage = getConfig().getBoolean("message.send-start", false);
        String startMessage = getConfig().getString("message.start");
        sendDisableMessage = getConfig().getBoolean("message.send-disable", false);
        disableMessage = getConfig().getString("message.disable");
        serverMessage = getConfig().getString("message.server");
        linkMessage = getConfig().getString("message.link");

        if (token == null || chatId == null) {
            getLogger().severe("Telegram token or chat-id not set!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("JoinSendTg enabled");

        if (sendStartMessage) {
            sendToTelegram(startMessage);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (sendJoinMessage) {
            String playerName = event.getPlayer().getName();
            String text = joinMessage.replace("{player}", playerName);

            // Отправка в Telegram
            Bukkit.getScheduler().runTaskAsynchronously(this, () -> sendToTelegram(text));

            // Сообщение в чат сервера
            sendToServerChat();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (sendQuitMessage) {
            String playerName = event.getPlayer().getName();
            String text = quitMessage.replace("{player}", playerName);

            // Отправка в Telegram
            Bukkit.getScheduler().runTaskAsynchronously(this, () -> sendToTelegram(text));
        }
    }

    private void sendToTelegram(String message) {
        try {
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
            String urlString =
                    "https://api.telegram.org/bot" + token +
                            "/sendMessage?chat_id=" + chatId +
                            "&text=" + encodedMessage;

            URL url = URI.create(urlString).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            connection.getInputStream().close();
            connection.disconnect();
        } catch (Exception e) {
            getLogger().warning("Failed to send Telegram message: " + e.getMessage());
        }
    }

    private void sendToServerChat() {
        Component message = Component.text(serverMessage, NamedTextColor.YELLOW);
        if (!Objects.equals(linkMessage, "")) {
            message = Component.text(serverMessage, NamedTextColor.YELLOW)
                    .append(
                            Component.text(linkMessage, NamedTextColor.AQUA)
                                    .clickEvent(ClickEvent.openUrl(linkMessage))
                    );
        }

        Bukkit.getServer().broadcast(message);
    }

        @Override
        public void onDisable() {
            if (sendDisableMessage) {
                sendToTelegram(disableMessage);
            }
        }
}