/**      
 * 项目名称：PetMod<br> 
 */
/*package com.czq.mod.pet.patches.player;

import com.czq.mod.pet.helpers.MonsterHelper;
import com.czq.mod.pet.helpers.PetHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月10日/下午5:16:16<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月10日/下午5:16:16<br>
 * 修改备注：<br>
 * 版本：1.0
 *
@SpirePatch(cls = "com.megacrit.cardcrawl.characters.AbstractPlayer", method = "damage", paramtypes = { "com.megacrit.cardcrawl.cards.DamageInfo" })
public class DamagePatch {
	public static SpireReturn Prefix(final AbstractPlayer _instance,
			final DamageInfo info) {
		if (info.owner != null && info.owner instanceof AbstractMonster) {
			final AbstractMonster monster = (AbstractMonster) info.owner;
			AbstractMonster pet = MonsterHelper.getTarget(monster);
			if (PetHelper.isAlive(pet)) {
				AbstractDungeon.actionManager
						.addToBottom((AbstractGameAction) new DamageAction(pet,
								info, AbstractGameAction.AttackEffect.NONE));
				return SpireReturn.Return((Object) null);
			}
		}

		return SpireReturn.Continue();
	}

	/*
	 * private static boolean checkAttackMonsterIntent( final
	 * AbstractMonster.Intent intent) { return intent ==
	 * MonsterIntentEnum.ATTACK_PET || intent ==
	 * MonsterIntentEnum.ATTACK_BUFF_PET || intent ==
	 * MonsterIntentEnum.ATTACK_DEBUFF_PET || intent ==
	 * MonsterIntentEnum.ATTACK_DEFEND_PET; }
	 *

}*/