
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.jobs.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import fr.eriniumgroups.erinium.jobs.client.gui.WonXpOverlayConfigScreen;
import fr.eriniumgroups.erinium.jobs.client.gui.QFQScreen;
import fr.eriniumgroups.erinium.jobs.client.gui.ConfigPage1Screen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EriniumjobsModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(EriniumjobsModMenus.WON_XP_OVERLAY_CONFIG.get(), WonXpOverlayConfigScreen::new);
			MenuScreens.register(EriniumjobsModMenus.CONFIG_PAGE_1.get(), ConfigPage1Screen::new);
			MenuScreens.register(EriniumjobsModMenus.QFQ.get(), QFQScreen::new);
		});
	}
}
