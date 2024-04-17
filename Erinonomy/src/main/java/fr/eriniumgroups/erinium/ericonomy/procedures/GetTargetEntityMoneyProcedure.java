package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.io.File;

public class GetTargetEntityMoneyProcedure {
	public static File execute(Entity entity) {
		if (entity == null)
			return new File("");
		File file = new File("");
		com.google.gson.JsonObject jsonObject = new com.google.gson.JsonObject();
		double return_value = 0;
		return new File((FMLPaths.GAMEDIR.get().toString() + "/Ericonomy/accounts/"), File.separator + (entity.getUUID().toString() + ".json"));
	}
}
