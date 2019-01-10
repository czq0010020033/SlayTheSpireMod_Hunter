/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.patches.monster;

import java.lang.reflect.Field;

import com.czq.mod.pet.monsters.pets.Pet;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

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
@SpirePatch(cls = "com.megacrit.cardcrawl.monsters.AbstractMonster", method = "updateIntentTip")
public class UpdateIntentTipPatch {
	@SpirePostfixPatch
	public static void Postfix(AbstractMonster monster) {
		if (monster != null && (monster instanceof Pet)) {
			Pet pet = (Pet) monster;

			Field cardField = null;
			PowerTip tip = null;
			try {
				cardField = AbstractMonster.class.getDeclaredField("intentTip");
				cardField.setAccessible(true);
				tip = (PowerTip) cardField.get(monster);
				if (tip != null)
					pet.changeTip(tip);

		/*		final Field f_intentTip = AbstractMonster.class
						.getDeclaredField("intentTip");
				f_intentTip.setAccessible(true);
				final Field f_isMultiDamage = AbstractMonster.class
						.getDeclaredField("isMultiDmg");
				f_isMultiDamage.setAccessible(true);
				final Field f_intentDmg = AbstractMonster.class
						.getDeclaredField("intentDmg");
				f_intentDmg.setAccessible(true);
				final Field f_intentMultiAmt = AbstractMonster.class
						.getDeclaredField("intentMultiAmt");
				f_intentMultiAmt.setAccessible(true);
				final PowerTip intentTip = (PowerTip) f_intentTip.get(monster);
				final boolean isMultiDmg = f_isMultiDamage.getBoolean(monster);
				final int intentDmg = f_intentDmg.getInt(monster);
				final int intentMultiAmt = f_intentMultiAmt.getInt(monster);
				final AbstractMonster.Intent intent = monster.intent;

				if (intent == MonsterIntentEnum.ATTACK_PET) {
					intentTip.header = AbstractMonster.TEXT[0];
					if (isMultiDmg) {
						intentTip.body = AbstractMonster.TEXT[1] + intentDmg
								+ AbstractMonster.TEXT[2] + intentMultiAmt
								+ AbstractMonster.TEXT[3];
					} else {
						intentTip.body = AbstractMonster.TEXT[4] + intentDmg
								+ AbstractMonster.TEXT[5];
					}
					// intentTip.img = monster.getAttackIntentTip();
				} else if (intent == MonsterIntentEnum.ATTACK_BUFF_PET) {
					intentTip.header = AbstractMonster.TEXT[6];
					if (isMultiDmg) {
						intentTip.body = AbstractMonster.TEXT[7] + intentDmg
								+ AbstractMonster.TEXT[2] + intentMultiAmt
								+ AbstractMonster.TEXT[8];
					} else {
						intentTip.body = AbstractMonster.TEXT[9] + intentDmg
								+ AbstractMonster.TEXT[5];
					}
					intentTip.img = ImageMaster.INTENT_ATTACK_BUFF;
				} else if (intent == MonsterIntentEnum.ATTACK_DEBUFF_PET) {
					intentTip.header = AbstractMonster.TEXT[10];
					intentTip.body = AbstractMonster.TEXT[11] + intentDmg
							+ AbstractMonster.TEXT[5];
					intentTip.img = ImageMaster.INTENT_ATTACK_DEBUFF;
				} else if (intent == MonsterIntentEnum.ATTACK_DEFEND_PET) {
					intentTip.header = AbstractMonster.TEXT[0];
					if (isMultiDmg) {
						intentTip.body = AbstractMonster.TEXT[12] + intentDmg
								+ AbstractMonster.TEXT[2] + intentMultiAmt
								+ AbstractMonster.TEXT[3];
					} else {
						intentTip.body = AbstractMonster.TEXT[12] + intentDmg
								+ AbstractMonster.TEXT[5];
					}
					intentTip.img = ImageMaster.INTENT_ATTACK_DEFEND;
				}*/
			} catch (NoSuchFieldException | SecurityException
					| IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
	}
}
