package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class ReturnNextLevelInProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "\u00A7a" + Component.translatable("jobs.info.nextlevel.string").getString() + " \u00A76" + new java.text.DecimalFormat("#,###.##").format(ReturnCapXpProcedure.execute(entity) - ReturnPlayerXpProcedure.execute(entity));
	}
}
