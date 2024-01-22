package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.commands.CommandSourceStack;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class ReturnCommandOfflineEntityPathProcedure {
	public static File execute(CommandContext<CommandSourceStack> arguments) {
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String tempUUID = "";
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/nameToUUID/"), File.separator + (StringArgumentType.getString(arguments, "player") + ".json"));
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
				tempUUID = JsonObject.get("uuid").getAsString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/"), File.separator + (tempUUID + ".json"));
	}
}
