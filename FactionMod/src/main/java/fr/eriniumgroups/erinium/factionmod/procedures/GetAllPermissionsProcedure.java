package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class GetAllPermissionsProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String result_text = "";
		String permission = "";
		File = new File(entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_perm_path, File.separator + (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_perm_file + ".json"));
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
				{
					EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
					_vars.temp_perm_list = JsonObject.get("permissions_list").getAsString();
					_vars.syncPlayerVariables(entity);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
