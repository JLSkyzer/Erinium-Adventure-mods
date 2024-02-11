package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class ReturnBlackListItemProcedure {
	public static String execute() {
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject UUIDJsonObject = new com.google.gson.JsonObject();
		String temp_string = "";
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumFaction/"), File.separator + "blacklist_item.json");
		if (file.exists()) {
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					temp_string = JsonObject.get("list").getAsString();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			temp_string = "";
		}
		return temp_string;
	}
}
