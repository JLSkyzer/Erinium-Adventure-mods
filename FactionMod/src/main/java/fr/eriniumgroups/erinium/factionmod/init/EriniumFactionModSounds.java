
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.factionmod.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

public class EriniumFactionModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, EriniumFactionMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> DING = REGISTRY.register("ding", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("erinium_faction", "ding")));
}
