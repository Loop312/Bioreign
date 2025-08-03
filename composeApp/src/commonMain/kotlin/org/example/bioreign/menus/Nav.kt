package org.example.bioreign.menus

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.example.bioreign.gamemodes.GameMode
import org.example.bioreign.gamemodes.Online
import org.example.bioreign.gamemodes.Rogue
import org.example.bioreign.gamemodes.StoryMode



sealed class AppRoute {
    @Serializable
    object Home

    @Serializable
    object Settings

    @Serializable
    object KeyEdit

    @Serializable
    object ModeSelect

    @Serializable
//get the selected game mode from SelectModeRoute
    data class SaveSelect(val gameMode: String)

    @Serializable
//get the selected save from SelectSaveRoute and pass the game mode
    data class CharacterSelect(val gameMode: String, val selectedSaveId: Int)

    @Serializable
    data class PvP(val selectedSaveId: Int, val selectedCharacterId: String)

    @Serializable
    data class Story(val selectedSaveId: Int, val selectedCharacterId: String)

    @Serializable
    data class Rogue(val selectedSaveId: Int, val selectedCharacterId: String)

    @Serializable
    object InGameMenu
}

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
        //switch back to startDestination = HomeRoute later
        NavHost(navController = navController, startDestination = AppRoute.Story(1,"")) {
            //HOME
            composable<AppRoute.Home> {
                homeMenu.open(
                    navModeMenu = { navController.navigate(AppRoute.ModeSelect) },
                    navKeysMenu = { navController.navigate(AppRoute.KeyEdit) },
                    navSettingsMenu = { navController.navigate(AppRoute.Settings) }
                )
            }
            //SETTINGS
            composable<AppRoute.Settings> {
                settingsMenu.open(
                    navBack = { navController.popBackStack() }
                )
            }
            //MODE SELECT
            composable<AppRoute.ModeSelect> {
                modeMenu.open(
                    navStory = {
                        navController.navigate(AppRoute.SaveSelect(GameMode.STORY.name))
                    },
                    navPvP = {
                        navController.navigate(AppRoute.SaveSelect(GameMode.PVP.name))
                    },
                    navRogue = {
                        navController.navigate(AppRoute.SaveSelect(GameMode.ROGUE.name))
                    },
                    navBack = { navController.popBackStack() }
                )
            }
            //SAVE SELECT
            composable<AppRoute.SaveSelect> { backStackEntry ->
                val route = backStackEntry.toRoute<AppRoute.SaveSelect>()
                val gameMode = route.gameMode
                saveMenu.open(
                    navCharSelect = { saveId ->
                        navController.navigate(
                            AppRoute.CharacterSelect(
                                gameMode = gameMode,
                                selectedSaveId = saveId
                            )
                        )
                    },
                    navBack = { navController.popBackStack() }
                )
            }
            //CHARACTER SELECT
            composable<AppRoute.CharacterSelect> { backStackEntry ->
                val route = backStackEntry.toRoute<AppRoute.CharacterSelect>()
                val gameMode = route.gameMode
                val selectedSaveId = route.selectedSaveId
                val gameRoute = when (gameMode) {
                    //remember to change selectedCharacterId = "0"
                    GameMode.STORY.name -> AppRoute.Story(selectedSaveId, "0")
                    GameMode.PVP.name -> AppRoute.PvP(selectedSaveId, "0")
                    GameMode.ROGUE.name -> AppRoute.Rogue(selectedSaveId, "0")
                    else -> ""
                }
                characterMenu.open(

                    navStoryMode = { navController.navigate(gameRoute)} ,
                    navBack = { navController.popBackStack() }
                )
            }
            //GAME MENU
            composable<AppRoute.InGameMenu> {
                gameMenu.open()
            }
            //EDIT KEYS
            composable<AppRoute.KeyEdit> {
                editKeysMenu.open(
                    navBack = { navController.popBackStack() }
                )
            }
            //STORY MODE
            composable<AppRoute.Story> {
                storyMode.play(
                    toHomeMenu = { navController.navigate(AppRoute.Home) }
                )
            }
            //ONLINE/PvP MODE
            composable<AppRoute.PvP> {
                online.play()
            }
            //ROGUELIKE MODE
            composable<AppRoute.Rogue> {
                rogue.play(
                    toHomeMenu = { navController.navigate(AppRoute.Home) }
                )
            }
        }
    }
}