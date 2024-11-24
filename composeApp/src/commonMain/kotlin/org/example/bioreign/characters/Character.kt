package org.example.bioreign.characters
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bioreign.composeapp.generated.resources.BioreignTempLogo
import bioreign.composeapp.generated.resources.Res
import bioreign.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.bioreign.magic.Fireball
import org.example.bioreign.magic.RockBullet
import org.example.bioreign.magic.WaterBall
import org.example.bioreign.magic.WindDart
import org.example.bioreign.map
import org.example.bioreign.player
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

//later add a parameter for their sprite
open class Character {
    //health values
    open var maxHp by mutableStateOf(10)      //max health
    open var hp by mutableStateOf(10)         //current health
    //damage values
    open var str by mutableStateOf(10)        //physical damage (strength)
    open var mag by mutableStateOf(10)        //magic damage
    //defense values
    open var def by mutableStateOf(10)        //physical defense
    open var res by mutableStateOf(10)        //magic resistance
    //movement values
    open var spd by mutableStateOf(10)        //speed
    open var maxStamina by mutableStateOf(10.0)
    open var stamina by mutableStateOf(10.0)    //stamina
    //unique values
    open var name by mutableStateOf("bob")    //character's name
    open var stealth by mutableStateOf(10)    //stealth
    open var acc by mutableStateOf(10)        //accuracy
    open var intel by mutableStateOf(10)      //intelligence
    open var lck by mutableStateOf(10)        //luck
    //exp system 
    open var exp by mutableStateOf(0F)       //experience
    open var explimit by mutableStateOf(10F) //experience need to lvlup
    open var lvl by mutableStateOf(1)       //Player level
    open var skillPoints by mutableStateOf(0) //used to upgrade character
    //mana
    open var maxMana by mutableStateOf(10.0)
    open var mana by mutableStateOf(10.0)
    //status tracker
    var alive = true
    open var race = "lol"
    var hiding by mutableStateOf(false)
    var sprinting by mutableStateOf(false)
    var attacking by mutableStateOf(false)
    var casting by mutableStateOf(false)
    var switching by mutableStateOf(false)
    var spells by mutableStateOf(arrayOf(Fireball(), WaterBall(), WindDart(), RockBullet()))
    var currentSpell by mutableStateOf(0)
    var x by mutableStateOf(0F)
    var y by mutableStateOf(0F)
    open var image = Res.drawable.compose_multiplatform

    var movingUp = false
    var movingDown = false
    var movingLeft = false
    var movingRight = false

    //displays character stats
    //might need to shorten/rearrange stuff
    fun displayStats(): String {

        return  "Name: $name        Race: $race\n" +
                "Level: $lvl        Skill Points: $skillPoints\n" +
                "Hp: $hp/$maxHp     "+ getExp() +
                "Str: $str         Mag: $mag \n" +
                "Def: $def        Res: $res \n" +
                "Spd: $spd        Stamina: $stamina\n" +
                "Acc: $acc        Stealth: $stealth\n" +
                "Lck: $lck        Intel: $intel\n"
    }

    //gain exp
    //open due to human class
    open fun gainExp(num: Float){
        exp += num
    }
    fun getExp(): String {
        return "Exp: " + exp.roundToInt() + "/$explimit\n"
    }
    //levels up character
    //need to figure out how I want to implement lvl ups
    open fun lvlup(){
        lvl += 1
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
    //built into taking damage functions
    private fun checkHp() {
        if (hp < 0) {
            hp = 0
            alive = false
        }
    }

    //delete later
    //simulates lvl ups
    fun lvlupSim(num: Int): String {
        for (i in 1..num) {
            displayStats()
            gainExp(explimit)
            lvlup()
        }
        return displayStats()
    }

    //handles character movement
    fun move(dx: Float, dy: Float) {
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
                stamina -= 0.1
                map.x -= (dx * spd)
                map.y -= (dy * spd)
            } else {
                map.x -= (dx * spd/2)
                map.y -= (dy * spd/2)
            }
        }
    }

    //loads character on screen
    @Composable
    fun load() {
        Box (Modifier.fillMaxSize()) {
            //renders character
            if (!hiding) {
                Image(
                    painter = painterResource(image),
                    contentDescription = "lol",
                    modifier = Modifier
                        .offset(player.x.dp, player.y.dp)
                        .size(100.dp)
                        .align(Alignment.Center)
                )
            }
            //renders characters attack
            if (attacking) {
                Image(
                    painterResource(Res.drawable.BioreignTempLogo),
                    null,
                    Modifier
                        .offset((player.x + xDirection("melee")).dp, (player.y + yDirection("melee")).dp)
                        .size(100.dp)
                        .align(Alignment.Center)
                )
            }
            if (casting) {
                Image(
                    painterResource(spells[currentSpell].image),
                    spells[currentSpell].name,
                    Modifier
                        .offset((player.x + xDirection("magic")).dp, (player.y + yDirection("magic")).dp)
                        .size(spells[currentSpell].size.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }

    fun attack(){
        if (!attacking) {
            attacking = true
            CoroutineScope(Dispatchers.Default).launch {
                delay(333)
                attacking = false
            }
        }
    }

    //handles attack direction offsets on x axis
    private fun xDirection(attackType: String): Int{
        if (attackType == "melee") {
            if (movingLeft) return -50
            if (movingRight) return +50
        }
        if (attackType == "magic") {
            //placeholder (use animatable numbers)
            if (movingLeft) return -50
            if (movingRight) return +50
        }
        return 0
    }

    //handles attack direction offsets on y axis
    private fun yDirection(attackType: String): Int{
        if (attackType == "melee") {
            if (movingUp) return -50
            if (movingDown) return +50
        }
        if (attackType == "magic") {
            //placeholder (use animatable numbers)
            if (movingUp) return -50
            if (movingDown) return +50
        }
        return 0
    }

    fun castSpell(){
        if (mana > spells[currentSpell].cost) {
            if (!casting) {
                casting = true
                mana -= spells[currentSpell].cost
                CoroutineScope(Dispatchers.Default).launch {
                    delay(1000)
                    casting = false
                }
            }
        }
    }

    fun cycleSpell(){
        if (!switching) {
            switching = true

            if (currentSpell < spells.size - 1) {
                currentSpell += 1
            } else currentSpell = 0

            CoroutineScope(Dispatchers.Default).launch {
                delay(333)
                switching = false
            }
        }
    }

    //every race will have their own unique skill
    open fun uniqueSkill(){
        //placeholder
    }
}
