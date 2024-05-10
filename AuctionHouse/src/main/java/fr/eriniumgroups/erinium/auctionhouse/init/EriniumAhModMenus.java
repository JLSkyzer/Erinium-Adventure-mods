
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.auctionhouse.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.ThemeSelect0Menu;
import fr.eriniumgroups.erinium.auctionhouse.world.inventory.SellGuiMenu;
import fr.eriniumgroups.erinium.auctionhouse.world.inventory.DeleteItemsMenu;
import fr.eriniumgroups.erinium.auctionhouse.world.inventory.BuyGuiMenu;
import fr.eriniumgroups.erinium.auctionhouse.world.inventory.AhMainMenuMenu;
import fr.eriniumgroups.erinium.auctionhouse.EriniumAhMod;

public class EriniumAhModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, EriniumAhMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<AhMainMenuMenu>> AH_MAIN_MENU = REGISTRY.register("ah_main_menu", () -> IMenuTypeExtension.create(AhMainMenuMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BuyGuiMenu>> BUY_GUI = REGISTRY.register("buy_gui", () -> IMenuTypeExtension.create(BuyGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<DeleteItemsMenu>> DELETE_ITEMS = REGISTRY.register("delete_items", () -> IMenuTypeExtension.create(DeleteItemsMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<SellGuiMenu>> SELL_GUI = REGISTRY.register("sell_gui", () -> IMenuTypeExtension.create(SellGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ThemeSelect0Menu>> THEME_SELECT_0 = REGISTRY.register("theme_select_0", () -> IMenuTypeExtension.create(ThemeSelect0Menu::new));
}
