package com.czq.mod.pet.monsters.pets;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.helpers.StringConstant;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

public class GremlinTsunderePet extends Pet {
	private static final Logger logger = LogManager
			.getLogger(GremlinTsunderePet.class.getName());
	public static final String ID = "GremlinTsunderePet";
	private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack
			.getMonsterStrings("GremlinTsundere");
	public static final String NAME = monsterStrings.NAME;
	public static final String[] MOVES = monsterStrings.MOVES;
	public static final String[] DIALOG = monsterStrings.DIALOG;
	private static final int HP_MIN = 12;
	private static final int HP_MAX = 15;
	private static final int A_2_HP_MIN = 13;
	private static final int A_2_HP_MAX = 17;
	private static final int BLOCK_AMOUNT = 7;
	private static final int A_2_BLOCK_AMOUNT = 8;
	private static final int BASH_DAMAGE = 6;
	private static final int A_2_BASH_DAMAGE = 8;
	public static final int AMOUNT = 6;
	// private int blockAmt;
	private int bashDmg;
	private static final byte PROTECT = 1;
	private static final byte BASH = 2;

	public GremlinTsunderePet(float x) {
		this(x, 0);
	}

	public GremlinTsunderePet(float x, float y) {
		super(NAME, "GremlinTsunderePet", 10, 0.0F, 0.0F, 120.0F, 200.0F, null,
				x, y);
		this.drawX = x;
		this.drawY = y;
		this.dialogY = (60.0F * Settings.scale);
		setHp(10, 10);
		this.blockAmt = AMOUNT;
		this.bashDmg = 6;
		this.damage.add(new DamageInfo(this, this.bashDmg));

		loadAnimation("images/monsters/theBottom/femaleGremlin/skeleton.atlas",
				"images/monsters/theBottom/femaleGremlin/skeleton.json", 1.0F);

		AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
		e.setTime(e.getEndTime() * MathUtils.random());
	}

	public void takeTurn() {
		switch (this.nextMove) {
		case 1:
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(
					AbstractDungeon.player, this, this.blockAmt));
			setMove(MOVES[0], (byte) 1, AbstractMonster.Intent.DEFEND);
			break;
		case 2:
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(
					AbstractDungeon.player, this, this.blockAmt));
			break;
		case 3:
			AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters
					.getRandomMonster(this, true);
			if (monster != null) {
				AbstractDungeon.actionManager
						.addToBottom(new AnimateSlowAttackAction(this));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(
						monster,

						(DamageInfo) this.damage.get(0),
						AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			}
			break;
		case 99:
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

	public void die() {
		super.die();
	}

	public void escapeNext() {
		if ((!this.cannotEscape) && (!this.escapeNext)) {
			this.escapeNext = true;
			AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX,
					this.dialogY, 3.0F, DIALOG[2], false));
		}
	}

	protected void getMove(int num) {
		setMove(MOVES[0], (byte) 1, AbstractMonster.Intent.DEFEND);

	}

	public void applySpirit(int amount) {
		if (this.blockAmt == 6 + amount * 3)
			return;
		this.blockAmt = 6 + amount * 3;
		getMove(1);
		createIntent();
	}

	public void deathReact() {
		if ((this.intent != AbstractMonster.Intent.ESCAPE) && (!this.isDying)) {
			AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX,
					this.dialogY, 3.0F, DIALOG[2], false));
			setMove((byte) 99, AbstractMonster.Intent.ESCAPE);
			createIntent();
		}
	}

	public void changeTip(PowerTip tip) {
		if (this.intent == Intent.DEFEND) {
			// tip.header = AbstractMonster.TEXT[13];
			tip.body = StringConstant.IntentBlock_1 + this.blockAmt
					+ StringConstant.IntentBlock_2;
			// tip.img = ImageMaster.INTENT_DEFEND;
		}
	}

	public void renderAmount(SpriteBatch sb, BobEffect bob) {
		if (this.intent == Intent.DEFEND) {
			FontHelper.renderFontLeftTopAligned(sb,
					FontHelper.topPanelInfoFont,
					Integer.toString(this.blockAmt), this.intentHb.cX - 30.0f
							* Settings.scale, this.intentHb.cY + bob.y - 12.0f
							* Settings.scale, Color.WHITE);
		}
	}

}
