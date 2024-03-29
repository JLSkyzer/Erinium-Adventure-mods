
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
import fr.eriniumgroups.erinium.jobs.world.inventory.UnlockedGuiMenu;
import fr.eriniumgroups.erinium.jobs.world.inventory.RequiredMakerMenu;
import fr.eriniumgroups.erinium.jobs.world.inventory.RankInfoMenu;
import fr.eriniumgroups.erinium.jobs.world.inventory.QFQMenu;
import fr.eriniumgroups.erinium.jobs.world.inventory.EarnXpMakerMenu;
import fr.eriniumgroups.erinium.jobs.world.inventory.EarnXpGuiMenu;
import fr.eriniumgroups.erinium.jobs.world.inventory.ConfigPage1Menu;
import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

public class EriniumjobsModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EriniumjobsMod.MODID);
	public static final RegistryObject<MenuType<WonXpOverlayConfigMenu>> WON_XP_OVERLAY_CONFIG = REGISTRY.register("won_xp_overlay_config", () -> IForgeMenuType.create(WonXpOverlayConfigMenu::new));
	public static final RegistryObject<MenuType<ConfigPage1Menu>> CONFIG_PAGE_1 = REGISTRY.register("config_page_1", () -> IForgeMenuType.create(ConfigPage1Menu::new));
	public static final RegistryObject<MenuType<QFQMenu>> QFQ = REGISTRY.register("qfq", () -> IForgeMenuType.create(QFQMenu::new));
	public static final RegistryObject<MenuType<EarnXpGuiMenu>> EARN_XP_GUI = REGISTRY.register("earn_xp_gui", () -> IForgeMenuType.create(EarnXpGuiMenu::new));
	public static final RegistryObject<MenuType<UnlockedGuiMenu>> UNLOCKED_GUI = REGISTRY.register("unlocked_gui", () -> IForgeMenuType.create(UnlockedGuiMenu::new));
	public static final RegistryObject<MenuType<EarnXpMakerMenu>> EARN_XP_MAKER = REGISTRY.register("earn_xp_maker", () -> IForgeMenuType.create(EarnXpMakerMenu::new));
	public static final RegistryObject<MenuType<RequiredMakerMenu>> REQUIRED_MAKER = REGISTRY.register("required_maker", () -> IForgeMenuType.create(RequiredMakerMenu::new));
	public static final RegistryObject<MenuType<RankInfoMenu>> RANK_INFO = REGISTRY.register("rank_info", () -> IForgeMenuType.create(RankInfoMenu::new));
}
