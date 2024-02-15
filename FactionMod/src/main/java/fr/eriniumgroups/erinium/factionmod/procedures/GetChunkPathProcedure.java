package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class GetChunkPathProcedure {
	public static File execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return new File("");
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String returnOwned = "";
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
		if (!File.exists()) {
			try {
				File.getParentFile().mkdirs();
				File.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			JsonObject.addProperty("captured_by", "wilderness");
			JsonObject.addProperty("cord_x", (entity.getX()));
			JsonObject.addProperty("cord_y", (entity.getY()));
			JsonObject.addProperty("cord_z", (entity.getZ()));
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
		}
		return new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_claims/" + new Object() {
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
	}
}
