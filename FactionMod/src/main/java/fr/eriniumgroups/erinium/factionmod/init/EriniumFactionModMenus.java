
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.factionmod.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroups.erinium.factionmod.world.inventory.SeleteRankGuiMenu;
import fr.eriniumgroups.erinium.factionmod.world.inventory.EditPermissionGuiMenu;
import fr.eriniumgroups.erinium.factionmod.world.inventory.BlacklistItemGuiMenu;
import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

public class EriniumFactionModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, EriniumFactionMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<EditPermissionGuiMenu>> EDIT_PERMISSION_GUI = REGISTRY.register("edit_permission_gui", () -> IMenuTypeExtension.create(EditPermissionGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<SeleteRankGuiMenu>> SELETE_RANK_GUI = REGISTRY.register("selete_rank_gui", () -> IMenuTypeExtension.create(SeleteRankGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BlacklistItemGuiMenu>> BLACKLIST_ITEM_GUI = REGISTRY.register("blacklist_item_gui", () -> IMenuTypeExtension.create(BlacklistItemGuiMenu::new));
}
