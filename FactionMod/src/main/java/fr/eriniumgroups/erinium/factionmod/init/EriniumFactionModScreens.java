
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.factionmod.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import fr.eriniumgroups.erinium.factionmod.client.gui.SeleteRankGuiScreen;
import fr.eriniumgroups.erinium.factionmod.client.gui.EditPermissionGuiScreen;
import fr.eriniumgroups.erinium.factionmod.client.gui.BlacklistItemGuiScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EriniumFactionModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(EriniumFactionModMenus.EDIT_PERMISSION_GUI.get(), EditPermissionGuiScreen::new);
		event.register(EriniumFactionModMenus.SELETE_RANK_GUI.get(), SeleteRankGuiScreen::new);
		event.register(EriniumFactionModMenus.BLACKLIST_ITEM_GUI.get(), BlacklistItemGuiScreen::new);
	}
}
