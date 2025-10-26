package org.example.bioreign.viewmodel

//import androidx.lifecycle.ViewModel
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.bioreign.model.CameraState
import org.example.bioreign.model.CharacterState
import org.example.bioreign.model.CharacterStats
import org.example.bioreign.model.MapState
import org.example.bioreign.model.Race

class CharacterViewModel {
    private val _characterState = MutableStateFlow(
        CharacterState(
            race = Race.HUMAN,
            stats = createCharacterStats(Race.HUMAN),
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
                maxStamina = 15f,
                stamina = 15f,
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

    //scale character size to tileSize
    fun setCharacterSize(tileSize: Float) {
        _characterState.update {
            val size = Size(tileSize, tileSize)
            it.copy(hitBox = Rect(it.hitBox.topLeft, size))
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

    fun cycleSpell(){
        _characterState.update { currentState ->
            val newSpellIndex = (currentState.currentSpell + 1) % currentState.spells.size
            currentState.copy(currentSpell = newSpellIndex)
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

    fun gainExp(amount: Float) {
        _characterState.update { currentState ->
            val currentStats = currentState.stats
            var newExp = currentStats.exp + amount

            // Check for level-up within the same update block
            var newLvl = currentStats.lvl
            var newExpLimit = currentStats.expLimit
            var newSkillPoints = currentStats.skillPoints

            if (newExp >= newExpLimit) {
                newLvl += 1
                newExp -= newExpLimit
                newExpLimit *= 2
                newSkillPoints += 5
            }

            currentState.copy(
                stats = currentStats.copy(
                    exp = newExp,
                    lvl = newLvl,
                    expLimit = newExpLimit,
                    skillPoints = newSkillPoints
                )
            )
        }
    }

    fun handleStamina(deltaTime: Float) {
        //SPRINT
        if (_characterState.value.stats.stamina <= 0 && _characterState.value.sprinting) {
            _characterState.update { it.copy(sprinting = false) }
            return
        }
        if (_characterState.value.sprinting) {
            _characterState.update { currentState ->
                val currentStats = currentState.stats
                val newStamina = currentStats.stamina - (currentStats.maxStamina * 0.5f * deltaTime)
                currentState.copy(
                    stats = currentStats.copy(stamina = newStamina)
                )
            }
            return
        }

        //REGEN
        if (_characterState.value.stats.stamina >= _characterState.value.stats.maxStamina ) {
            return
        }
        _characterState.update { currentState ->
            val currentStats = currentState.stats
            val newStamina = currentStats.stamina + (currentStats.maxStamina * 0.5f * deltaTime)
            currentState.copy(
                stats = currentStats.copy(stamina = newStamina)
            )
        }
    }

    fun handleManaRegen(deltaTime: Float) {
        if (_characterState.value.stats.mana >= _characterState.value.stats.maxMana) {
            return
        }
        _characterState.update { currentState ->
            val currentStats = currentState.stats
            val newMana = currentStats.mana + (currentStats.maxMana * 0.5f * deltaTime)
            currentState.copy(
                stats = currentStats.copy(mana = newMana)
            )
        }
    }

    //used with the Key Handler
    fun move(dx: Float, dy: Float) {
        _characterState.update { currentState ->
            val horizontalVelocity = currentState.horizontalVelocity
            val verticalVelocity = currentState.verticalVelocity
            val speed = currentState.stats.spd
            val velocityScaleFactor = .25F
            currentState.copy(
                horizontalVelocity = horizontalVelocity + dx * speed * velocityScaleFactor,
                verticalVelocity = verticalVelocity + dy * speed * velocityScaleFactor
            )
        }
    }

    //used with the Overlay/Joystick
    fun moveX(dx: Float) {
        _characterState.update { currentState ->
            val speed = currentState.stats.spd
            val scaleFactor = .25f
            currentState.copy(
                horizontalVelocity = dx * speed * scaleFactor
            )
        }
    }

    fun moveY(dy: Float) {
        _characterState.update { currentState ->
            val speed = currentState.stats.spd
            val scaleFactor = .25f
            currentState.copy(
                verticalVelocity = dy * speed * scaleFactor
            )
        }
    }

    fun sprint() {
        _characterState.update { currentState ->
            currentState.copy(
                sprinting = true
            )
        }
    }

    fun stopSprint() {
        _characterState.update { currentState ->
            currentState.copy(
                sprinting = false
            )
        }
    }

    fun handleMovement(deltaTime: Float, tileSize: Float) {
        _characterState.update { currentState ->
            val currentPosition = currentState.hitBox.topLeft
            val sprintMultiplier = if (currentState.sprinting) 2 else 1
            val movementFactor = deltaTime * tileSize * sprintMultiplier

            var horizontalPosition = currentPosition.x
            horizontalPosition += currentState.horizontalVelocity * movementFactor
            var verticalPosition = currentPosition.y
            verticalPosition +=  currentState.verticalVelocity * movementFactor

            val hitbox = Rect(Offset(horizontalPosition, verticalPosition), currentState.hitBox.size)
            currentState.copy(
                hitBox = hitbox
            )
        }
    }
    //temporary, will swap out for what's needed to calculate player offset on clamp
    //(cameraState and mapState)
    fun getOffsetX(cameraState: CameraState, mapState: MapState): Float {
        //println("offset X: ${_characterState.value.position.x % (cameraState.width * mapState.tileSize)}")
        return if (cameraState.clampLeft) {
            _characterState.value.hitBox.left
        } else if (cameraState.clampRight) {
            val mapEnd = mapState.tiles.size * mapState.tileSize
            val cameraEnd = cameraState.width * mapState.tileSize
            _characterState.value.hitBox.left - (mapEnd - cameraEnd)
        } else {
            0f
        }
    }

    fun getOffsetY(cameraState: CameraState, mapState: MapState): Float {
        //println("Offset Y: ${_characterState.value.position.y % (cameraState.height * mapState.tileSize)}")
        return if (cameraState.clampTop) {
            _characterState.value.hitBox.top
        } else if (cameraState.clampBottom) {
            val mapEnd = mapState.tiles[0].size * mapState.tileSize
            val cameraEnd = cameraState.height * mapState.tileSize
            _characterState.value.hitBox.top - (mapEnd - cameraEnd)
        } else {
            0f
        }
    }
}