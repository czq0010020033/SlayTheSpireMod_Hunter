package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.actions.DoubleHpAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.PetHelper;
import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.powers.ManaPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaGrowth
  extends AbstractModCard
{
  public static final String ID = "Mana Growth";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 2;
  
  public ManaGrowth()
  {
    super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST, DESCRIPTION, AbstractCard.CardType.SKILL,  AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);
    this.baseBlock = 10;
    this.baseMagicNumber = 2;
    this.magicNumber = this.baseMagicNumber;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	  AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
	  for(AbstractMonster pet : GiantModHelper.pets)
	  {
		  if(PetHelper.isAlive(pet)){
			  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(pet, pet, new ManaPower(pet, this.magicNumber), this.magicNumber));
		  }
	  }
    
  }
  
  
  
  public AbstractCard makeCopy()
  {
    return new ManaGrowth();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(2);
      upgradeBlock(3);
    }
  }
}
