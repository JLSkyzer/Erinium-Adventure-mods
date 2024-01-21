package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class FactionCreateRankProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		List<Object> myArray = new ArrayList<>();
		double count = 0;
		File file = new File("");
		boolean duplicatedname = false;
		String Displayname = "";
		String ID = "";
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		myArray.add("Recruit");
		myArray.add("Member");
		myArray.add("Ancient");
		myArray.add("Officier");
		count = 0;
		while (!(count == 4)) {
			file = new File(
					(FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + "/permissions/"),
					File.separator + ((myArray.get((int) count) instanceof String _s ? _s : "") + ".json"));
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			JsonObject.addProperty("permissions_list", "");
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
			count = count + 1;
		}
	}
}
