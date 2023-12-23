
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.automation.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

import fr.eriniumgroup.eriniumadventure.automation.world.features.ores.AmenineOreFeature;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

@Mod.EventBusSubscriber
public class EriniumAutomationModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, EriniumAutomationMod.MODID);
	public static final RegistryObject<Feature<?>> AMENINE_ORE = REGISTRY.register("amenine_ore", AmenineOreFeature::new);
}
