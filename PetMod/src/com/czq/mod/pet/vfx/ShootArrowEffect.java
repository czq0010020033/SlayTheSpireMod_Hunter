package com.czq.mod.pet.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ShootArrowEffect extends AbstractGameEffect {
	private float sX;
	private float sY;
	private float tX;
	private float tY;
	int count = 0;
	private float timer = 0.0F;
	private Color color;

	public ShootArrowEffect(float sX, float sY, float tX, float tY, int count,
			Color color) {
		this.sX = sX;
		this.sY = sY;
		this.tX = tX;
		this.tY = tY;
		this.count = count;
		this.color = color;
	    CardCrawlGame.sound.play("ATTACK_DAGGER_1", 0.5F);
	}

	public void update() {
		this.timer -= Gdx.graphics.getDeltaTime();
		if (this.timer < 0.0F) {
			this.timer += MathUtils.random(0.05F, 0.15F);
			AbstractDungeon.effectsQueue.add(new ShootArrowEffectParticle(
					this.sX, this.sY, this.tX, this.tY, this.color));
			this.count -= 1;
			if (this.count == 0) {
				this.isDone = true;
			}
		}
	}

	public void render(SpriteBatch sb) {
	}

	public void dispose() {
	}
}
