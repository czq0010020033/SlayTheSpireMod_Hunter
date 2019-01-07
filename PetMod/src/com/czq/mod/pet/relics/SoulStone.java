package com.czq.mod.pet.relics;

import basemod.abstracts.CustomRelic;

import com.czq.mod.pet.powers.GiantSpiritPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SoulStone

extends CustomRelic
{
  public static final String ID = "GiantSoulStone";
  private static final int STR = 1;
  
  public SoulStone()
  {
    super(ID, ImageMaster.loadImage("images/giant/relics/GiantSoulStone.png"), AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.CLINK);
  }
  
  public String getUpdatedDescription()
  {
    return this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1];
  }
  
  public void atBattleStart()
  {
    flash();
    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GiantSpiritPower(AbstractDungeon.player, 1), 1));
    AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
  }
  
  public AbstractRelic makeCopy()
  {
    return new SoulStone();
  }
}
