
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumAdventureModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EriniumAdventureMod.MODID);
	public static final RegistryObject<CreativeModeTab> ERINIUM_ADVENTURE_TAB = REGISTRY.register("erinium_adventure_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.erinium_adventure.erinium_adventure_tab")).icon(() -> new ItemStack(EriniumAdventureModItems.LOGO_ERINIUM.get())).displayItems((parameters, tabData) -> {
				tabData.accept(EriniumAdventureModItems.NITRIATE_GEM.get());
				tabData.accept(EriniumAdventureModBlocks.NITRIATE_ORE.get().asItem());
				tabData.accept(EriniumAdventureModBlocks.CAVE_BLOCK.get().asItem());
				tabData.accept(EriniumAdventureModBlocks.LIGHT_DARK_BRICK.get().asItem());
			}).withSearchBar().build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {

		if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(EriniumAdventureModItems.NITRIATE_SWORD.get());
			tabData.accept(EriniumAdventureModItems.NITRIATE_ARMOR_HELMET.get());
			tabData.accept(EriniumAdventureModItems.NITRIATE_ARMOR_CHESTPLATE.get());
			tabData.accept(EriniumAdventureModItems.NITRIATE_ARMOR_LEGGINGS.get());
			tabData.accept(EriniumAdventureModItems.NITRIATE_ARMOR_BOOTS.get());
		}

		if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(EriniumAdventureModItems.NITRIATE_AXE.get());
			tabData.accept(EriniumAdventureModItems.NITRIATE_PICKAXE.get());
			tabData.accept(EriniumAdventureModItems.NITRIATE_SHOVEL.get());
			tabData.accept(EriniumAdventureModItems.NITRIATE_HOE.get());
		}
	}
}
