package net.gabriel.armorsetstweaker.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.gabriel.armorsetstweaker.ArmorSets;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = ArmorSets.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModKeyMappings {
    public static final KeyMapping OPEN_ARMOR_SETS = new KeyMapping(
            "key.armorsetstweaker.open_armor_sets",  // Nombre interno
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,  // Tecla G para abrir el menú
            "key.categories.armorsetstweaker"  // Categoría personalizada
    );

    public static final KeyMapping SWITCH_ARMOR_SET = new KeyMapping(
            "key.armorsetstweaker.switch_armor_set",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_H,  // Tecla H para cambiar de set
            "key.categories.armorsetstweaker"
    );

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(OPEN_ARMOR_SETS);
        event.register(SWITCH_ARMOR_SET);
    }
}
