/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.czq.mod.pet.helpers.LogHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月11日/上午12:30:53<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月11日/上午12:30:53<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class Bow extends Weapon {
	/**
	 * <p>
	 * 描述:
	 * </p>
	 * 
	 * @param image
	 * @param x
	 * @param y
	 * @param hb_w
	 * @param hb_h
	 *            this.region48 = new
	 *            AtlasRegion(ImageMaster.loadImage(ImageHelper
	 *            .getPowerImageUrl(id + "_48")), 0, 0, 48, 48);
	 */

	public static final Texture BOW = ImageMaster.loadImage("images/giant/ui/weapon/Bow.png");
	public static final Texture BOW_GLOW = ImageMaster.loadImage("images/giant/ui/weapon/Bow_Glow.png");
	public static final Texture BOW_HOVER = ImageMaster.loadImage("images/giant/ui/weapon/Bow_Hover.png");
	public static final Texture BOW_ARROW = ImageMaster.loadImage("images/giant/ui/weapon/Arrow.png");
	public Bow() {
		super(BOW);

	}
	/**
	* 描述： 
	* @see basemod.ClickableUIElement#onUnhover()
	*/ 
	@Override
	protected void onUnhover() {
	}

}
