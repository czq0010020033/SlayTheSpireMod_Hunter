package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.actions.BloodFuryAction;
import com.czq.mod.pet.cards.AbstractModCustomCard;
import com.megacrit.cardcrawl.actions.unique.BladeFuryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class StormOfBlood
  extends AbstractModCustomCard
{
  public static final String ID = "Storm Of Blood";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 2;
  
  public StormOfBlood()
  {
    super(ID, NAME, "green/skill/storm_of_steel", 2, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE);
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    AbstractDungeon.actionManager.addToBottom(new BloodFuryAction(this.upgraded));
  }
  
  public AbstractCard makeCopy()
  {
    return new StormOfBlood();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();
    }
  }
}
