package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.actions.ComboFromDeckToHandAction;
import com.czq.mod.pet.actions.PetFromDeckToHandAction;
import com.czq.mod.pet.cards.AbstractComboCard;
import com.czq.mod.pet.cards.AbstractModCustomCard;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SeekPet
  extends AbstractModCustomCard
{
  public static final String ID = "Seek Pet";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
  
  public SeekPet()
  {
    super(ID, NAME, "colorless/skill/secret_weapon", 0, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
    this.baseMagicNumber = 1;
    this.magicNumber = this.baseMagicNumber;
    this.exhaust = true;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	
    AbstractDungeon.actionManager.addToBottom(new PetFromDeckToHandAction(this.magicNumber));
  }
  
 /* public boolean canUse(AbstractPlayer p, AbstractMonster m)
  {
    boolean canUse = super.canUse(p, m);
    if (!canUse) {
      return false;
    }
    boolean hasAttack = false;
    for (AbstractCard c : p.drawPile.group) {
      if (c instanceof AbstractComboCard) {
        hasAttack = true;
      }
    }
    if (!hasAttack)
    {
      this.cantUseMessage = EXTENDED_DESCRIPTION[0];
      canUse = false;
    }
    return canUse;
  }
  */
  public AbstractCard makeCopy()
  {
    return new SeekPet();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      this.isInnate = true;
      upgradeMagicNumber(1);
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();
    }
  }
}
