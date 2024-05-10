package fr.eriniumgroups.eriniumcrate.procedures;

import net.minecraft.world.entity.Entity;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class ReturnJsonObjectForLootProcedure {
	public static com.google.gson.JsonObject execute(Entity entity) {
		if (entity == null)
			return new com.google.gson.JsonObject();
		File file = new File("");
		com.google.gson.JsonObject SubJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		double lore_index = 0;
		double index = 0;
		double itemcount = 0;
		double slot_count = 0;
		file = ReturnFileLootProcedure.execute(entity);
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
				JsonObject = JsonObject;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return JsonObject;
	}
}
