package org.example.bioreign.menus

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.bioreign.gamemodes.Online
import org.example.bioreign.gamemodes.Rogue
import org.example.bioreign.gamemodes.StoryMode

class Nav {
    val homeMenu = HomeMenu()
    val settingsMenu = SettingsMenu()
    val editKeysMenu = EditKeysMenu()
    val modeMenu = ModeMenu()
    val characterMenu = CharacterMenu()

    val gameMenu = InGameMenu()

    val storyMode = StoryMode()
    val online = Online()
    val rogue = Rogue()

    @Composable
    fun activate() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screens.Home.route) {
            composable(Screens.Home.route) {
                homeMenu.open(
                    navModeMenu = { navController.navigate(Screens.SelectMode.route) },
                    navKeysMenu = { navController.navigate(Screens.EditKeys.route) },
                    navSettingsMenu = { navController.navigate(Screens.Settings.route) }
                )
            }
            composable(Screens.Settings.route) {
                settingsMenu.open(
                    navBack = { navController.popBackStack() }
                )
            }
            composable(Screens.SelectMode.route) {
                modeMenu.open(
                    navStory = { navController.navigate(Screens.SelectCharacter.route) },
                    navPvP = { navController.navigate(Screens.SelectCharacter.route) },
                    navRogue = { navController.navigate(Screens.InGame.route) },
                    navBack = { navController.popBackStack() }
                )
            }
            composable(Screens.SelectCharacter.route) {
                characterMenu.open(
                    navStoryMode = { navController.navigate(Screens.InGame.route) },
                    navBack = { navController.popBackStack() }
                )
            }
            composable(Screens.InGame.route) {
                gameMenu.open()
            }
            composable(Screens.EditKeys.route) {
                editKeysMenu.open(
                    navBack = { navController.popBackStack() }
                )
            }
            composable(Screens.Story.route) {
                storyMode.play(
                    toHomeMenu = { navController.navigate(Screens.Home.route) }
                )
            }
            composable(Screens.PvP.route) {
                online.play()
            }
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
    SelectCharacter("SelectCharacter"),
    InGame("InGame"),
    PvP("PvP"),
    Story("Story"),
    Rogue("Rogue")
}