package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class RightClickBlockProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event);
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs"))).isDirectory() && new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs"))).exists()) {
			// Récupérer la liste des fichiers et sous-dossiers dans le dossier
			java.io.File[] fichiers = new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs"))).listFiles();
			// Parcourir la liste et supprimer chaque fichier/dossier
			if (fichiers != null) {
				for (java.io.File fichier : fichiers) {
					fichier.delete();
				}
			}
			if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs"))).listFiles() != null) {
				new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs"))).delete();
			} else {
				System.out.println("Unable to delete the folder, it seems that a file was recreated after deleting all the files");
			}
		}
		if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs2"))).isDirectory() && new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs2"))).exists()) {
			// Récupérer la liste des fichiers et sous-dossiers dans le dossier
			java.io.File[] fichiers = new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs2"))).listFiles();
			// Parcourir la liste et supprimer chaque fichier/dossier
			if (fichiers != null) {
				for (java.io.File fichier : fichiers) {
					fichier.delete();
				}
			}
			if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs2"))).listFiles() != null) {
				new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs2"))).delete();
			} else {
				System.out.println("Unable to delete the folder, it seems that a file was recreated after deleting all the files");
			}
		}
	}
}
