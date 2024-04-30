package fr.eriniumgroups.erinium.jobs.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.io.File;

public class JobListProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		com.google.gson.JsonObject ClearJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject JobJsonObject = new com.google.gson.JsonObject();
		File File = new File("");
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7aList job id (Click to copy)"), false);
		String cheminDossier = (FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/jobs/");
		java.io.File dossier = new java.io.File(cheminDossier);
		if (dossier.exists() && dossier.isDirectory()) {
			java.io.File[] fichiers = dossier.listFiles();
			// Parcours tous les fichiers du dossier
			for (java.io.File currentFile : fichiers) {
				// ...
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("tellraw " + entity.getDisplayName().getString() + " {\"text\":\"\u00A7e- " + currentFile.getName().replace(".json", "") + "\",\"clickEvent\":{\"action\":\"copy_to_clipboard\",\"value\":\""
									+ currentFile.getName().replace(".json", "") + "\"}}"));
			}
		} else {
			System.out.println("Le dossier n'existe pas ou n'est pas un dossier valide.");
		}
	}
}
