package fr.eriniumgroups.eriniumcrate.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.commands.CommandSourceStack;

import java.io.File;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

public class ReturnCustomFileCommandProcedure {
	public static File execute(CommandContext<CommandSourceStack> arguments) {
		File file = new File("");
		return new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumCrate/custom/"), File.separator + (StringArgumentType.getString(arguments, "id") + ".json"));
	}
}
