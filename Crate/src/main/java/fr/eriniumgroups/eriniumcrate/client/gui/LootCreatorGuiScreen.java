package fr.eriniumgroups.eriniumcrate.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroups.eriniumcrate.world.inventory.LootCreatorGuiMenu;
import fr.eriniumgroups.eriniumcrate.procedures.ReturnTypeTextProcedure;
import fr.eriniumgroups.eriniumcrate.procedures.LootCreatorCreateButtonShowConditionProcedure;
import fr.eriniumgroups.eriniumcrate.network.LootCreatorGuiButtonMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class LootCreatorGuiScreen extends AbstractContainerScreen<LootCreatorGuiMenu> {
	private final static HashMap<String, Object> guistate = LootCreatorGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox chance;
	EditBox count;
	EditBox eventname;
	EditBox eventmessage;
	Button button_create_the_loot;

	public LootCreatorGuiScreen(LootCreatorGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 231;
	}

	private static final ResourceLocation texture = new ResourceLocation("eriniumcrate:textures/screens/loot_creator_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		chance.render(guiGraphics, mouseX, mouseY, partialTicks);
		count.render(guiGraphics, mouseX, mouseY, partialTicks);
		eventname.render(guiGraphics, mouseX, mouseY, partialTicks);
		eventmessage.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 7 && mouseX < leftPos + 43 && mouseY > topPos + 39 && mouseY < topPos + 57)
			guiGraphics.renderTooltip(font, Component.translatable("gui.eriniumcrate.loot_creator_gui.tooltip_change_in_001_max"), mouseX, mouseY);
		if (mouseX > leftPos + 43 && mouseX < leftPos + 97 && mouseY > topPos + 21 && mouseY < topPos + 39)
			guiGraphics.renderTooltip(font, Component.translatable("gui.eriniumcrate.loot_creator_gui.tooltip_item_count_min_1_to_dont_broke"), mouseX, mouseY);
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
		if (chance.isFocused())
			return chance.keyPressed(key, b, c);
		if (count.isFocused())
			return count.keyPressed(key, b, c);
		if (eventname.isFocused())
			return eventname.keyPressed(key, b, c);
		if (eventmessage.isFocused())
			return eventmessage.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumcrate.loot_creator_gui.label_loot_creator"), 7, 3, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnTypeTextProcedure.execute(entity), 97, 3, -3407872, false);
	}

	@Override
	public void init() {
		super.init();
		chance = new EditBox(this.font, this.leftPos + 8, this.topPos + 40, 34, 18, Component.translatable("gui.eriniumcrate.loot_creator_gui.chance"));
		chance.setMaxLength(32767);
		guistate.put("text:chance", chance);
		this.addWidget(this.chance);
		count = new EditBox(this.font, this.leftPos + 44, this.topPos + 22, 52, 18, Component.translatable("gui.eriniumcrate.loot_creator_gui.count"));
		count.setMaxLength(32767);
		guistate.put("text:count", count);
		this.addWidget(this.count);
		eventname = new EditBox(this.font, this.leftPos + 8, this.topPos + 67, 88, 18, Component.translatable("gui.eriniumcrate.loot_creator_gui.eventname")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.eriniumcrate.loot_creator_gui.eventname").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.eriniumcrate.loot_creator_gui.eventname").getString());
				else
					setSuggestion(null);
			}
		};
		eventname.setMaxLength(32767);
		eventname.setSuggestion(Component.translatable("gui.eriniumcrate.loot_creator_gui.eventname").getString());
		guistate.put("text:eventname", eventname);
		this.addWidget(this.eventname);
		eventmessage = new EditBox(this.font, this.leftPos + 8, this.topPos + 94, 160, 18, Component.translatable("gui.eriniumcrate.loot_creator_gui.eventmessage")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.eriniumcrate.loot_creator_gui.eventmessage").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.eriniumcrate.loot_creator_gui.eventmessage").getString());
				else
					setSuggestion(null);
			}
		};
		eventmessage.setMaxLength(32767);
		eventmessage.setSuggestion(Component.translatable("gui.eriniumcrate.loot_creator_gui.eventmessage").getString());
		guistate.put("text:eventmessage", eventmessage);
		this.addWidget(this.eventmessage);
		button_create_the_loot = Button.builder(Component.translatable("gui.eriniumcrate.loot_creator_gui.button_create_the_loot"), e -> {
			if (LootCreatorCreateButtonShowConditionProcedure.execute(entity, guistate)) {
				PacketDistributor.SERVER.noArg().send(new LootCreatorGuiButtonMessage(0, x, y, z));
				LootCreatorGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 43, this.topPos + 120, 90, 20).build(builder -> new Button(builder) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (LootCreatorCreateButtonShowConditionProcedure.execute(entity, guistate))
					super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		});
		guistate.put("button:button_create_the_loot", button_create_the_loot);
		this.addRenderableWidget(button_create_the_loot);
	}
}
