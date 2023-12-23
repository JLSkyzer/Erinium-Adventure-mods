
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import fr.eriniumgroup.eriniumadventure.automation.block.entity.FarmerBlockBlockEntity;
import fr.eriniumgroup.eriniumadventure.automation.block.entity.AstralMinerBlockEntity;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

public class EriniumAutomationModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EriniumAutomationMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> FARMER_BLOCK = register("farmer_block", EriniumAutomationModBlocks.FARMER_BLOCK, FarmerBlockBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ASTRAL_MINER = register("astral_miner", EriniumAutomationModBlocks.ASTRAL_MINER, AstralMinerBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
