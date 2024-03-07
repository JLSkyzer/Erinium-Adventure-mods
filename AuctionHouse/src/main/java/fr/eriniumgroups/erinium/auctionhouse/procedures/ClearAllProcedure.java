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
			double _setval = 0;
			entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.ah_page = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			boolean _setval = false;
			entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.ah_initialised = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
