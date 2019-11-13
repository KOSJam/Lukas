package xyz.flaflo.kosjam.engine.texture

import xyz.flaflo.kosjam.engine.api.Drawable
import xyz.flaflo.kosjam.engine.api.Updatable
import java.awt.Graphics

open class Sprite(texture: Texture) : Drawable, Updatable {
    override fun update() {
    }

    override fun draw(graphics: Graphics) {
    }
}