package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.actions.BloodPerNonAttackAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.unique.BlockPerNonAttackAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HealWind
  extends AbstractModCard
{
  public static final String ID = "Heal Wind";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOOD_AMOUNT = 3;
  
  public HealWind()
  {
    super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST, DESCRIPTION, AbstractCard.CardType.SKILL,  AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
    
    this.baseMagicNumber = BLOOD_AMOUNT;
    this.magicNumber =   this.baseMagicNumber;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    AbstractDungeon.actionManager.addToBottom(new BloodPerNonAttackAction(this.magicNumber));
  }
  
  public AbstractCard makeCopy()
  {
    return new HealWind();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(2);
    }
  }
}
