package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.cards.AbstractComboCard;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.StringConstant;
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
import com.megacrit.cardcrawl.powers.BlurPower;

public class Backward
  extends AbstractComboDefendCard
{
  public static final String ID = "Backward Giant";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOCK_AMT = 8;
  public static final int LEVEL = 3;
  
  public Backward()
  {
    super(ID, NAME, "red/skill/defend", COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, LEVEL);
    
    this.baseBlock = 10;
    this.baseMagicNumber = 10;
    this.magicNumber = this.baseMagicNumber;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	  AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
      if(this.combo){
    	  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BlurPower(p, 2), 2));
      }
  }
  
  public void applyCombo() {
		super.applyCombo();
		if(this.combo){
		      this.rawDescription = DESCRIPTION + StringConstant.Available;
		      initializeDescription();
		}
		else{
			  this.rawDescription = DESCRIPTION;
		      initializeDescription();
		}
	}
  
  public AbstractCard makeCopy()
  {
    return new Backward();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeBlock(3);
      upgradeMagicNumber(5);
    }
  }


}
