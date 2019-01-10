package com.czq.mod.pet.monsters.pets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.czq.mod.pet.actions.pet.LightningAction;
import com.czq.mod.pet.cards.pet.DefectPetCall;
import com.czq.mod.pet.helpers.StringConstant;
import com.czq.mod.pet.powers.ManaPower;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BobEffect;

public class DefectPet extends Pet {
	private static final Logger logger = LogManager.getLogger(DefectPet.class
			.getName());
	public static final String ID = "DefectPet";
	private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack
			.getMonsterStrings("DefectPet");
	public static final String NAME = monsterStrings.NAME;
	public static final String[] MOVES = monsterStrings.MOVES;
	public static final String[] DIALOG = monsterStrings.DIALOG;
	private static final int HP_MIN = 48;
	private static final int HP_MAX = 54;
	private static final int A_2_HP_MIN = 50;
	private static final int A_2_HP_MAX = 56;
	private static final float HB_X = -8.0F;
	private static final float HB_Y = 10.0F;
	private static final float HB_W = 230.0F;
	private static final float HB_H = 240.0F;
	private static final int ATTACK_DMG = 6;
	private boolean firstMove = true;
	private boolean saidPower = false;
	private static final int RITUAL_AMT = 3;
	private static final int A_2_RITUAL_AMT = 4;
	private int ritualAmount = 0;
	private static final byte DARK_STRIKE = 1;
	private boolean talky = true;
	protected boolean flipHorizontal = true;

	public static boolean seenDefect = false;
	public static boolean harmonious = true;

	public DefectPet(float x) {
		this(x, 0, true);
	}

	public DefectPet(float x, float y) {
		this(x, y, true);
	}

	private int baseAmount = 6;

	public DefectPet(float x, float y, boolean talk) {
		super(NAME, ID, 10, -8.0F, 10.0F, 240.0F, 244.0F, null, x, y);
		this.drawX = x;
		this.drawY = y;
		setHp(15, 15);
		this.dialogX = (50.0F * Settings.scale);
		this.dialogY = (50.0F * Settings.scale);
		this.damage.add(new DamageInfo(this, 6));
		this.peckCount = 1;
		this.passiveAmount = baseAmount;
		if (Settings.FAST_MODE) {
			this.talky = false;
		}
		loadAnimation("images/characters/defect/idle/skeleton.atlas",
				"images/characters/defect/idle/skeleton.json", 1F);

		AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
		this.stateData.setMix("Hit", "Idle", 0.1F);
		e.setTimeScale(0.9F);
		// e.setTime(e.getEndTime() * MathUtils.random());
	}

	@Override
	public void beforeSpawn() {
		// this.addPower(new RitualPower(this, this.ritualAmount));
	}

	@Override
	public void onSpawn() {
		// this.addPower(new RitualPower(this, this.ritualAmount));
		if (!seenDefect && (!this.hasPower(ManaPower.POWER_ID))) {
			AbstractDungeon.actionManager.addToBottom(new TalkAction(this,
					DIALOG[0], 2.0F, 2.0F));
		}
	/*	else {
			AbstractDungeon.actionManager.addToBottom(new TalkAction(this,
					DIALOG[1], 1.0F, 2.0F));
		}*/
	}

	public void takeTurn() {

		switch (this.nextMove) {
		case 10:
			super.takeTurn();
			break;
		case 1:
			AbstractDungeon.actionManager.addToBottom(new LightningAction(
					new DamageInfo(AbstractDungeon.player, this.passiveAmount,
							DamageInfo.DamageType.THORNS)));

			break;
		case 2:
			AbstractDungeon.actionManager.addToBottom(new LightningAction(
					new DamageInfo(this, this.passiveAmount,
							DamageInfo.DamageType.THORNS)));

			break;
		}
		AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
	}

	public void die() {

		if (!seenDefect && (!this.hasPower(ManaPower.POWER_ID))) {
			AbstractDungeon.actionManager.addToBottom(new TalkAction(this,
					DIALOG[3], 2.0F, 2.0F));
			AbstractDungeon.actionManager.addToBottom(new AddCardToDeckAction(
					CardLibrary.getCard(DefectPetCall.ID).makeCopy()));
		}
		super.die();
	}

	@Override
	public void damage(final DamageInfo info) {
		if ((!seenDefect) && (info != null) && (info.owner != null)
				&& (info.owner == AbstractDungeon.player) && harmonious && (!this.hasPower(ManaPower.POWER_ID))) {
			harmonious = false;
			AbstractDungeon.actionManager.addToBottom(new TalkAction(this,
					DIALOG[2], 2.0F, 2.0F));
			this.passiveAmount += 5;
			this.baseAmount += 5;
			getMove(1);
			createIntent();
		}
		super.damage(info);
	}

	protected void getMove(int num) {
		if (harmonious)
			setMove(MOVES[0], (byte) 1, AbstractMonster.Intent.BUFF);
		else
			setMove(MOVES[0], (byte) 2, AbstractMonster.Intent.BUFF);

	}

	public void applySpirit(int amount) {
		if (this.passiveAmount == baseAmount + amount * 2)
			return;
		this.passiveAmount = baseAmount + amount * 2;
		getMove(1);
		createIntent();
	}

	public void changeTip(PowerTip tip) {

		if (this.intent == Intent.BUFF) {
			if (harmonious)
				tip.body = StringConstant.IntentLighting_1 + this.passiveAmount
						+ StringConstant.IntentLighting_2;
			else
				tip.body = StringConstant.IntentLighting_3 + this.passiveAmount
						+ StringConstant.IntentLighting_4;
		}
	}

	public void renderAmount(SpriteBatch sb, BobEffect bob) {
		if (this.intent == Intent.BUFF) {
			if(harmonious)
			FontHelper.renderFontLeftTopAligned(sb,
					FontHelper.topPanelInfoFont,
					Integer.toString(this.passiveAmount), this.intentHb.cX
							- 30.0f * Settings.scale, this.intentHb.cY + bob.y
							- 12.0f * Settings.scale, Color.WHITE);
			else
				FontHelper.renderFontLeftTopAligned(sb,
						FontHelper.topPanelInfoFont,
						Integer.toString(this.passiveAmount), this.intentHb.cX
								- 30.0f * Settings.scale, this.intentHb.cY + bob.y
								- 12.0f * Settings.scale, Color.RED);
		} 
	}

}
