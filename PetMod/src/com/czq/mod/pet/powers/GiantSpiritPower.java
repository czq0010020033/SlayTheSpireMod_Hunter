package com.czq.mod.pet.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.czq.mod.pet.helpers.ImageHelper;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class GiantSpiritPower
  extends AbstractPower
{
  public static final String POWER_ID = "GiantSpirit";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  
  public GiantSpiritPower(AbstractCreature owner, int amount)
  {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    updateDescription();
    loadRegion(POWER_ID);
  }
  
  @Override
	public void loadRegion(String id){
		this.region128 = new AtlasRegion(ImageMaster.loadImage(ImageHelper.getPowerImageUrl(id + "_128")), 0, 0, 128, 128);
		this.region48 = new AtlasRegion(ImageMaster.loadImage(ImageHelper.getPowerImageUrl(id + "_48")), 0, 0, 48, 48);
		
	}
  public void playApplyPowerSfx()
  {
    CardCrawlGame.sound.play("POWER_FOCUS", 0.05F);
  }
  
  public void stackPower(int stackAmount)
  {
    this.fontScale = 8.0F;
    this.amount += stackAmount;
    if (this.amount == 0) {
      AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
  }
  
  public void reducePower(int reduceAmount)
  {
    this.fontScale = 8.0F;
    this.amount -= reduceAmount;
    if (this.amount == 0) {
      AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
  }
  
  public void updateDescription()
  {
      this.description = DESCRIPTIONS[0];
  }
}
