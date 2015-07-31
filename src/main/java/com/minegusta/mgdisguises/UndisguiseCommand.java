package com.minegusta.mgdisguises;

import me.libraryaddict.disguise.DisguiseAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class UndisguiseCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args)
    {
        if(!(s instanceof Player)) return false;

        Player p = (Player) s;

        if(DisguiseAPI.isDisguised(p))
        {
            DisguiseAPI.undisguiseToAll(p);
            sendString(p, ChatColor.GREEN + "You have been undisguised!");
            return true;
        }
        sendString(p, ChatColor.GREEN + "You are not disguised!");
        return true;
    }

    private void sendMessage(Player p, List<String> list)
    {
        p.sendMessage(ChatColor.YELLOW + "[=][=] -=" + ChatColor.DARK_GREEN + "MG Disguises" + ChatColor.YELLOW + "=- [=][=]");
        for(String s : list)
        {
            p.sendMessage(s);
        }
    }
    private void sendString(Player p, String s)
    {
        p.sendMessage(ChatColor.YELLOW + "[=][=] -=" + ChatColor.DARK_GREEN + "MG Disguises" + ChatColor.YELLOW + "=- [=][=]");
        p.sendMessage(s);
    }
}
