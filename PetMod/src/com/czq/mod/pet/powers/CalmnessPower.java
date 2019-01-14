package com.czq.mod.pet.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CalmnessPower extends AbstractPower {
	public static final String POWER_ID = "Calmness Power";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack
			.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public CalmnessPower(AbstractCreature owner, int drawAmt) {
		this.name = NAME;
		this.ID = "Heatsink";
		this.owner = owner;
		this.amount = drawAmt;
		updateDescription();
		loadRegion("heatsink");
	}

	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (card.type == AbstractCard.CardType.SKILL && card.costForTurn == 0) {
			flash();
			AbstractDungeon.actionManager.addToTop(new DrawCardAction(
					this.owner, this.amount));
		}
	}

	public void stackPower(int stackAmount) {
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}

	public void updateDescription() {
		if (this.amount <= 1) {
			this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		} else {
			this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2]);
		}
	}
}
