package com.czq.mod.pet.powers;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class DeathRattlePower
  extends AbstractPower
{
  public static final String POWER_ID = "Death Rattle";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  public String applyPowerID;
  public DeathRattlePower(AbstractCreature owner, String powerID, int amount)
  {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    applyPowerID = powerID;
    updateDescription();
    loadRegion("sporeCloud");
  }
  
  public void updateDescription()
  {
    this.description = DESCRIPTIONS[0] + applyPowerID;
  }
  
  public void onDeath()
  {
    if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
      return;
    }
    CardCrawlGame.sound.play("SPORE_CLOUD_RELEASE");
    flashWithoutSound();
    if(applyPowerID.equals(StrengthPower.POWER_ID))
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,  new StrengthPower(AbstractDungeon.player, 3),3));
  }
}
