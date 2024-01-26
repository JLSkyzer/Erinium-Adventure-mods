package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class FactionPromoteOnlineProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		if (TargetEntityHaveFactionProcedure.execute(entity)) {
			if (TargetEntityIsChefProcedure.execute(entity) || PlayerCanPromoteProcedure.execute(entity)) {
				if (CommandEntityAreSameFactionProcedure.execute(arguments, entity)) {
					if (PromoteOnlineLogicRankProcedure.execute(arguments, entity)) {
						File = ReturnCommandEntityPathProcedure.execute(arguments);
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
								JsonObject.addProperty("faction_rank", PromoteOnlineStringRankProcedure.execute(arguments, entity));
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
						{
							String _setval = PromoteOnlineStringRankProcedure.execute(arguments, entity);
							(new Object() {
								public Entity getEntity() {
									try {
										return EntityArgument.getEntity(arguments, "player");
									} catch (CommandSyntaxException e) {
										e.printStackTrace();
										return null;
									}
								}
							}.getEntity()).getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.faction_rank = _setval;
								capability.syncPlayerVariables((new Object() {
									public Entity getEntity() {
										try {
											return EntityArgument.getEntity(arguments, "player");
										} catch (CommandSyntaxException e) {
											e.printStackTrace();
											return null;
										}
									}
								}.getEntity()));
							});
						}
						if ((new Object() {
							public Entity getEntity() {
								try {
									return EntityArgument.getEntity(arguments, "player");
								} catch (CommandSyntaxException e) {
									e.printStackTrace();
									return null;
								}
							}
						}.getEntity()) instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u00A7aYou have been promoted to \u00A75" + ((new Object() {
								public Entity getEntity() {
									try {
										return EntityArgument.getEntity(arguments, "player");
									} catch (CommandSyntaxException e) {
										e.printStackTrace();
										return null;
									}
								}
							}.getEntity()).getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_rank)), false);
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u00A7aPromoted \u00A7e" + JsonObject.get("player_name").getAsString() + " \u00A7ato rank : \u00A75" + ((new Object() {
								public Entity getEntity() {
									try {
										return EntityArgument.getEntity(arguments, "player");
									} catch (CommandSyntaxException e) {
										e.printStackTrace();
										return null;
									}
								}
							}.getEntity()).getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_rank)), false);
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7cPlayer are not in your faction"), false);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cYou can't promote"), false);
			}
		}
	}
}
