package fr.eriniumgroups.eriniumcrate.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.io.File;

import fr.eriniumgroups.eriniumcrate.network.EriniumcrateModVariables;

public class ReturnFileLootProcedure {
	public static File execute(Entity entity) {
		if (entity == null)
			return new File("");
		File file = new File("");
		return new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumCrate/"), File.separator + (entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES).lootfor + ".json"));
	}
}
