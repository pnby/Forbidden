package me.pink.forbidden

import me.pink.forbidden.commands.GreetCommand
import org.bukkit.plugin.java.JavaPlugin

class Forbidden : JavaPlugin() {
    override fun onEnable() {
        getCommand("greet")?.setExecutor(GreetCommand()) // Регистрируем нашу команду
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
