
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.factionmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

public class EriniumFactionModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EriniumFactionMod.MODID);
	public static final RegistryObject<SoundEvent> DING = REGISTRY.register("ding", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("erinium_faction", "ding")));
}
