package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

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

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class TempProcProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String returnOwned = "";
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("Chunk : " + new Object() {
				private String getChunk(int chunkX, int chunkZ) {
					ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
					return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
				}
			}.getChunk((int) (entity.getX()), (int) (entity.getZ())))), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("Region : " + new Object() {
				private String getRegion(int chunkX, int chunkZ) {
					ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
					return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
				}
			}.getRegion((int) (entity.getX()), (int) (entity.getZ())))), false);
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_claims/" + new Object() {
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
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("Owned : " + JsonObject.get("captured_by").getAsString())), false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("Owned : " + "wilderness")), false);
		}
	}
}
