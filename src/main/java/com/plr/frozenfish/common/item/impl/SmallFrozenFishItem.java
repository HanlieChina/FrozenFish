package com.plr.frozenfish.common.item.impl;

import com.plr.frozenfish.common.item.api.IFrozenFish;
import com.plr.frozenfish.common.item.tier.FrozenFishTier;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SmallFrozenFishItem extends SwordItem implements IFrozenFish {
    private final ItemStack original;

    public SmallFrozenFishItem(ItemStack original) {
        super(FrozenFishTier.INSTANCE, 3, -2.8F, new Properties().tab(CreativeModeTab.TAB_COMBAT));
        this.original = original;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }

    @Override
    public ItemStack getOriginalFishStack() {
        return original;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        tick(pLevel, pEntity, pStack);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        return attack(pStack, pAttacker);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        return mine(pLevel, pState, pPos, pStack, pEntityLiving);
    }

    @Override
    public Component getName(ItemStack pStack) {
        return new TranslatableComponent("item.frozenfish.frozen", new TranslatableComponent(getOriginalFishStack().getDescriptionId()));
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        return repair(ctx.getLevel(), ctx.getPlayer(), ctx.getItemInHand(), ctx.getClickedPos()) ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }
}
