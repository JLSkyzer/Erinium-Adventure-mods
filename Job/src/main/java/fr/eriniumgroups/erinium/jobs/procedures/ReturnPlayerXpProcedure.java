package fr.eriniumgroups.erinium.jobs.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class ReturnPlayerXpProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		double temp_number = 0;
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumJobs/player_information/" + entity.getUUID().toString()), File.separator + (entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).temp_job_id + ".json"));
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				temp_number = JsonObject.get("xp").getAsDouble();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return temp_number;
	}
}
