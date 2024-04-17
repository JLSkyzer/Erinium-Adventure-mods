package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.File;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class CanInteractWithClaimsProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String returnOwned = "";
		if ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).bypass_claim) {
			return true;
		} else {
			if (!IsWarzoneProcedure.execute(world, entity) && !IsSafezoneProcedure.execute(world, entity)) {
				if ((ReturnOwnedFactiionProcedure.execute(world, entity)).equals("wilderness")
						|| (ReturnOwnedFactiionProcedure.execute(world, entity)).equals((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name)) {
					return true;
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("chunk.message.caninteract").getString())), false);
				return false;
			}
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("chunk.message.caninteract").getString())), false);
		return false;
	}
}
