
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.factionmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import fr.eriniumgroups.erinium.factionmod.world.inventory.SeleteRankGuiMenu;
import fr.eriniumgroups.erinium.factionmod.world.inventory.EditPermissionGuiMenu;
import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

public class EriniumFactionModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EriniumFactionMod.MODID);
	public static final RegistryObject<MenuType<SeleteRankGuiMenu>> SELETE_RANK_GUI = REGISTRY.register("selete_rank_gui", () -> IForgeMenuType.create(SeleteRankGuiMenu::new));
	public static final RegistryObject<MenuType<EditPermissionGuiMenu>> EDIT_PERMISSION_GUI = REGISTRY.register("edit_permission_gui", () -> IForgeMenuType.create(EditPermissionGuiMenu::new));
}
