
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.jobs.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import fr.eriniumgroups.erinium.jobs.client.gui.WonXpOverlayConfigScreen;
import fr.eriniumgroups.erinium.jobs.client.gui.UnlockedGuiScreen;
import fr.eriniumgroups.erinium.jobs.client.gui.RequiredMakerScreen;
import fr.eriniumgroups.erinium.jobs.client.gui.RankInfoScreen;
import fr.eriniumgroups.erinium.jobs.client.gui.QFQScreen;
import fr.eriniumgroups.erinium.jobs.client.gui.EarnXpMakerScreen;
import fr.eriniumgroups.erinium.jobs.client.gui.EarnXpGuiScreen;
import fr.eriniumgroups.erinium.jobs.client.gui.ConfigPage1Screen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EriniumjobsModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(EriniumjobsModMenus.WON_XP_OVERLAY_CONFIG.get(), WonXpOverlayConfigScreen::new);
		event.register(EriniumjobsModMenus.CONFIG_PAGE_1.get(), ConfigPage1Screen::new);
		event.register(EriniumjobsModMenus.QFQ.get(), QFQScreen::new);
		event.register(EriniumjobsModMenus.EARN_XP_GUI.get(), EarnXpGuiScreen::new);
		event.register(EriniumjobsModMenus.UNLOCKED_GUI.get(), UnlockedGuiScreen::new);
		event.register(EriniumjobsModMenus.EARN_XP_MAKER.get(), EarnXpMakerScreen::new);
		event.register(EriniumjobsModMenus.REQUIRED_MAKER.get(), RequiredMakerScreen::new);
		event.register(EriniumjobsModMenus.RANK_INFO.get(), RankInfoScreen::new);
	}
}
