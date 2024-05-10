package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class UpProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(EriniumAhModVariables.PLAYER_VARIABLES).ah_page - 1 >= 0) {
			{
				EriniumAhModVariables.PlayerVariables _vars = entity.getData(EriniumAhModVariables.PLAYER_VARIABLES);
				_vars.ah_page = entity.getData(EriniumAhModVariables.PLAYER_VARIABLES).ah_page - 1;
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumAhModVariables.PlayerVariables _vars = entity.getData(EriniumAhModVariables.PLAYER_VARIABLES);
				_vars.ah_initialised = false;
				_vars.syncPlayerVariables(entity);
			}
			ClearAllProcedure.execute(entity);
		}
	}
}
