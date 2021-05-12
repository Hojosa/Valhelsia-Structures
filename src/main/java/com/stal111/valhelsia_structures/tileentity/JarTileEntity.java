package com.stal111.valhelsia_structures.tileentity;

import com.stal111.valhelsia_structures.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Jar Tile Entity
 * Valhelsia Structures - com.stal111.valhelsia_structures.tileentity.JarTileEntity
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-13
 */
public class JarTileEntity extends TileEntity {

    private ItemStack plant = ItemStack.EMPTY;

    public JarTileEntity() {
        super(ModTileEntities.JAR.get());
    }

    public boolean hasPlant() {
        return !getPlant().isEmpty();
    }

    public ItemStack getPlant() {
        return plant;
    }

    public void setPlant(ItemStack plant) {
        this.plant = plant;
        this.markDirty();
    }

    @Override
    public void read(@Nonnull BlockState state, @Nonnull CompoundNBT compound) {
        super.read(state, compound);
        this.plant =  compound.contains("Plant", 10) ? ItemStack.read(compound.getCompound("Plant")) : ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public CompoundNBT write(@Nonnull CompoundNBT compound) {
        super.write(compound);
        if (hasPlant()) {
            compound.put("Plant", plant.write(new CompoundNBT()));
        }

        return compound;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, SUpdateTileEntityPacket packet) {
        if (this.world != null) {
            this.read(this.world.getBlockState(packet.getPos()), packet.getNbtCompound());
        }
    }
}