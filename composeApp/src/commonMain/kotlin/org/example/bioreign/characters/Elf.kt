package characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/*
Strengths: magic, resistance, accuracy
Weaknesses: hp, defense, strength
Unique Skill: TBD
Magic: Fire and Water (for now)
*/
class Elf : Character() {
    override var race = "Elf"
    //Strengths
    override var mag by mutableStateOf(15)
    override var res by mutableStateOf(15)
    override var acc by mutableStateOf(15)
    //weaknesses
    override var str by mutableStateOf(5)
    override var maxHp by mutableStateOf(5)
    override var hp by mutableStateOf(5)
    override var def by mutableStateOf(5)

    override fun uniqueSkill(){
        //placeholder
    }
}