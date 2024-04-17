
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import fr.eriniumgroup.eriniumadventure.automation.block.WitherSkeletonBlockBlock;
import fr.eriniumgroup.eriniumadventure.automation.block.NetherStarGeneratorBlock;
import fr.eriniumgroup.eriniumadventure.automation.block.FarmerBlockBlock;
import fr.eriniumgroup.eriniumadventure.automation.block.EnrichedFarmlandBlock;
import fr.eriniumgroup.eriniumadventure.automation.block.DarkIronBlockBlock;
import fr.eriniumgroup.eriniumadventure.automation.block.AstralMinerBlock;
import fr.eriniumgroup.eriniumadventure.automation.block.AmenineOreBlock;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

public class EriniumAutomationModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK, EriniumAutomationMod.MODID);
	public static final DeferredHolder<Block, Block> FARMER_BLOCK = REGISTRY.register("farmer_block", () -> new FarmerBlockBlock());
	public static final DeferredHolder<Block, Block> DARK_IRON_BLOCK = REGISTRY.register("dark_iron_block", () -> new DarkIronBlockBlock());
	public static final DeferredHolder<Block, Block> AMENINE_ORE = REGISTRY.register("amenine_ore", () -> new AmenineOreBlock());
	public static final DeferredHolder<Block, Block> ASTRAL_MINER = REGISTRY.register("astral_miner", () -> new AstralMinerBlock());
	public static final DeferredHolder<Block, Block> ENRICHED_FARMLAND = REGISTRY.register("enriched_farmland", () -> new EnrichedFarmlandBlock());
	public static final DeferredHolder<Block, Block> NETHER_STAR_GENERATOR = REGISTRY.register("nether_star_generator", () -> new NetherStarGeneratorBlock());
	public static final DeferredHolder<Block, Block> WITHER_SKELETON_BLOCK = REGISTRY.register("wither_skeleton_block", () -> new WitherSkeletonBlockBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
