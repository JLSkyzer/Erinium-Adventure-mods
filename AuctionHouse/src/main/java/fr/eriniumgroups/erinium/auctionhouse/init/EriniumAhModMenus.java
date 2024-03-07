
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.auctionhouse.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.AhMainMenuMenu;
import fr.eriniumgroups.erinium.auctionhouse.EriniumAhMod;

public class EriniumAhModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EriniumAhMod.MODID);
	public static final RegistryObject<MenuType<AhMainMenuMenu>> AH_MAIN_MENU = REGISTRY.register("ah_main_menu", () -> IForgeMenuType.create(AhMainMenuMenu::new));
}
