package com.minegusta.mgdisguises;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    @Override
    public void onEnable()
    {

        //Commands
        getCommand("disguise").setExecutor(new DisguiseCommand());
        getCommand("undisguise").setExecutor(new UndisguiseCommand());

        //Listener
        Bukkit.getPluginManager().registerEvents(new DisguiseListener(), this);
    }

    @Override
    public void onDisable()
    {

    }
}
