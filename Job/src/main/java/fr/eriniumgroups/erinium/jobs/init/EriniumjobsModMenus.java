
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.jobs.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

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
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, EriniumjobsMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<WonXpOverlayConfigMenu>> WON_XP_OVERLAY_CONFIG = REGISTRY.register("won_xp_overlay_config", () -> IMenuTypeExtension.create(WonXpOverlayConfigMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ConfigPage1Menu>> CONFIG_PAGE_1 = REGISTRY.register("config_page_1", () -> IMenuTypeExtension.create(ConfigPage1Menu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<QFQMenu>> QFQ = REGISTRY.register("qfq", () -> IMenuTypeExtension.create(QFQMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<EarnXpGuiMenu>> EARN_XP_GUI = REGISTRY.register("earn_xp_gui", () -> IMenuTypeExtension.create(EarnXpGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<UnlockedGuiMenu>> UNLOCKED_GUI = REGISTRY.register("unlocked_gui", () -> IMenuTypeExtension.create(UnlockedGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<EarnXpMakerMenu>> EARN_XP_MAKER = REGISTRY.register("earn_xp_maker", () -> IMenuTypeExtension.create(EarnXpMakerMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<RequiredMakerMenu>> REQUIRED_MAKER = REGISTRY.register("required_maker", () -> IMenuTypeExtension.create(RequiredMakerMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<RankInfoMenu>> RANK_INFO = REGISTRY.register("rank_info", () -> IMenuTypeExtension.create(RankInfoMenu::new));
}
