package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.neoforged.fml.ModList;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.ericonomy.configuration.ServerConfigConfiguration;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class MoneyResetProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		boolean return_logic = false;
		if (EntityIsAdminProcedure.execute(entity)) {
			file = GetCommandEntityPathProcedure.execute(arguments);
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					JsonObject.addProperty("money", ((double) ServerConfigConfiguration.START_MONEY.get()));
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7eEriconomy \u00A7f>> " + "\u00A7dRESET " + "\u00A7b--> " + (new Object() {
							public Entity getEntity() {
								try {
									return EntityArgument.getEntity(arguments, "player");
								} catch (CommandSyntaxException e) {
									e.printStackTrace();
									return null;
								}
							}
						}.getEntity()).getDisplayName().getString())), false);
					if ((new Object() {
						public Entity getEntity() {
							try {
								return EntityArgument.getEntity(arguments, "player");
							} catch (CommandSyntaxException e) {
								e.printStackTrace();
								return null;
							}
						}
					}.getEntity()) instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7eEriconomy \u00A7f>> " + "\u00A7dRESET your balance")), false);
					if (ModList.get().isLoaded("erinium_logs")) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"erilog " + "Ericonomy" + " " + "info" + " " + ("ADMIN " + entity.getDisplayName().getString() + " reset " + " balance of " + (new Object() {
										public Entity getEntity() {
											try {
												return EntityArgument.getEntity(arguments, "player");
											} catch (CommandSyntaxException e) {
												e.printStackTrace();
												return null;
											}
										}
									}.getEntity()).getDisplayName().getString()));
					} else {
						System.out.println("Erilog is not installed ! install here : https://github.com/JLSkyzer/Erinium-Adventure-mods/releases/tag/Erilog");
					}
					{
						com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
						try {
							FileWriter fileWriter = new FileWriter(file);
							fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
							fileWriter.close();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
