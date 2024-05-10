package fr.eriniumgroups.eriniumcrate.procedures;

import net.neoforged.fml.loading.FMLPaths;

import java.io.File;

public class ReturnPremiumFileProcedure {
	public static File execute() {
		File file = new File("");
		return new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumCrate/"), File.separator + "premium.json");
	}
}
