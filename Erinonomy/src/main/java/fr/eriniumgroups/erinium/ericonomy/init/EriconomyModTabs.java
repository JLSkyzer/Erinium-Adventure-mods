
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.ericonomy.init;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroups.erinium.ericonomy.EriconomyMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriconomyModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EriconomyMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {

		if (tabData.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			tabData.accept(EriconomyModBlocks.COBBLE_VOID_STATION.get().asItem());
		}

		if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			tabData.accept(EriconomyModItems.PIECE_1_CENT.get());
			tabData.accept(EriconomyModItems.PIECE_5_CENT.get());
			tabData.accept(EriconomyModItems.PIECE_10_CENT.get());
			tabData.accept(EriconomyModItems.PIECE_20_CENT.get());
			tabData.accept(EriconomyModItems.PIECE_50_CENT.get());
			tabData.accept(EriconomyModItems.PIECE_1_DOLLARS.get());
			tabData.accept(EriconomyModItems.PIECE_2_DOLLARS.get());
			tabData.accept(EriconomyModItems.PIECE_5_DOLLARS.get());
			tabData.accept(EriconomyModItems.BILL_10_DOLLARS.get());
			tabData.accept(EriconomyModItems.BILLE_20_DOLLARS.get());
			tabData.accept(EriconomyModItems.BILL_50_DOLLARS.get());
			tabData.accept(EriconomyModItems.BILL_100_DOLLARS.get());
			tabData.accept(EriconomyModItems.BILL_200_DOLLARS.get());
			tabData.accept(EriconomyModItems.BILLE_500_DOLLARS.get());
			tabData.accept(EriconomyModItems.BILL_1000_DOLLARS.get());
		}

		if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(EriconomyModItems.COBBLE_VOID.get());
		}
	}
}
