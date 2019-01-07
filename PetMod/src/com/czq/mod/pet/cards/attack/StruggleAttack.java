/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.cards.attack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.cards.status.BloodGiant;
import com.czq.mod.pet.powers.ComboPower;
import com.czq.mod.pet.powers.EaglePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
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
public class StruggleAttack extends AbstractModCard{
	private static final Logger logger = LogManager
			.getLogger(StruggleAttack.class.getName());
	 public static final String ID = "Struggle Attack";
	   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	   public static final String NAME = cardStrings.NAME;
	   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
		public final int BASE_DAMAGE = 5;
	public StruggleAttack() {
		super(ID, NAME, "colorless/skill/finesse", "colorless/skill/finesse",
				2, DESCRIPTION, 
				AbstractCard.CardType.ATTACK, AbstractCardEnum.GIANT_COLOR,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = BASE_DAMAGE;
	}


	/**
	* 描述： 
	* @see com.megacrit.cardcrawl.cards.AbstractCard#makeCopy()
	*/ 
	@Override
	public AbstractCard makeCopy() {
		return new StruggleAttack();
	}

	/**
	* 描述： 
	* @see com.megacrit.cardcrawl.cards.AbstractCard#upgrade()
	*/ 
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(1);
		}
	}

	public void use(AbstractPlayer p, AbstractMonster m)
	  {
		
	    this.baseDamage = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth + BASE_DAMAGE;
	    if(this.baseDamage > 50)
			  this.baseDamage = 50;
	    calculateCardDamage(m);
	    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new BloodGiant(), 1, true, true));
	    
	  }
	  
	  public void applyPowers()
	  {
		  this.baseDamage = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth + BASE_DAMAGE;
		  if(this.baseDamage > 50)
			  this.baseDamage = 50;
	      super.applyPowers();
	  }

}
