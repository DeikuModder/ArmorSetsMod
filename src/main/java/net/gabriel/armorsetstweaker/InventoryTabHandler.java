package net.gabriel.armorsetstweaker;

import net.gabriel.armorsetstweaker.gui.CustomTabScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArmorSets.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InventoryTabHandler {

    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init event) {
        // Check if the current screen is the inventory screen
        if (event.getScreen() instanceof InventoryScreen) {
            // Add a button to the inventory screen
            event.addListener(Button.builder(
                    Component.translatable("gui.armorsetstweaker.open_tab"), // Button text
                    button -> {
                        // Open the custom tab when the button is clicked
                        Minecraft.getInstance().setScreen(new CustomTabScreen(Minecraft.getInstance().player));
                    }
            ).bounds(
                    event.getScreen().width / 2 + 100, // X position (right side)
                    event.getScreen().height / 2 - 100, // Y position
                    80, 20                              // Width and height
            ).build());
        }
    }
}