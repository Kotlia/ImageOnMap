/*
 * Copyright (C) 2013 Moribus
 * Copyright (C) 2015 ProkopyL <prokopylmc@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.moribus.imageonmap.gui.core;

import fr.moribus.imageonmap.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;


/**
 * @author IamBlueSlime
 *
 * Changes by Amaury Carrade to use statics (beh, code style, these things).
 */
public class GuiListener implements Listener {

	public static void init(ImageOnMap plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(new GuiListener(), plugin);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		if (event.getWhoClicked() instanceof Player) {
			Player player = (Player) event.getWhoClicked();
			AbstractGui gui = GuiManager.getPlayerGui(player);

			if (gui != null) {
				if (event.getInventory() instanceof PlayerInventory)
					return;

				String action = gui.getAction(event.getSlot());

				if (action != null)
					gui.onClick(player, event.getCurrentItem(), action, event.getClick());

				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event)
	{
		if (GuiManager.getPlayerGui(event.getPlayer()) != null)
			GuiManager.removeClosedGui((Player) event.getPlayer());
	}
}
