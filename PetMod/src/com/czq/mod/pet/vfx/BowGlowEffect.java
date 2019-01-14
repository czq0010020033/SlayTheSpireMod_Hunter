package com.czq.mod.pet.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.czq.mod.pet.weapon.Bow;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class BowGlowEffect extends AbstractGameEffect {
	private static final float DURATION = 2.0F;
	private float scale = 0.0F;
	private static final int IMG_W = 256;

	public BowGlowEffect() {
		this.duration = 2.0F;
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		this.scale = Interpolation.fade.apply(Settings.scale,
				2.0F * Settings.scale, 1.0F - this.duration / 2.0F);
		this.color.a = (Interpolation.fade.apply(0.4F, 0.0F,
				1.0F - this.duration / 2.0F) / 2.0F);
		if (this.duration < 0.0F) {
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb, float x, float y) {
		sb.setBlendFunction(770, 1);
		sb.setColor(this.color);
		float halfWidth = Bow.BOW_GLOW.getWidth() / 2.0F;
		float halfHeight = Bow.BOW_GLOW.getHeight() / 2.0F;
		sb.draw(Bow.BOW_GLOW, x - halfWidth + halfHeight * Settings.scale, y
				- halfHeight + halfHeight * Settings.scale, halfWidth,
				halfHeight, Bow.BOW_GLOW.getWidth(), Bow.BOW_GLOW.getHeight(),
				this.scale, this.scale, 0.0F, 0, 0, 256, 256, false, false);

		sb.setBlendFunction(770, 771);
	}

	public void dispose() {
	}

	public void render(SpriteBatch sb) {
	}
}
