package seml.netherRatioPlugin

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerPortalEvent
import org.bukkit.plugin.java.JavaPlugin

class NetherRatioPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onPlayerPortal(event: PlayerPortalEvent) {
        val player = event.player
        val from = event.from
        val to = event.to

        // 네더와 오버월드 간 이동 확인
        if (player.world.environment == org.bukkit.World.Environment.NORMAL && to.world.environment == org.bukkit.World.Environment.NETHER) {
            // 오버월드 -> 네더: 1:1 비율로 변경 (기본 1/8 대신 1/1)
            to.x = from.x  // X 좌표 1:1
            to.z = from.z  // Z 좌표 1:1
            to.y = from.y  // Y는 변경 없음
        } else if (player.world.environment == org.bukkit.World.Environment.NETHER && to.world.environment == org.bukkit.World.Environment.NORMAL) {
            // 네더 -> 오버월드: 1:1 비율로 변경 (기본 *8 대신 *1)
            to.x = from.x
            to.z = from.z
            to.y = from.y
        }

        event.to = to  // 변경된 좌표 적용
    }
}
