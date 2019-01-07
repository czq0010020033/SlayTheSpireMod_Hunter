package com.czq.mod.pet.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.czq.mod.pet.cards.attack.FirstCombo;
import com.czq.mod.pet.helpers.ImageHelper;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.colorless.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class InfiniteFirstPower
  extends AbstractModPower
{
  public static final String POWER_ID = "Infinite First";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  
  public InfiniteFirstPower(AbstractCreature owner, int bladeAmt)
  {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = bladeAmt;
    updateDescription();
    loadRegion(POWER_ID);
  }
  
  

  
  public void atStartOfTurn()
  {
    if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())
    {
      flash();
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new FirstCombo(), this.amount, false));
    }
  }
  
  public void stackPower(int stackAmount)
  {
    this.fontScale = 8.0F;
    this.amount += stackAmount;
  }
  
  public void updateDescription()
  {
    if (this.amount > 1) {
      this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    } else {
      this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2]);
    }
  }
}
