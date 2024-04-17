
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.core.registries.BuiltInRegistries;

import fr.eriniumgroup.eriniumadventure.automation.item.ModLogoItem;
import fr.eriniumgroup.eriniumadventure.automation.item.LaserModuleItem;
import fr.eriniumgroup.eriniumadventure.automation.item.LaserDrillItem;
import fr.eriniumgroup.eriniumadventure.automation.item.BatteryItem;
import fr.eriniumgroup.eriniumadventure.automation.item.AstralFootItem;
import fr.eriniumgroup.eriniumadventure.automation.item.AmenineGemItem;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

public class EriniumAutomationModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, EriniumAutomationMod.MODID);
	public static final DeferredHolder<Item, Item> FARMER_BLOCK = block(EriniumAutomationModBlocks.FARMER_BLOCK);
	public static final DeferredHolder<Item, Item> ASTRAL_FOOT = REGISTRY.register("astral_foot", () -> new AstralFootItem());
	public static final DeferredHolder<Item, Item> LASER_DRILL = REGISTRY.register("laser_drill", () -> new LaserDrillItem());
	public static final DeferredHolder<Item, Item> LASER_MODULE = REGISTRY.register("laser_module", () -> new LaserModuleItem());
	public static final DeferredHolder<Item, Item> BATTERY = REGISTRY.register("battery", () -> new BatteryItem());
	public static final DeferredHolder<Item, Item> DARK_IRON_BLOCK = block(EriniumAutomationModBlocks.DARK_IRON_BLOCK);
	public static final DeferredHolder<Item, Item> AMENINE_ORE = block(EriniumAutomationModBlocks.AMENINE_ORE);
	public static final DeferredHolder<Item, Item> AMENINE_GEM = REGISTRY.register("amenine_gem", () -> new AmenineGemItem());
	public static final DeferredHolder<Item, Item> ASTRAL_MINER = block(EriniumAutomationModBlocks.ASTRAL_MINER);
	public static final DeferredHolder<Item, Item> ENRICHED_FARMLAND = block(EriniumAutomationModBlocks.ENRICHED_FARMLAND);
	public static final DeferredHolder<Item, Item> MOD_LOGO = REGISTRY.register("mod_logo", () -> new ModLogoItem());
	public static final DeferredHolder<Item, Item> NETHER_STAR_GENERATOR = block(EriniumAutomationModBlocks.NETHER_STAR_GENERATOR);
	public static final DeferredHolder<Item, Item> WITHER_SKELETON_BLOCK = block(EriniumAutomationModBlocks.WITHER_SKELETON_BLOCK);

	// Start of user code block custom items
	// End of user code block custom items
	public static void register(IEventBus bus) {
		REGISTRY.register(bus);
	}

	private static DeferredHolder<Item, Item> block(DeferredHolder<Block, Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
