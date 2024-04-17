
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import fr.eriniumgroup.eriniumadventure.automation.block.entity.NetherStarGeneratorBlockEntity;
import fr.eriniumgroup.eriniumadventure.automation.block.entity.FarmerBlockBlockEntity;
import fr.eriniumgroup.eriniumadventure.automation.block.entity.AstralMinerBlockEntity;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumAutomationModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, EriniumAutomationMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> FARMER_BLOCK = register("farmer_block", EriniumAutomationModBlocks.FARMER_BLOCK, FarmerBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> ASTRAL_MINER = register("astral_miner", EriniumAutomationModBlocks.ASTRAL_MINER, AstralMinerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> NETHER_STAR_GENERATOR = register("nether_star_generator", EriniumAutomationModBlocks.NETHER_STAR_GENERATOR, NetherStarGeneratorBlockEntity::new);

	private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, FARMER_BLOCK.get(), (blockEntity, side) -> ((FarmerBlockBlockEntity) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, FARMER_BLOCK.get(), (blockEntity, side) -> ((FarmerBlockBlockEntity) blockEntity).getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ASTRAL_MINER.get(), (blockEntity, side) -> ((AstralMinerBlockEntity) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ASTRAL_MINER.get(), (blockEntity, side) -> ((AstralMinerBlockEntity) blockEntity).getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NETHER_STAR_GENERATOR.get(), (blockEntity, side) -> ((NetherStarGeneratorBlockEntity) blockEntity).getItemHandler());
	}
}
