
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import fr.eriniumgroup.eriniumadventure.base.item.NitriateGemItem;
import fr.eriniumgroup.eriniumadventure.base.item.LogoEriniumItem;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

public class EriniumAdventureModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, EriniumAdventureMod.MODID);
	public static final RegistryObject<Item> LOGO_ERINIUM = REGISTRY.register("logo_erinium", () -> new LogoEriniumItem());
	public static final RegistryObject<Item> NITRIATE_GEM = REGISTRY.register("nitriate_gem", () -> new NitriateGemItem());
	public static final RegistryObject<Item> NITRIATE_ORE = block(EriniumAdventureModBlocks.NITRIATE_ORE);

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
