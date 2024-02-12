package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;
import fr.eriniumgroups.erinium.jobs.configuration.CommonConfigConfiguration;

@Mod.EventBusSubscriber
public class PlayerJoinTheWorldProcedure {
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
		{
			double _setval = (double) CommonConfigConfiguration.XP_MULTIPLIER.get();
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.xp_multiplier = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) CommonConfigConfiguration.WONXP_MULTIPLIER.get();
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.won_xp_multiplier = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
