package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class ReturnSellQuantityProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "\u00A79Quantity (max: " + new java.text.DecimalFormat("##").format((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).selltempquantity) + ")";
	}
}
