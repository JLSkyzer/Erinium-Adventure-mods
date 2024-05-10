package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import java.util.function.Supplier;
import java.util.Map;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class ClearAllProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double count = 0;
		for (int index0 = 0; index0 < 7; index0++) {
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				((Slot) _slots.get((int) count)).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
			count = count + 1;
		}
		{
			EriniumAhModVariables.PlayerVariables _vars = entity.getData(EriniumAhModVariables.PLAYER_VARIABLES);
			_vars.ah_initialised = false;
			_vars.syncPlayerVariables(entity);
		}
	}
}
