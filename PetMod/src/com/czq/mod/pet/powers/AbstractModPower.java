/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.czq.mod.pet.helpers.ImageHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月2日/下午9:29:20<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月2日/下午9:29:20<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public abstract class AbstractModPower extends AbstractPower {
	@Override
	public void loadRegion(String id) {
		this.region128 = new AtlasRegion(ImageMaster.loadImage(ImageHelper
				.getPowerImageUrl(id + "_128")), 0, 0, 128, 128);
		this.region48 = new AtlasRegion(ImageMaster.loadImage(ImageHelper
				.getPowerImageUrl(id + "_48")), 0, 0, 48, 48);

	}
}
