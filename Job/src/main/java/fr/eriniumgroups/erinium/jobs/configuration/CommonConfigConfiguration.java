package fr.eriniumgroups.erinium.jobs.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Double> XP_MULTIPLIER;
	public static final ForgeConfigSpec.ConfigValue<Double> WONXP_MULTIPLIER;
	static {
		BUILDER.push("Multiplier");
		XP_MULTIPLIER = BUILDER.comment("Xp multiplier see here : https://1drv.ms/x/s!Aq5o6W9h7OB9gYExyFZm1cFhJ0n0EA?e=zVMCg7").define("XP Multiplier", (double) 1.095);
		WONXP_MULTIPLIER = BUILDER.comment("Won XP multiplier see here : https://1drv.ms/x/s!Aq5o6W9h7OB9gYExyFZm1cFhJ0n0EA?e=zVMCg7").define("Won XP Multiplier", (double) 0.982);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
