package com.czq.mod.pet.monsters.pets;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

public class GremlinFatPet extends Pet {
	public static final String ID = "GremlinFatPet";
	private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack
			.getMonsterStrings("GremlinFat");
	public static final String NAME = monsterStrings.NAME;
	public static final String[] MOVES = monsterStrings.MOVES;
	public static final String[] DIALOG = monsterStrings.DIALOG;
	private static final int HP_MIN = 13;
	private static final int HP_MAX = 17;
	private static final int A_2_HP_MIN = 14;
	private static final int A_2_HP_MAX = 18;
	private static final int BLUNT_DAMAGE = 4;
	private static final int A_2_BLUNT_DAMAGE = 5;
	private static final int WEAK_AMT = 1;
	private static final byte BLUNT = 2;

	public GremlinFatPet(int mana, float x) {
		this(mana, x, 0);
	}
	public GremlinFatPet(int mana, float x, float y) {
		super(NAME, "GremlinFatPet", 7, 0.0F, 0.0F, 110.0F, 220.0F, null, x, y);
		this.drawX = (Settings.WIDTH * x);
		this.drawY = AbstractDungeon.floorY - 20F * Settings.scale;
		if (AbstractDungeon.ascensionLevel >= 7) {
			setHp(7, 7);
		} else {
			setHp(7, 7);
		}
		if (AbstractDungeon.ascensionLevel >= 2) {
			this.damage.add(new DamageInfo(this, 4));
		} else {
			this.damage.add(new DamageInfo(this, 4));
		}
		loadAnimation("images/monsters/theBottom/fatGremlin/skeleton.atlas",
				"images/monsters/theBottom/fatGremlin/skeleton.json", 1.0F);

		AnimationState.TrackEntry e = this.state.setAnimation(0, "animation",
				true);
		e.setTime(e.getEndTime() * MathUtils.random());
	}

	public void takeTurn() {
		AbstractMonster monster;
		switch (this.nextMove) {
		case 1:
			monster = AbstractDungeon.getCurrRoom().monsters
					.getRandomMonster(this, true);
			if (monster != null) {
				AbstractDungeon.actionManager
						.addToBottom(new AnimateSlowAttackAction(this));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster, (DamageInfo) this.damage.get(0),
						AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			}
			break;
		case 2:
			monster = AbstractDungeon.getCurrRoom().monsters
					.getRandomMonster(this, true);
			if (monster != null) {
				AbstractDungeon.actionManager
						.addToBottom(new AnimateSlowAttackAction(this));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster,

						(DamageInfo) this.damage.get(0),
						AbstractGameAction.AttackEffect.BLUNT_LIGHT));

				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
						monster, this, new WeakPower(monster, 1, true), 1));
			}
			if (this.escapeNext) {
				AbstractDungeon.actionManager.addToBottom(new SetMoveAction(
						this, (byte) 99, AbstractMonster.Intent.ESCAPE));
			} else {
				AbstractDungeon.actionManager.addToBottom(new RollMoveAction(
						this));
			}
			break;
		case 99:
			playSfx();
			AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX
					+ this.dialogX, this.hb.cY + this.dialogY, 2.5F, DIALOG[1],
					false));

			AbstractDungeon.actionManager.addToBottom(new EscapeAction(this));
			AbstractDungeon.actionManager.addToBottom(new SetMoveAction(this,
					(byte) 99, AbstractMonster.Intent.ESCAPE));
			break;
		}
		AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
	}

	private void playSfx() {
		int roll = MathUtils.random(2);
		if (roll == 0) {
			AbstractDungeon.actionManager.addToBottom(new SFXAction(
					"VO_GREMLINFAT_1A"));
		} else if (roll == 1) {
			AbstractDungeon.actionManager.addToBottom(new SFXAction(
					"VO_GREMLINFAT_1B"));
		} else {
			AbstractDungeon.actionManager.addToBottom(new SFXAction(
					"VO_GREMLINFAT_1C"));
		}
	}

	private void playDeathSfx() {
		int roll = MathUtils.random(2);
		if (roll == 0) {
			CardCrawlGame.sound.play("VO_GREMLINFAT_2A");
		} else if (roll == 1) {
			CardCrawlGame.sound.play("VO_GREMLINFAT_2B");
		} else {
			CardCrawlGame.sound.play("VO_GREMLINFAT_2C");
		}
	}

	public void die() {
		super.die();
		playDeathSfx();
	}

	public void escapeNext() {
		if ((!this.cannotEscape) && (!this.escapeNext)) {
			this.escapeNext = true;
			AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX,
					this.dialogY, 3.0F, DIALOG[2], false));
		}
	}

	protected void getMove(int num) {
		if (num < 50)
			setMove(MOVES[0], (byte) 2, AbstractMonster.Intent.ATTACK_DEBUFF,
					((DamageInfo) this.damage.get(0)).base);
		else
			setMove((byte) 1, AbstractMonster.Intent.ATTACK,
					((DamageInfo) this.damage.get(0)).base);
	}

	public void deathReact() {
		if ((this.intent != AbstractMonster.Intent.ESCAPE) && (!this.isDying)) {
			AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX,
					this.dialogY, 3.0F, DIALOG[2], false));
			setMove((byte) 99, AbstractMonster.Intent.ESCAPE);
			createIntent();
		}
	}
}
