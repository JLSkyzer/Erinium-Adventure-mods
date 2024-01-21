package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.commands.CommandSourceStack;

import java.io.File;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

public class StringFactionExistProcedure {
	public static boolean execute(CommandContext<CommandSourceStack> arguments) {
		com.google.gson.JsonObject JSonObject = new com.google.gson.JsonObject();
		File File = new File("");
		String player_name = "";
		String uuid_list = "";
		double whilecount = 0;
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + StringArgumentType.getString(arguments, "factionName") + "/"), File.separator + "global_informations.json");
		if (File.exists()) {
			return true;
		}
		return false;
	}
}
