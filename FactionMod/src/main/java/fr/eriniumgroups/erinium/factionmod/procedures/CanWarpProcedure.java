package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

import java.io.File;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class CanWarpProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String result_text = "";
		String permission = "";
		permission = "can_" + "warp";
		if (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_perm_list.contains(permission)) {
			result_text = "\u00A7a" + permission;
		} else {
			result_text = "\u00A7c" + permission;
		}
		return result_text;
	}
}
