/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tux2.spoutlwc;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericRadioButton;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author david
 */
public class LWCRadioButton extends GenericRadioButton {

    private SpoutLWC plugin;
    
    public LWCRadioButton(String name, SpoutLWC plugin) {
        super(name);
        this.plugin = plugin;
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        String completebutton = this.getText();
        SpoutPlayer player = event.getPlayer();
        
        if (plugin.lwc.hasPermission(player, "lwc.protect")) {
            if (completebutton.equalsIgnoreCase("Password Lock")
                    || completebutton.equalsIgnoreCase("Private Lock")
                    || completebutton.equalsIgnoreCase("Public Lock")) {
                if (plugin.guiscreens.containsKey(player)) {
                    PlayerLwcGUI tgui = plugin.guiscreens.get(player);
                    tgui.savebutton.setEnabled(true);
                }
            }
        }
    }
    }
