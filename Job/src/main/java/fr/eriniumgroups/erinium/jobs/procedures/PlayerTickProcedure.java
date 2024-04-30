package fr.eriniumgroups.erinium.jobs.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

@Mod.EventBusSubscriber
public class PlayerTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_timer > 0) {
			{
				EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
				_vars.won_xp_timer = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_timer - 1;
				_vars.syncPlayerVariables(entity);
			}
		} else {
			if ((entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_message).length() > 0 || (entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_message_2).length() > 0) {
				if (!entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_config) {
					{
						EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
						_vars.won_xp_message = "";
						_vars.syncPlayerVariables(entity);
					}
					{
						EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
						_vars.won_xp_message_2 = "";
						_vars.syncPlayerVariables(entity);
					}
				}
			}
		}
	}
}
