package me.kazuhira.distributed20

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.ChatColor

class PredeterminedText {
    companion object {
        val rollText = "" + ChatColor.GOLD +"Rolling: "
        val defaultTag= "" + ChatColor.DARK_GRAY + "[" + ChatColor.RESET + ChatColor.GOLD + "Distributed20" + ChatColor.RESET + ChatColor.DARK_GRAY + "]"
        val invalidCommand = "" + ChatColor.GOLD + "Invalid command!"
        val helpExample= "" + ChatColor.GOLD + "Example: /roll 1d20 +2"
        val noPermission = "" + ChatColor.RED + "" + ChatColor.BOLD + "Hey!" + " " + ChatColor.RESET + ChatColor.GRAY  + "You don't have permissions to do that!"
    }
}