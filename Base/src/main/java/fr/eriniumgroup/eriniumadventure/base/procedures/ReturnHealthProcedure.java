package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

public class ReturnHealthProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "\u00A7a" + new java.text.DecimalFormat("#,###.##").format((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health) + " \u00A7f/ \u00A72"
				+ new java.text.DecimalFormat("#,###.##").format((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).max_health);
	}
}
