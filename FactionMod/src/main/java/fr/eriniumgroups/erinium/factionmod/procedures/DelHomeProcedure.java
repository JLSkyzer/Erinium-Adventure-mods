package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.File;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class DelHomeProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		if (TargetEntityHaveFactionProcedure.execute(entity)) {
			if (PlayerCanDelhomeProcedure.execute(entity) || TargetEntityIsChefProcedure.execute(entity)) {
				File = EntityFactionHomeProcedure.execute(entity);
				if (File.exists()) {
					if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/"
							+ (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + "/" + "faction_home.json"))).exists()) { // Vérifie si le fichier existe avant de le supprimer
						// Supprime le fichier
						boolean suppressionReussie = new java.io.File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/"
								+ (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + "/" + "faction_home.json")).delete();
						if (suppressionReussie) {
							System.out.println("Le fichier a été supprimé avec succès.");
						} else {
							System.out.println("Impossible de supprimer le fichier.");
						}
					} else {
						System.out.println("Le fichier n'existe pas.");
					}
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7a" + Component.translatable("faction.message.delhome.succeful").getString())), false);
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("faction.message.home.noexist").getString())), false);
				}
			}
		}
	}
}
