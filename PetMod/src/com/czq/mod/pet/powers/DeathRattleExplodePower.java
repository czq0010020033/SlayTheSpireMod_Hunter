package com.czq.mod.pet.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DeathRattleExplodePower
  extends AbstractPower
{
  public static final String POWER_ID = "DeathRattleExplode";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  public DeathRattleExplodePower(AbstractCreature owner, int amount)
  {
    this.name = NAME;
    this.ID = POWER_ID;
  this.owner = owner;
    this.amount = amount ;
    updateDescription();
    loadRegion("explosive");
  }
  
  public void updateDescription()
  {
    this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] ;
  }
  
  public void onDeath()
  {
	if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
		return;
	}
    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null, 
            
            DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
  }
}
