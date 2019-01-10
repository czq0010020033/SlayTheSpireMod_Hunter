package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractEchoCard;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.PetHelper;
import com.czq.mod.pet.helpers.StringConstant;
import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.powers.ManaPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class UpUpUp
  extends AbstractEchoCard
{
  public static final String ID = "Up Up Up";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  
  public UpUpUp()
  {
    super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST, DESCRIPTION, AbstractCard.CardType.SKILL,  AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
    this.baseMagicNumber = 1;
    this.magicNumber =  this.baseMagicNumber;
    this.exhaust = true;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new StrengthPower(m, this.magicNumber),this.magicNumber));
     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new ManaPower(m, this.magicNumber),this.magicNumber));
     super.use(p, m);
  }
  
  public boolean canUse(AbstractPlayer p, AbstractMonster m)
  {
	  boolean canUse = super.canUse(p, m);
	    if (!canUse) {
	      return false;
	    }
	    if(m != null && m instanceof Pet)
	    	return true;
	    else
	    {
	    	this.cantUseMessage = StringConstant.cantUseMessage;
	        return false;
	    }
  }
  
  
  public AbstractCard makeCopy()
  {
    return makeEchoCopy(new UpUpUp()) ;
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(1);
    }
  }

/**
* 描述： 
* @see com.czq.mod.pet.cards.AbstractEchoCard#refreshDescription()
*/ 
@Override
public void refreshDescription() {
		if (exhaust && isEthereal) {
			this.rawDescription = DESCRIPTION + StringConstant.Ethereal;
			this.initializeDescription();
		}
		else{
			this.rawDescription = DESCRIPTION;
			this.initializeDescription();
		}

}
}
