/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.patches.monster;

import java.lang.reflect.Field;

import com.czq.mod.pet.helpers.GiantModHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.sun.corba.se.spi.monitoring.MonitoredAttributeInfo;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月10日/上午10:47:05<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月10日/上午10:47:05<br>
 * 修改备注：<br>
 * 版本：1.0
 */
/*@SpirePatch(clz = AbstractMonster.class, method = "setMove", paramtypez = {
		String.class, byte.class, AbstractMonster.Intent.class, int.class,
		int.class, boolean.class })
public class SetMovePatch {
	@SpirePostfixPatch
	public static void Postfix(final AbstractMonster monster,
			final String moveName, final byte nextMove,
			final AbstractMonster.Intent intent, final int baseDamage,
			final int multiplier, final boolean isMultiDamage) {
		Field moveInfo;
		EnemyMoveInfo info = null;
		try {
			moveInfo = AbstractMonster.class.getDeclaredField("move");
			moveInfo.setAccessible(true);
			info = (EnemyMoveInfo) moveInfo.get(monster);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	
		if (info != null && GiantModHelper.hasTauntMinion()) {
			switch (intent) {
			case ATTACK:
				info.intent = MonsterIntentEnum.ATTACK_PET;
				break;
			case ATTACK_BUFF:
				info.intent = MonsterIntentEnum.ATTACK_BUFF_PET;
				break;
			case ATTACK_DEBUFF:
				info.intent = MonsterIntentEnum.ATTACK_DEBUFF_PET;
			case ATTACK_DEFEND:
				info.intent = MonsterIntentEnum.ATTACK_DEFEND_PET;
				break;
			default:
				break;
			}
		}
	}

}
*/