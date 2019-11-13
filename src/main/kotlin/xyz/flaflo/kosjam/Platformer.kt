package xyz.flaflo.kosjam

import xyz.flaflo.kosjam.engine.G2D
import xyz.flaflo.kosjam.engine.G2D.g2d
import java.awt.Color

object Platformer {

    fun start() {
        g2d {
            window {
                title = "KOSJam"
                width = 800
                height = 600
            }
            timer {
                targetFps = 120
                targetTps = 20
            }
        }

        G2D.drawingSubscribers.add {
            it.color = Color.RED
            it.drawString("FPS: ${G2D.timer.currentFps} / ${G2D.timer.targetFps}", 10, 15)
            it.drawString("TPS: ${G2D.timer.currentTps} / ${G2D.timer.targetTps}", 10, 30)
        }

        G2D.start()
    }
}