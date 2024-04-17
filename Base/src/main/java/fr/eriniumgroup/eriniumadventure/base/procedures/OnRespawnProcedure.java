package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

@Mod.EventBusSubscriber
public class OnRespawnProcedure {
	@SubscribeEvent
	public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		{
			EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
			_vars.health = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).max_health;
			_vars.syncPlayerVariables(entity);
		}
	}
}
