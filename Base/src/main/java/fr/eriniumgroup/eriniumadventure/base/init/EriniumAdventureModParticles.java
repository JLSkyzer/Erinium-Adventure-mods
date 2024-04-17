
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import fr.eriniumgroup.eriniumadventure.base.client.particle.BloodParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EriniumAdventureModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(EriniumAdventureModParticleTypes.BLOOD.get(), BloodParticle::provider);
	}
}
