/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.patches;

import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.powers.DeathRattleExplodePower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.AbstractCreature;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2018年12月28日/下午10:21:43<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2018年12月28日/下午10:21:43<br>
 * 修改备注：<br>
 * 版本：1.0
 */
@SpirePatch(cls = "com.megacrit.cardcrawl.core.AbstractCreature", method = "increaseMaxHp", paramtypez = {
		int.class, boolean.class })
public class IncreaseMaxHpPatch {
	@SpirePostfixPatch
	public static void Postfix(AbstractCreature creature, int amount, boolean b) {
		if (creature instanceof Pet) {
			Pet pet = (Pet) creature;
			if (pet.hasPower(DeathRattleExplodePower.POWER_ID)) {
				pet.getPower(DeathRattleExplodePower.POWER_ID).updateDescription();
			}
		}
	}
}
