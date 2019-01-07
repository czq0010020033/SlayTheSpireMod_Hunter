package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.cards.AbstractComboCard;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.powers.EaglePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ComboDefend
  extends AbstractComboDefendCard
{
  public static final String ID = "Combo Defend";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 0;
  private static final int BLOCK_AMT = 5;
  
  public ComboDefend()
  {
    super(ID, NAME, "red/skill/defend", COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, 1);
    
    this.baseBlock = 5;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
 
      AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EaglePower(p)));
  }
  
  public AbstractCard makeCopy()
  {
    return new ComboDefend();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeBlock(3);
    }
  }
  

}
