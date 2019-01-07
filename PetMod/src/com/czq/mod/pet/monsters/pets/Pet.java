package com.czq.mod.pet.monsters.pets;

import java.lang.reflect.Field;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class Pet extends AbstractMonster {

	public static float LEFT_X = 0.13F;
	public static float RIGHT_X = 0.38F;
	protected int mana = 0;
	public int peckDmg;
	public int peckCount;

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
		if (this.skeleton != null && !this.skeleton.getFlipX()) {
			this.skeleton.setFlipX(true);
		}
		if (!flipHorizontal)
			flipHorizontal = true;
		super.update();
	}

	public void render(SpriteBatch sb) {
		if (this.skeleton != null && !this.skeleton.getFlipX()) {
			this.skeleton.setFlipX(true);
		}
		if (!flipHorizontal)
			flipHorizontal = true;
		super.render(sb);
	}

	public void afterSpawn() {

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

	private void renderPowerIconsPatch(SpriteBatch sb, float x, float y) {
		Field hbTextColorField;
		Field POWER_ICON_PADDING_XField;
		Color hbTextColor = null;
		float POWER_ICON_PADDING_X = 0;
		try {
			hbTextColorField = AbstractCreature.class.getDeclaredField("hbTextColor");
			hbTextColorField.setAccessible(true);
			hbTextColor = (Color) hbTextColorField.get(this);
			POWER_ICON_PADDING_XField =  AbstractCreature.class.getDeclaredField(
					"POWER_ICON_PADDING_X");
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
}
