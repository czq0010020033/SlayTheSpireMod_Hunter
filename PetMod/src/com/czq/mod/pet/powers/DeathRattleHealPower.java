package com.czq.mod.pet.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.czq.mod.pet.helpers.ImageHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DeathRattleHealPower extends AbstractPower {
	public static final String POWER_ID = "DeathRattleHeal";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack
			.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public DeathRattleHealPower(AbstractCreature owner, int amount) {
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
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
	}

	public void onDeath() {
		if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
			return;
		}
		AbstractDungeon.actionManager.addToBottom(new HealAction(
				AbstractDungeon.player, AbstractDungeon.player, amount));

	}
}
