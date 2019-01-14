/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.weapon;

import java.util.ArrayList;
import java.util.Iterator;

import basemod.ClickableUIElement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.helpers.LogHelper;
import com.czq.mod.pet.helpers.MonsterHelper;
import com.czq.mod.pet.powers.PoisonWeaponPower;
import com.czq.mod.pet.powers.VampireWeaponPower;
import com.czq.mod.pet.vfx.BowGlowEffect;
import com.czq.mod.pet.vfx.ShootArrowEffect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月11日/上午12:06:53<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月11日/上午12:06:53<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public abstract class Weapon extends ClickableUIElement {

	private static final UIStrings uiStrings = CardCrawlGame.languagePack
			.getUIString("Weapon Giant");
	private static final String[] TEXT = uiStrings.TEXT;
	private static final String NAME = TEXT[0];
	private static float STATUS_ICON_PADDING_X = 10;
	private Color hbTextColor = Color.WHITE;
	public String id;
	public String name = NAME;
	public String description;
	public int durability = 0;
	public int damage = 0;
	public int time = 0;
	public boolean enabled = false;
	public boolean hidden = true;
	private static final float HIDDEN_INTERVAL = 0.5f;
	private float hidden_timer = HIDDEN_INTERVAL;
	public WeaponUse weaponUse;
	private float fontScale = 1.0f;
	private Color durability_color = Color.WHITE;
	private Color damage_color = Color.WHITE;
	protected Color activeColor = Color.WHITE;
	protected Color inactiveColor = new Color(0.7F, 0.7F, 0.7F, 1.0F);;
	private ArrayList<AbstractGameEffect> effect = new ArrayList<AbstractGameEffect>();

	// public ArrayList<WeaponStatus> statuses = new ArrayList();

	public void clear() {
		this.durability = 0;
		this.damage = 0;
	}

	public void init(int durability, int damage) {
		this.durability += durability;
		this.damage += damage;
	}

	public void init() {
		durability = 2;
		damage = 4;
	}

	/**
	 * <p>
	 * 描述:
	 * </p>
	 * AbstractDungeon.player.drawX + AbstractDungeon.player.hb_w / 2,
	 * AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h
	 * 
	 * @param image
	 * @param x
	 * @param y
	 * @param hb_w
	 * @param hb_h
	 */
	public Weapon(Texture img) {
		super(img, 1640.0F - img.getWidth() / 2, 210.0F + 110f / 2, img
				.getWidth(), img.getHeight());
		weaponUse = new WeaponUse(this);
		this.region = new AtlasRegion(img, 0, 0, img.getWidth(),
				img.getHeight());
	}

	public Texture getImg() {
		return this.image;
	}

	public TextureAtlas.AtlasRegion getRegion() {
		return this.region;
	}

	protected void onHover() {
		if (!this.weaponUse.targetMode) {
			this.description = TEXT[1] + this.durability + TEXT[2]
					+ this.damage + TEXT[3];
			TipHelper.renderGenericTip(this.x - this.image.getWidth()
					* Settings.scale, this.y - 30.0f * Settings.scale, name,
					description);
		}
	}

	protected void onClick() {

		LogHelper.info("weapon onclick.");
		if (!AbstractDungeon.actionManager.turnHasEnded && (this.enabled)
				&& (this.durability > 0) && (this.time > 0)) {
			CardCrawlGame.sound.play("UI_CLICK_1");
			int count = MonsterHelper.getMonstersAliveCount();
			if (count == 0) {
				LogHelper
						.info("error, there are no enemy when weapon onclick.");
			} else if (count == 1) {
				attack(AbstractDungeon.getCurrRoom().monsters
						.getRandomMonster(true));

			} else {
				weaponUse.targetMode = true;
				com.megacrit.cardcrawl.core.GameCursor.hidden = true;
			}
		}

	}

	public void enable() {
		this.time = 2;
		this.enabled = true;
	}

	public void disable() {
		this.enabled = false;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return this.hb_w;
	}

	public float getHeight() {
		return this.hb_h;
	}

	public void attack(AbstractMonster m) {
		if (m != null) {
			AbstractPlayer p = AbstractDungeon.player;
			Color c = Color.WHITE;
			if (p.hasPower(VampireWeaponPower.POWER_ID))
				c = Color.RED;
			if (p.hasPower(PoisonWeaponPower.POWER_ID))
				c = Color.GREEN;
			AbstractDungeon.actionManager.addToBottom(new VFXAction(
					new ShootArrowEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, 6,
							c), 0.15F));

			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
					new DamageInfo(p, this.damage, DamageType.NORMAL),
					AbstractGameAction.AttackEffect.NONE));

			if (p.hasPower(PoisonWeaponPower.POWER_ID) && (this.damage > 0)) {

				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
						m, p, new PoisonPower(m, p, this.damage), this.damage));
				AbstractDungeon.actionManager
						.addToBottom(new ReducePowerAction(p, p, p
								.getPower(PoisonWeaponPower.POWER_ID), 1));
			}
			if (p.hasPower(VampireWeaponPower.POWER_ID) && (this.damage > 0)) {

				AbstractDungeon.actionManager.addToBottom(new HealAction(p, p,
						this.damage));
				AbstractDungeon.actionManager
						.addToBottom(new ReducePowerAction(p, p, p
								.getPower(VampireWeaponPower.POWER_ID), 1));
			}
			// AbstractDungeon.actionManager
			// .addToBottom(new IncreaseWeaponDurabilityAction(-1));
			durability--;
			time--;
		}
	}

	public void update() {
	
		if (!isActive() )
			return;
		super.update();
		updateFlash();
		weaponUse.update();
		if (this.hitbox.justHovered) {
			CardCrawlGame.sound.play("UI_HOVER");
		}
	}

	public void render(SpriteBatch sb) {
		if (!isActive())
			return;
		if (isActive()) {
			if ((this.hitbox.hovered)) {
				if ((InputHelper.isMouseDown)) {
					this.image = Bow.BOW;
				}
				this.image = Bow.BOW_HOVER;
			}
			this.image = Bow.BOW_GLOW;

		} else {
			this.image = Bow.BOW;
		}

		if (isActive()) {
			super.render(sb);
			for (AbstractGameEffect e : this.effect) {
				e.render(sb, x, y);
			}
		} else
			super.render(sb, inactiveColor);

		// renderGlowEffect(sb, this.x, this.y);

		weaponUse.render(sb);
		FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont,
				Integer.toString(this.durability), x + hb_w, y, this.fontScale,
				this.durability_color);
		if (AbstractDungeon.player.hasPower(PoisonWeaponPower.POWER_ID))
			this.damage_color = Color.GREEN;
		else
			this.damage_color = Color.WHITE;
		if (AbstractDungeon.player.hasPower(VampireWeaponPower.POWER_ID))
			this.damage_color = Color.RED;
		FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont,
				Integer.toString(this.damage), x + 14.0f, y, this.fontScale,
				this.damage_color);
	}

	public void playApplyWeaponSfx(boolean buff) {
		int roll = MathUtils.random(0, 2);
		if (buff) {
			if (roll == 0) {
				CardCrawlGame.sound.play("BUFF_1");
			} else if (roll == 1) {
				CardCrawlGame.sound.play("BUFF_2");
			} else {
				CardCrawlGame.sound.play("BUFF_3");
			}
		} else {
			if (roll == 0) {
				CardCrawlGame.sound.play("DEBUFF_1");
			} else if (roll == 1) {
				CardCrawlGame.sound.play("DEBUFF_2");
			} else {
				CardCrawlGame.sound.play("DEBUFF_3");
			}
		}
	}

	public boolean isActive() {
		if (!AbstractDungeon.actionManager.turnHasEnded && (this.enabled)
				&& (this.durability > 0) && (this.time > 0)) {
			return true;
		}
		return false;
	}

	public void flash(boolean buff) {
		this.playApplyWeaponSfx(buff);
		// this.effect.add(new WeaponFlashEffect(this, buff));
	}

	private void updateFlash() {
		for (Iterator<AbstractGameEffect> i = this.effect.iterator(); i
				.hasNext();) {
			AbstractGameEffect e = (AbstractGameEffect) i.next();
			e.update();
			if (e.isDone) {
				i.remove();
			}
		}
	}

	private ArrayList<BowGlowEffect> glowList = new ArrayList();
	private static final float GLOW_INTERVAL = 1.2F;
	private float glowTimer = 0.0F;
	public boolean isGlowing = false;
	public boolean isWarning = false;

	private void renderGlowEffect(SpriteBatch sb, float x, float y) {
		for (BowGlowEffect e : this.glowList) {
			e.render(sb, x, y);
		}
	}
	/*
	 * private void renderWeaponIcons(SpriteBatch sb, float x, float y) { float
	 * offset = 10.0F * Settings.scale; for (WeaponStatus s : this.statuses) {
	 * s.renderIcons(sb, x + offset, y - 48.0F * Settings.scale,
	 * this.hbTextColor); offset += STATUS_ICON_PADDING_X; } offset = 0.0F *
	 * Settings.scale; for (WeaponStatus p : this.statuses) { p.renderAmount(sb,
	 * x + offset + 32.0F * Settings.scale, y - 66.0F Settings.scale,
	 * this.hbTextColor); offset += STATUS_ICON_PADDING_X; } }
	 */
}
