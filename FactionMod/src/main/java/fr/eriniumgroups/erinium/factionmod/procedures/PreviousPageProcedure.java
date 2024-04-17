package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class PreviousPageProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).blacklist_item_page - 1 >= 0) {
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.blacklist_item_page = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).blacklist_item_page - 1;
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.BL_Item_page_initialised = false;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
