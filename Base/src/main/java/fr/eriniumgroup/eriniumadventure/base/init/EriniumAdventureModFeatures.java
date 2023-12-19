
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

import fr.eriniumgroup.eriniumadventure.base.world.features.ores.NitriateOreFeature;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

@Mod.EventBusSubscriber
public class EriniumAdventureModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, EriniumAdventureMod.MODID);
	public static final RegistryObject<Feature<?>> NITRIATE_ORE = REGISTRY.register("nitriate_ore", NitriateOreFeature::new);
}
