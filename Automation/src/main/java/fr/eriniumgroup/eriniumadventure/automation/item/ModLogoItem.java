
package fr.eriniumgroup.eriniumadventure.automation.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class ModLogoItem extends Item {
	public ModLogoItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
