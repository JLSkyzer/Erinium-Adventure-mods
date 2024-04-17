package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class CommandEntityAreSameFactionProcedure {
	public static boolean execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name).equals((new Object() {
			public Entity getEntity() {
				try {
					return EntityArgument.getEntity(arguments, "player");
				} catch (CommandSyntaxException e) {
					e.printStackTrace();
					return null;
				}
			}
		}.getEntity()).getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name)) {
			return true;
		}
		return false;
	}
}
