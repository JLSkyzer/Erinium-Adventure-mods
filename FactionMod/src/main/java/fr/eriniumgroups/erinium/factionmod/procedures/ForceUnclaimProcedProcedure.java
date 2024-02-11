package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class ForceUnclaimProcedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		String player_name = "";
		if (TargetEntityIsAdminProcedure.execute(entity)) {
			if (IsWarzoneProcedure.execute(world, entity) || IsSafezoneProcedure.execute(world, entity)) {
				File = GetChunkPathProcedure.execute(world, entity);
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
						JsonObject.addProperty("captured_by", "wilderness");
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
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7a" + Component.translatable("faction.message.claim.succeful").getString())), false);
			} else {
				if (!(ReturnOwnedFactiionProcedure.execute(world, entity)).equals("wilderness")) {
					File = ChunkFactionPathProcedure.execute(world, entity);
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
							JsonObject.addProperty("claims", (JsonObject.get("claims").getAsDouble() - 1));
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
					DeleteClaimAtEnemyFactionProcedure.execute(world, entity);
					player_name = GetPlayerListAtFactionChunkProcedure.execute(entity);
					for (Entity entityiterator : new ArrayList<>(world.players())) {
						if (player_name.contains(entityiterator.getDisplayName().getString())) {
							player_name = player_name.replace(entityiterator.getDisplayName().getString() + ", ", "");
							if (entityiterator instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal(("\u00A76BY ADMIN \u00A74" + "faction.message.chunk.lost" + "\u00A7e" + "X" + entity.getX() + " " + "Y" + entity.getY() + " " + "Z" + entity.getZ())), false);
						}
					}
					File = GetChunkPathProcedure.execute(world, entity);
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
							SecJsonObject.addProperty("captured_by", "wilderness");
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
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7a" + Component.translatable("faction.message.claim.succeful").getString())), false);
				}
			}
		}
	}
}
