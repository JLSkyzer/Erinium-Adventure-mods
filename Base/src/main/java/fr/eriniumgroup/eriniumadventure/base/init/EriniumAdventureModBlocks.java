
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import fr.eriniumgroup.eriniumadventure.base.block.NitriateOreBlock;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

public class EriniumAdventureModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, EriniumAdventureMod.MODID);
	public static final RegistryObject<Block> NITRIATE_ORE = REGISTRY.register("nitriate_ore", () -> new NitriateOreBlock());
}
