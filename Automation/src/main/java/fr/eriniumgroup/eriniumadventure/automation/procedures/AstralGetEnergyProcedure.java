package fr.eriniumgroup.eriniumadventure.automation.procedures;

import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class AstralGetEnergyProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		return "\u00A72Energy : \u00A7a" + (new java.text.DecimalFormat("###,###").format(new Object() {
			public int getEnergyStored(LevelAccessor level, BlockPos pos) {
				if (level instanceof ILevelExtension _ext) {
					IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, pos, null);
					if (_entityStorage != null)
						return _entityStorage.getEnergyStored();
				}
				return 0;
			}
		}.getEnergyStored(world, BlockPos.containing(x, y, z)))) + " \u00A7f/ \u00A7a" + (new java.text.DecimalFormat("###,###").format(new Object() {
			public int getMaxEnergyStored(LevelAccessor level, BlockPos pos) {
				if (level instanceof ILevelExtension _ext) {
					IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, pos, null);
					if (_entityStorage != null)
						return _entityStorage.getMaxEnergyStored();
				}
				return 0;
			}
		}.getMaxEnergyStored(world, BlockPos.containing(x, y, z))));
	}
}
