package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

public class ValidateBtnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ValidateProcedure.execute(entity);
		CloseGuiProcedure.execute(entity);
	}
}
