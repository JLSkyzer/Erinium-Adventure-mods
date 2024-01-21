package fr.eriniumgroups.erinium.factionmod.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.client.Minecraft;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

import com.google.gson.Gson;

public class GetPlayerFactionMemberListProcedure {
	public static String execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return "";
		com.google.gson.JsonObject JSonObject = new com.google.gson.JsonObject();
		File File = new File("");
		String player_name = "";
		String uuid_list = "";
		double whilecount = 0;
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
				uuid_list = JSonObject.get("member_count").getAsString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (int index0 = 0; index0 < 20; index0++) {
			if ((uuid_list).length() != 0) {
				player_name = player_name + "" + Minecraft.getInstance().level.getPlayerByUUID(java.util.UUID.fromString(new Object() {
					private String split(String text, String space, int index) {
						String s = text.split(space)[index];
						return s;
					}
				}.split(uuid_list, ", ", (int) ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_count)))).getName().getString() + ", ";
				uuid_list = uuid_list.replace(new Object() {
					private String split(String text, String space, int index) {
						String s = text.split(space)[index];
						return s;
					}
				}.split(uuid_list, ", ", (int) ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_count)) + ", ", "");
			} else {
				return player_name;
			}
		}
		return player_name;
	}
}
