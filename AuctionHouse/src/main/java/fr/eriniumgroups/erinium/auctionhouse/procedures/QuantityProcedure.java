package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class QuantityProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Quantity ("
				+ new java.text.DecimalFormat("##").format(((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getDouble("quantity"))
				+ ")";
	}
}
