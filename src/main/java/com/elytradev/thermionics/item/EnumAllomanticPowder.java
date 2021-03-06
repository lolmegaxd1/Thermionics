/*
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

package com.elytradev.thermionics.item;

import net.minecraft.util.IStringSerializable;

public enum EnumAllomanticPowder implements IStringSerializable {
	COPPER("copper") //Used on Tasselcloak, grants invisibility in darkness (Mistcloak)
	//TIN("tin")     //Used on Spectacles(?), see through fog and in darkness (??)
	//IRON("iron")   //Used on ???, grants the ability to launch (lurch) dangerously fast towards ferrous blocks
	//STEEL("steel") //Used on ???, grants the ability to push off ferrous blocks. Damages if combined with IRON effect
	//ZINC("zinc")   //Used on Tasselcloak, enrages monsters, ignites creepers, and passively taunts enemies (Stormcloak)
	;

	private final String name;
	EnumAllomanticPowder(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
}
