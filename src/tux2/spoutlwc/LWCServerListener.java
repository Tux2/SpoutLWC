package tux2.spoutlwc;

import com.griefcraft.lwc.LWCPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

public class LWCServerListener implements Listener {
	
	SpoutLWC plugin;
	
	public LWCServerListener(SpoutLWC plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPluginDisable(PluginDisableEvent event) {
        if (plugin.lwc != null) {
            if (event.getPlugin().getDescription().getName().equals("LWC")) {
                plugin.lwc = null;
                System.out.println("[SpoutLWC] un-hooked from LWC. Disabling Spout GUI.");
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPluginEnable(PluginEnableEvent event) {
        if (plugin.lwc == null) {
            Plugin lwcPlugin = plugin.getServer().getPluginManager().getPlugin("LWC");

            if (lwcPlugin != null) {
                if (lwcPlugin.isEnabled() && lwcPlugin.getClass().getName().equals("com.griefcraft.lwc.LWCPlugin")) {
                    plugin.lwc = ((LWCPlugin) lwcPlugin).getLWC();
                    System.out.println("[SpoutLWC] Found LWC. Enabling Spout GUI!");
                }
            }
        }
    }
}
