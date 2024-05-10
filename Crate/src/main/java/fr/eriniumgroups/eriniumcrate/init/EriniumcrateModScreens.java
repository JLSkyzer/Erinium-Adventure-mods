
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.eriniumcrate.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import fr.eriniumgroups.eriniumcrate.client.gui.LootListScreen;
import fr.eriniumgroups.eriniumcrate.client.gui.LootCreatorGuiScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EriniumcrateModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(EriniumcrateModMenus.LOOT_LIST.get(), LootListScreen::new);
		event.register(EriniumcrateModMenus.LOOT_CREATOR_GUI.get(), LootCreatorGuiScreen::new);
	}
}
