package net.gabriel.armorsetstweaker.client;

import net.gabriel.armorsetstweaker.menu.ArmorSetsMenu;
import net.gabriel.armorsetstweaker.registry.ModMenuTypes;
import net.gabriel.armorsetstweaker.screen.ArmorSetsScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModScreens {
    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenuTypes.ARMOR_SETS_MENU.get(), ArmorSetsScreen::new);
            net.minecraft.client.Minecraft.getInstance().options.keyMappings =
                    net.minecraft.client.Minecraft.getInstance().options.keyMappings.clone();
        });

    }
}
