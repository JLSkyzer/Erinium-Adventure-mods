
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.ericonomy.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import fr.eriniumgroups.erinium.ericonomy.item.Piece5DollarsItem;
import fr.eriniumgroups.erinium.ericonomy.item.Piece5CentItem;
import fr.eriniumgroups.erinium.ericonomy.item.Piece50CentItem;
import fr.eriniumgroups.erinium.ericonomy.item.Piece2DollarsItem;
import fr.eriniumgroups.erinium.ericonomy.item.Piece20CentItem;
import fr.eriniumgroups.erinium.ericonomy.item.Piece1DollarsItem;
import fr.eriniumgroups.erinium.ericonomy.item.Piece1CentItem;
import fr.eriniumgroups.erinium.ericonomy.item.Piece10CentItem;
import fr.eriniumgroups.erinium.ericonomy.item.CobbleVoidItem;
import fr.eriniumgroups.erinium.ericonomy.item.Bille500DollarsItem;
import fr.eriniumgroups.erinium.ericonomy.item.Bille20DollarsItem;
import fr.eriniumgroups.erinium.ericonomy.item.Bill50DollarsItem;
import fr.eriniumgroups.erinium.ericonomy.item.Bill200DollarsItem;
import fr.eriniumgroups.erinium.ericonomy.item.Bill10DollarsItem;
import fr.eriniumgroups.erinium.ericonomy.item.Bill100DollarsItem;
import fr.eriniumgroups.erinium.ericonomy.item.Bill1000DollarsItem;
import fr.eriniumgroups.erinium.ericonomy.EriconomyMod;

public class EriconomyModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, EriconomyMod.MODID);
	public static final RegistryObject<Item> PIECE_1_CENT = REGISTRY.register("piece_1_cent", () -> new Piece1CentItem());
	public static final RegistryObject<Item> PIECE_5_CENT = REGISTRY.register("piece_5_cent", () -> new Piece5CentItem());
	public static final RegistryObject<Item> PIECE_10_CENT = REGISTRY.register("piece_10_cent", () -> new Piece10CentItem());
	public static final RegistryObject<Item> PIECE_20_CENT = REGISTRY.register("piece_20_cent", () -> new Piece20CentItem());
	public static final RegistryObject<Item> PIECE_50_CENT = REGISTRY.register("piece_50_cent", () -> new Piece50CentItem());
	public static final RegistryObject<Item> PIECE_1_DOLLARS = REGISTRY.register("piece_1_dollars", () -> new Piece1DollarsItem());
	public static final RegistryObject<Item> PIECE_2_DOLLARS = REGISTRY.register("piece_2_dollars", () -> new Piece2DollarsItem());
	public static final RegistryObject<Item> PIECE_5_DOLLARS = REGISTRY.register("piece_5_dollars", () -> new Piece5DollarsItem());
	public static final RegistryObject<Item> BILL_10_DOLLARS = REGISTRY.register("bill_10_dollars", () -> new Bill10DollarsItem());
	public static final RegistryObject<Item> BILLE_20_DOLLARS = REGISTRY.register("bille_20_dollars", () -> new Bille20DollarsItem());
	public static final RegistryObject<Item> BILL_50_DOLLARS = REGISTRY.register("bill_50_dollars", () -> new Bill50DollarsItem());
	public static final RegistryObject<Item> BILL_100_DOLLARS = REGISTRY.register("bill_100_dollars", () -> new Bill100DollarsItem());
	public static final RegistryObject<Item> BILL_200_DOLLARS = REGISTRY.register("bill_200_dollars", () -> new Bill200DollarsItem());
	public static final RegistryObject<Item> BILLE_500_DOLLARS = REGISTRY.register("bille_500_dollars", () -> new Bille500DollarsItem());
	public static final RegistryObject<Item> BILL_1000_DOLLARS = REGISTRY.register("bill_1000_dollars", () -> new Bill1000DollarsItem());
	public static final RegistryObject<Item> COBBLE_VOID = REGISTRY.register("cobble_void", () -> new CobbleVoidItem());
	public static final RegistryObject<Item> COBBLE_VOID_STATION = block(EriconomyModBlocks.COBBLE_VOID_STATION);

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
