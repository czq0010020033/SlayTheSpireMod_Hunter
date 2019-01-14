package com.czq.mod.pet.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.czq.mod.pet.weapon.Weapon;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class WeaponFlashEffect extends AbstractGameEffect {
	private static final float EFFECT_DUR = 2.0F;
	private float scale;
	private Texture image;

	public WeaponFlashEffect(Weapon weapon, boolean buff) {
		this.duration = 1.0F;
		this.startingDuration = 1.0F;
		this.image = weapon.getImg();
	//	weapon.playApplyWeaponSfx(buff);
		this.scale = Settings.scale;
		this.color = new Color(1.0F, 1.0F, 1.0F, 0.5F);
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration > 0.5F) {
			this.scale = Interpolation.exp5Out.apply(3.0F * Settings.scale,
					Settings.scale, -(this.duration - 2.0F) / 1.5F);
		} else {
			this.color.a = Interpolation.fade.apply(0.5F, 0.0F,
					1.0F - this.duration);
		}
	}

	public void render(SpriteBatch sb, float x, float y) {
		sb.setColor(this.color);
		if (this.image != null) {
			float halfWidth = this.image.getWidth() / 2.0F;
			float halfHeight = this.image.getHeight() / 2.0F;
			sb.draw(this.image, x, y, 0, 0, this.image.getWidth(),
					this.image.getHeight(), Settings.scale * 1.5F,
					Settings.scale * 1.5F, 0.0F, 0, 0, 32, 32, false, false);
		}
		sb.setBlendFunction(770, 1);
		sb.setBlendFunction(770, 771);
	}

	public void dispose() {
	}

	public void render(SpriteBatch sb) {
	}
}
