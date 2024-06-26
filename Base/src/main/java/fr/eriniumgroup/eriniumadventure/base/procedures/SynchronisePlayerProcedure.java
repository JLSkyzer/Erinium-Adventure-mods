package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

public class SynchronisePlayerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack tempItem = ItemStack.EMPTY;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7eSynch your player...."), false);
		{
			EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
			_vars.max_health = 20 + entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).health_multiplier * 10;
			_vars.syncPlayerVariables(entity);
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7aSuccefully synched your data !"), false);
	}
}
