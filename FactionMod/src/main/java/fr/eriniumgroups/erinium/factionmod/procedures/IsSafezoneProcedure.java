package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import java.io.File;

import com.google.gson.JsonObject;

public class IsSafezoneProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String returnOwned = "";
		if ((ReturnOwnedFactiionProcedure.execute(world, entity)).equals("Safezone")) {
			return true;
		}
		return false;
	}
}
