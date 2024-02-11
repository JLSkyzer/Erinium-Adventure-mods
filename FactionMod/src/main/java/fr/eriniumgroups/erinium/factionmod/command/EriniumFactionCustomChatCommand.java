
package fr.eriniumgroups.erinium.factionmod.command;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

import fr.eriniumgroups.erinium.factionmod.procedures.CustomChatProcProcedure;

@Mod.EventBusSubscriber
public class EriniumFactionCustomChatCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("eriniumfactioncustomchat").requires(s -> s.hasPermission(2)).executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null)
				entity = FakePlayerFactory.getMinecraft(world);
			Direction direction = entity.getDirection();

			CustomChatProcProcedure.execute(world, entity);
			return 0;
		}));
	}
}
