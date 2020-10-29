package stream.azakt.oneplayersleep;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class OnePlayerSleep extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("=> Successfully initiated.");
    }

    @Override
    public void onDisable() {
        getLogger().info("=> Successfully shut down.");
    }

    @EventHandler
    public void onPlayerEnterBed(PlayerBedEnterEvent e) {
        World w = e.getBed().getWorld();
        if(!w.isDayTime() && w.getEnvironment() == World.Environment.NORMAL) {
            getLogger().info(String.format("=> Player (%s) detected as sleeping. Skipping night.", e.getPlayer().getDisplayName()));
            w.setTime(0);
            Bukkit.broadcastMessage(String.format("%s slept through the night!", e.getPlayer().getDisplayName()));
            if(w.hasStorm() || w.isThundering()) {
                w.setStorm(false);
                w.setThundering(false);
            }
        }
    }
}
