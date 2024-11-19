package org.example.bioreign.magic

import bioreign.composeapp.generated.resources.Res
import bioreign.composeapp.generated.resources.compose_multiplatform

open class Spell {
    open var image = Res.drawable.compose_multiplatform
    open var icon = Res.drawable.compose_multiplatform
    open var name = "No Name"
    open var element = "No Element"
    open var description = "Unavailable"
    open var damage = 10
    open var range = 10
    open var cooldown = 10
    open var speed = 10
    open var cost = 5
    open var size = 25
}