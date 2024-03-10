package fr.eriniumgroups.erinium.auctionhouse.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.SellGuiMenu;
import fr.eriniumgroups.erinium.auctionhouse.procedures.ReturnSellQuantityProcedure;
import fr.eriniumgroups.erinium.auctionhouse.network.SellGuiButtonMessage;
import fr.eriniumgroups.erinium.auctionhouse.EriniumAhMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class SellGuiScreen extends AbstractContainerScreen<SellGuiMenu> {
	private final static HashMap<String, Object> guistate = SellGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox price;
	EditBox quantity;
	Checkbox announce;
	Button button_sell;

	public SellGuiScreen(SellGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 233;
	}

	private static final ResourceLocation texture = new ResourceLocation("erinium_ah:textures/screens/sell_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		price.render(guiGraphics, mouseX, mouseY, partialTicks);
		quantity.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (price.isFocused())
			return price.keyPressed(key, b, c);
		if (quantity.isFocused())
			return quantity.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		price.tick();
		quantity.tick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_ah.sell_gui.label_sell_item"), 60, 4, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_ah.sell_gui.label_price_unity"), 6, 40, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSellQuantityProcedure.execute(entity), 6, 76, -12829636, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		price = new EditBox(this.font, this.leftPos + 7, this.topPos + 50, 70, 18, Component.translatable("gui.erinium_ah.sell_gui.price"));
		price.setMaxLength(32767);
		guistate.put("text:price", price);
		this.addWidget(this.price);
		quantity = new EditBox(this.font, this.leftPos + 7, this.topPos + 86, 70, 18, Component.translatable("gui.erinium_ah.sell_gui.quantity"));
		quantity.setMaxLength(32767);
		guistate.put("text:quantity", quantity);
		this.addWidget(this.quantity);
		button_sell = Button.builder(Component.translatable("gui.erinium_ah.sell_gui.button_sell"), e -> {
			if (true) {
				EriniumAhMod.PACKET_HANDLER.sendToServer(new SellGuiButtonMessage(0, x, y, z));
				SellGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 60, this.topPos + 130, 46, 20).build();
		guistate.put("button:button_sell", button_sell);
		this.addRenderableWidget(button_sell);
		announce = new Checkbox(this.leftPos + 6, this.topPos + 112, 20, 20, Component.translatable("gui.erinium_ah.sell_gui.announce"), false);
		guistate.put("checkbox:announce", announce);
		this.addRenderableWidget(announce);
	}
}
