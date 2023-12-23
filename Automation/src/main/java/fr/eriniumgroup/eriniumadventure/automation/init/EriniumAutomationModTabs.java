
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

public class EriniumAutomationModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EriniumAutomationMod.MODID);
	public static final RegistryObject<CreativeModeTab> ERINIUM_AUTOMATION = REGISTRY.register("erinium_automation",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.erinium_automation.erinium_automation")).icon(() -> new ItemStack(EriniumAutomationModBlocks.FARMER_BLOCK.get())).displayItems((parameters, tabData) -> {
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
			})

					.build());
}
