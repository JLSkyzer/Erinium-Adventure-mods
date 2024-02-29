package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import java.io.File;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class GetCommandEntityPathProcedure {
	public static File execute(CommandContext<CommandSourceStack> arguments) {
		File file = new File("");
		com.google.gson.JsonObject jsonObject = new com.google.gson.JsonObject();
		double return_value = 0;
		return new File((FMLPaths.GAMEDIR.get().toString() + "/Ericonomy/accounts/"), File.separator + ((new Object() {
			public Entity getEntity() {
				try {
					return EntityArgument.getEntity(arguments, "player");
				} catch (CommandSyntaxException e) {
					e.printStackTrace();
					return null;
				}
			}
		}.getEntity()).getUUID().toString() + ".json"));
	}
}
