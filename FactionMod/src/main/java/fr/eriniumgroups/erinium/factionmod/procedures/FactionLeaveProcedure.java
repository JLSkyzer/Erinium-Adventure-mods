package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class FactionLeaveProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		String player_name = "";
		if (!TargetEntityIsChefProcedure.execute(entity)) {
			if (TargetEntityHaveFactionProcedure.execute(entity)) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7cYou leave the faction \u00A7a" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name)), false);
				File = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + "/"), File.separator + "global_informations.json");
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
						JsonObject.addProperty("power", (JsonObject.get("power").getAsDouble() - ReturnTargetEntityPowerProcedure.execute(entity)));
						JsonObject.addProperty("max_power", (JsonObject.get("max_power").getAsDouble() - 10));
						JsonObject.addProperty("member_count", (JsonObject.get("member_count").getAsString().replace(entity.getUUID().toString() + ", ", "")));
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
				File = new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/"), File.separator + (entity.getUUID().toString() + ".json"));
				{
					try {
						BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
						StringBuilder jsonstringbuilder = new StringBuilder();
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							jsonstringbuilder.append(line);
						}
						bufferedReader.close();
						SecJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						SecJsonObject.addProperty("faction", "wilderness");
						SecJsonObject.addProperty("faction_rank", "");
						{
							com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
							try {
								FileWriter fileWriter = new FileWriter(File);
								fileWriter.write(mainGSONBuilderVariable.toJson(SecJsonObject));
								fileWriter.close();
							} catch (IOException exception) {
								exception.printStackTrace();
							}
						}
						{
							EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
							_vars.faction_name = SecJsonObject.get("faction").getAsString();
							_vars.syncPlayerVariables(entity);
						}
						{
							EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
							_vars.faction_rank = SecJsonObject.get("faction_rank").getAsString();
							_vars.syncPlayerVariables(entity);
						}
						{
							EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
							_vars.faction_displayname = "Wilderness";
							_vars.syncPlayerVariables(entity);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cyou are not in a faction"), false);
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7cYou can't leave your own faction"), false);
		}
	}
}
