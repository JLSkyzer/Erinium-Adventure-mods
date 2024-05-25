package fr.eriniumgroup.eriniumroleplay.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class CreatePlayerFileProcedure {
	public static File execute(Entity entity) {
		if (entity == null)
			return new File("");
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumRoleplay/players/"), File.separator + (entity.getStringUUID() + ".json"));
		if (!File.exists()) {
			try {
				File.getParentFile().mkdirs();
				File.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			{
				com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(File);
					fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
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
				if (!JsonObject.has("DisplayName")) {
					JsonObject.addProperty("DisplayName", (entity.getDisplayName().getString()));
				}
				if (!JsonObject.has("scheduled_command")) {
					JsonObject.addProperty("scheduled_command", "");
				}
				if (!JsonObject.has("job_id")) {
					JsonObject.addProperty("job_id", "chomage");
				}
				if (!JsonObject.has("job_name")) {
					JsonObject.addProperty("job_name", "\u00A7eChomage");
				}
				if (!JsonObject.has("job_rankid")) {
					JsonObject.addProperty("job_rankid", "");
				}
				if (!JsonObject.has("job_rankname")) {
					JsonObject.addProperty("job_rankname", "");
				}
				if (!JsonObject.has("organisation_id")) {
					JsonObject.addProperty("organisation_id", "");
				}
				if (!JsonObject.has("organisation_name")) {
					JsonObject.addProperty("organisation_name", "");
				}
				if (!JsonObject.has("organisation_rankid")) {
					JsonObject.addProperty("organisation_rankid", "");
				}
				if (!JsonObject.has("organisation_rankname")) {
					JsonObject.addProperty("organisation_rankname", "");
				}
				{
					com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
					try {
						FileWriter fileWriter = new FileWriter(File);
						fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
						fileWriter.close();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return File;
	}
}
