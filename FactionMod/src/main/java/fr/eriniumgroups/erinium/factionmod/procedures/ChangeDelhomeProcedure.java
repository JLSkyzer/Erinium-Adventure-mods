package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class ChangeDelhomeProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String permission = "";
		permission = "can_" + "delhome";
		if (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_perm_list.contains(permission)) {
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.temp_perm_list = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_perm_list.replace(permission + ", ", "");
				_vars.syncPlayerVariables(entity);
			}
		} else {
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.temp_perm_list = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_perm_list + "" + permission + ", ";
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
