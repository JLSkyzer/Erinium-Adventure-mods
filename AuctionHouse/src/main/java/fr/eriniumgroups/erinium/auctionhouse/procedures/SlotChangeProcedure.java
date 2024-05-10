package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class SlotChangeProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			EriniumAhModVariables.PlayerVariables _vars = entity.getData(EriniumAhModVariables.PLAYER_VARIABLES);
			_vars.sell_loaded = false;
			_vars.syncPlayerVariables(entity);
		}
	}
}
