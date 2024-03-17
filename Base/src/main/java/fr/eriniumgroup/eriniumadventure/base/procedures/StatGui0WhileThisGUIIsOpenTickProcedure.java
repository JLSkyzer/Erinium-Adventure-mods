package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

public class StatGui0WhileThisGUIIsOpenTickProcedure {
	public static void execute(double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double i = 0;
		double j = 0;
		String tag = "";
		ItemStack tempItem = ItemStack.EMPTY;
		if (!(entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).stat_initialised) {
			InitialiseFlameProcedure.execute(entity);
			InitialiseHeartProcedure.execute(entity);
			{
				boolean _setval = true;
				entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.stat_initialised = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
