package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.CommandSourceStack;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.mojang.brigadier.context.CommandContext;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class BlackListItemRemoveProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String temp_string = "";
		if (TargetEntityIsAdminProcedure.execute(entity)) {
			File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumFaction/"), File.separator + "blacklist_item.json");
			if (File.exists()) {
				{
					try {
						BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
						StringBuilder jsonstringbuilder = new StringBuilder();
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							jsonstringbuilder.append(line);
						}
						bufferedReader.close();
						JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						if (JsonObject.get("list").getAsString().contains(ForgeRegistries.ITEMS.getKey((ItemArgument.getItem(arguments, "item").getItem().getDefaultInstance()).getItem()).toString() + ", ")) {
							JsonObject.addProperty("list", (JsonObject.get("list").getAsString().replace(ForgeRegistries.ITEMS.getKey((ItemArgument.getItem(arguments, "item").getItem().getDefaultInstance()).getItem()).toString() + ", ", "")));
							{
								Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
								try {
									FileWriter fileWriter = new FileWriter(File);
									fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
									fileWriter.close();
								} catch (IOException exception) {
									exception.printStackTrace();
								}
							}
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal(("\u00A7aRemoved : \u00A7b" + ForgeRegistries.ITEMS.getKey((ItemArgument.getItem(arguments, "item").getItem().getDefaultInstance()).getItem()).toString())), false);
						} else {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal("\u00A7cNo exist !"), false);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					File.getParentFile().mkdirs();
					File.createNewFile();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
				JsonObject.addProperty("list", "");
				{
					Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
					try {
						FileWriter fileWriter = new FileWriter(File);
						fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
						fileWriter.close();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				}
				temp_string = "File not exist, he has been created !";
			}
		}
	}
}
