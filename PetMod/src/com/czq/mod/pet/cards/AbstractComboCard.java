/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.cards;

import com.czq.mod.pet.powers.EaglePower;
import com.czq.mod.pet.powers.UnlimitedComboPower;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2018年10月16日/下午6:55:15<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2018年10月16日/下午6:55:15<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public abstract class AbstractComboCard extends AbstractModCard {

	public int level = 0;
	public boolean combo = false;
	
	public AbstractComboCard(String id, String name, String jokeUrl, String imgUrl,
			int cost, String rawDescription, CardType type, CardColor color,
			CardRarity rarity, CardTarget target, int level) {
		super(id, name, jokeUrl, imgUrl, cost, rawDescription, type, AbstractCardEnum.GIANT_COLOR,
				rarity, target);
		this.level = level;

	}
	
	public AbstractComboCard(String id, String name, String imgUrl,
			int cost, String rawDescription, CardType type, CardColor color,
			CardRarity rarity, CardTarget target, int level) {
		super(id, name, imgUrl,  cost, rawDescription, type,  AbstractCardEnum.GIANT_COLOR,
				rarity, target);
		this.level = level;
	}

	public boolean hasUnlimitedPower() {
		for (AbstractPower p : AbstractDungeon.player.powers) {
			if (p instanceof UnlimitedComboPower)
				return true;
		}
		return false;
	}

	public boolean isCombo() {
		for (AbstractPower p : AbstractDungeon.player.powers) {
			if ((p instanceof EaglePower && p.amount == level - 1)) {
				return true;
			}
		}
		return false;
	}

	public void applyCombo() {
		boolean combo = false;
		for (AbstractPower p : AbstractDungeon.player.powers) {
			if ((p instanceof EaglePower && p.amount == level - 1)
					|| (p instanceof UnlimitedComboPower)) {
				combo = true;
				break;
			}
		}
		this.combo = combo;
		
		
	}

	



}
