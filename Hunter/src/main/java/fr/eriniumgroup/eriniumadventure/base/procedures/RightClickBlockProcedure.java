package fr.eriniumgroup.eriniumadventure.base.procedures;

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
		String cheminDossier = "test";

		java.io.File dossier = new java.io.File(cheminDossier);

		if (dossier.exists() && dossier.isDirectory()) {
			java.io.File[] fichiers = dossier.listFiles();

			// Parcours tous les fichiers du dossier
			for (java.io.File currentFile : fichiers) {
				// ...
				currentFile.getName();
				${statement$boucle}
			}
		} else {
			System.out.println("Le dossier n'existe pas ou n'est pas un dossier valide.");
		}
	}
}
