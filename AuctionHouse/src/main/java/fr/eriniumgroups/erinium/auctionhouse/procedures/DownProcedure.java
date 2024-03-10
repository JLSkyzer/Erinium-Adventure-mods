package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class DownProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = (entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_page + 1;
			entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.ah_page = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			boolean _setval = false;
			entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.ah_initialised = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		ClearAllProcedure.execute(entity);
	}
}
