
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.auctionhouse.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.ThemeSelect0Menu;
import fr.eriniumgroups.erinium.auctionhouse.world.inventory.SellGuiMenu;
import fr.eriniumgroups.erinium.auctionhouse.world.inventory.DeleteItemsMenu;
import fr.eriniumgroups.erinium.auctionhouse.world.inventory.BuyGuiMenu;
import fr.eriniumgroups.erinium.auctionhouse.world.inventory.AhMainMenuMenu;
import fr.eriniumgroups.erinium.auctionhouse.EriniumAhMod;

public class EriniumAhModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EriniumAhMod.MODID);
	public static final RegistryObject<MenuType<AhMainMenuMenu>> AH_MAIN_MENU = REGISTRY.register("ah_main_menu", () -> IForgeMenuType.create(AhMainMenuMenu::new));
	public static final RegistryObject<MenuType<BuyGuiMenu>> BUY_GUI = REGISTRY.register("buy_gui", () -> IForgeMenuType.create(BuyGuiMenu::new));
	public static final RegistryObject<MenuType<DeleteItemsMenu>> DELETE_ITEMS = REGISTRY.register("delete_items", () -> IForgeMenuType.create(DeleteItemsMenu::new));
	public static final RegistryObject<MenuType<SellGuiMenu>> SELL_GUI = REGISTRY.register("sell_gui", () -> IForgeMenuType.create(SellGuiMenu::new));
	public static final RegistryObject<MenuType<ThemeSelect0Menu>> THEME_SELECT_0 = REGISTRY.register("theme_select_0", () -> IForgeMenuType.create(ThemeSelect0Menu::new));
}
