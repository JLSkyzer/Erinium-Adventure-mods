
package fr.eriniumgroup.eriniumadventure.base.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class LogoEriniumItem extends Item {
	public LogoEriniumItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
