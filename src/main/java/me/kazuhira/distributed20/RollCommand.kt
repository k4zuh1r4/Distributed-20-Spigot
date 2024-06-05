package me.kazuhira.distributed20

import me.kazuhira.distributed20.PredeterminedText.Companion.defaultTag
import me.kazuhira.distributed20.PredeterminedText.Companion.helpExample
import me.kazuhira.distributed20.PredeterminedText.Companion.invalidCommand
import me.kazuhira.distributed20.PredeterminedText.Companion.noPermission
import me.kazuhira.distributed20.PredeterminedText.Companion.rollText
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sqrt

class RollCommand: CommandExecutor {
    companion object {
        var result: Int = 0
        var splitModifier: Int = 0
        const val stdDev = 4.5
        var mean = 0.0
    }
    private fun randomEngine(mean: Double, stdDev: Double, max: Int, min: Int = 1): Int {
        var u1: Double
        var u2: Double
        do {
            u1 = Math.random()
            u2 = Math.random()
        } while (u1 <= 0.0 && u2 <= 0.0)

        val z0: Double = sqrt(-2.0 * ln(u1)) * cos(2 * PI * u2)
        if ((z0 * stdDev + mean).toInt() > max) {
            return max
        }
        if ((z0 * stdDev + mean).toInt() < min) {
            return min
        }
        return (z0 * stdDev + mean).toInt()
    }

    private fun rollEngine(p0: CommandSender, times: Int, diceSize: Int, modifier: Int = 0): Int {
        val rollAppender = StringBuilder()
        var sum = 0
        for (i in 0 until times) {
            mean = (diceSize / 2 + 1.5)
            result = randomEngine(mean, stdDev, diceSize)
            rollAppender.append("$result + ")
            sum += result
        }
        sum += modifier
        p0.sendMessage(""+ defaultTag + " " + rollText + ChatColor.RESET + ChatColor.DARK_AQUA + "$rollAppender$modifier = " + ChatColor.RESET +ChatColor.GOLD +sum)
        return sum
    }
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (p0 !is Player) return false
        if (p1.name == "roll") {
            if (!p0.hasPermission("Distributed20.commands.roll")) {
                p0.sendMessage(noPermission)
                return false
            }
            if (p3!!.isEmpty()) {
                rollEngine(p0, 1, 20)
                return false
            }
            try {
                if (p3.isNotEmpty() && p3.size <= 2) {
                    if (p3[0].contains("d")) {
                        val splitString = p3[0].split("d").toTypedArray()
                        if (splitString.size > 2) {
                            p0.sendMessage("$defaultTag $invalidCommand")
                            p0.sendMessage(helpExample)
                            return false
                        }

                        if (p3.size == 2 && p3[1] != null) {
                            if (p3[1].contains("+") || p3[1].contains("-")) {
                                splitModifier = p3[1].toInt()
                                rollEngine(p0, splitString[0].toInt(), splitString[1].toInt(), splitModifier)
                                return false
                            }
                        }
                        rollEngine(p0, splitString[0].toInt(), splitString[1].toInt())
                    }
                    return false
                }
                else {
                    p0.sendMessage("$defaultTag $invalidCommand")
                    p0.sendMessage(helpExample)
                    return false
                }
            }
            catch (e: Exception) {
                p0.sendMessage("$defaultTag $invalidCommand")
                p0.sendMessage(helpExample)
                return false
            }
        }
        else {
            p0.sendMessage("$defaultTag $invalidCommand")
            p0.sendMessage(helpExample)
            return false
        }
    }
}
