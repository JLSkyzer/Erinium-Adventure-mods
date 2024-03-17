
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.ericonomy.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import fr.eriniumgroups.erinium.ericonomy.block.entity.CobbleVoidStationBlockEntity;
import fr.eriniumgroups.erinium.ericonomy.EriconomyMod;

public class EriconomyModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EriconomyMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> COBBLE_VOID_STATION = register("cobble_void_station", EriconomyModBlocks.COBBLE_VOID_STATION, CobbleVoidStationBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
