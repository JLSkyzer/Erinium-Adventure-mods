
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.eriniumcrate.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroups.eriniumcrate.world.inventory.LootListMenu;
import fr.eriniumgroups.eriniumcrate.world.inventory.LootCreatorGuiMenu;
import fr.eriniumgroups.eriniumcrate.EriniumcrateMod;

public class EriniumcrateModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, EriniumcrateMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<LootListMenu>> LOOT_LIST = REGISTRY.register("loot_list", () -> IMenuTypeExtension.create(LootListMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<LootCreatorGuiMenu>> LOOT_CREATOR_GUI = REGISTRY.register("loot_creator_gui", () -> IMenuTypeExtension.create(LootCreatorGuiMenu::new));
}
