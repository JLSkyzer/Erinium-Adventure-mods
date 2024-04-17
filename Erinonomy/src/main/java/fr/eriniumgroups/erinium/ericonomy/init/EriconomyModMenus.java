
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.ericonomy.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroups.erinium.ericonomy.world.inventory.CobbleVoidStationGuiMenu;
import fr.eriniumgroups.erinium.ericonomy.EriconomyMod;

public class EriconomyModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, EriconomyMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<CobbleVoidStationGuiMenu>> COBBLE_VOID_STATION_GUI = REGISTRY.register("cobble_void_station_gui", () -> IMenuTypeExtension.create(CobbleVoidStationGuiMenu::new));
}
