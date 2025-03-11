package net.gabriel.armorsetstweaker.item.custom;

import net.gabriel.armorsetstweaker.ArmorSets;
import net.gabriel.armorsetstweaker.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    private static TagKey<Block> tag(String name) {
        return BlockTags.create(new ResourceLocation(ArmorSets.MODID, name));
    }

    public static final Tier ANOSTEEL = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2500, 0.5f, 4f, 25, tag("need_anosteel_tool"), () -> Ingredient.of(ModItems.ANOSTEEl.get())),
            new ResourceLocation(ArmorSets.MODID, "anosteel"), List.of(Tiers.NETHERITE), List.of());
}
