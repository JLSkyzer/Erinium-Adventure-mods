package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.commands.CommandSourceStack;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

import com.google.gson.Gson;

public class GetStringFactionDisplayNameProcedure {
	public static String execute(CommandContext<CommandSourceStack> arguments) {
		com.google.gson.JsonObject JSonObject = new com.google.gson.JsonObject();
		File File = new File("");
		String player_name = "";
		String uuid_list = "";
		double whilecount = 0;
		uuid_list = "";
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + StringArgumentType.getString(arguments, "factionName") + "/"), File.separator + "global_informations.json");
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
				uuid_list = JSonObject.get("faction_displayname").getAsString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return uuid_list;
	}
}
