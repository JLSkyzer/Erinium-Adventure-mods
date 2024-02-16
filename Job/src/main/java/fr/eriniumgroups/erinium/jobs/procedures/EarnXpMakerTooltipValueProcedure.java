package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;


import java.util.ArrayList;
import java.util.List;

public class EarnXpMakerTooltipValueProcedure {
	public static String execute() {

		return Component.literal("Bonjour").append("Hey").toString();
	}
}
