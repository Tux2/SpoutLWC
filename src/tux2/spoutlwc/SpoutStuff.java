package tux2.spoutlwc;

import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutStuff {
	
	SpoutLWC plugin;
	
	public SpoutStuff(SpoutLWC plugin) {
		this.plugin = plugin;
	}
	
	public void createLwcGUI(String title, SpoutPlayer splayer) {
		GenericPopup monsters = new GenericPopup();
		GenericLabel label = new GenericLabel(title);
		label.setTextColor(new Color(0, 200, 0)); //This makes the label green.
		label.setAlign(WidgetAnchor.TOP_CENTER).setAnchor(WidgetAnchor.TOP_CENTER); //This puts the label at top center and align the text correctly.
		label.shiftYPos(5);
		monsters.attachWidget(plugin, label);
		GenericButton tbutton = new GenericButton("Close");
		tbutton.setX(200).setY(210);
		tbutton.setWidth(80).setHeight(20);
		monsters.attachWidget(plugin, tbutton);
		splayer.getMainScreen().attachPopupScreen(monsters);
	}

}
