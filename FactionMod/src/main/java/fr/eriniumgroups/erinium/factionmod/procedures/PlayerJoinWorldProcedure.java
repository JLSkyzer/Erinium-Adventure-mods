package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

@Mod.EventBusSubscriber
public class PlayerJoinWorldProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject UUIDJsonObject = new com.google.gson.JsonObject();
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/"), File.separator + (entity.getUUID().toString() + ".json"));
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			JsonObject.addProperty("player_name", (entity.getDisplayName().getString()));
			JsonObject.addProperty("faction", entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name);
			JsonObject.addProperty("faction_rank", entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank);
			JsonObject.addProperty("power", 10);
			JsonObject.addProperty("rank", entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).rank);
			JsonObject.addProperty("admin.permission", false);
			{
				com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		} else {
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
					if (!JsonObject.has("player_name")) {
						JsonObject.addProperty("player_name", (entity.getDisplayName().getString()));
					} else {
						if (!(JsonObject.get("player_name").getAsString()).equals(entity.getDisplayName().getString())) {
							JsonObject.addProperty("player_name", (entity.getDisplayName().getString()));
						}
					}
					if (!JsonObject.has("faction")) {
						JsonObject.addProperty("faction", entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name);
					}
					if (!JsonObject.has("faction_rank")) {
						JsonObject.addProperty("faction_rank", entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank);
					}
					if (!JsonObject.has("power")) {
						JsonObject.addProperty("power", 10);
					}
					if (!JsonObject.has("rank")) {
						JsonObject.addProperty("rank", entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).rank);
					}
					if (!JsonObject.has("admin.permission")) {
						JsonObject.addProperty("admin.permission", false);
					}
					{
						com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
						try {
							FileWriter fileWriter = new FileWriter(file);
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
		}
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
				{
					EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
					_vars.faction_name = JsonObject.get("faction").getAsString();
					_vars.syncPlayerVariables(entity);
				}
				{
					EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
					_vars.faction_rank = JsonObject.get("faction_rank").getAsString();
					_vars.syncPlayerVariables(entity);
				}
				{
					EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
					_vars.rank = JsonObject.get("rank").getAsString();
					_vars.syncPlayerVariables(entity);
				}
				if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name).equals("wilderness")) {
					{
						EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
						_vars.faction_displayname = "Wilderness";
						_vars.syncPlayerVariables(entity);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/nameToUUID/"), File.separator + (entity.getDisplayName().getString() + ".json"));
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			UUIDJsonObject.addProperty("uuid", entity.getUUID().toString());
			{
				com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(mainGSONBuilderVariable.toJson(UUIDJsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
}
