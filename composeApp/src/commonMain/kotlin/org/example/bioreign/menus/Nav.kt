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


@Serializable object HomeRoute

@Serializable object SettingsRoute

@Serializable object EditKeysRoute

@Serializable object SelectModeRoute

@Serializable
//get the selected game mode from SelectModeRoute
data class SelectSaveRoute(val gameMode: String)

@Serializable
//get the selected save from SelectSaveRoute and pass the game mode
data class SelectCharacterRoute(val gameMode: String, val selectedSaveId: Int)

@Serializable
data class PvPRoute (val selectedSaveId: Int, val selectedCharacterId: String)

@Serializable
data class StoryRoute (val selectedSaveId: Int, val selectedCharacterId: String)

@Serializable
data class RogueRoute (val selectedSaveId: Int, val selectedCharacterId: String)

@Serializable object InGameMenuRoute

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
                    navStory = {
                        navController.navigate(SelectSaveRoute(GameMode.STORY.name))
                    },
                    navPvP = {
                        navController.navigate(SelectSaveRoute(GameMode.PVP.name))
                    },
                    navRogue = {
                        navController.navigate(SelectSaveRoute(GameMode.ROGUE.name))
                    },
                    navBack = { navController.popBackStack() }
                )
            }
            //SAVE SELECT
            composable<SelectSaveRoute> { backStackEntry ->
                val route = backStackEntry.toRoute<SelectSaveRoute>()
                val gameMode = route.gameMode
                saveMenu.open(
                    navCharSelect = { saveId ->
                        navController.navigate(
                            SelectCharacterRoute(
                                gameMode = gameMode,
                                selectedSaveId = saveId
                            )
                        )
                    },
                    navBack = { navController.popBackStack() }
                )
            }
            //CHARACTER SELECT
            composable<SelectCharacterRoute> { backStackEntry ->
                val route = backStackEntry.toRoute<SelectCharacterRoute>()
                val gameMode = route.gameMode
                val selectedSaveId = route.selectedSaveId
                val gameRoute = when (gameMode) {
                    //remember to change selectedCharacterId = "0"
                    GameMode.STORY.name -> StoryRoute(selectedSaveId, "0")
                    GameMode.PVP.name -> PvPRoute(selectedSaveId, "0")
                    GameMode.ROGUE.name -> RogueRoute(selectedSaveId, "0")
                    else -> ""
                }
                characterMenu.open(

                    navStoryMode = { navController.navigate(gameRoute)} ,
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