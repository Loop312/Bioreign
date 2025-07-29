package org.example.bioreign.menus

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.example.bioreign.gameLoop
import org.example.bioreign.gamemodes.Online
import org.example.bioreign.gamemodes.Rogue
import org.example.bioreign.gamemodes.StoryMode


@Serializable
object HomeRoute

@Serializable
object SettingsRoute

@Serializable
object EditKeysRoute

@Serializable
object SelectModeRoute

@Serializable
object SelectSaveRoute

@Serializable
data class SelectCharacterRoute(val selectedSaveId: Int) //if character selection needs a save ID

@Serializable
object InGameMenuRoute

@Serializable
object PvPRoute

@Serializable
object StoryRoute

@Serializable
object RogueRoute

@Serializable
data class Save(val mode: String, val saveId: String)

class Nav {
    val homeMenu = HomeMenu()
    val settingsMenu = SettingsMenu()
    val editKeysMenu = EditKeysMenu()
    val modeMenu = ModeMenu()
    val saveMenu = SaveMenu()
    val characterMenu = CharacterMenu()

    val gameMenu = InGameMenu()

    val storyMode = StoryMode()
    val online = Online()
    val rogue = Rogue()

    @Composable
    fun activate() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = HomeRoute) {
            //HOME
            composable<HomeRoute> {
                homeMenu.open(
                    navModeMenu = { navController.navigate(SelectModeRoute) },
                    navKeysMenu = { navController.navigate(EditKeysRoute) },
                    navSettingsMenu = { navController.navigate(SettingsRoute) }
                )
            }
            //SETTINGS
            composable<SettingsRoute> {
                settingsMenu.open(
                    navBack = { navController.popBackStack() }
                )
            }
            //MODE SELECT
            composable<SelectModeRoute> {
                modeMenu.open(
                    navStory = { navController.navigate(StoryRoute); gameLoop.isPlaying = true },
                    navPvP = { navController.navigate(PvPRoute) },
                    navRogue = { navController.navigate(RogueRoute) },
                    navBack = { navController.popBackStack() }
                )
            }
            //not used rn
            //SAVE SELECT
            composable<SelectSaveRoute> {
                saveMenu.open(
                    //remember to change the save ID in selectCharacterRoute
                    navCharSelect = { navController.navigate(SelectCharacterRoute(1)) },
                    navBack = { navController.popBackStack() }
                )
            }
            //not used rn
            //CHARACTER SELECT
            composable<SelectCharacterRoute> {
                characterMenu.open(
                    navStoryMode = { navController.navigate(StoryRoute) },
                    navBack = { navController.popBackStack() }
                )
            }
            //GAME MENU
            composable<InGameMenuRoute> {
                gameMenu.open()
            }
            //EDIT KEYS
            composable<EditKeysRoute> {
                editKeysMenu.open(
                    navBack = { navController.popBackStack() }
                )
            }
            //STORY MODE
            composable<StoryRoute> {
                storyMode.play(
                    toHomeMenu = { navController.navigate(HomeRoute) }
                )
            }
            //ONLINE/PvP MODE
            composable<PvPRoute> {
                online.play()
            }
            //ROGUELIKE MODE
            composable<RogueRoute> {
                rogue.play(
                    toHomeMenu = { navController.navigate(HomeRoute) }
                )
            }
        }
    }
}