
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import fr.eriniumgroup.eriniumadventure.base.block.NitriateOreBlock;
import fr.eriniumgroup.eriniumadventure.base.block.LightDarkBrickBlock;
import fr.eriniumgroup.eriniumadventure.base.block.CaveBlockBlock;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

public class EriniumAdventureModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK, EriniumAdventureMod.MODID);
	public static final DeferredHolder<Block, Block> NITRIATE_ORE = REGISTRY.register("nitriate_ore", () -> new NitriateOreBlock());
	public static final DeferredHolder<Block, Block> CAVE_BLOCK = REGISTRY.register("cave_block", () -> new CaveBlockBlock());
	public static final DeferredHolder<Block, Block> LIGHT_DARK_BRICK = REGISTRY.register("light_dark_brick", () -> new LightDarkBrickBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
