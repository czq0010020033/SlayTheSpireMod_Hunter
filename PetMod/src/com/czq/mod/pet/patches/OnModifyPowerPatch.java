/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.patches;

import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.powers.GiantSpiritPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

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
@SpirePatch(cls = "com.megacrit.cardcrawl.dungeons.AbstractDungeon", method = "onModifyPower")
public class OnModifyPowerPatch {
	@SpirePostfixPatch
	public static void Postfix() {
		for (AbstractMonster pet : GiantModHelper.pets) {
			if (AbstractDungeon.player.hasPower(GiantSpiritPower.POWER_ID))
				((Pet) pet).applySpirit(AbstractDungeon.player
						.getPower(GiantSpiritPower.POWER_ID).amount);
			else
				((Pet) pet).applySpirit(0);
		}
	}
}
