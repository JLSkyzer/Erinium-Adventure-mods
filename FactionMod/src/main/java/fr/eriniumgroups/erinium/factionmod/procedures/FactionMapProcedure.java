package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class FactionMapProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		double sx = 0;
		double sz = 0;
		double temp_number = 0;
		com.google.gson.JsonObject tempJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject clearJsonObject = new com.google.gson.JsonObject();
		String temp_message = "";
		String returnOwned = "";
		String legend_text = "";
		String already_do_myposition = "";
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A75===== Faction Map ====="), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A73-----------"), false);
		legend_text = legend_text + "\u00A78# : \u00A7e" + "wilderness" + " \u00A76| ";
		legend_text = legend_text + "\u00A7a# : \u00A7e" + "Safezone" + " \u00A76| ";
		legend_text = legend_text + "\u00A74# : \u00A7e" + "Warzone" + " \u00A76| ";
		temp_number = 0;
		sz = -56;
		for (int index0 = 0; index0 < 7; index0++) {
			temp_message = "\u00A73|";
			sx = -88;
			for (int index1 = 0; index1 < 11; index1++) {
				if ((new Object() {
					private String getChunk(int chunkX, int chunkZ) {
						ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
						return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
					}
				}.getChunk((int) (entity.getX() + sx), (int) (entity.getZ() + sz)) + "+" + new Object() {
					private String getRegion(int chunkX, int chunkZ) {
						ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
						return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
					}
				}.getRegion((int) (entity.getX() + sx), (int) (entity.getZ() + sz))).equals(new Object() {
					private String getChunk(int chunkX, int chunkZ) {
						ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
						return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
					}
				}.getChunk((int) (entity.getX()), (int) (entity.getZ())) + "+" + new Object() {
					private String getRegion(int chunkX, int chunkZ) {
						ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
						return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
					}
				}.getRegion((int) (entity.getX()), (int) (entity.getZ())))) {
					file = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_claims/" + new Object() {
						private String getRegion(int chunkX, int chunkZ) {
							ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
							return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
						}
					}.getRegion((int) (entity.getX()), (int) (entity.getZ())) + "/"), File.separator + (new Object() {
						private String getChunk(int chunkX, int chunkZ) {
							ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
							return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
						}
					}.getChunk((int) (entity.getX()), (int) (entity.getZ())) + ".json"));
				} else {
					file = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_claims/" + new Object() {
						private String getRegion(int chunkX, int chunkZ) {
							ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
							return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
						}
					}.getRegion((int) (entity.getX() + sx), (int) (entity.getZ() + sz)) + "/"), File.separator + (new Object() {
						private String getChunk(int chunkX, int chunkZ) {
							ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
							return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
						}
					}.getChunk((int) (entity.getX() + sx), (int) (entity.getZ() + sz)) + ".json"));
				}
				if (file.exists()) {
					{
						try {
							BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
							StringBuilder jsonstringbuilder = new StringBuilder();
							String line;
							while ((line = bufferedReader.readLine()) != null) {
								jsonstringbuilder.append(line);
							}
							bufferedReader.close();
							JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							returnOwned = JsonObject.get("captured_by").getAsString();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					returnOwned = "wilderness";
				}
				if ((new Object() {
					private String getChunk(int chunkX, int chunkZ) {
						ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
						return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
					}
				}.getChunk((int) (entity.getX() + sx), (int) (entity.getZ() + sz)) + "+" + new Object() {
					private String getRegion(int chunkX, int chunkZ) {
						ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
						return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
					}
				}.getRegion((int) (entity.getX() + sx), (int) (entity.getZ() + sz))).equals(new Object() {
					private String getChunk(int chunkX, int chunkZ) {
						ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
						return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
					}
				}.getChunk((int) (entity.getX()), (int) (entity.getZ())) + "+" + new Object() {
					private String getRegion(int chunkX, int chunkZ) {
						ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
						return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
					}
				}.getRegion((int) (entity.getX()), (int) (entity.getZ())))) {
					temp_message = temp_message + "\u00A7f+";
					legend_text = legend_text + "\u00A7aYou : \u00A7e" + returnOwned + " \u00A76| ";
				} else if ((returnOwned).equals("wilderness")) {
					temp_message = temp_message + "\u00A78#";
				} else if ((returnOwned).equals("Safezone")) {
					temp_message = temp_message + "\u00A7a#";
				} else if ((returnOwned).equals("Warzone")) {
					temp_message = temp_message + "\u00A74#";
				} else {
					if (!tempJsonObject.has(returnOwned)) {
						tempJsonObject.addProperty(returnOwned, temp_number);
						tempJsonObject.addProperty((new java.text.DecimalFormat("##").format(temp_number)), returnOwned);
						legend_text = legend_text + "\u00A7e" + new java.text.DecimalFormat("##").format(temp_number) + " : " + returnOwned + " \u00A76| ";
						temp_number = temp_number + 1;
					}
					temp_message = temp_message + "\u00A7e" + new java.text.DecimalFormat("##").format(tempJsonObject.get(returnOwned).getAsDouble());
				}
				sx = sx + 16;
			}
			temp_message = temp_message + "\u00A73|";
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(temp_message), false);
			sz = sz + 16;
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A73-----------"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7b\u00A7l=========="), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(legend_text), false);
	}
}
