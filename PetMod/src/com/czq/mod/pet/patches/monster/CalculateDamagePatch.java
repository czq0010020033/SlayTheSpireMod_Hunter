/**      
 * 项目名称：PetMod<br> 
 */
/*package com.czq.mod.pet.patches.monster;

import java.lang.reflect.Field;

import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月10日/上午11:47:34<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月10日/上午11:47:34<br>
 * 修改备注：<br>
 * 版本：1.0
 *
@SpirePatch(cls = "com.megacrit.cardcrawl.monsters.AbstractMonster", method = "calculateDamage", paramtypes = { "int" })
public class CalculateDamagePatch {
	public static SpireReturn Prefix(final AbstractMonster monster, int dmg) {
		try {
			final Field f_intentDmg = AbstractMonster.class
					.getDeclaredField("intentDmg");
			f_intentDmg.setAccessible(true);
			final AbstractMonster.Intent intent = monster.intent;
			if (intent != MonsterIntentEnum.ATTACK_PET
					&& intent != MonsterIntentEnum.ATTACK_BUFF_PET
					&& intent != MonsterIntentEnum.ATTACK_DEBUFF_PET
					&& intent != MonsterIntentEnum.ATTACK_DEFEND_PET) {
				return SpireReturn.Continue();
			}
			AbstractMonster pet = GiantModHelper.getRandomTauntMinion();
			if (pet == null) {
				return SpireReturn.Continue();
			}
			float tmp = dmg;
			for (final AbstractPower p : monster.powers) {
				tmp = p.atDamageGive(tmp, DamageInfo.DamageType.NORMAL);
			}
			for (final AbstractPower p : pet.powers) {
				tmp = p.atDamageReceive(tmp, DamageInfo.DamageType.NORMAL);
			}
			for (final AbstractPower p : monster.powers) {
				tmp = p.atDamageFinalGive(tmp, DamageInfo.DamageType.NORMAL);
			}
			for (final AbstractPower p : pet.powers) {
				tmp = p.atDamageFinalReceive(tmp, DamageInfo.DamageType.NORMAL);
			}
			dmg = MathUtils.floor(tmp);
			if (dmg < 0) {
				dmg = 0;
			}
			f_intentDmg.set(monster, dmg);
			return SpireReturn.Return((Object) null);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return SpireReturn.Continue();
		} catch (IllegalAccessException e2) {
			e2.printStackTrace();
			return SpireReturn.Continue();
		}
	}
}*/