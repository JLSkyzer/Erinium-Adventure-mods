
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.eriniumcrate.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import fr.eriniumgroups.eriniumcrate.block.BasiccrateBlock;
import fr.eriniumgroups.eriniumcrate.EriniumcrateMod;

public class EriniumcrateModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK, EriniumcrateMod.MODID);
	public static final DeferredHolder<Block, Block> BASICCRATE = REGISTRY.register("basiccrate", () -> new BasiccrateBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
