package xyz.flaflo.kosjam.engine

class Timer(var targetFps: Int = 120, var targetUps: Int = 120) {

    private var frames = 0
    private var updates = 0

    var currentFps: Int = 0
    var currentUps: Int = 0

    private var lastTick = System.currentTimeMillis()
    private var nextTick = lastTick + 1000L

    var deltaUpdate = 0.0
    private var deltaFrame = 0.0

    private val updateRate
        get() = 1000.0 / targetUps

    private val frameRate
        get() = 1000.0 / targetFps

    fun tick() {
        val currentTime = System.currentTimeMillis()
        val timeDifference = currentTime - lastTick

        lastTick = currentTime

        deltaUpdate += timeDifference / updateRate
        deltaFrame += timeDifference / frameRate

        while (deltaUpdate >= 1) {
            updates++
            G2D.update()
            deltaUpdate--
        }

        if (deltaFrame >= 1) {
            frames++
            G2D.draw()
            deltaFrame--
        }

        if (System.currentTimeMillis() >= nextTick) {
            currentFps = frames
            currentUps = updates

            frames = 0
            updates = 0

            nextTick = System.currentTimeMillis() + 1000
        }
    }
}
