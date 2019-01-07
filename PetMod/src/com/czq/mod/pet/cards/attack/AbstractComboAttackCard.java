/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.cards.attack;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractComboCard;
import com.czq.mod.pet.cards.AbstractModCard;
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
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2019年1月2日/下午9:57:53<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2019年1月2日/下午9:57:53<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
public abstract class AbstractComboAttackCard extends AbstractComboCard {
	public AbstractComboAttackCard(String id, String name, String jokeUrl, String imgUrl,
			int cost, String rawDescription, CardType type, CardColor color,
			CardRarity rarity, CardTarget target, int level) {
		super(id, name, jokeUrl, imgUrl, cost, rawDescription, type, color,
				rarity, target, level);

	}
	
	public AbstractComboAttackCard(String id, String name, String imgUrl,
			int cost, String rawDescription, CardType type, CardColor color,
			CardRarity rarity, CardTarget target, int level) {
		super(id, name, imgUrl,  cost, rawDescription, type,  AbstractCardEnum.GIANT_COLOR,
				rarity, target, level);
	}

	@Override
	public void applyPowers() {
		int temp = this.baseDamage;
		applyCombo();

		super.applyPowers();

		/*
		 * for (AbstractPower p : AbstractDungeon.player.powers) { if (p
		 * instanceof EaglePower) { ((EaglePower) p).isCombo = false; } }
		 */
		if (combo && this.level > 1)
			this.isDamageModified = true;
		this.baseDamage = temp;
	}
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		int temp = this.baseDamage;
		applyCombo();
		super.calculateCardDamage(mo);
		if (combo && this.level > 1)
			this.isDamageModified = true;
		this.baseDamage = temp;

	}
	
	public void applyCombo() {
		super.applyCombo();
		if(this.combo)
			this.baseDamage += this.magicNumber;
	
	}
}
