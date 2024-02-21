package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class JobHelpProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7e- \u00A7b/job list \u00A7aTo get job list (ID)"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7e- \u00A7b/job earnxp <jobid> \u00A7aTo get all ways to earnxp on the job set ! awesone to know how work that job !"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7e- \u00A7b/job unlocked <jobid> \u00A7aTo get all unlockable item / block on the job, awesome to get all possibility with a job !"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7e- \u00A7b/job info <jobid> \u00A7aTo get your informations about a job"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A74[ADMIN] \u00A7e- \u00A7b/job create job <jobid> <jobdisplayname> \u00A7aCreate a job with an ID and an quotable displayName !"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A74[ADMIN] \u00A7e- \u00A7b/job create earnxp \u00A7aCreate an earnxp way to a job"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A74[ADMIN] \u00A7e- \u00A7b/job create required \u00A7aCreate requirement with a job"), false);
	}
}
