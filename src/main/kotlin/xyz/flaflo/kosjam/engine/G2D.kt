package xyz.flaflo.kosjam.engine

import java.awt.Canvas
import java.awt.Color
import java.awt.Graphics
import java.lang.Thread.sleep

object G2D : Canvas(), Runnable {

    var window: Window = Window()
    var timer: Timer = Timer()

    private var runningThread: Thread = Thread()

    val updateSubscribers = ArrayList<() -> Unit>()
    val drawingSubscribers = ArrayList<(graphics: Graphics) -> Unit>()

    fun window(init: Window.() -> Unit): Window {
        val window = Window()

        init(window)

        this.setSize(window.width, window.height)
        window.init()

        this.window = window

        return window
    }

    fun timer(init: Timer.() -> Unit): Timer {
        val timer = Timer()

        init(timer)

        this.timer = timer

        return timer
    }

    fun g2d(init: G2D.() -> Unit): G2D = G2D.apply(init)

    fun start() {
        window.show()
        runningThread = Thread(this)
        runningThread.start()
    }

    override fun run() {
        while (window.isVisible()) {
            timer.run()

            //sleeping 1 millisecond massively reduces cpu usage
            sleep(1)
        }
    }

    fun update() {
        updateSubscribers.forEach { it() }
    }

    fun draw() {
        if (bufferStrategy == null) {
            createBufferStrategy(window.bufferCount)
        } else {
            val graphics = bufferStrategy.drawGraphics
            graphics.color = Color.BLACK
            graphics.fillRect(0, 0, width, height)

            drawingSubscribers.forEach { it(graphics) }

            graphics.dispose()
            bufferStrategy.show()
        }
    }
}