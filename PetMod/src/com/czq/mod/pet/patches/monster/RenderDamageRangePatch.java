/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.patches.monster;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.powers.DeathRattleExplodePower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.vfx.BobEffect;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月7日/下午8:01:53<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月7日/下午8:01:53<br>
 * 修改备注：<br>
 * 版本：1.0
 */
@SpirePatch(cls = "com.megacrit.cardcrawl.monsters.AbstractMonster", method = "renderDamageRange", paramtypez = { SpriteBatch.class })
public class RenderDamageRangePatch {
	@SpirePostfixPatch
	public static void Postfix(AbstractMonster monster, final SpriteBatch sb) {
		if (monster != null && (monster instanceof Pet)) {
			Pet pet = (Pet) monster;
			Field cardField = null;
			BobEffect bob = null;
			try {
				cardField = AbstractMonster.class.getDeclaredField("bobEffect");
				cardField.setAccessible(true);
				bob = (BobEffect) cardField.get(monster);
			} catch (NoSuchFieldException | SecurityException
					| IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			if (bob != null && sb != null)
				pet.renderAmount(sb, bob);
		}
		
	}
}
