package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

public class ReturnHealthProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "\u00A7a" + new java.text.DecimalFormat("#,###.##").format(entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).health) + " \u00A7f/ \u00A72"
				+ new java.text.DecimalFormat("#,###.##").format(entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).max_health);
	}
}
