package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.google.gson.Gson;

public class GetPermissionOfTargetEntityProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		File file = new File("");
		List<Object> myArray = new ArrayList<>();
		double count = 0;
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String temp_perm = "";
		if (TargetEntityHaveFactionProcedure.execute(entity)) {
			file = new File(
					(FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + "/permissions/"),
					File.separator + ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_rank + ".json"));
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
					temp_perm = JsonObject.get("permissions_list").getAsString();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return temp_perm;
		}
		return temp_perm;
	}
}
