package fr.eriniumgroups.erinium.auctionhouse.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.BuyGuiMenu;
import fr.eriniumgroups.erinium.auctionhouse.procedures.QuantityProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.PriceProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.GetMoneyProcedure;
import fr.eriniumgroups.erinium.auctionhouse.network.BuyGuiButtonMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class BuyGuiScreen extends AbstractContainerScreen<BuyGuiMenu> {
	private final static HashMap<String, Object> guistate = BuyGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox quantity;
	ImageButton imagebutton_buy;

	public BuyGuiScreen(BuyGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("erinium_ah:textures/screens/buy_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (quantity.isFocused())
			return quantity.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_ah.buy_gui.label_buy"), 78, 7, -12829636, false);
		guiGraphics.drawString(this.font,

				QuantityProcedure.execute(entity), 6, 34, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_ah.buy_gui.label_money"), 6, 25, -12829636, false);
		guiGraphics.drawString(this.font,

				GetMoneyProcedure.execute(world, entity), 51, 25, -12829636, false);
		guiGraphics.drawString(this.font,

				PriceProcedure.execute(world, entity, guistate), 6, 70, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		quantity = new EditBox(this.font, this.leftPos + 7, this.topPos + 44, 70, 18, Component.translatable("gui.erinium_ah.buy_gui.quantity"));
		quantity.setMaxLength(32767);
		guistate.put("text:quantity", quantity);
		this.addWidget(this.quantity);
		imagebutton_buy = new ImageButton(this.leftPos + 60, this.topPos + 97, 54, 20, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/buy.png"), new ResourceLocation("erinium_ah:textures/screens/buy.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new BuyGuiButtonMessage(0, x, y, z));
				BuyGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_buy", imagebutton_buy);
		this.addRenderableWidget(imagebutton_buy);
	}
}
