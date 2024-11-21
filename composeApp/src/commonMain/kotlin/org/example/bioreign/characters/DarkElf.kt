package org.example.bioreign.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/*
Strengths: speed, stealth, stamina
Weaknesses: strength, hp, defense
Unique Skill: invisibility
Magic: Wind and Water (for now)
*/
class DarkElf : Character() {
    override var race = "Dark Elf"
    //Strengths
    override var spd by mutableStateOf(15)
    override var stealth by mutableStateOf(15)
    override var maxStamina by mutableStateOf(15.0)
    override var stamina by mutableStateOf(15.0)

    //weaknesses
    override var str by mutableStateOf(5)
    override var maxHp by mutableStateOf(5)
    override var hp by mutableStateOf(5)
    override var def by mutableStateOf(5)

    override fun uniqueSkill(){
        //placeholder
    }

}