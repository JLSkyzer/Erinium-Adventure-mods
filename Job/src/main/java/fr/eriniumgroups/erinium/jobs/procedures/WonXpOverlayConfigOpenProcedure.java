package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class WonXpOverlayConfigOpenProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			boolean _setval = true;
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.won_xp_config = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = "Message 1";
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.won_xp_message = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = "Message 2";
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.won_xp_message_2 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
