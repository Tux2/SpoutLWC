package tux2.spoutlwc;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.Plugin;

import com.griefcraft.lwc.LWCPlugin;

public class LWCServerListener extends ServerListener {
	
	SpoutLWC plugin;
	
	public LWCServerListener(SpoutLWC plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPluginDisable(PluginDisableEvent event) {
        if (plugin.lwc != null) {
            if (event.getPlugin().getDescription().getName().equals("LWC")) {
                plugin.lwc = null;
                System.out.println("[SpoutLWC] un-hooked from LWC. Disabling Spout GUI.");
            }
        }
    }

    @Override
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
