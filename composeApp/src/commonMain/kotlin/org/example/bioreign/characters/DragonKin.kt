package org.example.bioreign.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.bioreign.map

/*
Strengths: Strength, hp, defense
Weaknesses: Stealth, speed, resistance
Unique Skill: Regeneration and parry/counter
Magic: Earth and Fire (for now)
*/
class DragonKin : Character() {
    override var race = "Dragon Kin"
    //Strengths
    override var str by mutableStateOf(15)
    override var maxHp by mutableStateOf(15)
    override var def by mutableStateOf(15)
    //weaknesses
    override var stealth by mutableStateOf(5)
    override var spd by mutableStateOf(5)
    override var res by mutableStateOf(5)


    override fun uniqueSkill(){
        //placeholder
        map.canMoveUp = false
        map.canMoveDown = false
        map.canMoveLeft = false
        map.canMoveRight = false

        //if attacked parry/counter

        CoroutineScope(Dispatchers.Default).launch {
            delay(333)
            map.canMoveUp = true
            map.canMoveDown = true
            map.canMoveLeft = true
            map.canMoveRight = true
        }

    }
}