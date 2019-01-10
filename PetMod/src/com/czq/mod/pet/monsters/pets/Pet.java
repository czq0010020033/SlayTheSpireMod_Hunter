package com.czq.mod.pet.monsters.pets;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.czq.mod.pet.helpers.StringConstant;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BobEffect;

public abstract class Pet extends AbstractMonster {
	private static final Logger logger = LogManager.getLogger(Pet.class
			.getName());
	public static float LEFT_X = 0.13F;
	public static float RIGHT_X = 0.38F;
	protected int mana = 0;
	public int peckDmg;
	public int peckCount;
	public int blockAmt = 0;
	public int heal = 0;
	public int passiveAmount = 0;

	/**
	 * <p>
	 * 描述:
	 * </p>
	 * 
	 * @param name
	 * @param id
	 * @param maxHealth
	 * @param hb_x
	 * @param hb_y
	 * @param hb_w
	 * @param hb_h
	 * @param imgUrl
	 */
	public Pet(String name, String id, int maxHealth, float hb_x, float hb_y,
			float hb_w, float hb_h, String imgUrl) {

		super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl);
		this.peckDmg = 6;
		this.peckCount = 1;

	}

	/**
	 * <p>
	 * 描述:
	 * </p>
	 * 
	 * @param name
	 * @param id
	 * @param maxHealth
	 * @param hb_x
	 * @param hb_y
	 * @param hb_w
	 * @param hb_h
	 * @param imgUrl
	 * @param offsetX
	 * @param offsetY
	 */
	public Pet(String name, String id, int maxHealth, float hb_x, float hb_y,
			float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY) {
		super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX,
				offsetY);

	}

	@Override
	protected void updateFastAttackAnimation() {
		this.animationTimer -= Gdx.graphics.getDeltaTime();
		float targetPos = 90.0F * Settings.scale;

		if (this.animationTimer > 0.5F) {
			this.animX = Interpolation.exp5In.apply(0.0F, targetPos,
					(1.0F - this.animationTimer / 1.0F) * 2.0F);
		} else if (this.animationTimer < 0.0F) {
			this.animationTimer = 0.0F;
			this.animX = 0.0F;
		} else {
			this.animX = Interpolation.fade.apply(0.0F, targetPos,
					this.animationTimer / 1.0F * 2.0F);
		}
	}

	protected void updateSlowAttackAnimation() {
		this.animationTimer -= Gdx.graphics.getDeltaTime();
		float targetPos = 90.0F * Settings.scale;

		if (this.animationTimer > 0.5F) {
			this.animX = Interpolation.exp10In.apply(0.0F, targetPos,
					(1.0F - this.animationTimer / 1.0F) * 2.0F);
		} else if (this.animationTimer < 0.0F) {
			this.animationTimer = 0.0F;
			this.animX = 0.0F;
		} else {
			this.animX = Interpolation.fade.apply(0.0F, targetPos,
					this.animationTimer / 1.0F * 2.0F);
		}
	}

	public void update() {
		if (this.skeleton != null && !this.skeleton.getFlipX() && (!(this instanceof DefectPet))) {
			this.skeleton.setFlipX(true);
		}
		if (!flipHorizontal  && (!(this instanceof DefectPet)))
			flipHorizontal = true;
		super.update();
	}

	public void render(SpriteBatch sb) {
		if (this.skeleton != null && !this.skeleton.getFlipX()   && (!(this instanceof DefectPet))) {
			this.skeleton.setFlipX(true);
		}
		if (!flipHorizontal  && (!(this instanceof DefectPet)))
			flipHorizontal = true;
		super.render(sb);
	}

	public void beforeSpawn() {

	}
	
	public void onSpawn(){
		
	}

	public void takeTurn() {
		AbstractMonster monster;
		if (this.nextMove == (byte) 10) {

			monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(
					this, true);
			if (monster != null) {
				if (peckCount > 1) {
					AbstractDungeon.actionManager
							.addToBottom(new AnimateFastAttackAction(this));
					for (int i = 0; i < this.peckCount; i++) {
						AbstractDungeon.actionManager
								.addToBottom(new DamageAction(
										monster,

										(DamageInfo) this.damage.get(0),
										AbstractGameAction.AttackEffect.BLUNT_LIGHT,
										true));
					}
				} else {
					AbstractDungeon.actionManager
							.addToBottom(new AnimateSlowAttackAction(this));
					AbstractDungeon.actionManager.addToBottom(new DamageAction(
							monster, (DamageInfo) this.damage.get(0),
							AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
				}
			}
		}
		// AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
	}

	protected void getMove(int num) {
		if (peckCount > 1) {
			setMove((byte) 10, AbstractMonster.Intent.ATTACK,
					((DamageInfo) this.damage.get(0)).base, this.peckCount,
					true);
		} else {
			setMove((byte) 10, AbstractMonster.Intent.ATTACK,
					((DamageInfo) this.damage.get(0)).base);
		}
	}

	public void applySpirit(int amount) {

	}

	
	public void changeTip(PowerTip tip){
	
	}
	
	public void renderAmount(SpriteBatch sb, BobEffect bob){
		
	}
/*	private void renderPowerIconsPatch(SpriteBatch sb, float x, float y) {
		logger.info("renderPowerIconsPatch");
		Field hbTextColorField;
		Field POWER_ICON_PADDING_XField;
		Color hbTextColor = null;
		float POWER_ICON_PADDING_X = 0;
		try {
			hbTextColorField = AbstractCreature.class
					.getDeclaredField("hbTextColor");
			hbTextColorField.setAccessible(true);
			hbTextColor = (Color) hbTextColorField.get(this);
			POWER_ICON_PADDING_XField = AbstractCreature.class
					.getDeclaredField("POWER_ICON_PADDING_X");
			POWER_ICON_PADDING_XField.setAccessible(true);
			POWER_ICON_PADDING_X = (float) POWER_ICON_PADDING_XField.get(this);
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		float offset;
		offset = 2.0F * Settings.scale;
		for (AbstractPower p : this.powers) {
			p.renderIcons(sb, x + offset, y - 48.0F * Settings.scale,
					hbTextColor);
			offset += POWER_ICON_PADDING_X;
		}
		offset = 0.0F * Settings.scale;
		for (AbstractPower p : this.powers) {
			p.renderAmount(sb, x + offset + 32.0F * Settings.scale, y - 66.0F
					* Settings.scale, hbTextColor);
			offset += POWER_ICON_PADDING_X;
		}
	}


*/
	/*public void renderTip(final SpriteBatch sb) {
		final ArrayList<PowerTip> tips = new ArrayList<PowerTip>();
		if (this.intentAlphaTarget == 1.0f && this.intent != Intent.NONE) {

			Field cardField = null;
			PowerTip tip = null;
			try {
				cardField = AbstractMonster.class.getDeclaredField("intentTip");
				cardField.setAccessible(true);
				tips.add((PowerTip) cardField.get(this));
			} catch (NoSuchFieldException | SecurityException
					| IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}

		}
		for (final AbstractPower p : this.powers) {
			if (p.region48 != null) {
				tips.add(new PowerTip(p.name, p.description, p.region48));
			} else {
				tips.add(new PowerTip(p.name, p.description, p.img));
			}
		}
		if (!tips.isEmpty()) {
			final float offsetY = (tips.size() - 1)
					* AbstractMonster.MULTI_TIP_Y_OFFSET
					+ AbstractMonster.TIP_OFFSET_Y;
			if (this.hb.cX + this.hb.width / 2.0f < AbstractMonster.TIP_X_THRESHOLD) {
				TipHelper.queuePowerTips(this.hb.cX + this.hb.width / 2.0f
						+ AbstractMonster.TIP_OFFSET_R_X, this.hb.cY + offsetY,
						tips);
			} else {
				TipHelper.queuePowerTips(this.hb.cX - this.hb.width / 2.0f
						+ AbstractMonster.TIP_OFFSET_L_X, this.hb.cY + offsetY,
						tips);
			}
		}
	}
*/
	
	
}