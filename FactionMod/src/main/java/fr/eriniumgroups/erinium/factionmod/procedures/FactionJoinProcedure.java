package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;
import fr.eriniumgroups.erinium.factionmod.configuration.ConfigConfiguration;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class FactionJoinProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		boolean duplicatedname = false;
		String Displayname = "";
		String ID = "";
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		if (!TargetEntityHaveFactionProcedure.execute(entity)) {
			if (GetStringFactionMemberCountProcedure.execute(arguments, entity) < (double) ConfigConfiguration.MAX_MEMBER.get()) {
				if (((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).InvitedBy).contains(StringArgumentType.getString(arguments, "factionName"))) {
					File = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + StringArgumentType.getString(arguments, "factionName") + "/"), File.separator + "global_informations.json");
					if (File.exists()) {
						{
							try {
								BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
								StringBuilder jsonstringbuilder = new StringBuilder();
								String line;
								while ((line = bufferedReader.readLine()) != null) {
									jsonstringbuilder.append(line);
								}
								bufferedReader.close();
								JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
								JsonObject.addProperty("max_power", (JsonObject.get("max_power").getAsDouble() + 10));
								JsonObject.addProperty("power", (JsonObject.get("power").getAsDouble() + ReturnTargetEntityPowerProcedure.execute(entity)));
								JsonObject.addProperty("member_count", (JsonObject.get("member_count").getAsString() + "" + entity.getUUID().toString() + ", "));
								{
									String _setval = StringArgumentType.getString(arguments, "factionName");
									entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.faction_name = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								{
									String _setval = JsonObject.get("faction_displayname").getAsString();
									entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.faction_displayname = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								{
									String _setval = "Recruit";
									entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.faction_rank = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								{
									Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
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
						File = ReturnTargetEntityPathProcedure.execute(entity);
						{
							try {
								BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
								StringBuilder jsonstringbuilder = new StringBuilder();
								String line;
								while ((line = bufferedReader.readLine()) != null) {
									jsonstringbuilder.append(line);
								}
								bufferedReader.close();
								SecJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
								SecJsonObject.addProperty("faction", ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name));
								SecJsonObject.addProperty("faction_rank", ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_rank));
								{
									Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
									try {
										FileWriter fileWriter = new FileWriter(File);
										fileWriter.write(mainGSONBuilderVariable.toJson(SecJsonObject));
										fileWriter.close();
									} catch (IOException exception) {
										exception.printStackTrace();
									}
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						{
							String _setval = ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).InvitedBy)
									.replace(StringArgumentType.getString(arguments, "factionName") + ", ", "");
							entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.InvitedBy = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(
									("\u00A7eYou joinded the faction \u00A7a" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_displayname)),
									false);
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal("\u00A7cThe faction dont exist"), false);
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7cThe faction dont invite you"), false);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cThe faction is full"), false);
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7cYou are already in faction, please leave your faction before joining a new faction"), false);
		}
	}
}
