package com.czq.mod.pet.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.czq.mod.pet.helpers.ImageHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RecoverHPPower
  extends AbstractPower
{
  public static final String POWER_ID = "RecoverHP";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  private int damage;
  private static int bombIdOffset;
  public int recover = 0;
  public int initialHP = 0;
  
  public RecoverHPPower(AbstractCreature owner, int turns)
  {
    this.name = NAME;
    this.ID = (POWER_ID + bombIdOffset);
    bombIdOffset += 1;
    this.owner = owner;
    this.amount = turns;
    this.initialHP = owner.currentHealth;
    updateDescription();
    loadRegion(POWER_ID);
  }
  @Override
	public void loadRegion(String id){
		this.region128 = new AtlasRegion(ImageMaster.loadImage(ImageHelper.getPowerImageUrl(id + "_128")), 0, 0, 128, 128);
		this.region48 = new AtlasRegion(ImageMaster.loadImage(ImageHelper.getPowerImageUrl(id + "_48")), 0, 0, 48, 48);
		
	} 
  
  public void atEndOfTurn(boolean isPlayer)
  {
    if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())
    {
    	 this.recover = initialHP - this.owner.currentHealth;
    	if(this.recover < 0)
    		this.recover = 0;
    	AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));
      if (this.amount == 1) {
    	  AbstractDungeon.actionManager.addToBottom(new HealAction(owner, owner, recover));
      }
    }
  }
  @Override
	public int onLoseHp(int damageAmount) {
		if ( owner == null)
			return damageAmount;
		this.recover = initialHP - this.owner.currentHealth + damageAmount;
		if (this.recover < 0)
			this.recover = 0;
		updateDescription();
		return damageAmount;
	}
  
  public void updateDescription()
  {
    if (this.amount == 1) {
      this.description = String.format(DESCRIPTIONS[1], new Object[] { Integer.valueOf(this.recover) });
    } else {
      this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount), Integer.valueOf(this.recover) });
    }
  }
}
