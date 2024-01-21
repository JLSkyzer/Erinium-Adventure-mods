package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

public class PlayerCanKickProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		String permission = "";
		permission = "can_" + "kick";
		if (GetPermissionOfTargetEntityProcedure.execute(entity).contains(permission)) {
			return true;
		}
		return false;
	}
}
