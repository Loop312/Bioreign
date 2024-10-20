package characters

/*
Strengths: magic, resistance, accuracy
Weaknesses: hp, defense, strength
Unique Skill: TBD
*/
class Elf : Character() {
    override var race = "Elf"
    //Strengths
    override var mag = 15
    override var res = 15
    override var acc = 15
    //weaknesses
    override var str = 5
    override var maxHp = 5
    override var hp = 5
    override var def = 5
}