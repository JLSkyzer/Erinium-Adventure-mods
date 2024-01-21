package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

import com.google.gson.Gson;

public class GetPlayerFactionPowerProcedure {
	public static double execute(CommandContext<CommandSourceStack> arguments) {
		com.google.gson.JsonObject JSonObject = new com.google.gson.JsonObject();
		File File = new File("");
		String player_name = "";
		String uuid_list = "";
		double whilecount = 0;
		double powerReturn = 0;
		powerReturn = 0;
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + ((new Object() {
			public Entity getEntity() {
				try {
					return EntityArgument.getEntity(arguments, "player");
				} catch (CommandSyntaxException e) {
					e.printStackTrace();
					return null;
				}
			}
		}.getEntity()).getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + "/"), File.separator + "global_informations.json");
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
				powerReturn = JSonObject.get("power").getAsDouble();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return powerReturn;
	}
}
