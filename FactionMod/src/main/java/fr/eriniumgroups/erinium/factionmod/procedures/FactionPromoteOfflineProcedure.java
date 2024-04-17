package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.mojang.brigadier.context.CommandContext;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class FactionPromoteOfflineProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		if (TargetEntityHaveFactionProcedure.execute(entity)) {
			if (TargetEntityIsChefProcedure.execute(entity) || PlayerCanPromoteProcedure.execute(entity)) {
				if (OfflineEntityExistProcedure.execute(arguments, entity)) {
					if (CommandOfflineEntityAreSameFactionProcedure.execute(arguments, entity)) {
						if (PromoteOfflineLogicRankProcedure.execute(arguments, entity)) {
							if (!OfflinePlayerAreInTheServerProcedure.execute(world, arguments)) {
								File = ReturnCommandOfflineEntityPathProcedure.execute(arguments);
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
										JsonObject.addProperty("faction_rank", PromoteOfflineStringRankProcedure.execute(arguments, entity));
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
										if (entity instanceof Player _player && !_player.level().isClientSide())
											_player.displayClientMessage(Component.literal(("\u00A7aPromoted \u00A7e" + JsonObject.get("player_name").getAsString() + " \u00A7ato rank : \u00A75" + JsonObject.get("faction_rank").getAsString())),
													false);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							} else {
								if (entity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal("\u00A7cPlayer are on the server"), false);
							}
						}
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal("\u00A7cPlayer are not in your faction"), false);
					}
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cYou can't promote"), false);
			}
		}
	}
}
