package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class UpdateOverlayPositionProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = (net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledWidth()
					* (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_percent_x) / 100;
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.won_xp_x = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledHeight()
					* (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_percent_y) / 100;
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.won_xp_y = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_percent_x >= 51) {
			{
				double _setval = (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_x - 120;
				entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.won_xp_x = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_percent_y >= 51) {
			{
				double _setval = (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_y - 44;
				entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.won_xp_y = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
