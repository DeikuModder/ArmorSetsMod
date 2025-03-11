package net.gabriel.armorsetstweaker.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.gabriel.armorsetstweaker.ArmorSets;
import net.gabriel.armorsetstweaker.menu.CustomTabContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CustomTabScreen extends Screen {
    private static final ResourceLocation CUSTOM_TAB_TEXTURE = new ResourceLocation(ArmorSets.MODID, "textures/gui/custom_tab.png");
    private final CustomTabContainer container;

    public CustomTabScreen(Player player) {
        super(Component.translatable("gui.armorsetstweaker.custom_tab")); // Title of the screen
        this.container = new CustomTabContainer(0, player.getInventory());
    }

    @Override
    protected void init() {
        super.init();

        int tabWidth = (this.width - 256) / 2;
        int tabHeight = (this.height - 256) / 2;

        // Add a "Close Tab" button
        int buttonWidth = 100;
        int buttonHeight = 20;
        int buttonX = tabWidth - buttonWidth; // Center the button horizontally
        int buttonY = tabHeight; // Position the button vertically

        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.armorsetstweaker.close_tab"), // Button text
                button -> {
                    Player player = Minecraft.getInstance().player;

                    // Return to the inventory screen when the button is clicked
                    if (player == null) {
                        this.onClose();
                    } else {
                        Minecraft.getInstance().setScreen(new InventoryScreen(player));
                    }
                }
        ).bounds(buttonX, buttonY, buttonWidth, buttonHeight).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Render the background
        renderBackgroundTexture(guiGraphics);

        // Render the slots
        renderSlots(guiGraphics);

        // Render the player's inventory slots
        renderPlayerInventory(guiGraphics);

        // Render tooltips for hovered items
        renderHoveredTooltips(guiGraphics, mouseX, mouseY);

        // Draw the title
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF); // Title at the top

        // Render other widgets (e.g., buttons)
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    private void renderBackgroundTexture(GuiGraphics guiGraphics) {
        // Bind the custom texture
        guiGraphics.blit(CUSTOM_TAB_TEXTURE,
                (this.width - 256) / 2, // Center the texture horizontally
                (this.height - 256) / 2, // Center the texture vertically
                0, 0, // Texture UV coordinates (start at 0, 0)
                256, 256, // Texture width and height
                256, 256); // Full texture size
    }

    private void renderSlots(GuiGraphics guiGraphics) {
        int startX = (this.width - 256);
        int startY = (this.height) / 2;

        for (int i = 0; i < 4; i++) {
            int slotX = startX + i * 20;
            int slotY = startY;

            guiGraphics.blit(InventoryScreen.INVENTORY_LOCATION, slotX, slotY, 7, 7, 18, 18);

            Slot slot = this.container.slots.get(i);
            ItemStack stack = slot.getItem();
            if (!stack.isEmpty()) {
                guiGraphics.renderItem(stack, slotX + 1, slotY + 1);
                guiGraphics.renderItemDecorations(this.font, stack, slotX + 1, slotY + 1);
            }
        }
    }

    private void renderPlayerInventory(GuiGraphics guiGraphics) {
        int startX = (this.width - 176) / 2; // Center the inventory horizontally
        int startY = (this.height - 20) / 2 + 40; // Position the inventory below the custom slots

        // Render the inventory texture
        guiGraphics.blit(InventoryScreen.INVENTORY_LOCATION, startX, startY, 0, 0, 176, 94);

        // Render the player's inventory slots (27 slots)
        for (int i = 0; i < 27; i++) {
            int slotX = startX + 8 + (i % 9) * 18; // Calculate X position
            int slotY = startY + 18 + (i / 9) * 18; // Calculate Y position

            Slot slot = this.container.slots.get(4 + i); // Skip the custom slots
            ItemStack stack = slot.getItem();
            if (!stack.isEmpty()) {
                guiGraphics.renderItem(stack, slotX + 1, slotY + 1); // Adjust item position
                guiGraphics.renderItemDecorations(this.font, stack, slotX + 1, slotY + 1);
            }
        }

        // Render the hotbar slots (9 slots)
        for (int i = 0; i < 9; i++) {
            int slotX = startX + 8 + i * 18; // Calculate X position
            int slotY = startY + 76; // Fixed Y position for the hotbar

            Slot slot = this.container.slots.get(31 + i); // Skip the custom and main inventory slots
            ItemStack stack = slot.getItem();
            if (!stack.isEmpty()) {
                guiGraphics.renderItem(stack, slotX + 1, slotY + 1); // Adjust item position
                guiGraphics.renderItemDecorations(this.font, stack, slotX + 1, slotY + 1);
            }
        }
    }

    private void renderHoveredTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int startX = (this.width - 80) / 2; // Center the slots horizontally
        int startY = (this.height - 20) / 2; // Position the slots vertically

        // Check each slot for hover
        for (int i = 0; i < 4; i++) {
            int slotX = startX + i * 20; // Horizontal spacing between slots
            int slotY = startY;

            if (mouseX >= slotX && mouseY >= slotY && mouseX < slotX + 18 && mouseY < slotY + 18) {
                Slot slot = this.container.slots.get(i);
                ItemStack stack = slot.getItem();
                if (!stack.isEmpty()) {
                    guiGraphics.renderTooltip(this.font, stack, mouseX, mouseY);
                }
            }
        }
    }
}