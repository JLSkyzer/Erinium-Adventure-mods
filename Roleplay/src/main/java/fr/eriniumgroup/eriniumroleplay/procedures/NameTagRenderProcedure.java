package fr.eriniumgroup.eriniumroleplay.procedures;

import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import fr.eriniumgroup.eriniumroleplay.network.EriniumroleplayModVariables;

@Mod.EventBusSubscriber
public class NameTagRenderProcedure {
	@SubscribeEvent
	public static void onNameTagRenderer(RenderNameTagEvent event) {
		if (event.getEntity() instanceof Player){
			event.setResult(Event.Result.DENY);
		}
	}
}
