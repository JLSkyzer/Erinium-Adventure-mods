package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class EntityIsAdminProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		boolean return_logic = false;
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumJobs/player_information/" + entity.getUUID().toString()), File.separator + "global_info.json");
		if (File.exists()) {
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					return_logic = JsonObject.get("command.admin").getAsBoolean();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return return_logic;
	}
}
