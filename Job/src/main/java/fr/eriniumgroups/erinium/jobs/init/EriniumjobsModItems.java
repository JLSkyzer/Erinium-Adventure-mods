
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.jobs.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.world.item.Item;
import net.minecraft.core.registries.BuiltInRegistries;

import fr.eriniumgroups.erinium.jobs.item.InfoItemItem;
import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

public class EriniumjobsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, EriniumjobsMod.MODID);
	public static final DeferredHolder<Item, Item> INFO_ITEM = REGISTRY.register("info_item", () -> new InfoItemItem());

	// Start of user code block custom items
	// End of user code block custom items
	public static void register(IEventBus bus) {
		REGISTRY.register(bus);
	}
}
