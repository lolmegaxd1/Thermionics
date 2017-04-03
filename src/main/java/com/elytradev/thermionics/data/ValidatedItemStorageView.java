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

import java.util.function.BiFunction;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ValidatedItemStorageView implements IItemHandler {
	private IItemHandler delegate;
	private BiFunction<Integer,ItemStack,Boolean> validator;
	
	public ValidatedItemStorageView(IItemHandler delegate, BiFunction<Integer,ItemStack,Boolean> validator) {
		this.delegate = delegate;
		this.validator = validator;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (!validator.apply(slot, stack)) return stack;
		else return delegate.insertItem(slot, stack, simulate);
	}

	@Override
	public int getSlots() {
		return delegate.getSlots();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return delegate.getStackInSlot(slot);
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return delegate.extractItem(slot, amount, simulate);
	}

	@Override
	public int getSlotLimit(int slot) {
		return delegate.getSlotLimit(slot);
	}
}