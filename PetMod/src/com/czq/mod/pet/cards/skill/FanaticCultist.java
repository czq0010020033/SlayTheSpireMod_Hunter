package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.actions.DoubleHpAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.PetHelper;
import com.czq.mod.pet.monsters.pets.CultistPet;
import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.powers.ManaPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

public class FanaticCultist
  extends AbstractModCard
{
  public static final String ID = "Fanatic Cultist";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  
  public FanaticCultist()
  {
    super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST, DESCRIPTION, AbstractCard.CardType.SKILL,  AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);
    this.baseMagicNumber = 8;
    this.magicNumber = this.baseMagicNumber;
    this.exhaust = true;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	  for(AbstractMonster pet : GiantModHelper.pets)
	  {
		  if(PetHelper.isAlive(pet) && pet instanceof CultistPet){
			  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(pet, pet, new StrengthPower(pet, this.magicNumber), this.magicNumber));
			  if(pet.hasPower(ManaPower.POWER_ID))
			  {
				  if(pet.getPower(ManaPower.POWER_ID).amount <=2)
				  {
					  AbstractDungeon.actionManager.addToBottom(new VFXAction(new ExplosionSmallEffect(pet.hb.cX, pet.hb.cY), 0.3F));
				      AbstractDungeon.actionManager.addToBottom(new SuicideAction((AbstractMonster)pet));
				  }
				  else
				  AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(pet, pet, ManaPower.POWER_ID, 2));
			  }
			 
		  }
	  }
    
  }
  
  
  
  public AbstractCard makeCopy()
  {
    return new FanaticCultist();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(4);
      this.exhaust = false;
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();
    }
  }
}
