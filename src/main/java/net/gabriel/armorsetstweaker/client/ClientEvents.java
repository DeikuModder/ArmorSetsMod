package net.gabriel.armorsetstweaker.client;

import net.gabriel.armorsetstweaker.menu.ArmorSetsMenu;
import net.gabriel.armorsetstweaker.screen.ArmorSetsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    private static int currentSet = 0; // Set de equipamiento activo

    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;

        if (player != null) {
            // Abrir el menú con la tecla asignada
            if (ModKeyMappings.OPEN_ARMOR_SETS.isDown()) {
                mc.setScreen(new ArmorSetsScreen(new ArmorSetsMenu(0, player.getInventory(), new SimpleContainer(
                        ArmorSetsMenu.SET_COUNT * (ArmorSetsMenu.ARMOR_SLOT_COUNT + ArmorSetsMenu.ITEM_SLOT_COUNT)
                )), player.getInventory(), Component.literal("Armor Sets")));
            }

            // Cambiar de set con la tecla asignada
            if (ModKeyMappings.SWITCH_ARMOR_SET.isDown()) {
                switchArmorSet(player);
            }
        }
    }

    private static void switchArmorSet(LocalPlayer player) {
        currentSet = (currentSet + 1) % ArmorSetsMenu.SET_COUNT; // Rotar entre los 3 sets

        // Enviar mensaje al servidor para cambiar el set
        if (player.containerMenu instanceof ArmorSetsMenu armorSetsMenu) {
            armorSetsMenu.equipSet(player, currentSet);
            player.displayClientMessage(Component.literal("Switched to Armor Set " + (currentSet + 1)), true);
        }
    }
}
