package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class ReturnThemeStringProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return (entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).theme;
	}
}
