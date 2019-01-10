/**      
 * 项目名称：PetMod<br> 
 */
/*package com.czq.mod.pet.patches;

import com.czq.mod.pet.helpers.MonsterHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月10日/下午6:29:34<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月10日/下午6:29:34<br>
 * 修改备注：<br>
 * 版本：1.0
 *
@SpirePatch(cls = "com.megacrit.cardcrawl.actions.common.DamageAction", method = SpirePatch.CONSTRUCTOR, paramtypez = {
		AbstractCreature.class, DamageInfo.class,
		AbstractGameAction.AttackEffect.class })
public class DamageActionPatch {
	@SpirePostfixPatch
	public static void Postfix(DamageAction action, AbstractCreature creature,
			DamageInfo info, AbstractGameAction.AttackEffect effect) {
		if ((creature != null)
				&& (creature instanceof AbstractPlayer)
				&& (info != null)
				&& (info.owner != null)
				&& (info.owner instanceof AbstractMonster)
				&& (MonsterHelper.getTarget((AbstractMonster) info.owner) != null)) {
			
			action.target = MonsterHelper.getTarget((AbstractMonster) info.owner) ;
		}
	}
}*/