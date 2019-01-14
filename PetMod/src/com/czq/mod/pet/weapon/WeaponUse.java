package com.czq.mod.pet.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.czq.mod.pet.helpers.LogHelper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WeaponUse {
	/*
	 * private static final TutorialStrings tutorialStrings =
	 * CardCrawlGame.languagePack .getTutorialString("Potion Panel Tip"); public
	 * static final String[] MSG = tutorialStrings.TEXT; public static final
	 * String[] LABEL = tutorialStrings.LABEL; private static final UIStrings
	 * uiStrings = CardCrawlGame.languagePack .getUIString("PotionPopUp");
	 * public static final String[] TEXT = uiStrings.TEXT;
	 */
	private Weapon weapon;
	public boolean isHidden = true;
	public boolean targetMode = false;
	private static final int RAW_W = 282;
	private static final int RAW_H = 286;
	private static final float HB_W = 184.0F;
	private static final float HB_H = 52.0F;
	// private float x;
	// private float y;
	private static final int SEGMENTS = 20;
	private Vector2[] points = new Vector2[20];
	private Vector2 controlPoint;
	private float arrowScale;
	private float arrowScaleTimer = 0.0F;
	private static final float ARROW_TARGET_SCALE = 1.2F;
	private static final int TARGET_ARROW_W = 256;
	private AbstractMonster hoveredMonster = null;
	private boolean autoTargetFirst = false;

	public WeaponUse(Weapon weapon) {
		this.weapon = weapon;
		for (int i = 0; i < this.points.length; i++) {
			this.points[i] = new Vector2();
		}
	}

	/*
	 * public void open() {
	 * 
	 * this.x = weapon.getX() + weapon.getWidth() /2 ; this.y = weapon.getY() +
	 * weapon.getHeight() / 2;
	 * 
	 * }
	 */
	public void update() {
		if (this.targetMode) {
			updateTargetMode();
		}
	}

	private void updateTargetMode() {
		if ((InputHelper.justClickedRight)
				|| (AbstractDungeon.player.hoveredCard != null)) {
			this.targetMode = false;
			com.megacrit.cardcrawl.core.GameCursor.hidden = false;
		}
		this.hoveredMonster = null;
		for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
			if ((m.hb.hovered) && (!m.isDying)) {
				this.hoveredMonster = m;
				break;
			}
		}
		if ((InputHelper.justClickedLeft)) {
			InputHelper.justClickedLeft = false;
			if (this.hoveredMonster != null) {
				LogHelper.info("weapon attack..");
				this.weapon.attack(this.hoveredMonster);
				this.targetMode = false;
				com.megacrit.cardcrawl.core.GameCursor.hidden = false;
			}
		}
	}

	public void render(SpriteBatch sb) {
		if (this.targetMode) {
			if (this.hoveredMonster != null) {
				this.hoveredMonster.renderReticle(sb);
			}
			renderTargetingUi(sb);
		}
	}

	private void renderTargetingUi(SpriteBatch sb) {
		float x = InputHelper.mX;
		float y = InputHelper.mY;
		this.controlPoint = new Vector2(weapon.getX() + weapon.getWidth() / 2,
				weapon.getY() + weapon.getHeight() / 2);
		if (this.hoveredMonster == null) {
			this.arrowScale = Settings.scale;
			this.arrowScaleTimer = 0.0F;
			sb.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
		} else {
			this.arrowScaleTimer += Gdx.graphics.getDeltaTime();
			if (this.arrowScaleTimer > 1.0F) {
				this.arrowScaleTimer = 1.0F;
			}
			this.arrowScale = Interpolation.elasticOut.apply(Settings.scale,
					Settings.scale * 1.2F, this.arrowScaleTimer);

			sb.setColor(new Color(1.0F, 0.2F, 0.3F, 1.0F));
		}
		Vector2 tmp = new Vector2(this.controlPoint.x - x, this.controlPoint.y
				- y);
		tmp.nor();

		drawCurvedLine(sb, new Vector2(weapon.getX() + weapon.getWidth() / 2,
				weapon.getY() + weapon.getHeight() / 2), new Vector2(x, y),
				this.controlPoint);

		sb.draw(ImageMaster.TARGET_UI_ARROW, x - 128.0F, y - 128.0F, 128.0F,
				128.0F, 256.0F, 256.0F, this.arrowScale, this.arrowScale, tmp

				.angle() + 90.0F, 0, 0, 256, 256, false, false);
	}

	private void drawCurvedLine(SpriteBatch sb, Vector2 start, Vector2 end,
			Vector2 control) {
		float radius = 7.0F * Settings.scale;
		for (int i = 0; i < this.points.length - 1; i++) {
			this.points[i] = ((Vector2) Bezier.quadratic(this.points[i],
					i / 20.0F, start, control, end, new Vector2()));
			radius += 0.4F * Settings.scale;
			float angle;
			if (i != 0) {
				Vector2 tmp = new Vector2(this.points[(i - 1)].x
						- this.points[i].x, this.points[(i - 1)].y
						- this.points[i].y);
				angle = tmp.nor().angle() + 90.0F;
			} else {
				Vector2 tmp = new Vector2(this.controlPoint.x
						- this.points[i].x, this.controlPoint.y
						- this.points[i].y);
				angle = tmp.nor().angle() + 270.0F;
			}
			sb.draw(ImageMaster.TARGET_UI_CIRCLE, this.points[i].x - 64.0F,
					this.points[i].y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F,
					radius / 18.0F, radius / 18.0F, angle, 0, 0, 128, 128,
					false, false);
		}
	}
}
