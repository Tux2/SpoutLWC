package tux2.spoutlwc;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

/**
 * spoutlwc for Bukkit
 *
 * @author tux2
 */
public class SpoutLWC extends JavaPlugin {
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
	private static PermissionHandler Permissions;

    public SpoutLWC() {
        super();
        // TODO: Place any custom initialisation code here

        // NOTE: Event registration should be done in onEnable not here as all events are unregistered when a plugin is disabled
    }

   

    public void onEnable() {
    	setupPermissions();
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Type.CUSTOM_EVENT, new LWCScreenListener(this), Priority.Normal, this);
       

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }
    public void onDisable() {
        // TODO: Place any custom disable code here

        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        System.out.println("Goodbye world!");
    }
    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }



	public boolean hasPermissions(Player player, String node) {
	    if (Permissions != null) {
	        return Permissions.has(player, node);
	    } else {
	        return player.hasPermission(node);
	    }
	}



	private void setupPermissions() {
	    Plugin permissions = this.getServer().getPluginManager().getPlugin("Permissions");
	
	    if (Permissions == null) {
	        if (permissions != null) {
	            Permissions = ((Permissions)permissions).getHandler();
	        } else {
	        }
	    }
	}
}

