package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class QuantityProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Quantity (" + new java.text.DecimalFormat("##").format(entity.getData(EriniumAhModVariables.PLAYER_VARIABLES).ah_temp_item.getOrCreateTag().getDouble("quantity")) + ")";
	}
}
