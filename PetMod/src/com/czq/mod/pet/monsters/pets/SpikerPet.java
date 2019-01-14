package com.czq.mod.pet.monsters.pets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.helpers.StringConstant;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.BobEffect;

public class SpikerPet extends Pet {
	public static final String ID = "SpikerPet";
	private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack
			.getMonsterStrings("Spiker");
	public static final String NAME = monsterStrings.NAME;
	public static final String[] MOVES = monsterStrings.MOVES;
	public static final String[] DIALOG = monsterStrings.DIALOG;
	public static final String ENCOUNTER_NAME = "Ancient Shapes";
	private static final int HP_MIN = 42;
	private static final int HP_MAX = 56;
	private static final int A_2_HP_MIN = 44;
	private static final int A_2_HP_MAX = 60;
	private static final float HB_X = -8.0F;
	private static final float HB_Y = -10.0F;
	private static final float HB_W = 150.0F;
	private static final float HB_H = 150.0F;
	private static final int STARTING_THORNS = 3;
	private static final int A_2_STARTING_THORNS = 4;
	private int startingThorns;
	private static final byte ATTACK = 1;
	private static final int ATTACK_DMG = 7;
	private static final int A_2_ATTACK_DMG = 9;
	private int attackDmg;
	private static final byte BUFF_THORNS = 2;
	private static final int BUFF_AMT = 2;
	private int thornsCount = 0;
	public int thorns;

	public SpikerPet(float x, float y) {
		super(NAME, ID, 56, -8.0F, -10.0F, 150.0F, 150.0F, null, x, y + 10.0F);
		this.drawX = x;
		this.drawY = y;
		loadAnimation("images/monsters/theForest/spiker/skeleton.atlas",
				"images/monsters/theForest/spiker/skeleton.json", 1.0F);

		AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
		e.setTime(e.getEndTime() * MathUtils.random());
		setHp(15, 15);
		this.startingThorns = 3;
		this.thorns = this.startingThorns;
		this.attackDmg = 7;
		this.damage.add(new DamageInfo(this, this.attackDmg));
		this.taunt = true;
	}

	@Override
	public void beforeSpawn() {
		this.addPower(new ThornsPower(this, this.startingThorns));
	}

	public void takeTurn() {
		AbstractDungeon.actionManager.addToBottom(new IncreaseMaxHpAction(this,
				0.2f, true));
	}

	protected void getMove(int num) {
		setMove((byte) 2, AbstractMonster.Intent.BUFF);
	}

	public void changeTip(PowerTip tip) {
		if (this.intent == Intent.BUFF) {
			tip.body = StringConstant.IntentMaxHP_1
					+ MathUtils.round(this.maxHealth * 0.2F)
					+ StringConstant.IntentMaxHP_2;
		}
	}

	public void applySpirit(int amount) {
		int temp = 0;
		if (this.hasPower(ThornsPower.POWER_ID)) {
			temp = this.getPower(ThornsPower.POWER_ID).amount;
			if (temp == startingThorns + amount * 2)
				return;
			this.thorns = startingThorns + amount * 2;
			this.getPower(ThornsPower.POWER_ID).amount = this.thorns;
			// this.addPower(new RitualPower(this, amount + 1),amount - temp +
			// 1);
		}

	}

	@Override
	public void renderAmount(SpriteBatch sb, BobEffect bob) {
		if (this.intent == Intent.BUFF) {
			FontHelper.renderFontLeftTopAligned(sb,
					FontHelper.topPanelInfoFont,
					Integer.toString(MathUtils.round(this.maxHealth * 0.2f)),
					this.intentHb.cX - 30.0f * Settings.scale, this.intentHb.cY
							+ bob.y - 12.0f * Settings.scale, Color.WHITE);
		}

	}
}
