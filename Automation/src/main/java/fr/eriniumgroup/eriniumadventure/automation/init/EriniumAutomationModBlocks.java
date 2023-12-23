
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import fr.eriniumgroup.eriniumadventure.automation.block.FarmerBlockBlock;
import fr.eriniumgroup.eriniumadventure.automation.block.EnrichedFarmlandBlock;
import fr.eriniumgroup.eriniumadventure.automation.block.DarkIronBlockBlock;
import fr.eriniumgroup.eriniumadventure.automation.block.AstralMinerBlock;
import fr.eriniumgroup.eriniumadventure.automation.block.AmenineOreBlock;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

public class EriniumAutomationModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, EriniumAutomationMod.MODID);
	public static final RegistryObject<Block> FARMER_BLOCK = REGISTRY.register("farmer_block", () -> new FarmerBlockBlock());
	public static final RegistryObject<Block> DARK_IRON_BLOCK = REGISTRY.register("dark_iron_block", () -> new DarkIronBlockBlock());
	public static final RegistryObject<Block> AMENINE_ORE = REGISTRY.register("amenine_ore", () -> new AmenineOreBlock());
	public static final RegistryObject<Block> ASTRAL_MINER = REGISTRY.register("astral_miner", () -> new AstralMinerBlock());
	public static final RegistryObject<Block> ENRICHED_FARMLAND = REGISTRY.register("enriched_farmland", () -> new EnrichedFarmlandBlock());
}
