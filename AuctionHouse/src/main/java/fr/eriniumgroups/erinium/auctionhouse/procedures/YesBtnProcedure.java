package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class YesBtnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		double quantity = 0;
		file = GetItemPathProcedure.execute(entity);
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				quantity = JsonObject.get("quantity").getAsDouble();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item);
			_setstack.setCount((int) quantity);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		if (new java.io.File(new String(file.getPath())).exists()) { // Vérifie si le fichier existe avant de le supprimer
			// Supprime le fichier
			boolean suppressionReussie = new java.io.File(file.getPath()).delete();
			if (suppressionReussie) {
				System.out.println("Le fichier a été supprimé avec succès.");
			} else {
				System.out.println("Impossible de supprimer le fichier.");
			}
		} else {
			System.out.println("Le fichier n'existe pas.");
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7aSuccefully deleted !"), false);
	}
}
