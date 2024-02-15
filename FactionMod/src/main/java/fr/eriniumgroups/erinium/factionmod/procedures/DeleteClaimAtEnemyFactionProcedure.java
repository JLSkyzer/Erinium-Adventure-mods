package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import java.io.File;

public class DeleteClaimAtEnemyFactionProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		String uuid_list = "";
		String player_name = "";
		File File = new File("");
		if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + ReturnOwnedFactiionProcedure.execute(world, entity) + "/claim_list/" + new Object() {
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
			boolean suppressionReussie = new java.io.File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + ReturnOwnedFactiionProcedure.execute(world, entity) + "/claim_list/" + new Object() {
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
	}
}
