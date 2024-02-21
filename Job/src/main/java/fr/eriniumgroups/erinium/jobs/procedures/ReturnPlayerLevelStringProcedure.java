package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class ReturnPlayerLevelStringProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "\u00A7a" + Component.translatable("jobs.info.level.string").getString() + " \u00A7e" + new java.text.DecimalFormat("##").format(ReturnPlayerLevelProcedure.execute(entity));
	}
}
