package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

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
		if ((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_timer > 0) {
			{
				double _setval = (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_timer - 1;
				entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.won_xp_timer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			if (((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_message).length() > 0
					|| ((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_message_2).length() > 0) {
				if (!(entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_config) {
					{
						String _setval = "";
						entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.won_xp_message = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						String _setval = "";
						entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.won_xp_message_2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		}
		if ((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).windows_width != net.minecraft.client.Minecraft.getInstance().getWindow().getWidth()) {
			{
				double _setval = net.minecraft.client.Minecraft.getInstance().getWindow().getWidth();
				entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.windows_width = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			UpdateOverlayPositionProcedure.execute(entity);
		}
		if ((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).windows_height != net.minecraft.client.Minecraft.getInstance().getWindow().getHeight()) {
			{
				double _setval = net.minecraft.client.Minecraft.getInstance().getWindow().getHeight();
				entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.windows_width = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			UpdateOverlayPositionProcedure.execute(entity);
		}
	}
}
