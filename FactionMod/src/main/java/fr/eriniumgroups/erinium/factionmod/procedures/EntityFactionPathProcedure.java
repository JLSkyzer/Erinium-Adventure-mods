package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.io.File;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class EntityFactionPathProcedure {
	public static File execute(Entity entity) {
		if (entity == null)
			return new File("");
		String uuid_list = "";
		String player_name = "";
		File File = new File("");
		if (TargetEntityHaveFactionProcedure.execute(entity)) {
			return new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + "/"), File.separator + "global_informations.json");
		}
		return new File((FMLPaths.GAMEDIR.get().toString() + "/tmp/"), File.separator + "tmp.json");
	}
}
