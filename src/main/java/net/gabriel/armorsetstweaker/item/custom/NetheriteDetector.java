package net.gabriel.armorsetstweaker.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class NetheriteDetector extends Item {
    public NetheriteDetector(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            // Obtener la rotación vertical del jugador (mirando hacia arriba/abajo)
            float pitch = player.getXRot();

            // Si el jugador está mirando hacia abajo (pitch > 45 grados), buscar en el eje Y
            if (pitch > 45) {
                for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                    BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

                    if (isValuableBlock(state)) {
                        outputValuableCoordinates(positionClicked.below(i), player, state.getBlock());
                        foundBlock = true;
                        break;
                    }
                }
            } else {
                // Si el jugador está mirando hacia el frente, buscar en el eje X o Z
                // Obtener la rotación horizontal del jugador (yaw)
                float yaw = player.getYRot();

                // Determinar la dirección en la que el jugador está mirando
                if (yaw >= -45 && yaw < 45) {
                    // Mirando hacia el sur (eje Z positivo)
                    for (int i = 0; i <= 64; i++) {
                        BlockPos targetPos = positionClicked.south(i);
                        BlockState state = pContext.getLevel().getBlockState(targetPos);

                        if (isValuableBlock(state)) {
                            outputValuableCoordinates(targetPos, player, state.getBlock());
                            foundBlock = true;
                            break;
                        }
                    }
                } else if (yaw >= 45 && yaw < 135) {
                    // Mirando hacia el oeste (eje X negativo)
                    for (int i = 0; i <= 64; i++) {
                        BlockPos targetPos = positionClicked.west(i);
                        BlockState state = pContext.getLevel().getBlockState(targetPos);

                        if (isValuableBlock(state)) {
                            outputValuableCoordinates(targetPos, player, state.getBlock());
                            foundBlock = true;
                            break;
                        }
                    }
                } else if (yaw >= 135 || yaw < -135) {
                    // Mirando hacia el norte (eje Z negativo)
                    for (int i = 0; i <= 64; i++) {
                        BlockPos targetPos = positionClicked.north(i);
                        BlockState state = pContext.getLevel().getBlockState(targetPos);

                        if (isValuableBlock(state)) {
                            outputValuableCoordinates(targetPos, player, state.getBlock());
                            foundBlock = true;
                            break;
                        }
                    }
                } else if (yaw >= -135 && yaw < -45) {
                    // Mirando hacia el este (eje X positivo)
                    for (int i = 0; i <= 64; i++) {
                        BlockPos targetPos = positionClicked.east(i);
                        BlockState state = pContext.getLevel().getBlockState(targetPos);

                        if (isValuableBlock(state)) {
                            outputValuableCoordinates(targetPos, player, state.getBlock());
                            foundBlock = true;
                            break;
                        }
                    }
                }
            }

            if (!foundBlock) {
                player.sendSystemMessage(Component.literal("No ancient debris were found!"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " + "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(Blocks.ANCIENT_DEBRIS);
    }
}