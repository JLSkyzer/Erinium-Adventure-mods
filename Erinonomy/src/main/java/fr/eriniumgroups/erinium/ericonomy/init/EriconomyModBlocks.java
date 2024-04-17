
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.ericonomy.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import fr.eriniumgroups.erinium.ericonomy.block.CobbleVoidStationBlock;
import fr.eriniumgroups.erinium.ericonomy.EriconomyMod;

public class EriconomyModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK, EriconomyMod.MODID);
	public static final DeferredHolder<Block, Block> COBBLE_VOID_STATION = REGISTRY.register("cobble_void_station", () -> new CobbleVoidStationBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
