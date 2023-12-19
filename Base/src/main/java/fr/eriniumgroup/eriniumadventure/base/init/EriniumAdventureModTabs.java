
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

public class EriniumAdventureModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EriniumAdventureMod.MODID);
	public static final RegistryObject<CreativeModeTab> ERINIUM_ADVENTURE_TAB = REGISTRY.register("erinium_adventure_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.erinium_adventure.erinium_adventure_tab")).icon(() -> new ItemStack(EriniumAdventureModItems.LOGO_ERINIUM.get())).displayItems((parameters, tabData) -> {
				tabData.accept(EriniumAdventureModItems.NITRIATE_GEM.get());
				tabData.accept(EriniumAdventureModBlocks.NITRIATE_ORE.get().asItem());
			}).withSearchBar().build());
}
