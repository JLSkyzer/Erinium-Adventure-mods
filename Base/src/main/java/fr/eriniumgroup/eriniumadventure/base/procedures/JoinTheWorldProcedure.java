package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

@Mod.EventBusSubscriber
public class JoinTheWorldProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).max_health == 0) {
			{
				double _setval = 20;
				entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.max_health = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health == 0) {
			{
				double _setval = (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).max_health;
				entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.health = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
