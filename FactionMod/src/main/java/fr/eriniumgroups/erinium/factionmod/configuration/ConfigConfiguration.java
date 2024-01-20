package fr.eriniumgroups.erinium.factionmod.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Double> MAX_MEMBER;
	static {
		BUILDER.push("Factions");
		MAX_MEMBER = BUILDER.define("Max Members", (double) 20);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
