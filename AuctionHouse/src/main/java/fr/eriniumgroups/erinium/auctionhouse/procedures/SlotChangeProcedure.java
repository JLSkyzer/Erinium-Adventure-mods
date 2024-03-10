package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class SlotChangeProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			boolean _setval = false;
			entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.sell_loaded = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
