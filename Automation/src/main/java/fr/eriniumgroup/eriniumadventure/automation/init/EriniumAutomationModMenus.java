
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import fr.eriniumgroup.eriniumadventure.automation.world.inventory.FarmerGuiMenu;
import fr.eriniumgroup.eriniumadventure.automation.world.inventory.AstralMinerGuiMenu;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

public class EriniumAutomationModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EriniumAutomationMod.MODID);
	public static final RegistryObject<MenuType<FarmerGuiMenu>> FARMER_GUI = REGISTRY.register("farmer_gui", () -> IForgeMenuType.create(FarmerGuiMenu::new));
	public static final RegistryObject<MenuType<AstralMinerGuiMenu>> ASTRAL_MINER_GUI = REGISTRY.register("astral_miner_gui", () -> IForgeMenuType.create(AstralMinerGuiMenu::new));
}
