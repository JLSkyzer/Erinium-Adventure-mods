package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

import java.util.Calendar;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

@Mod.EventBusSubscriber
public class RealTimeProcedureProcedure {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.LevelTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.level);
		}
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		if (EriniumAdventureModVariables.MapVariables.get(world).toggle_realtime) {
			if (world instanceof ServerLevel _level)
				_level.setDayTime((int) (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 1000 - 6000 + Calendar.getInstance().get(Calendar.MINUTE) * 16.6));
		}
	}
}
