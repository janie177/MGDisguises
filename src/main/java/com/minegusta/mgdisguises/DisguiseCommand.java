package com.minegusta.mgdisguises;

import com.google.common.collect.Lists;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.LibsDisguises;
import me.libraryaddict.disguise.disguisetypes.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

import java.util.List;

public class DisguiseCommand implements CommandExecutor
{

    private static final List<EntityType> defaultAllowed = Lists.newArrayList(EntityType.CHICKEN, EntityType.SQUID, EntityType.SKELETON, EntityType.ENDERMAN, EntityType.CREEPER, EntityType.RABBIT, EntityType.HORSE, EntityType.SHEEP, EntityType.COW, EntityType.PIG, EntityType.GUARDIAN, EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.SLIME, EntityType.MAGMA_CUBE, EntityType.BLAZE, EntityType.GHAST, EntityType.PIG_ZOMBIE, EntityType.SILVERFISH, EntityType.VILLAGER);
    private static final List<String> help = Lists.newArrayList(ChatColor.DARK_GREEN + " - " + ChatColor.GREEN + "/Disguise Entity [EntityType]", ChatColor.DARK_GREEN + " - " + ChatColor.GREEN + "/Disguise Block [Id] [Data]", ChatColor.DARK_GREEN + " - " + ChatColor.GREEN + "/Disguise Player [Player]", ChatColor.DARK_GREEN + " - " + ChatColor.GREEN + "/Undisguise");

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        //Sender is not player
        if(!(s instanceof Player)) return false;

        Player p = (Player) s;

        if(!p.hasPermission("minegusta.disguise") || !p.hasPermission("minegusta.disguise.special"))
        {
            sendString(p, ChatColor.RED + "You cannot use that!");
            return true;
        }

        if(args.length < 2 || args.length > 3)
        {
            sendMessage(p, help);
            return true;
        }

        if(args[0].equalsIgnoreCase("Player"))
        {
            if(!p.hasPermission("minegusta.disguise.special"))
            {
                sendString(p, ChatColor.RED + "You cannot use that!");
                return true;
            }

            try{
                OfflinePlayer disguise = Bukkit.getOfflinePlayer(args[1]);
                DisguiseAPI.disguiseToAll(p, new PlayerDisguise(disguise.getName()));
                sendString(p, ChatColor.GREEN + "You disguised as " + disguise.getName() + "!");
                return true;
            } catch (Exception ignored){
                sendString(p, ChatColor.RED + "That player could not be found!");
                return true;
            }
        }
        if(args[0].equalsIgnoreCase("Block"))
        {
            if(!p.hasPermission("minegusta.disguise.special"))
            {
                sendString(p, ChatColor.RED + "You cannot use that!");
                return true;
            }

            if(args.length < 3)
            {
                sendString(p, ChatColor.RED + "Too few parameters! Usage: /D Block Id Data");
                return true;
            }
            try{

                int data = Integer.parseInt(args[2]);
                int block = Integer.parseInt(args[1]);

                if(Material.getMaterial(block) == null)
                {
                    sendString(p, ChatColor.RED + "That block could not be found!");
                    return true;
                }

                Disguise d = new MiscDisguise(DisguiseType.FALLING_BLOCK, block, data);
                DisguiseAPI.disguiseToAll(p, d);
                sendString(p, ChatColor.GREEN + "You disguised as " + Material.getMaterial(block).name() + "!");
                return true;
            } catch (Exception ignored){
                sendString(p, ChatColor.RED + "That block could not be found!");
                return true;
            }
        }
        if(args[0].equalsIgnoreCase("Entity"))
        {
            EntityType disguise;
            try{
                disguise = EntityType.valueOf(args[1].toUpperCase());
            } catch (Exception ignored){
                sendString(p, ChatColor.RED + "That entity could not be found!");
                return true;
            }
            if(!disguise.isSpawnable())
            {
                sendString(p, ChatColor.RED + "That entity could not be found!");
                return true;
            }

            if(defaultAllowed.contains(disguise))
            {
                if(disguise.isAlive())
                {
                    DisguiseAPI.disguiseToAll(p, new MobDisguise(disguise));
                    sendString(p, ChatColor.GREEN + "You disguised as a " + disguise.getName() + ".");
                    return true;
                }
                else
                {
                    DisguiseAPI.disguiseToAll(p, new MiscDisguise(disguise));
                    sendString(p, ChatColor.GREEN + "You disguised as a " + disguise.getName() + ".");
                    return true;
                }
            }
            else if(p.hasPermission("minegusta.disguise.special"))
            {
                if(disguise.isAlive())
                {
                    DisguiseAPI.disguiseToAll(p, new MobDisguise(disguise));
                    sendString(p, ChatColor.GREEN + "You disguised as a " + disguise.getName() + ".");
                    return true;
                }
                else
                {
                    DisguiseAPI.disguiseToAll(p, new MiscDisguise(disguise));
                    sendString(p, ChatColor.GREEN + "You disguised as a " + disguise.getName() + ".");
                    return true;
                }
            }
            sendString(p, ChatColor.RED + "You do not have permission to use that!");
            return true;
        }
        sendMessage(p, help);
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
