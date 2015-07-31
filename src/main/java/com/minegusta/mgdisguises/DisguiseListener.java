package com.minegusta.mgdisguises;

import me.libraryaddict.disguise.DisguiseAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

public class DisguiseListener implements Listener {

    //Listen on damage to cancel disguise
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e)
    {
        if(e.getEntity() instanceof Player && !e.isCancelled())
        {
            Player p = (Player) e.getEntity();
            if(DisguiseAPI.isDisguised(p))
            {
                DisguiseAPI.undisguiseToAll(p);
                sendString(p, ChatColor.RED + "You have been undisguised because of taking damage!");
            }

        }
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof Player && !e.isCancelled())
        {
            Player p = (Player) e.getEntity();
            if(DisguiseAPI.isDisguised(p))
            {
                DisguiseAPI.undisguiseToAll(p);
                sendString(p, ChatColor.RED + "You have been undisguised because of taking damage!");
            }

        }
    }
    @EventHandler
    public void onEntityDamage(EntityDamageByBlockEvent e)
    {
        if(e.getEntity() instanceof Player && !e.isCancelled())
        {
            Player p = (Player) e.getEntity();
            if(DisguiseAPI.isDisguised(p))
            {
                DisguiseAPI.undisguiseToAll(p);
                sendString(p, ChatColor.RED + "You have been undisguised because of taking damage!");
            }

        }
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
