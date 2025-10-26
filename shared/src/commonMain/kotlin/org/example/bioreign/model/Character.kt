package org.example.bioreign.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size

data class CharacterState(
    val name: String = "",
    val race: Race,
    val stats: CharacterStats,
    val alive: Boolean = true,
    val hiding: Boolean = false,
    val sprinting: Boolean = false,
    val attacking: Boolean = false,
    val casting: Boolean = false,
    val switching: Boolean = false,
    val spells: List<Spell> = listOf(Spell("Fireball", "small_stick2", 0, 0.0)),
    val currentSpell: Int = 0,
    val image: String, // Use a string to represent the resource
    val movingUp: Boolean = false,
    val movingDown: Boolean = false,
    val movingLeft: Boolean = false,
    val movingRight: Boolean = false,
    val verticalVelocity: Float = 0f,
    val horizontalVelocity: Float = 0f,
    val hitBox: Rect = Rect(Offset(0f,0f), Size(100f,100f)),
)

data class CharacterStats (
    val maxHp: Int = 10,
    val hp: Int = 10,
    val str: Int = 10,
    val mag: Int = 10,
    val def: Int = 10,
    val res: Int = 10,
    val spd: Int = 10,
    val maxStamina: Double = 10.0,
    val stamina: Double = 10.0,
    val maxMana: Double = 10.0,
    val mana: Double = 10.0,
    val stealth: Int = 10,
    val acc: Int = 10,
    val intel: Int = 10,
    val lck: Int = 10,
    val exp: Float = 10f,
    val expLimit: Float = 10f,
    val lvl: Int = 1,
    val skillPoints: Int = 5
)

data class Spell(
    val name: String,
    val image: String,
    val size: Int,
    val cost: Double,
)

enum class Race {
    DARK_ELF,
    DEMON,
    DRAGONKIN,
    ELF,
    HUMAN
}