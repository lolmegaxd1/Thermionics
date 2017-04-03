/**
 * MIT License
 *
 * Copyright (c) 2017 Isaac Ellingson (Falkreon) and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.elytradev.thermionics.data;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.items.ItemStackHandler;

public class ObservableItemStorage extends ItemStackHandler implements IInventory {
	private ArrayList<Runnable> listeners = new ArrayList<>();
	private String name = "";
	
	public ObservableItemStorage(int slots) {
		super(slots);
	}
	
	public ObservableItemStorage(int slots, String name) {
		super(slots);
		this.name = name;
	}

	public void markDirty() {
		for(Runnable r : listeners) {
			r.run();
		}
	}
	
	public void listen(@Nonnull Runnable r) {
		listeners.add(r);
	}
	
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		ItemStack stack = super.extractItem(slot, amount, simulate);
		if (!simulate) markDirty();
		return stack;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack itemStack, boolean simulate) {
		ItemStack result = super.insertItem(slot, itemStack, simulate);
		if (!simulate) markDirty();
		return result;
	}

	@Override
	public void setStackInSlot(int slot, ItemStack itemStack) {
		super.setStackInSlot(slot, itemStack);
		markDirty();
	}

	/* IInventory methods */
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(name);
	}

	@Override
	public int getSizeInventory() {
		return this.getSlots();
	}

	@Override
	public boolean isEmpty() {
		for(int i=0; i<this.getSlots(); i++) {
			if (!this.getStackInSlot(i).isEmpty()) return false;
		}
		
		return true;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return extractItem(index, count, false);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack result = this.getStackInSlot(index);
		setStackInSlot(index, ItemStack.EMPTY);
		return result;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		setStackInSlot(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true; //TODO: Often there are distance checks involved here
	}

	@Override
	public void openInventory(EntityPlayer player) {
		//Do Nothing
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		//Do Nothing
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true; //Override this for validation!!!
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for(int i=0; i<this.getSlots(); i++) {
			this.setStackInSlot(i, ItemStack.EMPTY);
		}
	}
}