package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.actions.ChangeManaAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractEchoCard;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.StringConstant;
import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.powers.DeathRattleExplodePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DeathExplode
  extends AbstractEchoCard
{
  public static final String ID = "Death Explode";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  
  public DeathExplode()
  {
    super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST, DESCRIPTION, AbstractCard.CardType.SKILL,  AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
    this.baseMagicNumber = 1;
   this.magicNumber = this.baseMagicNumber;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    		   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,m,new DeathRattleExplodePower(m, m.maxHealth *  this.magicNumber), m.maxHealth *  this.magicNumber));
     AbstractDungeon.actionManager.addToBottom(new ChangeManaAction(m, 1));
     super.use(p,m);
  }
  
  public boolean canUse(AbstractPlayer p, AbstractMonster m)
  {
    boolean canUse = super.canUse(p, m);
    if (!canUse) {
      return false;
    }
    if(m != null && m instanceof Pet)
    	return true;
    else
    {
    	this.cantUseMessage = StringConstant.cantUseMessage;
        return false;
    }
  }
  
  
  public AbstractCard makeCopy()
  {
    return makeEchoCopy(new DeathExplode());
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(1);
    }
  }

/**
* 描述： 
* @see com.czq.mod.pet.cards.AbstractEchoCard#refreshDescription()
*/ 
	@Override
	public void refreshDescription() {
		if (exhaust && isEthereal) {
		this.rawDescription = DESCRIPTION + StringConstant.Exhaust + StringConstant.Ethereal;
		this.initializeDescription();
		}
	}
}