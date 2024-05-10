
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.auctionhouse.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import fr.eriniumgroups.erinium.auctionhouse.client.gui.ThemeSelect0Screen;
import fr.eriniumgroups.erinium.auctionhouse.client.gui.SellGuiScreen;
import fr.eriniumgroups.erinium.auctionhouse.client.gui.DeleteItemsScreen;
import fr.eriniumgroups.erinium.auctionhouse.client.gui.BuyGuiScreen;
import fr.eriniumgroups.erinium.auctionhouse.client.gui.AhMainMenuScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EriniumAhModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(EriniumAhModMenus.AH_MAIN_MENU.get(), AhMainMenuScreen::new);
		event.register(EriniumAhModMenus.BUY_GUI.get(), BuyGuiScreen::new);
		event.register(EriniumAhModMenus.DELETE_ITEMS.get(), DeleteItemsScreen::new);
		event.register(EriniumAhModMenus.SELL_GUI.get(), SellGuiScreen::new);
		event.register(EriniumAhModMenus.THEME_SELECT_0.get(), ThemeSelect0Screen::new);
	}
}
