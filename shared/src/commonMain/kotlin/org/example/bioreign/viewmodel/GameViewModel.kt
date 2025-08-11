package org.example.bioreign.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.bioreign.model.CharacterState
import org.example.bioreign.model.CharacterStats
import org.example.bioreign.model.Position
import org.example.bioreign.model.Race

class GameViewModel {

    //character
    private val _characterState = MutableStateFlow(
        CharacterState(
            race = Race.HUMAN,
            stats = createCharacterStats(Race.HUMAN),
            spells = listOf(),
            currentSpell = 0,
            position = Position(0f,0f),
            image = "compose_multiplatform"
        )
    )
    val characterState: StateFlow<CharacterState> = _characterState.asStateFlow()

    private fun createCharacterStats(race: Race): CharacterStats {
        return when (race) {
            Race.ELF -> CharacterStats(
                //strengths
                mag = 15,
                res = 15,
                acc = 15,
                //weaknesses
                str = 5,
                def = 5,
                maxHp = 5,
                hp = 5
            )
            Race.DEMON -> CharacterStats(

            )
            Race.DARK_ELF -> CharacterStats(
                //strengths
                spd = 15,
                stealth = 15,
                maxStamina = 15.0,
                stamina = 15.0,
                //weaknesses
                str = 5,
                def = 5,
                maxHp = 5,
                hp = 5
            )
            Race.DRAGONKIN -> CharacterStats(
                //strengths
                str = 15,
                def = 15,
                maxHp = 15,
                hp = 15,
                //weaknesses
                stealth = 5,
                spd = 5,
                res = 5
            )
            Race.HUMAN -> CharacterStats()
        }
    }

    fun physDmg(dmg: Int){
        _characterState.update { currentState ->
            val currentStats = currentState.stats
            if (dmg > currentStats.def) {
                val newHp = (currentStats.hp - (dmg - currentStats.def)).coerceAtLeast(0)
                currentState.copy(
                    stats = currentStats.copy(hp = newHp),
                    alive = newHp > 0
                )
            } else {
                currentState
            }
        }
    }

    fun attack(){
        _characterState.update { it.copy(attacking = true) }
        CoroutineScope(Dispatchers.Default).launch {
            delay(333)
            _characterState.update { it.copy(attacking = false) }
        }
    }

    fun castSpell(){
        // Your original logic for casting a spell, now using `_characterState.value`
        _characterState.update { currentState ->
            val currentSpell = currentState.spells[currentState.currentSpell]
            val currentMana = currentState.stats.mana
            if (!currentState.casting && currentMana >= currentSpell.cost) {
                val newMana = currentMana - currentSpell.cost
                CoroutineScope(Dispatchers.Default).launch {
                    delay(1000)
                    _characterState.update { it.copy(casting = false) }
                }
                currentState.copy(
                    casting = true,
                    stats = currentState.stats.copy(mana = newMana)
                )
            } else {
                currentState
            }
        }
    }

    //press Q
    fun uniqueSkill() {
        val currentPlayer = _characterState.value
        when (currentPlayer.race) {
            Race.HUMAN -> humanUniqueSkill()
            Race.DRAGONKIN -> dragonkinUniqueSkill()
            Race.DARK_ELF -> darkElfUniqueSkill()
            Race.ELF -> elfUniqueSkill()
            Race.DEMON -> demonUniqueSkill()
        }
    }

    private fun humanUniqueSkill() {

    }
    private fun dragonkinUniqueSkill() {

    }
    private fun darkElfUniqueSkill() {

    }
    private fun elfUniqueSkill() {

    }
    private fun demonUniqueSkill() {

    }

    fun lvlUp(){
        _characterState.update { currentState ->
            val currentStats = currentState.stats

            // Check if the character has enough experience to level up
            if (currentStats.exp >= currentStats.expLimit) {
                // Update the stats for the next level
                val newLvl = currentStats.lvl + 1
                val newExp = currentStats.exp - currentStats.expLimit
                val newExpLimit = currentStats.expLimit * 2
                val newSkillPoints = currentStats.skillPoints + 5

                // This is where you can add other stat increases on level up
                // For example:
                // val newMaxHp = currentStats.maxHp + 2
                // val newHp = newMaxHp

                // Return a new CharacterState with the updated stats
                currentState.copy(
                    stats = currentStats.copy(
                        lvl = newLvl,
                        exp = newExp,
                        expLimit = newExpLimit,
                        skillPoints = newSkillPoints,
                        //... other stat increases ...
                    )
                )
            } else {
                // If not enough experience, return the current state unchanged
                currentState
            }
        }
    }

    fun gainExp(amount: Float) {
        _characterState.update { currentState ->
            val currentStats = currentState.stats
            // Update experience
            val newExp = currentStats.exp + amount

            // Return a new state with the updated experience
            currentState.copy(
                stats = currentStats.copy(exp = newExp)
            )
        }

        // After updating the experience, check if the character has leveled up
        lvlUp()
    }

}