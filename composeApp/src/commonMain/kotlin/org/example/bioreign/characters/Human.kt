package org.example.bioreign.characters

/*
Strengths: None
Weaknesses: None
Unique Skill: Exp Multiplier
Magic: None/Random/TBD
*/
class Human : Character() {
    override var race = "Human"
    var expMultiplier = 1.0F
    
    override fun gainExp(num: Float) {
        exp += (num*expMultiplier)
    }

    //every level up their multiplier gets stronger
    override fun lvlup(){
        lvl += 1
        exp = exp - explimit
        explimit *= 2
        skillPoints += 5
        expMultiplier += .05F
    }

    override fun uniqueSkill(){
        //placeholder
    }
}
