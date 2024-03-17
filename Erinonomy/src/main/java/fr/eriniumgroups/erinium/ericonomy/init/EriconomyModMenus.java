
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.ericonomy.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import fr.eriniumgroups.erinium.ericonomy.world.inventory.CobbleVoidStationGuiMenu;
import fr.eriniumgroups.erinium.ericonomy.EriconomyMod;

public class EriconomyModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EriconomyMod.MODID);
	public static final RegistryObject<MenuType<CobbleVoidStationGuiMenu>> COBBLE_VOID_STATION_GUI = REGISTRY.register("cobble_void_station_gui", () -> IForgeMenuType.create(CobbleVoidStationGuiMenu::new));
}
