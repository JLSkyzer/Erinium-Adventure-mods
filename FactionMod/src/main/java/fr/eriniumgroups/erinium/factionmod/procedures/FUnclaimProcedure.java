package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class FUnclaimProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject ThirdJsonObject = new com.google.gson.JsonObject();
		if (TargetEntityHaveFactionProcedure.execute(entity)) {
			if (PlayerCanUnclaimProcedure.execute(entity) || TargetEntityIsChefProcedure.execute(entity)) {
				if ((ReturnOwnedFactiionProcedure.execute(world, entity)).equals(entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name)) {
					if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + "/claim_list/" + new Object() {
						private String getRegion(int chunkX, int chunkZ) {
							ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
							return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
						}
					}.getRegion((int) (entity.getX()), (int) (entity.getZ())) + "/" + new Object() {
						private String getChunk(int chunkX, int chunkZ) {
							ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
							return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
						}
					}.getChunk((int) (entity.getX()), (int) (entity.getZ())) + ".json"))).exists()) { // Vérifie si le fichier existe avant de le supprimer
						// Supprime le fichier
						boolean suppressionReussie = new java.io.File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + "/claim_list/" + new Object() {
							private String getRegion(int chunkX, int chunkZ) {
								ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
								return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
							}
						}.getRegion((int) (entity.getX()), (int) (entity.getZ())) + "/" + new Object() {
							private String getChunk(int chunkX, int chunkZ) {
								ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
								return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
							}
						}.getChunk((int) (entity.getX()), (int) (entity.getZ())) + ".json")).delete();
						if (suppressionReussie) {
							System.out.println("Le fichier a été supprimé avec succès.");
						} else {
							System.out.println("Impossible de supprimer le fichier.");
						}
					} else {
						System.out.println("Le fichier n'existe pas.");
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
							SecJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							SecJsonObject.addProperty("claims", (SecJsonObject.get("claims").getAsDouble() - 1));
							{
								com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
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
							ThirdJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							ThirdJsonObject.addProperty("captured_by", "wilderness");
							{
								com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
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
						_player.displayClientMessage(Component.literal(("\u00A7a" + Component.translatable("faction.message.unclaim.succeful").getString())), false);
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("message.faction.unclaim.cant").getString())), false);
				}
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("faction.message.nofaction").getString())), false);
		}
	}
}
