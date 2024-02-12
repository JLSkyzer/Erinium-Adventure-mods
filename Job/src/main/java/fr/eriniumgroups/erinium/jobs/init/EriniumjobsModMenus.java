
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.jobs.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import fr.eriniumgroups.erinium.jobs.world.inventory.WonXpOverlayConfigMenu;
import fr.eriniumgroups.erinium.jobs.world.inventory.QFQMenu;
import fr.eriniumgroups.erinium.jobs.world.inventory.ConfigPage1Menu;
import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

public class EriniumjobsModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EriniumjobsMod.MODID);
	public static final RegistryObject<MenuType<WonXpOverlayConfigMenu>> WON_XP_OVERLAY_CONFIG = REGISTRY.register("won_xp_overlay_config", () -> IForgeMenuType.create(WonXpOverlayConfigMenu::new));
	public static final RegistryObject<MenuType<ConfigPage1Menu>> CONFIG_PAGE_1 = REGISTRY.register("config_page_1", () -> IForgeMenuType.create(ConfigPage1Menu::new));
	public static final RegistryObject<MenuType<QFQMenu>> QFQ = REGISTRY.register("qfq", () -> IForgeMenuType.create(QFQMenu::new));
}
