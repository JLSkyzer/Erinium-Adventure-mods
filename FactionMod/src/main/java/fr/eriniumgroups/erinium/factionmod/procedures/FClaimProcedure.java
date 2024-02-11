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

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class FClaimProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		String uuid_list = "";
		String player_name = "";
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject ThirdJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject FourthJsonObject = new com.google.gson.JsonObject();
		if (TargetEntityHaveFactionProcedure.execute(entity)) {
			if (PlayerCanClaimProcedure.execute(entity) || TargetEntityIsChefProcedure.execute(entity)) {
				if ((ReturnOwnedFactiionProcedure.execute(world, entity)).equals("wilderness")) {
					if (GetFactionPowerProcedure.execute(entity) > GetFactionClaimsNumberProcedure.execute(entity)) {
						File = FactionClaimsPathProcedure.execute(world, entity);
						try {
							File.getParentFile().mkdirs();
							File.createNewFile();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
						JsonObject.addProperty("x", (entity.getX()));
						JsonObject.addProperty("y", (entity.getY()));
						JsonObject.addProperty("z", (entity.getZ()));
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
								SecJsonObject.addProperty("claims", (SecJsonObject.get("claims").getAsDouble() + 1));
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
								ThirdJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
								ThirdJsonObject.addProperty("captured_by", ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name));
								{
									Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
									try {
										FileWriter fileWriter = new FileWriter(File);
										fileWriter.write(mainGSONBuilderVariable.toJson(ThirdJsonObject));
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
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("faction.message.power.enough").getString())), false);
					}
				} else if (!IsWarzoneProcedure.execute(world, entity) && !IsSafezoneProcedure.execute(world, entity)
						&& !(ReturnOwnedFactiionProcedure.execute(world, entity)).equals((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name)) {
					if (GetFactionClaimsAtChunkProcedure.execute(entity) > GetFactionPowerAtChunkProcedure.execute(entity) && GetFactionPowerProcedure.execute(entity) > GetFactionClaimsNumberProcedure.execute(entity)) {
						File = FactionClaimsPathProcedure.execute(world, entity);
						try {
							File.getParentFile().mkdirs();
							File.createNewFile();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
						JsonObject.addProperty("x", (entity.getX()));
						JsonObject.addProperty("y", (entity.getY()));
						JsonObject.addProperty("z", (entity.getZ()));
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
								SecJsonObject.addProperty("claims", (SecJsonObject.get("claims").getAsDouble() + 1));
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
								FourthJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
								FourthJsonObject.addProperty("claims", (FourthJsonObject.get("claims").getAsDouble() - 1));
								{
									Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
									try {
										FileWriter fileWriter = new FileWriter(File);
										fileWriter.write(mainGSONBuilderVariable.toJson(FourthJsonObject));
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
									_player.displayClientMessage(Component.literal(("\u00A74" + "faction.message.chunk.lost" + "\u00A7e" + "X" + entity.getX() + " " + "Y" + entity.getY() + " " + "Z" + entity.getZ())), false);
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
								ThirdJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
								ThirdJsonObject.addProperty("captured_by", ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name));
								{
									Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
									try {
										FileWriter fileWriter = new FileWriter(File);
										fileWriter.write(mainGSONBuilderVariable.toJson(ThirdJsonObject));
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
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("faction.message.power.enough").getString())), false);
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("message.faction.claim.cant").getString())), false);
				}
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("faction.message.nofaction").getString())), false);
		}
	}
}
