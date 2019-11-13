package xyz.flaflo.kosjam.engine

import javax.swing.JFrame

class Window(var title: String = "Untitled Game", var width: Int = 800, var height: Int = 600, var bufferCount: Int = 2) {
    private var frame: JFrame = JFrame()

    fun init() {
        frame = JFrame()
        frame.title = title
        frame.setSize(width, height)
        frame.isResizable = false
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)
        frame.add(G2D)
        frame.pack()
    }

    fun show() {
        frame.isVisible = true
    }

    fun isVisible(): Boolean {
        return frame.isVisible
    }
}
