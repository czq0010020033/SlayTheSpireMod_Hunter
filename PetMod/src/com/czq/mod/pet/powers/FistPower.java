package com.czq.mod.pet.powers;

import com.czq.mod.pet.cards.AbstractComboCard;
import com.czq.mod.pet.cards.attack.FinalFist;
import com.czq.mod.pet.cards.attack.LeftHookFist;
import com.czq.mod.pet.cards.attack.RightHookFist;
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
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class FistPower extends ComboPower {
	public static final String POWER_ID = "FistPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack
			.getPowerStrings(FistPower.POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public FistPower(AbstractCreature owner) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = 1;
		updateDescription();
		loadRegion("amplify");
	}

	
	@Override
	public void onAfterUseCard(AbstractCard card, UseCardAction action) {
		if(card != null && card instanceof AbstractComboCard)
		{
			AbstractComboCard c = (AbstractComboCard)card;
			if(c.level == this.amount + 1)
			{
				flash();
				this.amount++;
				updateDescription();
				if(this.amount == 3)
				{
					AbstractDungeon.actionManager
					.addToBottom(new RemoveSpecificPowerAction(this.owner,
							this.owner, ID));
				}
			}
			else if(c.level == this.amount)
			{
				flash();
			}
			else
			{
				AbstractDungeon.actionManager
				.addToBottom(new RemoveSpecificPowerAction(this.owner,
						this.owner, ID));
			}
		}
/*		if (card != null && card.cardID.equals(RightHookFist.ID)
				&& this.amount == 1) {
		//	flash();
			this.amount++;
			updateDescription();
		}
		else if (card != null && card.cardID.equals(FinalFist.ID)
				&& this.amount == 2) {
		//	flash();
			AbstractDungeon.actionManager
			.addToBottom(new RemoveSpecificPowerAction(this.owner,
					this.owner, ID));
		}
		else if (card != null && card.cardID.equals(LeftHookFist.ID)){
	//		flash();
		}
		else{
			AbstractDungeon.actionManager
			.addToBottom(new RemoveSpecificPowerAction(this.owner,
					this.owner, ID));
		}*/
	}

	 public void updateDescription()
	  {
		if(this.amount > 0 && this.amount < 3)
		this.description = DESCRIPTIONS[this.amount];
	  
	  }
	 
	  public void stackPower(int stackAmount)
	  {
	    this.fontScale = 8.0F;
	    this.amount = 1;
	  }
	 
	 
	 public String getHoverMessage(){
		 return this.name + ":\n" + this.DESCRIPTIONS[0];
	 }

	public void atEndOfTurn(boolean isPlayer) {
		AbstractDungeon.actionManager
				.addToBottom(new RemoveSpecificPowerAction(this.owner,
						this.owner, ID));
	}

	public float atDamageReceive(float damage, DamageInfo.DamageType type) {
		if (type == DamageInfo.DamageType.NORMAL && isCombo) {
			if (amount == 1)
				damage = damage + 5;
			else if (amount == 2)
				damage = damage + 40;
			isCombo = false;
		}
		return damage;
	}
}
