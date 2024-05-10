package fr.eriniumgroups.erinium.auctionhouse.client.gui;

import fr.eriniumgroups.erinium.auctionhouse.procedures.*;
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

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.AhMainMenuMenu;
import fr.eriniumgroups.erinium.auctionhouse.network.AhMainMenuButtonMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class AhMainMenuScreen extends AbstractContainerScreen<AhMainMenuMenu> {
	private final static HashMap<String, Object> guistate = AhMainMenuMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox search;
	ImageButton imagebutton_search_btn;
	ImageButton imagebutton_buy;
	ImageButton imagebutton_buy1;
	ImageButton imagebutton_buy2;
	ImageButton imagebutton_buy3;
	ImageButton imagebutton_buy4;
	ImageButton imagebutton_buy5;
	ImageButton imagebutton_buy6;
	ImageButton imagebutton_up;
	ImageButton imagebutton_down;

	public AhMainMenuScreen(AhMainMenuMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 427;
		this.imageHeight = 240;
	}

	private static final ResourceLocation texture = new ResourceLocation("erinium_ah:textures/screens/ah_main_menu.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		search.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("erinium_ah:textures/screens/bg_" + ReturnThemeStringProcedure.execute(entity) + ".png"), this.leftPos + 0, this.topPos + 0, 0, 0, 427, 240, 427, 240);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (search.isFocused())
			return search.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font,

				Line0ItemNameProcedure.execute(entity), 25, 32, -12829636, false);
		guiGraphics.drawString(this.font,

				Line1ItemNameProcedure.execute(entity), 25, 63, -12829636, false);
		guiGraphics.drawString(this.font,

				Line2ItemNameProcedure.execute(entity), 25, 94, -12829636, false);
		guiGraphics.drawString(this.font,

				Line3ItemNameProcedure.execute(entity), 25, 125, -12829636, false);
		guiGraphics.drawString(this.font,

				Line4ItemNameProcedure.execute(entity), 25, 156, -12829636, false);
		guiGraphics.drawString(this.font,

				Line5ItemNameProcedure.execute(entity), 25, 187, -12829636, false);
		guiGraphics.drawString(this.font,

				Line6PlayerNameProcedure.execute(entity), 25, 218, -12829636, false);
		guiGraphics.drawString(this.font,

				Line0PlayerNameProcedure.execute(entity), 186, 32, -12829636, false);
		guiGraphics.drawString(this.font,

				Line1PlayerNameProcedure.execute(entity), 186, 63, -12829636, false);
		guiGraphics.drawString(this.font,

				Line2PlayerNameProcedure.execute(entity), 186, 94, -12829636, false);
		guiGraphics.drawString(this.font,

				Line3PlayerNameProcedure.execute(entity), 186, 125, -12829636, false);
		guiGraphics.drawString(this.font,

				Line4PlayerNameProcedure.execute(entity), 186, 156, -12829636, false);
		guiGraphics.drawString(this.font,

				Line5PlayerNameProcedure.execute(entity), 186, 187, -12829636, false);
		guiGraphics.drawString(this.font,

				Line6PlayerNameProcedure.execute(entity), 186, 218, -12829636, false);
		guiGraphics.drawString(this.font,

				Line0QuantityProcedure.execute(entity), 287, 27, -12829636, false);
		guiGraphics.drawString(this.font,

				Line0PriceProcedure.execute(entity), 287, 37, -12829636, false);
		guiGraphics.drawString(this.font,

				Line1QuantityProcedure.execute(entity), 287, 58, -12829636, false);
		guiGraphics.drawString(this.font,

				Line1PriceProcedure.execute(entity), 287, 68, -12829636, false);
		guiGraphics.drawString(this.font,

				Line2QuantityProcedure.execute(entity), 287, 89, -12829636, false);
		guiGraphics.drawString(this.font,

				Line2PriceProcedure.execute(entity), 287, 99, -12829636, false);
		guiGraphics.drawString(this.font,

				Line3QuantityProcedure.execute(entity), 287, 120, -12829636, false);
		guiGraphics.drawString(this.font,

				Line3PriceProcedure.execute(entity), 287, 130, -12829636, false);
		guiGraphics.drawString(this.font,

				Line4QuantityProcedure.execute(entity), 287, 151, -12829636, false);
		guiGraphics.drawString(this.font,

				Line4PriceProcedure.execute(entity), 287, 161, -12829636, false);
		guiGraphics.drawString(this.font,

				Line5QuantityProcedure.execute(entity), 287, 182, -12829636, false);
		guiGraphics.drawString(this.font,

				Line5PriceProcedure.execute(entity), 287, 192, -12829636, false);
		guiGraphics.drawString(this.font,

				Line6QuantityProcedure.execute(entity), 287, 213, -12829636, false);
		guiGraphics.drawString(this.font,

				Line6PriceProcedure.execute(entity), 287, 223, -12829636, false);
		guiGraphics.drawString(this.font,

				GetMoneyMainPageProcedure.execute(world, entity), 343, 0, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_ah.ah_main_menu.label_market"), 1, 1, -16750951, false);
	}

	@Override
	public void init() {
		super.init();
		search = new EditBox(this.font, this.leftPos + 149, this.topPos + 1, 108, 18, Component.translatable("gui.erinium_ah.ah_main_menu.search"));
		search.setMaxLength(32767);
		guistate.put("text:search", search);
		this.addWidget(this.search);
		imagebutton_search_btn = new ImageButton(this.leftPos + 258, this.topPos + 0, 20, 20, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/search_btn.png"), new ResourceLocation("erinium_ah:textures/screens/search_btn.png")),
				e -> {
					if (true) {
						PacketDistributor.SERVER.noArg().send(new AhMainMenuButtonMessage(0, x, y, z));
						AhMainMenuButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_search_btn", imagebutton_search_btn);
		this.addRenderableWidget(imagebutton_search_btn);
		imagebutton_buy = new ImageButton(this.leftPos + 348, this.topPos + 27, 54, 20, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/buy.png"), new ResourceLocation("erinium_ah:textures/screens/buy.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new AhMainMenuButtonMessage(1, x, y, z));
				AhMainMenuButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_buy", imagebutton_buy);
		this.addRenderableWidget(imagebutton_buy);
		imagebutton_buy1 = new ImageButton(this.leftPos + 348, this.topPos + 58, 54, 20, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/buy.png"), new ResourceLocation("erinium_ah:textures/screens/buy.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new AhMainMenuButtonMessage(2, x, y, z));
				AhMainMenuButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_buy1", imagebutton_buy1);
		this.addRenderableWidget(imagebutton_buy1);
		imagebutton_buy2 = new ImageButton(this.leftPos + 348, this.topPos + 89, 54, 20, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/buy.png"), new ResourceLocation("erinium_ah:textures/screens/buy.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new AhMainMenuButtonMessage(3, x, y, z));
				AhMainMenuButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_buy2", imagebutton_buy2);
		this.addRenderableWidget(imagebutton_buy2);
		imagebutton_buy3 = new ImageButton(this.leftPos + 348, this.topPos + 120, 54, 20, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/buy.png"), new ResourceLocation("erinium_ah:textures/screens/buy.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new AhMainMenuButtonMessage(4, x, y, z));
				AhMainMenuButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_buy3", imagebutton_buy3);
		this.addRenderableWidget(imagebutton_buy3);
		imagebutton_buy4 = new ImageButton(this.leftPos + 348, this.topPos + 151, 54, 20, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/buy.png"), new ResourceLocation("erinium_ah:textures/screens/buy.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new AhMainMenuButtonMessage(5, x, y, z));
				AhMainMenuButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_buy4", imagebutton_buy4);
		this.addRenderableWidget(imagebutton_buy4);
		imagebutton_buy5 = new ImageButton(this.leftPos + 348, this.topPos + 182, 54, 20, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/buy.png"), new ResourceLocation("erinium_ah:textures/screens/buy.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new AhMainMenuButtonMessage(6, x, y, z));
				AhMainMenuButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_buy5", imagebutton_buy5);
		this.addRenderableWidget(imagebutton_buy5);
		imagebutton_buy6 = new ImageButton(this.leftPos + 348, this.topPos + 213, 54, 20, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/buy.png"), new ResourceLocation("erinium_ah:textures/screens/buy.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new AhMainMenuButtonMessage(7, x, y, z));
				AhMainMenuButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_buy6", imagebutton_buy6);
		this.addRenderableWidget(imagebutton_buy6);
		imagebutton_up = new ImageButton(this.leftPos + 406, this.topPos + 21, 20, 20, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/up.png"), new ResourceLocation("erinium_ah:textures/screens/up.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new AhMainMenuButtonMessage(8, x, y, z));
				AhMainMenuButtonMessage.handleButtonAction(entity, 8, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_up", imagebutton_up);
		this.addRenderableWidget(imagebutton_up);
		imagebutton_down = new ImageButton(this.leftPos + 406, this.topPos + 219, 20, 20, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/down.png"), new ResourceLocation("erinium_ah:textures/screens/down.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new AhMainMenuButtonMessage(9, x, y, z));
				AhMainMenuButtonMessage.handleButtonAction(entity, 9, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_down", imagebutton_down);
		this.addRenderableWidget(imagebutton_down);
	}
}
