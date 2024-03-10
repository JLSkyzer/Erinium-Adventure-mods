
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import fr.eriniumgroup.eriniumadventure.base.client.renderer.RocketHeadRenderer;
import fr.eriniumgroup.eriniumadventure.base.client.renderer.RocketBoosterRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EriniumAdventureModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(EriniumAdventureModEntities.ROCKET_BOOSTER.get(), RocketBoosterRenderer::new);
		event.registerEntityRenderer(EriniumAdventureModEntities.ROCKET_HEAD.get(), RocketHeadRenderer::new);
	}
}
