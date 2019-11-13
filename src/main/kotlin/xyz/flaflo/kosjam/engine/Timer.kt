package xyz.flaflo.kosjam.engine

data class Timer(var targetFps: Int = 120, var targetTps: Int = 120) {

    private var frames = 0
    private var ticks = 0

    var currentFps: Int = 0
    var currentTps: Int = 0

    private var lastUpdate = System.currentTimeMillis()
    private var nextUpdate = lastUpdate + 1000L

    var deltaUpdate = 0.0
    private var deltaFrame = 0.0

    private val tickRate
        get() = 1000.0 / targetTps

    private val frameRate
        get() = 1000.0 / targetFps

    fun run() {
        val currentTime = System.currentTimeMillis()
        val timeDifference = currentTime - lastUpdate

        lastUpdate = currentTime

        deltaUpdate += timeDifference / tickRate
        deltaFrame += timeDifference / frameRate

        while (deltaUpdate >= 1) {
            ticks++
            G2D.update()
            deltaUpdate--
        }

        if (deltaFrame >= 1) {
            frames++
            G2D.draw()
            deltaFrame--
        }

        if (System.currentTimeMillis() >= nextUpdate) {
            currentFps = frames
            currentTps = ticks

            frames = 0
            ticks = 0

            nextUpdate = System.currentTimeMillis() + 1000
        }
    }
}
