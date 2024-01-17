
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import fr.eriniumgroup.eriniumadventure.automation.item.ModLogoItem;
import fr.eriniumgroup.eriniumadventure.automation.item.LaserModuleItem;
import fr.eriniumgroup.eriniumadventure.automation.item.LaserDrillItem;
import fr.eriniumgroup.eriniumadventure.automation.item.BatteryItem;
import fr.eriniumgroup.eriniumadventure.automation.item.AstralFootItem;
import fr.eriniumgroup.eriniumadventure.automation.item.AmenineGemItem;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

public class EriniumAutomationModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, EriniumAutomationMod.MODID);
	public static final RegistryObject<Item> FARMER_BLOCK = block(EriniumAutomationModBlocks.FARMER_BLOCK);
	public static final RegistryObject<Item> ASTRAL_FOOT = REGISTRY.register("astral_foot", () -> new AstralFootItem());
	public static final RegistryObject<Item> LASER_DRILL = REGISTRY.register("laser_drill", () -> new LaserDrillItem());
	public static final RegistryObject<Item> LASER_MODULE = REGISTRY.register("laser_module", () -> new LaserModuleItem());
	public static final RegistryObject<Item> BATTERY = REGISTRY.register("battery", () -> new BatteryItem());
	public static final RegistryObject<Item> DARK_IRON_BLOCK = block(EriniumAutomationModBlocks.DARK_IRON_BLOCK);
	public static final RegistryObject<Item> AMENINE_ORE = block(EriniumAutomationModBlocks.AMENINE_ORE);
	public static final RegistryObject<Item> AMENINE_GEM = REGISTRY.register("amenine_gem", () -> new AmenineGemItem());
	public static final RegistryObject<Item> ASTRAL_MINER = block(EriniumAutomationModBlocks.ASTRAL_MINER);
	public static final RegistryObject<Item> ENRICHED_FARMLAND = block(EriniumAutomationModBlocks.ENRICHED_FARMLAND);
	public static final RegistryObject<Item> MOD_LOGO = REGISTRY.register("mod_logo", () -> new ModLogoItem());

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
