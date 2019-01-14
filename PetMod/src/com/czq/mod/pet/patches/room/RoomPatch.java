/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.patches.room;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.czq.mod.pet.characters.Giant;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月11日/上午12:59:54<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月11日/上午12:59:54<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class RoomPatch {
	@SpirePatch(cls = "com.megacrit.cardcrawl.rooms.AbstractRoom", method = "update")
	public static class UpdatePatch {
		@SpirePostfixPatch
		public static void Postfix(AbstractRoom room) {
			if ((room.phase == RoomPhase.COMBAT)
					&& (GiantModHelper.weapon != null)
					&& (AbstractDungeon.player instanceof Giant))
				GiantModHelper.weapon.update();
		}
	}

	@SpirePatch(cls = "com.megacrit.cardcrawl.rooms.AbstractRoom", method = "render", paramtypez = { SpriteBatch.class })
	public static class RenderPatch {
		@SpirePostfixPatch
		public static void Postfix(AbstractRoom room, SpriteBatch sb) {
			if ((room.phase == RoomPhase.COMBAT)
					&& (GiantModHelper.weapon != null)
					&& (AbstractDungeon.player instanceof Giant))
				GiantModHelper.weapon.render(sb);
		}
	}
}
