
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import fr.eriniumgroup.eriniumadventure.automation.client.gui.OneBlockCropGuiScreen;
import fr.eriniumgroup.eriniumadventure.automation.client.gui.NetherStarGenScreen;
import fr.eriniumgroup.eriniumadventure.automation.client.gui.MultipleBlocksCropsGuyScreen;
import fr.eriniumgroup.eriniumadventure.automation.client.gui.FarmerJsonBuilderGuiMainScreen;
import fr.eriniumgroup.eriniumadventure.automation.client.gui.FarmerGuiScreen;
import fr.eriniumgroup.eriniumadventure.automation.client.gui.AstralMinerGuiScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EriniumAutomationModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(EriniumAutomationModMenus.FARMER_GUI.get(), FarmerGuiScreen::new);
		event.register(EriniumAutomationModMenus.ASTRAL_MINER_GUI.get(), AstralMinerGuiScreen::new);
		event.register(EriniumAutomationModMenus.FARMER_JSON_BUILDER_GUI_MAIN.get(), FarmerJsonBuilderGuiMainScreen::new);
		event.register(EriniumAutomationModMenus.ONE_BLOCK_CROP_GUI.get(), OneBlockCropGuiScreen::new);
		event.register(EriniumAutomationModMenus.MULTIPLE_BLOCKS_CROPS_GUY.get(), MultipleBlocksCropsGuyScreen::new);
		event.register(EriniumAutomationModMenus.NETHER_STAR_GEN.get(), NetherStarGenScreen::new);
	}
}
