package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

public class PromoteOfflineStringRankProcedure {
	public static String execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return "";
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		List<Object> return_logic = new ArrayList<>();
		String tempUUID = "";
		String return_faction_name = "";
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/nameToUUID/"), File.separator + (StringArgumentType.getString(arguments, "player") + ".json"));
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
					JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					tempUUID = JsonObject.get("uuid").getAsString();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			file = new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/"), File.separator + (tempUUID + ".json"));
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					SecJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					return_faction_name = SecJsonObject.get("faction_rank").getAsString();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Chef")) {
			if ((return_faction_name).equals("Ancient")) {
				return "Officier";
			} else if ((return_faction_name).equals("Member")) {
				return "Ancient";
			} else if ((return_faction_name).equals("Recruit")) {
				return "Member";
			}
		} else if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Officier")) {
			if ((return_faction_name).equals("Member")) {
				return "Ancient";
			} else if ((return_faction_name).equals("Recruit")) {
				return "Member";
			}
		} else if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Ancient")) {
			if ((return_faction_name).equals("Recruit")) {
				return "Member";
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7cYou can't promote this person"), false);
		}
		return return_faction_name;
	}
}
