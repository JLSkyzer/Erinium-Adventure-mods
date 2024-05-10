package fr.eriniumgroups.eriniumcrate.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import java.util.function.Supplier;
import java.util.Map;

import fr.eriniumgroups.eriniumcrate.network.EriniumcrateModVariables;

public class PrevProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double slot_count = 0;
		if (entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES).page > 0) {
			for (int index0 = 0; index0 < 27; index0++) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get((int) slot_count)).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
				slot_count = slot_count + 1;
			}
			{
				EriniumcrateModVariables.PlayerVariables _vars = entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES);
				_vars.page = entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES).page - 1;
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumcrateModVariables.PlayerVariables _vars = entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES);
				_vars.page_initialised = false;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
