package org.example.bioreign.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/*
Strengths: Strength, hp, defense
Weaknesses: Stealth, speed, resistance
Unique Skill: Regeneration
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
    }
}