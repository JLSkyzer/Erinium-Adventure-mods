package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class BlackListItemGetPageProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Page : " + new java.text.DecimalFormat("##").format((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).blacklist_item_page + 1);
	}
}
