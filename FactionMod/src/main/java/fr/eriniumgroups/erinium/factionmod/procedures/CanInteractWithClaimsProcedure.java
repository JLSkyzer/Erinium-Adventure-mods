package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import java.io.File;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.google.gson.JsonObject;

public class CanInteractWithClaimsProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String returnOwned = "";
		if (!IsWarzoneProcedure.execute(world, entity) && !IsSafezoneProcedure.execute(world, entity)) {
			if ((ReturnOwnedFactiionProcedure.execute(world, entity)).equals("wilderness")
					|| (ReturnOwnedFactiionProcedure.execute(world, entity)).equals((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name)) {
				return true;
			}
			return false;
		}
		return false;
	}
}
