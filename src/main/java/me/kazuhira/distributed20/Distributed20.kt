package me.kazuhira.distributed20

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Distributed20 : JavaPlugin() {
    override fun onEnable() {
        registerCommands()
        logger.info("Enabled.")
    }
    private fun registerCommands() {
        getCommand("roll")?.setExecutor(RollCommand())
        logger.info("Registered command.")
    }
    private fun registerListeners() {
        logger.info("Registered listeners.")
    }

    override fun onDisable() {
        logger.info("Disabled.")
    }
}
