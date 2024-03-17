
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.ericonomy.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import fr.eriniumgroups.erinium.ericonomy.block.CobbleVoidStationBlock;
import fr.eriniumgroups.erinium.ericonomy.EriconomyMod;

public class EriconomyModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, EriconomyMod.MODID);
	public static final RegistryObject<Block> COBBLE_VOID_STATION = REGISTRY.register("cobble_void_station", () -> new CobbleVoidStationBlock());
}
