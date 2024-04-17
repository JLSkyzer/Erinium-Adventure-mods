package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import java.util.List;
import java.util.ArrayList;

import java.io.File;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class DemoteOnlineStringRankProcedure {
	public static String execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return "";
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		List<Object> return_logic = new ArrayList<>();
		String tempUUID = "";
		String return_faction_name = "";
		if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Chef")) {
			if (((new Object() {
				public Entity getEntity() {
					try {
						return EntityArgument.getEntity(arguments, "player");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()).getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Officier")) {
				return "Ancient";
			} else if (((new Object() {
				public Entity getEntity() {
					try {
						return EntityArgument.getEntity(arguments, "player");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()).getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Ancient")) {
				return "Member";
			} else if (((new Object() {
				public Entity getEntity() {
					try {
						return EntityArgument.getEntity(arguments, "player");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()).getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Member")) {
				return "Recruit";
			}
		} else if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Officier")) {
			if (((new Object() {
				public Entity getEntity() {
					try {
						return EntityArgument.getEntity(arguments, "player");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()).getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Ancient")) {
				return "Member";
			} else if (((new Object() {
				public Entity getEntity() {
					try {
						return EntityArgument.getEntity(arguments, "player");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()).getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Member")) {
				return "Recruit";
			}
		} else if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Ancient")) {
			if (((new Object() {
				public Entity getEntity() {
					try {
						return EntityArgument.getEntity(arguments, "player");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()).getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Member")) {
				return "Recruit";
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7cYou can't demote this player"), false);
		}
		return (new Object() {
			public Entity getEntity() {
				try {
					return EntityArgument.getEntity(arguments, "player");
				} catch (CommandSyntaxException e) {
					e.printStackTrace();
					return null;
				}
			}
		}.getEntity()).getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank;
	}
}
