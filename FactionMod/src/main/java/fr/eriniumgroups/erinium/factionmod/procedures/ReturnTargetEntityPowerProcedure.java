package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class ReturnTargetEntityPowerProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		double power = 0;
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/"), File.separator + (entity.getUUID().toString() + ".json"));
		power = 0;
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				power = JsonObject.get("power").getAsDouble();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return power;
	}
}
