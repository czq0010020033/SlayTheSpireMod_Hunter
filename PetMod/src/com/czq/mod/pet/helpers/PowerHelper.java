/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.helpers;



import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;


/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年12月24日/下午6:08:37<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年12月24日/下午6:08:37<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
public class PowerHelper {
	public static boolean hasPower(String id)
	{
		for(AbstractPower p : AbstractDungeon.player.powers)
		{
			if(p.ID.equals(id))
			{
				return true;
			}
		}
		return false;
	}
}
