package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class EntityIsAdminProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		File file = new File("");
		com.google.gson.JsonObject jsonObject = new com.google.gson.JsonObject();
		boolean return_value = false;
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/Ericonomy/accounts/"), File.separator + (entity.getUUID().toString() + ".json"));
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				jsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				return_value = jsonObject.get("ericonomy.admin").getAsBoolean();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return return_value;
	}
}
