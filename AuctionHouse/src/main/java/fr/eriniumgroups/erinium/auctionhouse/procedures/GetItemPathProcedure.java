package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.Entity;

import java.io.File;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class GetItemPathProcedure {
	public static File execute(Entity entity) {
		if (entity == null)
			return new File("");
		File file = new File("");
		return new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"),
				File.separator + (((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getString("id")));
	}
}
