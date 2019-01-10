package com.czq.mod.pet.cards.power;

import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.powers.BeastMasterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DemonFormPower;

public class BeastMaster
  extends AbstractModCard
{
  public static final String ID = "Beast Master";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 3;
  
  public BeastMaster()
  {
    super(ID, NAME, "red/power/demon_form", COST, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.RED, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE);
    
    this.baseMagicNumber = 1;
    this.magicNumber = this.baseMagicNumber;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BeastMasterPower(p, this.magicNumber), this.magicNumber));
  }
  
  public AbstractCard makeCopy()
  {
    return new BeastMaster();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(1);
    }
  }
}
