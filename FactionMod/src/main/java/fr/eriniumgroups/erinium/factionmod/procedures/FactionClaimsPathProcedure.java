package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import java.io.File;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class FactionClaimsPathProcedure {
	public static File execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return new File("");
		String uuid_list = "";
		String player_name = "";
		File File = new File("");
		if (TargetEntityHaveFactionProcedure.execute(entity)) {
			return new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name
					+ "/claim_list/" + new Object() {
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
		return new File((FMLPaths.GAMEDIR.get().toString() + "/tmp/"), File.separator + "tmp.json");
	}
}
