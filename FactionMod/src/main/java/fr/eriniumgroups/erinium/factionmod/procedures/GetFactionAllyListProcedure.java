package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.google.gson.Gson;

public class GetFactionAllyListProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		File File = new File("");
		String player_name = "";
		String uuid_list = "";
		double whilecount = 0;
		com.google.gson.JsonObject JSonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + "/"),
				File.separator + "global_informations.json");
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				JSonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (JSonObject.has("ally")) {
					uuid_list = JSonObject.get("ally").getAsString();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return uuid_list;
	}
}
