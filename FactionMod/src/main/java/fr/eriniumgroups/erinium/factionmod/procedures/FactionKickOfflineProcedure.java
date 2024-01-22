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

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class FactionKickOfflineProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		if (TargetEntityIsChefProcedure.execute(entity) || PlayerCanKickProcedure.execute(entity)) {
			if (OfflineEntityExistProcedure.execute(arguments, entity)) {
				if (CommandOfflineEntityAreSameFactionProcedure.execute(arguments, entity)) {
					if (CommandOfflineEntityRankCheckerToExecuteProcedure.execute(arguments, entity)) {
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
									JsonObject.addProperty("faction", "wilderness");
									JsonObject.addProperty("faction_rank", "");
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
										_player.displayClientMessage(Component.literal(("\u00A7e" + JsonObject.get("player_name").getAsString() + " \u00A7chas been kick from the faction")), false);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							File = EntityFactionPathProcedure.execute(entity);
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
									SecJsonObject.addProperty("member_count", (SecJsonObject.get("member_count").getAsString().replace(ReturnCommandOfflineEntityUuidProcedure.execute(arguments) + ", ", "")));
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
				_player.displayClientMessage(Component.literal("\u00A7cYou can't kick"), false);
		}
	}
}
