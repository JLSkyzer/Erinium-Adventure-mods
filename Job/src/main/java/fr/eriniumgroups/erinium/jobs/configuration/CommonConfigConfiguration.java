package fr.eriniumgroups.erinium.jobs.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CommonConfigConfiguration {
	public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final ModConfigSpec SPEC;
	public static final ModConfigSpec.ConfigValue<Double> XP_MULTIPLIER;
	public static final ModConfigSpec.ConfigValue<Double> WONXP_MULTIPLIER;
	public static final ModConfigSpec.ConfigValue<String> ITEM_GIVE;
	static {
		BUILDER.push("Multiplier");
		XP_MULTIPLIER = BUILDER.comment("Xp multiplier see here : https://1drv.ms/x/s!Aq5o6W9h7OB9gYExyFZm1cFhJ0n0EA?e=zVMCg7").define("XP Multiplier", (double) 1.095);
		WONXP_MULTIPLIER = BUILDER.comment("Won XP multiplier see here : https://1drv.ms/x/s!Aq5o6W9h7OB9gYExyFZm1cFhJ0n0EA?e=zVMCg7").define("Won XP Multiplier", (double) 0.982);
		BUILDER.pop();
		BUILDER.push("Won Level");
		ITEM_GIVE = BUILDER.comment("Example \"minecraft:diamond 12, minecraft:iron_sword 1\" Please separe each item by \", \" ! The \",\" and the space is very important").define("Item give each level", "minecraft:air 64");
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
