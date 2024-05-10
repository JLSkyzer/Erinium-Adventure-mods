
package fr.eriniumgroups.eriniumcrate.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class BasicKeyItem extends Item {
	public BasicKeyItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON));
	}
}
