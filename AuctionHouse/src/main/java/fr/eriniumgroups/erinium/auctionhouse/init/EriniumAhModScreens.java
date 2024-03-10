
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.auctionhouse.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import fr.eriniumgroups.erinium.auctionhouse.client.gui.ThemeSelect0Screen;
import fr.eriniumgroups.erinium.auctionhouse.client.gui.SellGuiScreen;
import fr.eriniumgroups.erinium.auctionhouse.client.gui.DeleteItemsScreen;
import fr.eriniumgroups.erinium.auctionhouse.client.gui.BuyGuiScreen;
import fr.eriniumgroups.erinium.auctionhouse.client.gui.AhMainMenuScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EriniumAhModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(EriniumAhModMenus.AH_MAIN_MENU.get(), AhMainMenuScreen::new);
			MenuScreens.register(EriniumAhModMenus.BUY_GUI.get(), BuyGuiScreen::new);
			MenuScreens.register(EriniumAhModMenus.DELETE_ITEMS.get(), DeleteItemsScreen::new);
			MenuScreens.register(EriniumAhModMenus.SELL_GUI.get(), SellGuiScreen::new);
			MenuScreens.register(EriniumAhModMenus.THEME_SELECT_0.get(), ThemeSelect0Screen::new);
		});
	}
}
