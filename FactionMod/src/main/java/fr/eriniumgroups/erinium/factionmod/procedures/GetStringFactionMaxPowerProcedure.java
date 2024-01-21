package fr.eriniumgroups.erinium.factionmod.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.CommandSourceStack;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

import com.google.gson.Gson;

public class GetStringFactionMaxPowerProcedure {
	public static double execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return 0;
		com.google.gson.JsonObject JSonObject = new com.google.gson.JsonObject();
		File File = new File("");
		String player_name = "";
		String uuid_list = "";
		double whilecount = 0;
		double TempMaxPower = 0;
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
				uuid_list = JSonObject.get("member_count").getAsString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (int index0 = 0; index0 < 20; index0++) {
			if ((uuid_list).length() != 0) {
				uuid_list = uuid_list.replace(new Object() {
					private String split(String text, String space, int index) {
						String s = text.split(space)[index];
						return s;
					}
				}.split(uuid_list, ", ", (int) ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_count)) + ", ", "");
				TempMaxPower = TempMaxPower + 10;
			} else {
				return TempMaxPower;
			}
		}
		return TempMaxPower;
	}
}
