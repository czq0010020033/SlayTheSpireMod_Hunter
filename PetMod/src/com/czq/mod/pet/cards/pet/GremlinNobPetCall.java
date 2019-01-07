package com.czq.mod.pet.cards.pet;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class GremlinNobPetCall
  extends PetCall
{
  public static final String ID = "GremlinNobPetCall";
 // private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_R");

  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("GremlinNobPetCall");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;

  public GremlinNobPetCall()
  {
    //super("GremlinNobPetCall", NAME, "colorless/skill/finesse", "colorless/skill/finesse", 1, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, 0);
    super(ID, NAME, DESCRIPTION , 3, CardColor.COLORLESS,CardRarity.RARE);
    this.exhaust = true;
   // this.baseBlock = 5;
  }

  
  public AbstractCard makeCopy()
  {
    return new GremlinNobPetCall();
  }
  
  public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(2);
		}
	}
 
}
