package com.czq.mod.pet.monsters.pets;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class ChampPet extends Pet {
	public static final String ID = "ChampPet";
	private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack
			.getMonsterStrings("Champ");
	public static final String NAME = monsterStrings.NAME;
	public static final String[] MOVES = monsterStrings.MOVES;
	public static final String[] DIALOG = monsterStrings.DIALOG;
	public static final int HP = 420;
	public static final int A_2_HP = 440;
	private static final byte HEAVY_SLASH = 1;
	private static final byte DEFENSIVE_STANCE = 2;
	private static final byte EXECUTE = 3;
	private static final byte FACE_SLAP = 4;
	private static final byte GLOAT = 5;
	private static final byte TAUNT = 6;
	private static final byte ANGER = 7;
	private static final String STANCE_NAME = MOVES[0];
	private static final String EXECUTE_NAME = MOVES[1];
	private static final String SLAP_NAME = MOVES[2];
	public static final int SLASH_DMG = 15;
	public static final int EXECUTE_DMG = 7;
	public static final int SLAP_DMG = 10;
	public static final int A_2_SLASH_DMG = 16;
	public static final int A_2_SLAP_DMG = 12;
	private int slashDmg;
	private int executeDmg;
	private int slapDmg;
	private static final int DEBUFF_AMT = 2;
	private static final int STR_AMT = 2;
	private static final int EXEC_COUNT = 3;
	private static final int FORGE_AMT = 5;
	private static final int BLOCK_AMT = 15;
	private static final int A_2_FORGE_AMT = 6;
	private static final int A_2_BLOCK_AMT = 18;
	private static final int A_2_STR_AMT = 3;
	private int strAmt;
	private int numTurns = 0;
	private int forgeTimes = 0;
	private int forgeThreshold = 2;
	private boolean thresholdReached = false;

	public ChampPet(int mana, float x) {
		super(NAME, "ChampPet", 20, 0.0F, -15.0F, 400.0F * 2F / 3F,
				410.0F * 2F / 3F, null, -90.0F, 0.0F);
		this.drawX = (Settings.WIDTH * x);
		this.drawY = AbstractDungeon.floorY - 20F * Settings.scale;
		this.type = AbstractMonster.EnemyType.NORMAL;
		this.dialogX = (-100.0F * Settings.scale);
		this.dialogY = (10.0F * Settings.scale);

		loadAnimation("images/monsters/theCity/champ/skeleton.atlas",
				"images/monsters/theCity/champ/skeleton.json", 3F / 2F);

		AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
		e.setTime(e.getEndTime() * MathUtils.random());
		e.setTimeScale(0.8F);
		if (AbstractDungeon.ascensionLevel >= 9) {
			setHp(30, 30);
		} else {
			setHp(30, 30);
		}
		if (AbstractDungeon.ascensionLevel >= 4) {
			this.slashDmg = 6;
			this.executeDmg = 3;
			this.slapDmg = 6;
			this.strAmt = 2;
		} else {
			this.slashDmg = 6;
			this.executeDmg = 3;
			this.slapDmg = 6;
			this.strAmt = 2;
		}
		this.damage.add(new DamageInfo(this, this.slashDmg));
		this.damage.add(new DamageInfo(this, this.executeDmg));
		this.damage.add(new DamageInfo(this, this.slapDmg));
	}

	public void takeTurn() {
		AbstractMonster monster;
		switch (this.nextMove) {
		case 7:
			AbstractDungeon.actionManager.addToBottom(new SFXAction(
					"VO_CHAMP_1A"));
			AbstractDungeon.actionManager.addToBottom(new ShoutAction(this,
					getLimitBreak(), 2.0F, 3.0F));
			AbstractDungeon.actionManager.addToBottom(new VFXAction(this,
					new InflameEffect(this), 0.25F));
			AbstractDungeon.actionManager.addToBottom(new VFXAction(this,
					new InflameEffect(this), 0.25F));
			AbstractDungeon.actionManager.addToBottom(new VFXAction(this,
					new InflameEffect(this), 0.25F));
			AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(
					this));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
					this, this, new StrengthPower(this, this.strAmt * 3),
					this.strAmt * 3));

			break;
		case 1:
			monster = AbstractDungeon.getCurrRoom().monsters
					.getRandomMonster(this, true);
			if (monster != null) {
				AbstractDungeon.actionManager
						.addToBottom(new ChangeStateAction(this, "ATTACK"));
				AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster, (DamageInfo) this.damage.get(0),
						AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
			}
			break;
		case 2:
			if (AbstractDungeon.ascensionLevel >= 9) {
				AbstractDungeon.actionManager.addToBottom(new GainBlockAction(
						this, this, 18));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
						this, this, new MetallicizePower(this, 6), 6));
			} else {
				AbstractDungeon.actionManager.addToBottom(new GainBlockAction(
						this, this, 15));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
						this, this, new MetallicizePower(this, 5), 5));
			}
			break;
		case 3:
			monster = AbstractDungeon.getCurrRoom().monsters
					.getRandomMonster(this, true);
			if (monster != null) {
				AbstractDungeon.actionManager
						.addToBottom(new AnimateJumpAction(this));
				AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster, (DamageInfo) this.damage.get(1),
						AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster, (DamageInfo) this.damage.get(1),
						AbstractGameAction.AttackEffect.SLASH_VERTICAL));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster, (DamageInfo) this.damage.get(1),
						AbstractGameAction.AttackEffect.SLASH_HEAVY));
			}
			break;
		case 4:
			monster = AbstractDungeon.getCurrRoom().monsters
					.getRandomMonster(this, true);
			if (monster != null) {
				AbstractDungeon.actionManager
						.addToBottom(new AnimateFastAttackAction(this));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster, (DamageInfo) this.damage.get(2),
						AbstractGameAction.AttackEffect.BLUNT_LIGHT));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
						monster, this, new FrailPower(monster, 2, true), 2));

				AbstractDungeon.actionManager
						.addToBottom(new ApplyPowerAction(monster, this,
								new VulnerablePower(monster, 2, true), 2));
			}
			break;
		case 5:
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
					this, this, new StrengthPower(this, this.strAmt),
					this.strAmt));

			break;
		case 6:
			monster = AbstractDungeon.getCurrRoom().monsters
					.getRandomMonster(this, true);
			if (monster != null) {
				AbstractDungeon.actionManager.addToBottom(new SFXAction(
						"VO_CHAMP_2A"));
				AbstractDungeon.actionManager.addToBottom(new TalkAction(this,
						getTaunt()));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
						monster, this, new WeakPower(monster, 2, true), 2));

				AbstractDungeon.actionManager
						.addToBottom(new ApplyPowerAction(monster, this,
								new VulnerablePower(monster, 2, true), 2));
			}
		}
		AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));

	}

	public void changeState(String key) {
		switch (key) {
		case "ATTACK":
			this.state.setAnimation(0, "Attack", false);
			this.state.addAnimation(0, "Idle", true, 0.0F);
			break;
		}
	}

	public void damage(DamageInfo info) {
		super.damage(info);
		if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS)
				&& (info.output > 0)) {
			this.state.setAnimation(0, "Hit", false);
			this.state.addAnimation(0, "Idle", true, 0.0F);
		}
	}

	private String getTaunt() {
		ArrayList<String> derp = new ArrayList();
		derp.add(DIALOG[0]);
		derp.add(DIALOG[1]);
		derp.add(DIALOG[2]);
		derp.add(DIALOG[3]);
		return (String) derp.get(MathUtils.random(derp.size() - 1));
	}

	private String getLimitBreak() {
		ArrayList<String> derp = new ArrayList();
		derp.add(DIALOG[4]);
		derp.add(DIALOG[5]);
		return (String) derp.get(MathUtils.random(derp.size() - 1));
	}

	private String getDeathQuote() {
		ArrayList<String> derp = new ArrayList();
		derp.add(DIALOG[6]);
		derp.add(DIALOG[7]);
		return (String) derp.get(MathUtils.random(derp.size() - 1));
	}

	protected void getMove(int num) {
		this.numTurns += 1;
		if ((this.currentHealth < this.maxHealth / 2)
				&& (!this.thresholdReached)) {
			this.thresholdReached = true;
			setMove((byte) 7, AbstractMonster.Intent.BUFF);
			return;
		}
		if ((!lastMove((byte) 3)) && (!lastMoveBefore((byte) 3))
				&& (this.thresholdReached)) {
			AbstractDungeon.actionManager.addToTop(new TalkAction(this,
					getDeathQuote(), 2.0F, 2.0F));
			setMove(EXECUTE_NAME, (byte) 3, AbstractMonster.Intent.ATTACK,
					((DamageInfo) this.damage.get(1)).base, 3, true);
			return;
		}
		if ((this.numTurns == 4) && (!this.thresholdReached)) {
			setMove((byte) 6, AbstractMonster.Intent.DEBUFF);
			this.numTurns = 0;
			return;
		}
		if ((!lastMove((byte) 2)) && (this.forgeTimes < this.forgeThreshold)
				&& (num <= 15)) {
			this.forgeTimes += 1;
			setMove(STANCE_NAME, (byte) 2, AbstractMonster.Intent.DEFEND_BUFF);
			return;
		}
		if ((!lastMove((byte) 5)) && (!lastMove((byte) 2)) && (num <= 30)) {
			setMove((byte) 5, AbstractMonster.Intent.BUFF);
			return;
		}
		if ((!lastMove((byte) 4)) && (num <= 55)) {
			setMove(SLAP_NAME, (byte) 4, AbstractMonster.Intent.ATTACK_DEBUFF,
					((DamageInfo) this.damage.get(2)).base);
			return;
		}
		if (!lastMove((byte) 1)) {
			setMove((byte) 1, AbstractMonster.Intent.ATTACK,
					((DamageInfo) this.damage.get(0)).base);
		} else {
			setMove(SLAP_NAME, (byte) 4, AbstractMonster.Intent.ATTACK_DEBUFF,
					((DamageInfo) this.damage.get(2)).base);
		}
	}

	public void die() {
		useFastShakeAnimation(5.0F);
		CardCrawlGame.screenShake.rumble(4.0F);
		this.deathTimer += 1.5F;
		super.die();
		if (MathUtils.randomBoolean()) {
			CardCrawlGame.sound.play("VO_CHAMP_3A");
		} else {
			CardCrawlGame.sound.play("VO_CHAMP_3B");
		}

	}
}
