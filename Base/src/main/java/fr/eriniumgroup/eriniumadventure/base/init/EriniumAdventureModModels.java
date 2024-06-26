
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import fr.eriniumgroup.eriniumadventure.base.client.model.Modelrocket_head;
import fr.eriniumgroup.eriniumadventure.base.client.model.Modelrocket_booster;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class EriniumAdventureModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelrocket_head.LAYER_LOCATION, Modelrocket_head::createBodyLayer);
		event.registerLayerDefinition(Modelrocket_booster.LAYER_LOCATION, Modelrocket_booster::createBodyLayer);
	}
}
