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
import org.example.bioreign.model.Tile

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
    fun scaleCharacterSizeAndPosition(tileSize: Float, rescaleFactor: Float) {
        _characterState.update { state ->
            val characterSize = tileSize * .9f
            val size = Size(characterSize, characterSize)
            val oldPosition = state.hitBox.topLeft
            val newPosition = Offset(
                oldPosition.x * rescaleFactor,
                oldPosition.y * rescaleFactor
            )
            val newHitbox = Rect(newPosition, size)
            updateTileOffset(newHitbox, tileSize)
            state.copy(hitBox = newHitbox)
        }
    }

    private fun updateTileOffset(hitbox: Rect, tileSize: Float) {
        _characterState.update { state ->
            val newTileOffset = hitbox.center / tileSize
            state.copy(tileOffset = newTileOffset)
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
    fun keyMove(dx: Float, dy: Float) {
        _characterState.update { currentState ->
            val xInput = (currentState.xInput + dx).coerceIn(-1f, 1f)
            val yInput = (currentState.yInput + dy).coerceIn(-1f, 1f)
            currentState.copy(
                xInput = xInput,
                yInput = yInput
            )
        }
    }

    fun stickMove(stickX: Float, stickY: Float) {
        _characterState.update { currentState ->
            currentState.copy(
                xInput = stickX,
                yInput = stickY
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

    fun handleMovement(deltaTime: Float, mapState: MapState) {
        _characterState.update { currentState ->
            val tileSize = mapState.tileSize
            val mapSizeX = mapState.tiles.size
            val mapSizeY = mapState.tiles[0].size
            val map = mapState.tiles

            val currentHitbox = currentState.hitBox
            val currentLeft = currentHitbox.left
            val currentTop = currentHitbox.top

            val velocityScaleFactor = .25f
            val speed = currentState.stats.spd
            val xInput = currentState.xInput
            val yInput = currentState.yInput
            val horizontalVelocity = xInput * speed * velocityScaleFactor
            val verticalVelocity = yInput * speed * velocityScaleFactor

            val sprintMultiplier = if (currentState.sprinting) 2 else 1
            val movementFactor = deltaTime * tileSize * sprintMultiplier

            val newHorizontalPosition = currentLeft + horizontalVelocity * movementFactor
            val newVerticalPosition = currentTop + verticalVelocity * movementFactor

            //check X direction
            val newHitboxX = Rect(
                Offset(
                    newHorizontalPosition,
                    currentTop
                ),
                currentHitbox.size
            )
            val newLeft = newHitboxX.left

            val finalHorizontalPosition = if (horizontalCollision(newHitboxX, mapSizeX, tileSize, map, horizontalVelocity)) {
                //horizontalVelocity = 0f
                currentLeft
            } else {
                newLeft
            }

            //check Y direction
            val newHitboxY = Rect(
                Offset(
                    finalHorizontalPosition,
                    newVerticalPosition
                ),
                currentHitbox.size
            )
            val newTop = newHitboxY.top

            val finalVerticalPosition = if (verticalCollision(newHitboxY, mapSizeY, tileSize, map, verticalVelocity)) {
                //verticalVelocity = 0f
                currentTop
            } else {
                newTop
            }

            val finalHitbox = Rect(
                Offset(
                    finalHorizontalPosition,
                    finalVerticalPosition
                ),
                currentState.hitBox.size
            )
            updateTileOffset(finalHitbox, tileSize)

            currentState.copy(
                hitBox = finalHitbox
            )
        }
    }

    fun horizontalCollision(hitBox: Rect, mapSizeX: Int, tileSize: Float, map: Array<Array<Tile>>, horizontalVelocity: Float): Boolean {
        // 1. Check Map Boundaries
        if (hitBox.left < 0f || hitBox.right > mapSizeX * tileSize) {
            println("HorizontalMapEdgeCollision")
            return true
        }

        // Determine the tile indices to check
        val yTop = (hitBox.top / tileSize).toInt().coerceAtLeast(0)
        val yBottom = (hitBox.bottom / tileSize).toInt().coerceAtMost(map[0].size - 1)

        // 2. Check for Tile Collision based on direction
        if (horizontalVelocity > 0f) { // Moving Right
            val xRight = (hitBox.right / tileSize).toInt().coerceAtMost(mapSizeX - 1)

            // Check right-top tile and right-bottom tile
            if (map[xRight][yTop].solid || map[xRight][yBottom].solid) {
                println("HorizontalTileCollision - Right")
                return true
            }
        } else if (horizontalVelocity < 0f) { // Moving Left
            val xLeft = (hitBox.left / tileSize).toInt().coerceAtLeast(0)

            // Check left-top tile and left-bottom tile
            if (map[xLeft][yTop].solid || map[xLeft][yBottom].solid) {
                println("HorizontalTileCollision - Left")
                return true
            }
        }

        // No collision
        return false
    }

    fun verticalCollision(hitBox: Rect, mapSizeY: Int, tileSize: Float, map: Array<Array<Tile>>, verticalVelocity: Float): Boolean {
        // 1. Check Map Boundaries
        if (hitBox.top < 0f || hitBox.bottom > mapSizeY * tileSize) {
            println("VerticalMapEdgeCollision")
            return true
        }

        // Determine the tile indices to check
        val xLeft = (hitBox.left / tileSize).toInt().coerceAtLeast(0)
        val xRight = (hitBox.right / tileSize).toInt().coerceAtMost(map.size - 1)

        // 2. Check for Tile Collision based on direction
        if (verticalVelocity > 0f) { // Moving Down
            val yBottom = (hitBox.bottom / tileSize).toInt().coerceAtMost(mapSizeY - 1)

            // Check bottom-left tile and bottom-right tile
            if (map[xLeft][yBottom].solid || map[xRight][yBottom].solid) {
                println("VerticalTileCollision - Down")
                return true
            }
        } else if (verticalVelocity < 0f) { // Moving Up
            val yTop = (hitBox.top / tileSize).toInt().coerceAtLeast(0)

            // Check top-left tile and top-right tile
            if (map[xLeft][yTop].solid || map[xRight][yTop].solid) {
                println("VerticalTileCollision - Up")
                return true
            }
        }

        // No collision
        return false
    }
    //temporary, will swap out for what's needed to calculate player offset on clamp
    //(cameraState and mapState)
    fun getOffsetX(cameraState: CameraState): Float {
        // Player World X (P_x) is the top-left of the player's hitbox
        val playerWorldX = _characterState.value.hitBox.left

        // Camera World Center X (C_x)
        val cameraWorldX = cameraState.translateX

        // Screen X = (V_width / 2) + P_x - C_x
        // When P_x == C_x, Screen X = V_width / 2 (i.e., player is centered)
        return cameraState.canvasWidth / 2f + playerWorldX - cameraWorldX
    }

    fun getOffsetY(cameraState: CameraState): Float {
        // Player World Y (P_y)
        val playerWorldY = _characterState.value.hitBox.top

        // Camera World Center Y (C_y)
        val cameraWorldY = cameraState.translateY

        // Screen Y = (V_height / 2) + P_y - C_y
        return cameraState.canvasHeight / 2f + playerWorldY - cameraWorldY
    }
}