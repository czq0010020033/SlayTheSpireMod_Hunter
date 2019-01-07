package com.czq.mod.pet.cards.pet;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class GremlinFatPetCall
  extends PetCall
{
  public static final String ID = "GremlinFatPetCall";
 // private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_R");
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("GremlinFatPetCall");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;


  public GremlinFatPetCall()
  {
    //super("CultistPetCall", NAME, "colorless/skill/finesse", "colorless/skill/finesse", 1, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, 0);
    super(ID, NAME, DESCRIPTION, 2,CardColor.COLORLESS,CardRarity.COMMON);
   // this.baseBlock = 5;
  }
  
  public AbstractCard makeCopy()
  {
    return new GremlinFatPetCall();
  }

/**
* 描述： 
* @see com.megacrit.cardcrawl.cards.AbstractCard#upgrade()
*/ 
@Override
public void upgrade() {
}
  
  
 
}
