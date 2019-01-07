/**      
 * 项目名称：PetMod<br> 
 */    
/*package com.czq.mod.pet.patches;

import java.lang.reflect.Field;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.czq.mod.pet.monsters.pets.Pet;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年12月29日/下午5:05:25<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年12月29日/下午5:05:25<br>    
 * 修改备注：<br>
 * 版本：1.0
 **
@SpirePatch(cls="com.megacrit.cardcrawl.core.AbstractCreature", method="renderPowerIconsPatch",paramtypez = {
		SpriteBatch.class, float.class, float.class })
public class RenderPowerIconsPatch {
	public static void Replace(AbstractCreature creature, SpriteBatch sb, float x, float y) {
		boolean isPet = false;
		if(creature instanceof Pet)
		{
			isPet = true;
		}
		 Field hbTextColorField;
		 Field POWER_ICON_PADDING_XField;
		 Color hbTextColor = null;
		 float POWER_ICON_PADDING_X = 0;
			try {
				hbTextColorField = creature.getClass().getDeclaredField("hbTextColor");
				hbTextColorField.setAccessible(true);
				hbTextColor = (Color)hbTextColorField.get(creature);
				POWER_ICON_PADDING_XField = creature.getClass().getDeclaredField("POWER_ICON_PADDING_X");
				POWER_ICON_PADDING_XField.setAccessible(true);
				POWER_ICON_PADDING_X = (float)POWER_ICON_PADDING_XField.get(creature);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		   float offset;
		   if(isPet){
			   offset = 2.0F* Settings.scale;
		   }
		   else
			   offset = 10.0F * Settings.scale;
		    for (AbstractPower p : creature.powers)
		    {
		      p.renderIcons(sb, x + offset, y - 48.0F * Settings.scale, hbTextColor);
		      offset += POWER_ICON_PADDING_X;
		    }
		    offset = 0.0F * Settings.scale;
		    for (AbstractPower p : creature.powers)
		    {
		      p.renderAmount(sb, x + offset + 32.0F * Settings.scale, y - 66.0F * Settings.scale, hbTextColor);
		      offset += POWER_ICON_PADDING_X;
		    }
	}
}*/