package com.czq.mod.pet.cards.power;

import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.powers.ComboFormPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ComboForm
  extends AbstractModCard
{
  public static final String ID = "Combo Form";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int SHIV_AMT = 1;
  private static final int COST = 1;
  
  public ComboForm()
  {
    super(ID, NAME, "green/power/infinite_blades", 1, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ComboFormPower(p)));
  }
  
  public AbstractCard makeCopy()
  {
    return new ComboForm();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      this.isInnate = true;
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();
    }
  }
}
