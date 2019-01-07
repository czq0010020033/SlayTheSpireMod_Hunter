package com.czq.mod.pet.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.czq.mod.pet.cards.AbstractComboCard;
import com.czq.mod.pet.cards.attack.EagleFirst;
import com.czq.mod.pet.cards.attack.EagleSecond;
import com.czq.mod.pet.cards.attack.EagleThird;
import com.czq.mod.pet.cards.attack.FinalFist;
import com.czq.mod.pet.cards.attack.LeftHookFist;
import com.czq.mod.pet.cards.attack.RightHookFist;
import com.czq.mod.pet.helpers.ImageHelper;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class EaglePower extends ComboPower {
	public static final String POWER_ID = "EaglePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack
			.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	
	public EaglePower(AbstractCreature owner) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = 1;
		updateDescription();
		loadRegion(POWER_ID);
	}

	@Override
	public void loadRegion(String id){
		this.region128 = new AtlasRegion(ImageMaster.loadImage(ImageHelper.getPowerImageUrl(id + "_128")), 0, 0, 128, 128);
		this.region48 = new AtlasRegion(ImageMaster.loadImage(ImageHelper.getPowerImageUrl(id + "_48")), 0, 0, 48, 48);
		
	}
	
	@Override
	public void onAfterUseCard(AbstractCard card, UseCardAction action) {
		if(card != null && card instanceof AbstractComboCard)
		{
			AbstractComboCard c = (AbstractComboCard)card;
			if(c.level == this.amount + 1 || hasUnlimitedPower())
			{
				flash();
				this.amount = c.level;
				updateDescription();
		/*		if(this.amount >= 5 && !hasUnlimitedPower())
				{
					AbstractDungeon.actionManager
					.addToBottom(new RemoveSpecificPowerAction(this.owner,
							this.owner, ID));
				}*/
			}
			else if(c.level == this.amount)
			{
			//	flash();
			}
	/*		else if(!hasUnlimitedPower())
			{
				AbstractDungeon.actionManager
				.addToBottom(new RemoveSpecificPowerAction(this.owner,
						this.owner, ID));
			}*/
		}
		
	}

	 public void updateDescription()
	  {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
	  
	  }
	 
	  public void stackPower(int stackAmount)
	  {
	    this.fontScale = 8.0F;
	    this.amount = 1;
	  }
	 
	 
	

	public void atEndOfTurn(boolean isPlayer) {
		AbstractDungeon.actionManager
				.addToBottom(new RemoveSpecificPowerAction(this.owner,
						this.owner, ID));
	}


/*	public float atDamageGive(float damage, DamageInfo.DamageType type)
	  {
		if (type == DamageInfo.DamageType.NORMAL && isCombo) {
			if (amount == 1 || amount == 2 || hasUnlimitedPower())
			{
				damage += increaseDamage;
			}
		}
		return damage;
	  }
	*/
	public  boolean hasUnlimitedPower()
	{
		for (AbstractPower p : AbstractDungeon.player.powers) {
			if(p instanceof UnlimitedComboPower)
				return true;
		}
		return false;
	}
	
}
