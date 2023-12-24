
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import fr.eriniumgroup.eriniumadventure.automation.client.gui.OneBlockCropGuiScreen;
import fr.eriniumgroup.eriniumadventure.automation.client.gui.FarmerJsonBuilderGuiMainScreen;
import fr.eriniumgroup.eriniumadventure.automation.client.gui.FarmerGuiScreen;
import fr.eriniumgroup.eriniumadventure.automation.client.gui.AstralMinerGuiScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EriniumAutomationModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(EriniumAutomationModMenus.FARMER_GUI.get(), FarmerGuiScreen::new);
			MenuScreens.register(EriniumAutomationModMenus.ASTRAL_MINER_GUI.get(), AstralMinerGuiScreen::new);
			MenuScreens.register(EriniumAutomationModMenus.FARMER_JSON_BUILDER_GUI_MAIN.get(), FarmerJsonBuilderGuiMainScreen::new);
			MenuScreens.register(EriniumAutomationModMenus.ONE_BLOCK_CROP_GUI.get(), OneBlockCropGuiScreen::new);
		});
	}
}
