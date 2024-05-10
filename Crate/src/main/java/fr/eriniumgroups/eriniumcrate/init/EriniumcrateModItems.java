
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.eriniumcrate.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.core.registries.BuiltInRegistries;

import fr.eriniumgroups.eriniumcrate.item.BasicKeyItem;
import fr.eriniumgroups.eriniumcrate.EriniumcrateMod;

public class EriniumcrateModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, EriniumcrateMod.MODID);
	public static final DeferredHolder<Item, Item> BASICCRATE = block(EriniumcrateModBlocks.BASICCRATE);
	public static final DeferredHolder<Item, Item> BASIC_KEY = REGISTRY.register("basic_key", () -> new BasicKeyItem());

	// Start of user code block custom items
	// End of user code block custom items
	public static void register(IEventBus bus) {
		REGISTRY.register(bus);
	}

	private static DeferredHolder<Item, Item> block(DeferredHolder<Block, Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
