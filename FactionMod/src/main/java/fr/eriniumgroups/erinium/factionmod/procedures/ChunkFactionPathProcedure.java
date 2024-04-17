package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import java.io.File;

public class ChunkFactionPathProcedure {
	public static File execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return new File("");
		String uuid_list = "";
		String player_name = "";
		File File = new File("");
		if (TargetEntityHaveFactionProcedure.execute(entity)) {
			return new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + ReturnOwnedFactiionProcedure.execute(world, entity) + "/"), File.separator + "global_informations.json");
		}
		return new File((FMLPaths.GAMEDIR.get().toString() + "/tmp/"), File.separator + "tmp.json");
	}
}
