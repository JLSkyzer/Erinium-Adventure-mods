
package fr.eriniumgroup.eriniumadventure.automation.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class LaserModuleItem extends Item {
	public LaserModuleItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
