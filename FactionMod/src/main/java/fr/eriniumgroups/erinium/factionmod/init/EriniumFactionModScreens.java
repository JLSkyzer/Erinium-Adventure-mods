
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.factionmod.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import fr.eriniumgroups.erinium.factionmod.client.gui.SeleteRankGuiScreen;
import fr.eriniumgroups.erinium.factionmod.client.gui.EditPermissionGuiScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EriniumFactionModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(EriniumFactionModMenus.SELETE_RANK_GUI.get(), SeleteRankGuiScreen::new);
			MenuScreens.register(EriniumFactionModMenus.EDIT_PERMISSION_GUI.get(), EditPermissionGuiScreen::new);
		});
	}
}
