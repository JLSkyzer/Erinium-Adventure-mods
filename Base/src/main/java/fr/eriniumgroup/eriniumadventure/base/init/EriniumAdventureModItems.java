
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import fr.eriniumgroup.eriniumadventure.base.item.NitriateSwordItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriateShovelItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriatePickaxeItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriateHoeItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriateGemItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriateAxeItem;
import fr.eriniumgroup.eriniumadventure.base.item.NitriateArmorItem;
import fr.eriniumgroup.eriniumadventure.base.item.LogoEriniumItem;
import fr.eriniumgroup.eriniumadventure.base.item.HeartIconItem;
import fr.eriniumgroup.eriniumadventure.base.item.FlameIconItem;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

public class EriniumAdventureModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, EriniumAdventureMod.MODID);
	public static final RegistryObject<Item> LOGO_ERINIUM = REGISTRY.register("logo_erinium", () -> new LogoEriniumItem());
	public static final RegistryObject<Item> NITRIATE_GEM = REGISTRY.register("nitriate_gem", () -> new NitriateGemItem());
	public static final RegistryObject<Item> NITRIATE_ORE = block(EriniumAdventureModBlocks.NITRIATE_ORE);
	public static final RegistryObject<Item> NITRIATE_AXE = REGISTRY.register("nitriate_axe", () -> new NitriateAxeItem());
	public static final RegistryObject<Item> NITRIATE_PICKAXE = REGISTRY.register("nitriate_pickaxe", () -> new NitriatePickaxeItem());
	public static final RegistryObject<Item> NITRIATE_SWORD = REGISTRY.register("nitriate_sword", () -> new NitriateSwordItem());
	public static final RegistryObject<Item> NITRIATE_SHOVEL = REGISTRY.register("nitriate_shovel", () -> new NitriateShovelItem());
	public static final RegistryObject<Item> NITRIATE_HOE = REGISTRY.register("nitriate_hoe", () -> new NitriateHoeItem());
	public static final RegistryObject<Item> NITRIATE_ARMOR_HELMET = REGISTRY.register("nitriate_armor_helmet", () -> new NitriateArmorItem.Helmet());
	public static final RegistryObject<Item> NITRIATE_ARMOR_CHESTPLATE = REGISTRY.register("nitriate_armor_chestplate", () -> new NitriateArmorItem.Chestplate());
	public static final RegistryObject<Item> NITRIATE_ARMOR_LEGGINGS = REGISTRY.register("nitriate_armor_leggings", () -> new NitriateArmorItem.Leggings());
	public static final RegistryObject<Item> NITRIATE_ARMOR_BOOTS = REGISTRY.register("nitriate_armor_boots", () -> new NitriateArmorItem.Boots());
	public static final RegistryObject<Item> CAVE_BLOCK = block(EriniumAdventureModBlocks.CAVE_BLOCK);
	public static final RegistryObject<Item> LIGHT_DARK_BRICK = block(EriniumAdventureModBlocks.LIGHT_DARK_BRICK);
	public static final RegistryObject<Item> ROCKET_BOOSTER_SPAWN_EGG = REGISTRY.register("rocket_booster_spawn_egg", () -> new ForgeSpawnEggItem(EriniumAdventureModEntities.ROCKET_BOOSTER, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> ROCKET_HEAD_SPAWN_EGG = REGISTRY.register("rocket_head_spawn_egg", () -> new ForgeSpawnEggItem(EriniumAdventureModEntities.ROCKET_HEAD, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> FLAME_ICON = REGISTRY.register("flame_icon", () -> new FlameIconItem());
	public static final RegistryObject<Item> HEART_ICON = REGISTRY.register("heart_icon", () -> new HeartIconItem());

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
