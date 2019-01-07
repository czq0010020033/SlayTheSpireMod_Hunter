package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.actions.DoubleHpAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.monsters.pets.Pet;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoubleHp
  extends AbstractModCard
{
  public static final String ID = "Double Hp";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  
  public DoubleHp()
  {
    super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST, DESCRIPTION, AbstractCard.CardType.SKILL,  AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
    
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    AbstractDungeon.actionManager.addToBottom(new DoubleHpAction(m, 1));
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
	    	this.cantUseMessage = "This is not a pet!";
	        return false;
	    }
  }
  
  
  public AbstractCard makeCopy()
  {
    return new DoubleHp();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(1);
    }
  }
}
