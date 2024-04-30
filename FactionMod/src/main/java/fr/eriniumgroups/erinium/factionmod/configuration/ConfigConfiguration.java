package fr.eriniumgroups.erinium.factionmod.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;

import net.minecraft.world.item.Items;
import net.minecraft.core.registries.BuiltInRegistries;

public class ConfigConfiguration {
	public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final ModConfigSpec SPEC;
	public static final ModConfigSpec.ConfigValue<String> ITEM_NEED_CREATE;
	public static final ModConfigSpec.ConfigValue<Double> ITEM_NUMBER_CREATE;
	public static final ModConfigSpec.ConfigValue<Double> MAX_MEMBER;
	public static final ModConfigSpec.ConfigValue<Double> MAX_POWER;
	static {
		BUILDER.push("Common");
		ITEM_NEED_CREATE = BUILDER.comment("Item needed to create a faction").define("Item need", BuiltInRegistries.ITEM.getKey(Items.DIAMOND).toString());
		ITEM_NUMBER_CREATE = BUILDER.comment("Iten needed number").define("Number item needed", (double) 100);
		MAX_MEMBER = BUILDER.comment("Max member in a faction").define("Max member", (double) 20);
		MAX_POWER = BUILDER.comment("Max power per member").define("Max power", (double) 10);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
