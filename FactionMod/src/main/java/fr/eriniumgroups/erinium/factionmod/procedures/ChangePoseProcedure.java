package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class ChangePoseProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String permission = "";
		permission = "can_" + "pose";
		if (((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_perm_list).contains(permission)) {
			{
				String _setval = ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_perm_list).replace(permission + ", ", "");
				entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.temp_perm_list = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				String _setval = (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_perm_list + "" + permission + ", ";
				entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.temp_perm_list = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
