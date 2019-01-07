/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.cards.attack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.cards.status.BloodGiant;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年10月16日/下午6:58:47<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年10月16日/下午6:58:47<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
public class BloodAttack extends AbstractModCard{
	private static final Logger logger = LogManager
			.getLogger(BloodAttack.class.getName());
	 public static final String ID = "Blood Attack";
	   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	   public static final String NAME = cardStrings.NAME;
	   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
		public final int BASE_DAMAGE = 7;
	public BloodAttack() {
		super(ID, NAME, "colorless/skill/finesse", "colorless/skill/finesse",
				0, DESCRIPTION, 
				AbstractCard.CardType.ATTACK, AbstractCardEnum.GIANT_COLOR,
				AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = BASE_DAMAGE;
		this.baseMagicNumber = 1;
		this.magicNumber = this.baseMagicNumber;
		this.exhaust = true;
	}


	/**
	* 描述： 
	* @see com.megacrit.cardcrawl.cards.AbstractCard#makeCopy()
	*/ 
	@Override
	public AbstractCard makeCopy() {
		return new BloodAttack();
	}

	/**
	* 描述： 
	* @see com.megacrit.cardcrawl.cards.AbstractCard#upgrade()
	*/ 
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(2);
		}
	}

	public void use(AbstractPlayer p, AbstractMonster m){

    AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.magicNumber));
    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new BloodGiant(),  this.magicNumber, true, true));
	}
	  
}
