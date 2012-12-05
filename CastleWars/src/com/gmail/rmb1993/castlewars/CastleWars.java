package com.gmail.rmb1993.castlewars;

import com.gmail.rmb1993.castlewars.arena.CWArena;
import com.gmail.rmb1993.castlewars.listeners.CWBlockListener;
import com.gmail.rmb1993.castlewars.listeners.CWPlayerListener;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Ryan
 */
public class CastleWars extends JavaPlugin {
    
    private CWBlockListener bl;
    private CWPlayerListener pl;
    private ConcurrentHashMap<String, CWArena> arenas = new ConcurrentHashMap();

    public ConcurrentHashMap<String, CWArena> getArenas() {
        return arenas;
    }
    
    @Override
    public void onEnable() {
        
    }
    
    @Override
    public void onDisable() {
        
    }
    
}
