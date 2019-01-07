package com.czq.mod.pet.monsters.pets;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.SetAnimationAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ByrdPet extends Pet {
	public static final String ID = "ByrdPet";
	private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack
			.getMonsterStrings("Byrd");
	public static final String NAME = monsterStrings.NAME;
	public static final String[] MOVES = monsterStrings.MOVES;
	public static final String[] DIALOG = monsterStrings.DIALOG;
	public static final String THREE_BYRDS = "3_Byrds";
	public static final String FOUR_BYRDS = "4_Byrds";
	public static final String IMAGE = "images/monsters/theCity/byrdFlying.png";
	private static final int HP_MIN = 25;
	private static final int HP_MAX = 31;
	private static final int A_2_HP_MIN = 26;
	private static final int A_2_HP_MAX = 33;
	private static final float HB_X_F = 0.0F;
	private static final float HB_X_G = 10.0F;
	private static final float HB_Y_F = 50.0F;
	private static final float HB_Y_G = -50.0F;
	private static final float HB_W = 240.0F;
	private static final float HB_H = 180.0F;
	private static final int PECK_DMG = 1;
	private static final int PECK_COUNT = 5;
	private static final int SWOOP_DMG = 12;
	private static final int A_2_PECK_COUNT = 6;
	private static final int A_2_SWOOP_DMG = 14;
	private int flightAmt;
	private int swoopDmg;
	private static final int HEADBUTT_DMG = 3;
	private static final int CAW_STR = 1;
	private static final byte PECK = 1;
	private static final byte GO_AIRBORNE = 2;
	private static final byte SWOOP = 3;
	private static final byte STUNNED = 4;
	private static final byte HEADBUTT = 5;
	private static final byte CAW = 6;
	private boolean firstMove = true;
	private boolean isFlying = true;
	public static final String FLY_STATE = "FLYING";
	public static final String GROUND_STATE = "GROUNDED";

	public ByrdPet(int mana, float x) {
		this(mana, x, 0);
	}

	public ByrdPet(int mana, float x, float y) {
		super(NAME, "ByrdPet", 10, 0.0F, 50.0F, 240.0F, 180.0F, null, x, y);
		this.drawX = (Settings.WIDTH * x);
		this.drawY = AbstractDungeon.floorY - 20F * Settings.scale;

		setHp(10, 10);
		this.flightAmt = 1;
		this.peckDmg = 3;
		this.peckCount = 3;
		this.swoopDmg = 10;
		this.damage.add(new DamageInfo(this, this.peckDmg));
		this.damage.add(new DamageInfo(this, this.swoopDmg));
		this.damage.add(new DamageInfo(this, 2));

		loadAnimation("images/monsters/theCity/byrd/flying.atlas",
				"images/monsters/theCity/byrd/flying.json", 1.0F);
		AnimationState.TrackEntry e = this.state.setAnimation(0, "idle_flap",
				true);
		e.setTime(e.getEndTime() * MathUtils.random());
	}

	public void afterSpawn() {

		this.addPower(new FlightPower(this, this.flightAmt));
		
	}

	public void takeTurn() {

		AbstractMonster monster;
		switch (this.nextMove) {
		case 10:
			super.takeTurn();
			break;

		case 1:
			monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(
					this, true);
			if (monster != null) {
				AbstractDungeon.actionManager
						.addToBottom(new AnimateFastAttackAction(this));
				for (int i = 0; i < this.peckCount; i++) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(
							monster,

							(DamageInfo) this.damage.get(0),
							AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
				}
			}
			break;
		case 5:
			monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(
					this, true);
			if (monster != null) {
				AbstractDungeon.actionManager
						.addToBottom(new AnimateSlowAttackAction(this));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster,

						(DamageInfo) this.damage.get(2),
						AbstractGameAction.AttackEffect.BLUNT_HEAVY));

				setMove((byte) 2, AbstractMonster.Intent.UNKNOWN);
			}
			return;
		case 2:
			this.isFlying = true;
			AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(
					this, "FLYING"));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
					this, this, new FlightPower(this, 2)));
			break;
		case 6:
			AbstractDungeon.actionManager.addToBottom(new TalkAction(this,
					DIALOG[0], 1.2F, 1.2F));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
					this, this, new StrengthPower(this, 1), 1));

			break;
		case 3:
			monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(
					this, true);
			if (monster != null) {
				AbstractDungeon.actionManager
						.addToBottom(new AnimateSlowAttackAction(this));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster,

						(DamageInfo) this.damage.get(1),
						AbstractGameAction.AttackEffect.SLASH_HEAVY));
			}
			break;
		case 4:
			AbstractDungeon.actionManager.addToBottom(new SetAnimationAction(
					this, "head_lift"));
			AbstractDungeon.actionManager
					.addToBottom(new TextAboveCreatureAction(this,
							TextAboveCreatureAction.TextType.STUNNED));
		}
		AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
	}

	public void changeState(String stateName) {
		AnimationState.TrackEntry e;
		switch (stateName) {
		case "FLYING":
			loadAnimation("images/monsters/theCity/byrd/flying.atlas",
					"images/monsters/theCity/byrd/flying.json", 1.0F);

			e = this.state.setAnimation(0, "idle_flap", true);
			e.setTime(e.getEndTime() * MathUtils.random());
			updateHitbox(0.0F, 50.0F, 240.0F, 180.0F);
			break;
		case "GROUNDED":
			setMove((byte) 4, AbstractMonster.Intent.STUN);
			createIntent();
			this.isFlying = false;
			loadAnimation("images/monsters/theCity/byrd/grounded.atlas",
					"images/monsters/theCity/byrd/grounded.json", 1.0F);

			e = this.state.setAnimation(0, "idle", true);
			e.setTime(e.getEndTime() * MathUtils.random());
			updateHitbox(10.0F, -50.0F, 240.0F, 180.0F);
			break;
		}
	}

	protected void getMove(int num) {
		if (this.isFlying) {
			if (num < 100) {
				super.getMove((byte) num);
			} else {
				setMove((byte) 6, AbstractMonster.Intent.BUFF);
			}
		} else {
			setMove((byte) 5, AbstractMonster.Intent.ATTACK,
					((DamageInfo) this.damage.get(2)).base);
		}
	}

	public void die() {
		super.die();
		CardCrawlGame.sound.play("BYRD_DEATH");
	}

	public void applySpirit(int amount) {
		if (this.peckCount == 3 + amount)
			return;
		this.peckCount = 3 + amount;
		// if(this.nextMove == (byte) 10){
		if (this.isFlying)
		{
			super.getMove((byte) 10);
			createIntent();
		}
		// }

	}
}
