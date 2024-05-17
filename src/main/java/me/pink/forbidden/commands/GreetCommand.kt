package me.pink.forbidden.commands

import me.pink.forbidden.utils.giveItemToPlayer
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashMap

class GreetCommand: CommandExecutor {
    // Создаем хэшмапу для хранения последнего преветствия для каждого игрока по его UUID
    private val lastGreetTime: HashMap<UUID, Long> = HashMap()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            val currentTime = System.currentTimeMillis()
            val lastTime = lastGreetTime[sender.uniqueId]
            val material = Material.getMaterial(Material.STICK.toString())!! // Например палка

            if (lastTime == null || currentTime - lastTime > 300000) { // 30000 милисекунд = 5 минут
                sender.sendMessage("Привет, ${sender.name}! Добро пожаловать на сервер TreexMine!")
                lastGreetTime[sender.uniqueId] = currentTime

                giveItemToPlayer(sender, material,
                    "Палка которая хранит NBT tag", key = sender.uniqueId.toString(),
                    value = currentTime.toString()) // Например будем сохранять то же самое что и в хэшмапе

            } else {
                sender.sendMessage("Преветствовать можно не чаще чем раз в 5 минут")
            }
            return true
        }
        return false
    }
}