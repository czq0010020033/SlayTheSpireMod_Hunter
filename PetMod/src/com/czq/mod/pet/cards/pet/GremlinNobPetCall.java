package com.czq.mod.pet.cards.pet;

import com.czq.mod.pet.helpers.StringConstant;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GremlinNobPetCall
  extends PetCall
{
  public static final String ID = "GremlinNobPetCall";
 // private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_R");

  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

  public GremlinNobPetCall()
  {
    //super("GremlinNobPetCall", NAME, "colorless/skill/finesse", "colorless/skill/finesse", 1, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, 0);
	  super(ID, NAME, DESCRIPTION, 2, CardColor.COLORLESS, CardRarity.COMMON);
	    this.exhaust = true;
	    this.baseMagicNumber = 3;
	  	this.magicNumber = this.baseMagicNumber;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
		 AbstractMonster pet = 	CallPet();
			if(pet != null && this.upgraded)
				pet.damage.get(0).base += 2;
}
  
  
  public AbstractCard makeCopy()
  {
    return new GremlinNobPetCall();
  }
  
  public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(2);
			refreshDescription();
		}
	}
  
  public void refreshDescription() {
		if (exhaust && isEthereal) {
			if(this.upgraded)
				this.rawDescription = UPGRADE_DESCRIPTION + StringConstant.Ethereal + StringConstant.Exhaust;
			else
				this.rawDescription = DESCRIPTION + StringConstant.Ethereal + StringConstant.Exhaust;
		}
		else{
			if(this.upgraded)
				this.rawDescription = UPGRADE_DESCRIPTION;
			else
				this.rawDescription = DESCRIPTION ;
		}
		this.initializeDescription();
	}
 
}
