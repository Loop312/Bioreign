package characters
import androidx.compose.runtime.*
import org.example.bioreign.map
import kotlin.math.roundToInt

//later add a parameter for their sprite
open class Character {
    //health values
    open var maxHp by mutableStateOf(10)      //max health
    open var hp by mutableStateOf(10)         //current health
    //damage values
    open var str = 10        //physical damage (strength)
    open var mag = 10        //magic damage
    //defense values
    open var def = 10        //physical defense
    open var res = 10        //magic resistance
    //movement values
    open var spd = 10        //speed
    open var maxStamina by mutableStateOf(10.0)
    open var stamina by mutableStateOf(10.0)    //stamina
    //unique values
    open var name = "Bob"    //character's name
    open var stealth = 10    //stealth
    open var acc = 10        //accuracy
    open var intel = 10      //intelligence
    open var lck = 10        //luck
    //exp system 
    open var exp = 0.0F       //experience
    open var explimit = 10.0F //experience need to lvlup
    open var level = 1       //Player level
    open var skillPoints = 0 //used to upgrade character
    //status tracker
    var alive = true
    open var race = "lol"
    var sprinting = false
    var x by mutableStateOf(0)
    var y by mutableStateOf(0)

    var movingUp = false
    var movingDown = false
    var movingLeft = false
    var movingRight = false

    //displays character stats
    //might need to shorten/rearrange stuff
    fun displayStats(): String {

        return  "Name: $name        Race: $race\n" +
                "Level: $level        Skill Points: $skillPoints\n" +
                "Hp: $hp/$maxHp     "+ getExp() +
                "Str: $str         Mag: $mag \n" +
                "Def: $def        Res: $res \n" +
                "Spd: $spd        Stamina: $stamina\n" +
                "Acc: $acc        Stealth: $stealth\n" +
                "Lck: $lck        Intel: $intel\n"
    }

    //gain exp
    open fun gainExp(num: Float){
        exp += num
    }
    fun getExp(): String {
        return "Exp: " + exp.roundToInt() + "/$explimit\n"
    }
    //levels up character
    //need to figure out how I want to implement lvl ups
    open fun lvlup(){
        level += 1
        exp -= explimit
        explimit *= 2
        skillPoints += 5
    }

    //has the character take damage
    //physical damage
    fun physDmg (dmg: Int){
        if (dmg > def) {
            hp -= (dmg - def)
            checkHp()
        }
    }
    //magic damage
    fun magDmg (dmg: Int){
        if (dmg > res) {
            hp -= (dmg - res)
            checkHp()
        }
    }
    //checks if hp < 0 and sets alive to false
    fun checkHp() {
        if (hp < 0) {
            hp = 0
            alive = false
        }
    }

    //delete later
    fun lvlupSim(num: Int): String {
        for (i in 1..num) {
            displayStats()
            gainExp(explimit)
            lvlup()
        }
        return displayStats()
    }

    //handles character movement
    fun move(dx: Int, dy: Int) {
        if (map.mapEdge == true) {

            if (sprinting) { //apparently u can write this instead of == true
                x += (dx * spd)
                y += (dy * spd)
            } else {
                x += (dx * spd/2)
                y += (dy * spd/2)
            }

        }
        else {
            if (sprinting) {
                map.x -= (dx * spd)
                map.y -= (dy * spd)
            } else {
                map.x -= (dx * spd/2)
                map.y -= (dy * spd/2)
            }
        }
    }
}
