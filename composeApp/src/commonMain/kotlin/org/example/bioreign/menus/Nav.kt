package org.example.bioreign.menus

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.bioreign.gameLoop
import org.example.bioreign.gamemodes.Online
import org.example.bioreign.gamemodes.Rogue
import org.example.bioreign.gamemodes.StoryMode

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
        NavHost(navController = navController, startDestination = Screens.Home.route) {
            //HOME
            composable(Screens.Home.route) {
                homeMenu.open(
                    navModeMenu = { navController.navigate(Screens.SelectMode.route) },
                    navKeysMenu = { navController.navigate(Screens.EditKeys.route) },
                    navSettingsMenu = { navController.navigate(Screens.Settings.route) }
                )
            }
            //SETTINGS
            composable(Screens.Settings.route) {
                settingsMenu.open(
                    navBack = { navController.popBackStack() }
                )
            }
            //MODE SELECT
            composable(Screens.SelectMode.route) {
                modeMenu.open(
                    navStory = { navController.navigate(Screens.Story.route); gameLoop.isPlaying = true },
                    navPvP = { navController.navigate(Screens.PvP.route) },
                    navRogue = { navController.navigate(Screens.Rogue.route) },
                    navBack = { navController.popBackStack() }
                )
            }
            //SAVE SELECT
            composable(Screens.SelectSave.route) {
                saveMenu.open(
                    navCharSelect = { navController.navigate(Screens.SelectCharacter.route) },
                    navBack = { navController.popBackStack() }
                )
            }
            //CHARACTER SELECT
            composable(Screens.SelectCharacter.route) {
                characterMenu.open(
                    navStoryMode = { navController.navigate(Screens.InGameMenu.route) },
                    navBack = { navController.popBackStack() }
                )
            }
            //GAME MENU
            composable(Screens.InGameMenu.route) {
                gameMenu.open()
            }
            //EDIT KEYS
            composable(Screens.EditKeys.route) {
                editKeysMenu.open(
                    navBack = { navController.popBackStack() }
                )
            }
            //STORY MODE
            composable(Screens.Story.route) {
                storyMode.play(
                    toHomeMenu = { navController.navigate(Screens.Home.route) }
                )
            }
            //ONLINE/PvP MODE
            composable(Screens.PvP.route) {
                online.play()
            }
            //ROGUELIKE MODE
            composable(Screens.Rogue.route) {
                rogue.play(
                    toHomeMenu = { navController.navigate(Screens.Home.route) }
                )
            }
        }
    }
}

enum class Screens(val route: String) {
    Home("Home"),
    Settings("Settings"),
    EditKeys("EditKeys"),
    SelectMode("SelectMode"),
    SelectSave("SelectSave"),
    SelectCharacter("SelectCharacter"),
    InGameMenu("InGameMenu"),
    PvP("PvP"),
    Story("Story"),
    Rogue("Rogue");
}