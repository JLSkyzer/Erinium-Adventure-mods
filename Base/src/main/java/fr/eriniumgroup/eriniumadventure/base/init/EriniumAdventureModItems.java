
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.core.registries.BuiltInRegistries;

import fr.eriniumgroup.eriniumadventure.base.item.StarshipItemItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriateSwordItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriateShovelItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriatePickaxeItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriateHoeItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriateGemItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriateAxeItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriateArmorItem;
import fr.eriniumgroup.eriniumadventure.base.item.LogoEriniumItem;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

public class EriniumAdventureModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, EriniumAdventureMod.MODID);
	public static final DeferredHolder<Item, Item> NITRIATE_GEM = REGISTRY.register("nitriate_gem", () -> new NitriateGemItem());
	public static final DeferredHolder<Item, Item> LOGO_ERINIUM = REGISTRY.register("logo_erinium", () -> new LogoEriniumItem());
	public static final DeferredHolder<Item, Item> NITRIATE_ORE = block(EriniumAdventureModBlocks.NITRIATE_ORE);
	public static final DeferredHolder<Item, Item> NITRIATE_PICKAXE = REGISTRY.register("nitriate_pickaxe", () -> new NitriatePickaxeItem());
	public static final DeferredHolder<Item, Item> NITRIATE_AXE = REGISTRY.register("nitriate_axe", () -> new NitriateAxeItem());
	public static final DeferredHolder<Item, Item> NITRIATE_SWORD = REGISTRY.register("nitriate_sword", () -> new NitriateSwordItem());
	public static final DeferredHolder<Item, Item> NITRIATE_SHOVEL = REGISTRY.register("nitriate_shovel", () -> new NitriateShovelItem());
	public static final DeferredHolder<Item, Item> NITRIATE_HOE = REGISTRY.register("nitriate_hoe", () -> new NitriateHoeItem());
	public static final DeferredHolder<Item, Item> NITRIATE_ARMOR_HELMET = REGISTRY.register("nitriate_armor_helmet", () -> new NitriateArmorItem.Helmet());
	public static final DeferredHolder<Item, Item> NITRIATE_ARMOR_CHESTPLATE = REGISTRY.register("nitriate_armor_chestplate", () -> new NitriateArmorItem.Chestplate());
	public static final DeferredHolder<Item, Item> NITRIATE_ARMOR_LEGGINGS = REGISTRY.register("nitriate_armor_leggings", () -> new NitriateArmorItem.Leggings());
	public static final DeferredHolder<Item, Item> NITRIATE_ARMOR_BOOTS = REGISTRY.register("nitriate_armor_boots", () -> new NitriateArmorItem.Boots());
	public static final DeferredHolder<Item, Item> CAVE_BLOCK = block(EriniumAdventureModBlocks.CAVE_BLOCK);
	public static final DeferredHolder<Item, Item> LIGHT_DARK_BRICK = block(EriniumAdventureModBlocks.LIGHT_DARK_BRICK);
	public static final DeferredHolder<Item, Item> ROCKET_BOOSTER_SPAWN_EGG = REGISTRY.register("rocket_booster_spawn_egg", () -> new DeferredSpawnEggItem(EriniumAdventureModEntities.ROCKET_BOOSTER, -1, -1, new Item.Properties()));
	public static final DeferredHolder<Item, Item> ROCKET_HEAD_SPAWN_EGG = REGISTRY.register("rocket_head_spawn_egg", () -> new DeferredSpawnEggItem(EriniumAdventureModEntities.ROCKET_HEAD, -1, -1, new Item.Properties()));
	public static final DeferredHolder<Item, Item> STARSHIP_ITEM = REGISTRY.register("starship_item", () -> new StarshipItemItem());

	// Start of user code block custom items
	// End of user code block custom items
	public static void register(IEventBus bus) {
		REGISTRY.register(bus);
	}

	private static DeferredHolder<Item, Item> block(DeferredHolder<Block, Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
