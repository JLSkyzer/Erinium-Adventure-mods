
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.jobs.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import fr.eriniumgroups.erinium.jobs.item.InfoItemItem;
import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

public class EriniumjobsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, EriniumjobsMod.MODID);
	public static final RegistryObject<Item> INFO_ITEM = REGISTRY.register("info_item", () -> new InfoItemItem());
}
