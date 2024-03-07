package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.Entity;

public class SearchBtnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ClearAllProcedure.execute(entity);
	}
}
