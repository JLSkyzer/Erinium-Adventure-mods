package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class ReturnThemeStringProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return entity.getData(EriniumAhModVariables.PLAYER_VARIABLES).theme;
	}
}
