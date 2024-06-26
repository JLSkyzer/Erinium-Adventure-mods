
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

public class EriniumAutomationModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EriniumAutomationMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ERINIUM_AUTOMATION = REGISTRY.register("erinium_automation",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.erinium_automation.erinium_automation")).icon(() -> new ItemStack(EriniumAutomationModItems.MOD_LOGO.get())).displayItems((parameters, tabData) -> {
				tabData.accept(EriniumAutomationModBlocks.FARMER_BLOCK.get().asItem());
				tabData.accept(EriniumAutomationModItems.ASTRAL_FOOT.get());
				tabData.accept(EriniumAutomationModItems.LASER_DRILL.get());
				tabData.accept(EriniumAutomationModItems.LASER_MODULE.get());
				tabData.accept(EriniumAutomationModItems.BATTERY.get());
				tabData.accept(EriniumAutomationModBlocks.DARK_IRON_BLOCK.get().asItem());
				tabData.accept(EriniumAutomationModBlocks.AMENINE_ORE.get().asItem());
				tabData.accept(EriniumAutomationModItems.AMENINE_GEM.get());
				tabData.accept(EriniumAutomationModBlocks.ASTRAL_MINER.get().asItem());
				tabData.accept(EriniumAutomationModBlocks.ENRICHED_FARMLAND.get().asItem());
				tabData.accept(EriniumAutomationModItems.MOD_LOGO.get());
				tabData.accept(EriniumAutomationModBlocks.NETHER_STAR_GENERATOR.get().asItem());
				tabData.accept(EriniumAutomationModBlocks.WITHER_SKELETON_BLOCK.get().asItem());
			})

					.build());
}
