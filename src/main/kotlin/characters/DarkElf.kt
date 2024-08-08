package characters

/*
Strengths: speed, stealth, stamina
Weaknesses: strength, hp, defense
Unique Skill: invisibility
*/
class DarkElf : Character() {
    override var race = "Dark Elf"
    //Strengths
    override var spd = 15
    override var stealth = 15
    override var stamina = 15

    //weaknesses
    override var str = 5
    override var maxHp = 5
    override var hp = 5
    override var def = 5


}