
package fr.eriniumgroups.erinium.jobs.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class InfoItemItem extends Item {
	public InfoItemItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
