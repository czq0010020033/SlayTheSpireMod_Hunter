/**      
 * 项目名称：PetMod<br> 
 */
/*package com.czq.mod.pet.weapon;

import java.util.ArrayList;

import basemod.ClickableUIElement;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月11日/下午4:12:09<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月11日/下午4:12:09<br>
 * 修改备注：<br>
 * 版本：1.0
 *
public class WeaponStatus extends ClickableUIElement {

	private Texture img;
	public TextureAtlas.AtlasRegion region48;
	public TextureAtlas.AtlasRegion region128;
	private ArrayList<AbstractGameEffect> effect = new ArrayList();
	private Color greenColor = Color.GREEN;
	private Color redColor = Color.RED;
	private float fontScale = 1.0f;
	protected boolean canGoNegative = false;

	public int amount = 0;

	
	public WeaponStatus(Texture image, float x, float y, float hb_w, float hb_h) {
		super(image, x, y, hb_w, hb_h);

	}

	@Override
	protected void onClick() {
	}

	
	@Override
	protected void onHover() {
	}


	@Override
	protected void onUnhover() {
	}

	public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
		if (this.img != null) {
			sb.setColor(c);
			sb.draw(this.img, x - 12.0F, y - 12.0F, 16.0F, 16.0F, 32.0F, 32.0F,
					Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0,
					32, 32, false, false);
		} else {
			sb.setColor(c);
			sb.draw(this.region48, x - this.region48.packedWidth / 2.0F, y
					- this.region48.packedHeight / 2.0F,
					this.region48.packedWidth / 2.0F,
					this.region48.packedHeight / 2.0F,
					this.region48.packedWidth, this.region48.packedHeight,
					Settings.scale, Settings.scale, 0.0F);
		}
		for (AbstractGameEffect e : this.effect) {
			e.render(sb, x, y);
		}
	}

	public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
		if (this.amount > 0) {
			this.greenColor.a = c.a;
			c = this.greenColor;
			FontHelper.renderFontRightTopAligned(sb,
					FontHelper.powerAmountFont, Integer.toString(this.amount),
					x, y, this.fontScale, c);
		} else if ((this.amount < 0) && (this.canGoNegative)) {
			this.redColor.a = c.a;
			c = this.redColor;
			FontHelper.renderFontRightTopAligned(sb,
					FontHelper.powerAmountFont,
					Integer.toString(this.amount), x, y, this.fontScale, c);
		}
	}
}
*/
