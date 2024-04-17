
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

public class EriniumAdventureModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, EriniumAdventureMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOOD = REGISTRY.register("blood", () -> new SimpleParticleType(true));
}
