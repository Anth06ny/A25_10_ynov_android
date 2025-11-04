package com.example.a25_10_ynov_android

import android.content.Context
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.core.app.ApplicationProvider
import com.example.a25_10_ynov_android.ui.screens.SearchScreen
import com.example.a25_10_ynov_android.viewmodel.MainViewModelTest
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    //Création de l'environnement  de Test pour Compose
    @get:Rule
    val composeTestRule = createComposeRule()

    //Si besoin du context pour les resources de l'application par exemple
    val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun testErrorStateDisplayed() {
        //On charge un ViewModel configuré dans un état
        val viewModel = MainViewModelTest().apply { errorState() }
        //On charge le composable à tester
        composeTestRule.setContent {
            SearchScreen(mainViewModel = viewModel)
        }

        // Message d'erreur visible
        composeTestRule.onNodeWithText(MainViewModelTest.ERROR_MESSAGE_TEST).assertIsDisplayed()

        // Vérifie que l'indicateur de chargement n'est pas visible
        val semantic = SemanticsMatcher.keyIsDefined(SemanticsProperties.ProgressBarRangeInfo)
        assertTrue(composeTestRule.onAllNodes(semantic, true).fetchSemanticsNodes().isEmpty())

        //La liste n'affiche aucun élément
        composeTestRule.onAllNodesWithText("Nice", substring = true, ignoreCase = true).assertCountEquals(viewModel.dataList.value.size)
    }

    @Test
    fun testLoadingStateDisplayed() {
        //On charge un ViewModel configuré dans un état
        val viewModel = MainViewModelTest().apply { loadingState() }
        //On charge le composable à tester
        composeTestRule.setContent {
            SearchScreen(mainViewModel = viewModel)
        }

        // Message d'erreur visible
        composeTestRule.onNodeWithText(MainViewModelTest.ERROR_MESSAGE_TEST).assertDoesNotExist()

        // Vérifie que l'indicateur de chargement n'est pas visible
        val semantic = SemanticsMatcher.keyIsDefined(SemanticsProperties.ProgressBarRangeInfo)
        assertTrue(composeTestRule.onAllNodes(semantic, true).fetchSemanticsNodes().isNotEmpty())

        //La liste n'affiche aucun élément
        composeTestRule.onAllNodesWithText("Nice", substring = true, ignoreCase = true).assertCountEquals(viewModel.dataList.value.size)
    }

    @Test
    fun testSuccessStateDisplayed() {
        //On charge un ViewModel configuré dans un état
        val viewModel = MainViewModelTest().apply { successState() }
        //On charge le composable à tester
        composeTestRule.setContent {
            SearchScreen(mainViewModel = viewModel)
        }

        // Message d'erreur visible
        composeTestRule.onNodeWithText(MainViewModelTest.ERROR_MESSAGE_TEST).assertDoesNotExist()

        // Vérifie que l'indicateur de chargement n'est pas visible
        val semantic = SemanticsMatcher.keyIsDefined(SemanticsProperties.ProgressBarRangeInfo)
        assertTrue(composeTestRule.onAllNodes(semantic, true).fetchSemanticsNodes().isEmpty())

        //La liste n'affiche aucun élément
        composeTestRule.onAllNodesWithText("Nice", substring = true, ignoreCase = true).assertCountEquals(viewModel.dataList.value.size)
    }

    @Test
    fun testSearchWeathersSuccess() {
        val viewModel = MainViewModelTest()

        //On charge le composable à tester
        composeTestRule.setContent {
            SearchScreen(mainViewModel = viewModel)
        }

        //Injecte du texte dans le TextField
        composeTestRule
            .onNodeWithText("Enter text")
            //Plus propre
            //.onNodeWithText(context.getText(R.string.searchtext_placeholder).toString())
            .performTextReplacement("Nice")

        //Simule un clic un bouton
        composeTestRule.onNodeWithText(context.getText(R.string.bt_load_data).toString()).performClick()

        composeTestRule.onNodeWithText(MainViewModelTest.ERROR_MESSAGE_TEST).assertDoesNotExist()

        // Vérifie que l'indicateur de chargement n'est pas visible
        val semantic = SemanticsMatcher.keyIsDefined(SemanticsProperties.ProgressBarRangeInfo)
        assertTrue(composeTestRule.onAllNodes(semantic, true).fetchSemanticsNodes().isEmpty())

        //Regarde s'il y a bien le bon nombre de résultat
        composeTestRule.onAllNodesWithText(
            text = "Nice"
            , substring = true, ignoreCase =  true)
            .assertCountEquals(viewModel.dataList.value.count() + 1)
    }
}