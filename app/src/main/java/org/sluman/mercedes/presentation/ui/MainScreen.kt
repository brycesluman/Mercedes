package org.sluman.mercedes.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.sluman.mercedes.R
import org.sluman.mercedes.presentation.UserDetailViewModel
import org.sluman.mercedes.presentation.UserViewModel

//enum class MainScreen(@StringRes val title: Int) {
//    UserList(title = R.string.user_list),
//    Detail(title = R.string.detail),
//}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainAppScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = backStackEntry?.destination?.route ?: UserList::class

    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    val topBarTitle = rememberSaveable { (mutableStateOf("")) }
    topBarState.value = "class $currentScreen" == UserList::class.toString()

    Scaffold(topBar = {
        AppBar(
            navigateUp = { navController.navigateUp() },
            topBarState = topBarState,
            topBarTitle = topBarTitle
        )
    }) { innerPadding ->
        SharedTransitionLayout {
            NavHost(
                navController = navController,
                startDestination = UserList,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                composable<UserList> {
                    val userViewModel: UserViewModel = hiltViewModel()
                    UsersScreen(
                        onItemClicked = {
                            topBarTitle.value = it
                            navController.navigate(
                                Detail(
                                    userId = it
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxSize(),
                        state = userViewModel.uiState.collectAsState(),
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable
                    )
                }
                composable<Detail> { entry ->
                    val args = entry.toRoute<Detail>()
                    val userViewModel: UserDetailViewModel = hiltViewModel()
                    LaunchedEffect(args.userId) {
                        userViewModel.getUserDetails(args.userId)
                    }
                    DetailsScreen(
                        modifier = Modifier
                            .fillMaxSize(),
                        state = userViewModel.uiState.collectAsState(),
                        userId = args.userId,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable
                    )
                }
            }
        }
    }
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    topBarState: MutableState<Boolean>,
    topBarTitle: MutableState<String>
) {
    TopAppBar(
        title = {
            Row() {
                if (topBarState.value) {
                    Image(
                        painter = painterResource(R.drawable.mercedes_logo),
                        contentDescription = "logo",
                        modifier = Modifier.size(72.dp)
                    )
                } else {
                    Text(text = topBarTitle.value)
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier,
        navigationIcon = {
            if (!topBarState.value) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


@Serializable
object UserList

@Serializable
data class Detail(
    val userId: String
)
