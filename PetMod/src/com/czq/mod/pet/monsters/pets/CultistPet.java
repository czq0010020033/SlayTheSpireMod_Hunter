package com.czq.mod.pet.monsters.pets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.powers.DeathRattlePower;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RitualPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

public class CultistPet extends Pet {
	private static final Logger logger = LogManager.getLogger(CultistPet.class
			.getName());
	public static final String ID = "CultistPet";
	private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack
			.getMonsterStrings("Cultist");
	public static final String NAME = monsterStrings.NAME;
	public static final String[] MOVES = monsterStrings.MOVES;
	public static final String[] DIALOG = monsterStrings.DIALOG;
	public static final String MURDER_ENCOUNTER_KEY = "Murder of Cultists";
	private static final String INCANTATION_NAME = MOVES[2];
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
	private static final byte INCANTATION = 3;
	private boolean talky = true;
	protected boolean flipHorizontal = true;

	public CultistPet(int mana, float x) {
		this(mana, x, 0, true);
	}

	public CultistPet(int mana, float x, float y, boolean talk) {
		super(NAME, "CultistPet", 10, -8.0F, 10.0F, 230.0F * 2F / 3F,
				240.0F * 2F / 3F, null, x, y);
		this.drawX = (Settings.WIDTH * x);
		this.drawY = AbstractDungeon.floorY - 20F * Settings.scale;
		if (AbstractDungeon.ascensionLevel >= 7) {
			setHp(10, 10);
		} else {
			setHp(10, 10);
		}
		this.dialogX = (-50.0F * Settings.scale * 2F / 3F);
		this.dialogY = (50.0F * Settings.scale * 2F / 3F);
		if (AbstractDungeon.ascensionLevel >= 2) {
			this.ritualAmount = 1;
		} else {
			this.ritualAmount = 1;
		}
		this.damage.add(new DamageInfo(this, 6));
		this.peckCount = 1;
		this.talky = talk;
		if (Settings.FAST_MODE) {
			this.talky = false;
		}
		loadAnimation("images/monsters/theBottom/cultist/skeleton.atlas",
				"images/monsters/theBottom/cultist/skeleton.json", 3F / 2F);

		AnimationState.TrackEntry e = this.state
				.setAnimation(0, "waving", true);

		e.setTime(e.getEndTime() * MathUtils.random());
	}

	@Override
	public void afterSpawn() {
		this.addPower(new RitualPower(this, this.ritualAmount));
	}

	public void takeTurn() {

		switch (this.nextMove) {
		case 10:
			super.takeTurn();
			break;
		case 3:
			int temp = MathUtils.random(1, 10);
			if (this.talky) {
				playSfx();
				if (temp < 4) {
					AbstractDungeon.actionManager.addToBottom(new TalkAction(
							this, DIALOG[0], 1.0F, 2.0F));
					this.saidPower = true;
				} else if (temp < 7) {
					AbstractDungeon.actionManager.addToBottom(new TalkAction(
							this, DIALOG[1], 1.0F, 2.0F));
				}
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
					this, this, new RitualPower(this, this.ritualAmount)));

			break;
		case 1:
			AbstractDungeon.actionManager
					.addToBottom(new AnimateSlowAttackAction(this));

			AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters
					.getRandomMonster(this, true);

			if (monster != null)
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster, (DamageInfo) this.damage.get(0),
						AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

			break;
		}
		AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
	}

	private void playSfx() {
		int roll = MathUtils.random(2);
		if (roll == 0) {
			AbstractDungeon.actionManager.addToBottom(new SFXAction(
					"VO_CULTIST_1A"));
		} else if (roll == 1) {
			AbstractDungeon.actionManager.addToBottom(new SFXAction(
					"VO_CULTIST_1B"));
		} else {
			AbstractDungeon.actionManager.addToBottom(new SFXAction(
					"VO_CULTIST_1C"));
		}
	}

	private void playDeathSfx() {
		int roll = MathUtils.random(2);
		if (roll == 0) {
			CardCrawlGame.sound.play("VO_CULTIST_2A");
		} else if (roll == 1) {
			CardCrawlGame.sound.play("VO_CULTIST_2B");
		} else {
			CardCrawlGame.sound.play("VO_CULTIST_2C");
		}
	}

	public void die() {
		playDeathSfx();
		this.state.setTimeScale(0.1F);
		useShakeAnimation(5.0F);
		if ((this.talky) && (this.saidPower)) {
			AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX
					+ this.dialogX, this.hb.cY + this.dialogY, 2.5F, DIALOG[2],
					false));

			this.deathTimer += 1.5F;
		}
		super.die();
	}

	protected void getMove(int num) {
		logger.info("getMove");
		/*
		 * if (this.firstMove) { this.firstMove = false;
		 * setMove(INCANTATION_NAME, (byte) 3, AbstractMonster.Intent.BUFF);
		 * return; }
		 */
		super.getMove(num);
	}

	public void applySpirit(int amount) {
		int temp = 0;
		if(this.hasPower(RitualPower.POWER_ID))
		{
			temp = this.getPower(RitualPower.POWER_ID).amount;
			if(amount == temp - 1)
				return;
			 this.getPower(RitualPower.POWER_ID).amount = amount + 1;
		//	this.addPower(new RitualPower(this, amount + 1),amount - temp + 1);
		}
			
	}
}
