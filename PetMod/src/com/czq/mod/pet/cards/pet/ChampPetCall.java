package com.czq.mod.pet.cards.pet;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class ChampPetCall
  extends PetCall
{
  public static final String ID = "ChampPetCall";
 // private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_R");
   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ChampPetCall");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;

  public ChampPetCall()
  {
    //super("CultistPetCall", NAME, "colorless/skill/finesse", "colorless/skill/finesse", 1, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, 0);
    super(ID, NAME, DESCRIPTION, 3, CardColor.COLORLESS, CardRarity.RARE);
   // this.baseBlock = 5;
  }
  
  public AbstractCard makeCopy()
  {
    return new ChampPetCall();
  }
  public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(2);
		}
	}
  
 
}
