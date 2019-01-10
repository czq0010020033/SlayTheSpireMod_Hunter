package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.cards.AbstractComboCard;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.StringConstant;
import com.czq.mod.pet.powers.EaglePower;
import com.czq.mod.pet.powers.UnlimitedComboPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;

public class MeditateGiant
  extends AbstractComboCard
{
  public static final String ID = "Meditate Giant";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 0;
  public static final int LEVEL = 4;
  
  public MeditateGiant()
  {
    super(ID, NAME, "red/skill/defend", COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, LEVEL);
    
    this.baseMagicNumber = 2;
    this.magicNumber = this.baseMagicNumber;
    this.baseBlock = 4;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	  AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
	  AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
      if(this.combo){
    	  AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
      }
  }

  
  public AbstractCard makeCopy()
  {
    return new MeditateGiant();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeBlock(2);
      upgradeMagicNumber(1);
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();
    }
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
		if(this.combo){
			if(this.upgraded)
				 this.rawDescription = UPGRADE_DESCRIPTION + StringConstant.Available;
			else
		      this.rawDescription = DESCRIPTION + StringConstant.Available;
		}
		else{
			if(this.upgraded)
				 this.rawDescription = UPGRADE_DESCRIPTION;
			else
		      this.rawDescription = DESCRIPTION;
		}
		  initializeDescription();
	
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		applyCombo();
		super.calculateCardDamage(mo);
	}

	@Override
	public void applyPowers() {
		applyCombo();
		super.applyPowers();
	}

	

}
