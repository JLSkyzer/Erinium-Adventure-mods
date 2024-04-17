
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroup.eriniumadventure.automation.world.inventory.OneBlockCropGuiMenu;
import fr.eriniumgroup.eriniumadventure.automation.world.inventory.NetherStarGenMenu;
import fr.eriniumgroup.eriniumadventure.automation.world.inventory.MultipleBlocksCropsGuyMenu;
import fr.eriniumgroup.eriniumadventure.automation.world.inventory.FarmerJsonBuilderGuiMainMenu;
import fr.eriniumgroup.eriniumadventure.automation.world.inventory.FarmerGuiMenu;
import fr.eriniumgroup.eriniumadventure.automation.world.inventory.AstralMinerGuiMenu;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

public class EriniumAutomationModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, EriniumAutomationMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<FarmerGuiMenu>> FARMER_GUI = REGISTRY.register("farmer_gui", () -> IMenuTypeExtension.create(FarmerGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<AstralMinerGuiMenu>> ASTRAL_MINER_GUI = REGISTRY.register("astral_miner_gui", () -> IMenuTypeExtension.create(AstralMinerGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FarmerJsonBuilderGuiMainMenu>> FARMER_JSON_BUILDER_GUI_MAIN = REGISTRY.register("farmer_json_builder_gui_main", () -> IMenuTypeExtension.create(FarmerJsonBuilderGuiMainMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<OneBlockCropGuiMenu>> ONE_BLOCK_CROP_GUI = REGISTRY.register("one_block_crop_gui", () -> IMenuTypeExtension.create(OneBlockCropGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<MultipleBlocksCropsGuyMenu>> MULTIPLE_BLOCKS_CROPS_GUY = REGISTRY.register("multiple_blocks_crops_guy", () -> IMenuTypeExtension.create(MultipleBlocksCropsGuyMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<NetherStarGenMenu>> NETHER_STAR_GEN = REGISTRY.register("nether_star_gen", () -> IMenuTypeExtension.create(NetherStarGenMenu::new));
}
