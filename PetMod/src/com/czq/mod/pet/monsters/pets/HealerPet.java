package com.czq.mod.pet.monsters.pets;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.StringConstant;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BobEffect;

public class HealerPet extends Pet {
	public static final String ID = "HealerPet";
	private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack
			.getMonsterStrings("Healer");
	public static final String NAME = monsterStrings.NAME;
	public static final String[] MOVES = monsterStrings.MOVES;
	public static final String[] DIALOG = monsterStrings.DIALOG;
	private static final float IDLE_TIMESCALE = 0.8F;
	public static final String ENC_NAME = "HealerTank";
	private static final int HP_MIN = 48;
	private static final int HP_MAX = 56;
	private static final int A_2_HP_MIN = 50;
	private static final int A_2_HP_MAX = 58;
	private static final int MAGIC_DMG = 8;
	private static final int HEAL_AMT = 16;
	private static final int STR_AMOUNT = 2;
	private static final int A_2_MAGIC_DMG = 9;
	private static final int A_2_STR_AMOUNT = 3;
	private int magicDmg;
	private int strAmt;
	private static final byte ATTACK = 1;
	private static final byte HEAL = 2;
	private static final byte BUFF = 3;
	public static int AMOUNT = 6;

	public HealerPet(float x) {
		this(x, 0);
	}

	public HealerPet(float x, float y) {
		super(NAME, "HealerPet", 10, 0.0F, -20.0F, 230.0F, 250.0F, null, x, y);
		this.drawX = x;
		this.drawY = y;
		setHp(10, 10);
		this.magicDmg = 8;
		this.strAmt = 2;
		this.damage.add(new DamageInfo(this, this.magicDmg));
		this.heal = AMOUNT;
		loadAnimation("images/monsters/theCity/healer/skeleton.atlas",
				"images/monsters/theCity/healer/skeleton.json", 1.0F);

		AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
		this.stateData.setMix("Hit", "Idle", 0.2F);
		e.setTime(e.getEndTime() * MathUtils.random());
		this.state.setTimeScale(0.8F);
	}

	public void takeTurn() {
		AbstractMonster monster;
		switch (this.nextMove) {

		case 1:
			monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(
					this, true);
			if (monster != null) {
				playSfx();
				AbstractDungeon.actionManager
						.addToBottom(new AnimateSlowAttackAction(this));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster,

						(DamageInfo) this.damage.get(0),
						AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
						monster, this, new FrailPower(monster, 2, true), 2));
			}
			break;
		case 2:
			playSfx();
			AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(
					this, "STAFF_RAISE"));
			AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
			/*
			 * for (AbstractMonster m : GiantModHelper.pets) { if ((!m.isDying)
			 * && (!m.isEscaping)) {
			 * AbstractDungeon.actionManager.addToBottom(new HealAction(m, this,
			 * heal)); } }
			 */
			AbstractDungeon.actionManager.addToBottom(new HealAction(
					AbstractDungeon.player, this, heal));
			break;
		case 3:
			playSfx();
			AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(
					this, "STAFF_RAISE"));
			AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
			for (AbstractMonster m : GiantModHelper.pets) {
				if ((!m.isDying) && (!m.isEscaping)) {
					AbstractDungeon.actionManager
							.addToBottom(new ApplyPowerAction(m, this,
									new StrengthPower(m, this.strAmt),
									this.strAmt));
				}
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
					AbstractDungeon.player, this, new StrengthPower(
							AbstractDungeon.player, this.strAmt), this.strAmt));
			break;
		}
		AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
	}

	private void playSfx() {
		if (MathUtils.randomBoolean()) {
			AbstractDungeon.actionManager.addToBottom(new SFXAction(
					"VO_HEALER_1A"));
		} else {
			AbstractDungeon.actionManager.addToBottom(new SFXAction(
					"VO_HEALER_1B"));
		}
	}

	private void playDeathSfx() {
		int roll = MathUtils.random(2);
		if (roll == 0) {
			CardCrawlGame.sound.play("VO_HEALER_2A");
		} else if (roll == 1) {
			CardCrawlGame.sound.play("VO_HEALER_2B");
		} else {
			CardCrawlGame.sound.play("VO_HEALER_2C");
		}
	}

	public void changeState(String key) {
		switch (key) {
		case "STAFF_RAISE":
			this.state.setAnimation(0, "Attack", false);
			this.state.setTimeScale(0.8F);
			this.state.addAnimation(0, "Idle", true, 0.0F);
			break;
		}
	}

	protected void getMove(int num) {
		if (num < 100)
			setMove((byte) 2, AbstractMonster.Intent.BUFF);
		else
			setMove((byte) 3, AbstractMonster.Intent.BUFF);
	}

	public void damage(DamageInfo info) {
		super.damage(info);
		if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS)
				&& (info.output > 0)) {
			this.state.setAnimation(0, "Hit", false);
			this.state.setTimeScale(0.8F);
			this.state.addAnimation(0, "Idle", true, 0.0F);
		}
	}

	public void die() {
		playDeathSfx();
		this.state.setTimeScale(0.1F);
		useShakeAnimation(5.0F);
		super.die();
	}

	public void applySpirit(int amount) {
		if (this.heal == 6 + amount * 2) {
			return;
		}
		this.heal = 6 + amount * 2;
		if (this.nextMove == (byte) 2) {
			getMove(1);
			createIntent();
		}
	}

	public void changeTip(PowerTip tip) {
		if (this.intent == Intent.BUFF) {
			tip.body = StringConstant.IntentHeal_1 + this.heal
					+ StringConstant.IntentHeal_2;
		}
	}

	@Override
	public void renderAmount(SpriteBatch sb, BobEffect bob) {
		if (this.intent == Intent.BUFF) {
			FontHelper.renderFontLeftTopAligned(sb,
					FontHelper.topPanelInfoFont,
					Integer.toString(this.heal), this.intentHb.cX - 30.0f
							* Settings.scale, this.intentHb.cY + bob.y
							- 12.0f * Settings.scale, Color.GREEN);
		} 

	}
}
