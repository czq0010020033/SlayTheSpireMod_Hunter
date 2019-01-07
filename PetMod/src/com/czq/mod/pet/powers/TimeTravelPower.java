package com.czq.mod.pet.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.czq.mod.pet.helpers.ImageHelper;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TimeTravelPower extends AbstractPower {
	public static final String POWER_ID = "TimeTravel";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack
			.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private int damage;
	private static int bombIdOffset;
	public int initialHP = 0;

	public TimeTravelPower(AbstractCreature owner) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.initialHP = owner.currentHealth;
		this.amount = 0;
		updateDescription();
		loadRegion(POWER_ID);
	}
	
	@Override
	public void loadRegion(String id){
		this.region128 = new AtlasRegion(ImageMaster.loadImage(ImageHelper.getPowerImageUrl(id + "_128")), 0, 0, 128, 128);
		this.region48 = new AtlasRegion(ImageMaster.loadImage(ImageHelper.getPowerImageUrl(id + "_48")), 0, 0, 48, 48);
		
	}
	
	public void onVictory() {
		AbstractPlayer p = AbstractDungeon.player;
		if (p.currentHealth > 0) {
			this.amount = initialHP - this.owner.currentHealth;
			if (this.amount <= 0)
			{
				return;
			}
			p.heal(this.amount);
		}
	}

	@Override
	public int onLoseHp(int damageAmount) {
		if ( owner == null)
			return damageAmount;
		this.amount = initialHP - this.owner.currentHealth + damageAmount;
		if (this.amount < 0)
			this.amount = 0;
		updateDescription();
		return damageAmount;
	}

	public void updateDescription() {
			this.description = String.format(
					DESCRIPTIONS[0],
					new Object[] { Integer.valueOf(this.amount) });
	}
}
