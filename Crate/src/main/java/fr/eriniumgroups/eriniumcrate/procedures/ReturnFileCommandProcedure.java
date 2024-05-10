package fr.eriniumgroups.eriniumcrate.procedures;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.io.File;

import fr.eriniumgroups.eriniumcrate.network.EriniumcrateModVariables;

public class ReturnFileCommandProcedure {
	public static File execute(Entity entity) {
		if (entity == null)
			return new File("");
		File file = new File("");
		if (entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES).creator_types.contains("custom")) {
			return new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumCrate/custom/"), File.separator + (new Object() {
				private String split(String text, String space, int index) {
					String s = text.split(space)[index];
					return s;
				}
			}.split(entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES).creator_types, ":", (int) 1) + ".json"));
		}
		return new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumCrate/"), File.separator + (entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES).creator_types + ".json"));
	}
}
