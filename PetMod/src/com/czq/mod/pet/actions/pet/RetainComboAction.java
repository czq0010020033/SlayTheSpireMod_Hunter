/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.actions.pet;

import com.czq.mod.pet.cards.AbstractComboCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2018年12月24日/下午3:41:25<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2018年12月24日/下午3:41:25<br>
 * 修改备注：<br>
 * 版本：1.0
 */

public class RetainComboAction extends AbstractGameAction {
	private static final UIStrings uiStrings = CardCrawlGame.languagePack
			.getUIString("RetainCardsAction");
	public static final String[] TEXT = uiStrings.TEXT;

	public RetainComboAction(AbstractCreature source) {
		setValues(AbstractDungeon.player, source);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}

	public void update() {

		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			if (!c.isEthereal && c instanceof AbstractComboCard) {
				c.retain = true;
			}
		//	AbstractDungeon.player.hand.addToTop(c);
		}
		

		tickDuration();
	}
}
