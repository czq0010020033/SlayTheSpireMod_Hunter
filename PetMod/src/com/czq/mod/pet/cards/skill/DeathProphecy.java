package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.PetHelper;
import com.czq.mod.pet.monsters.pets.Pet;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DeathProphecy
  extends AbstractModCard
{
  public static final String ID = "Death Prophecy";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 0;
  
  public DeathProphecy()
  {
    super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST, DESCRIPTION, AbstractCard.CardType.SKILL,  AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	  if((!this.upgraded) && m != null){ 
	 for(AbstractPower power : m.powers){
		 power.onDeath();
	 }
	  }else
	  {
		  for(AbstractMonster pet : GiantModHelper.pets){
			  if(PetHelper.isAlive(pet)){
				  for(AbstractPower power : pet.powers){
						 power.onDeath();
					 } 
			  }
		  }
	  }
  }
  
  public boolean canUse(AbstractPlayer p, AbstractMonster m)
  {
	  boolean canUse = super.canUse(p, m);
	    if (!canUse) {
	      return false;
	    }
	    if(this.upgraded)
	    	return true;
	    
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
    return new DeathProphecy();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      this.target = CardTarget.NONE;
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();
      
    }
  }
}
