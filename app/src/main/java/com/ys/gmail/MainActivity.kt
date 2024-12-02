package com.ys.gmail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ys.coreui.component.DetailsAppBar
import com.ys.coreui.component.DrawerCategory
import com.ys.coreui.component.DrawerDivider
import com.ys.coreui.component.DrawerMenuItem
import com.ys.coreui.component.DrawerTitleItem
import com.ys.coreui.component.HomeAppBar
import com.ys.coreui.content.getDrawerItemsList
import com.ys.coreui.uimodel.DrawerData
import com.ys.gmail.navigation.EmailDetails
import com.ys.gmail.navigation.EmailList
import com.ys.gmail.ui.theme.GmailCloneTheme
import com.ys.presentation.emaildetails.EmailDetailsScreen
import com.ys.presentation.emaildetails.mvi.EmailDetailsViewModel
import com.ys.presentation.emaillist.EmailListScreen
import com.ys.presentation.emaillist.mvi.EmailListContract
import com.ys.presentation.emaillist.mvi.EmailListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(Modifier.safeDrawingPadding()) {
                GmailCloneTheme {
                    val navController = rememberNavController()
                    var topAppbarState by remember { mutableStateOf(TopAppbarState.HOME) }
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            DrawerContent()
                        },
                        scrimColor = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.4f),
                        content = {
                            Scaffold(
                                topBar = {
                                    when(topAppbarState) {
                                        TopAppbarState.HOME ->
                                            HomeAppBar(modifier = Modifier.padding(horizontal = 16.dp)) {
                                                scope.launch {
                                                    drawerState.open()
                                                }
                                            }
                                        TopAppbarState.DETAILS -> DetailsAppBar(navController = navController)
                                    }
                                },
                                bottomBar = {
                                    BottomAppBar(modifier = Modifier) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceEvenly
                                        ) {
                                            IconButton(onClick = {}) {
                                                Icon(
                                                    imageVector = Icons.Filled.Email,
                                                    contentDescription = "Email"
                                                )
                                            }
                                            IconButton(onClick = {}) {
                                                Icon(
                                                    imageVector = Icons.Filled.Videocam,
                                                    contentDescription = "Video Call"
                                                )
                                            }
                                        }
                                    }
                                }
                            ) { innerPadding ->
                                NavHost(
                                    navController = navController,
                                    startDestination = EmailList,
                                    modifier = Modifier.padding(innerPadding)
                                ) {
                                    composable<EmailList>(
                                        exitTransition = {
                                            return@composable slideOutOfContainer(
                                                AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                                            )
                                        },
                                        popEnterTransition = {
                                            return@composable slideIntoContainer(
                                                AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                                            )
                                        }
                                    ) {
                                        topAppbarState = TopAppbarState.HOME
                                        val viewModel: EmailListViewModel = hiltViewModel()
                                        val state by viewModel.state.collectAsState()
                                        val effect by viewModel.effect.collectAsState(initial = null)
                                        val dispatch: (EmailListContract.EmailListEvent) -> Unit = { event ->
                                            viewModel.event(event)
                                        }

                                        EmailListScreen(
                                            state = state,
                                            effect = effect,
                                            dispatch = dispatch
                                        ) { model ->
                                            navController.navigate(
                                                EmailDetails(
                                                    from = model.from,
                                                    profileImage = model.profileImage,
                                                    subject = model.subject,
                                                    isPromotional = model.isPromotional,
                                                    isStarred = model.isStarred
                                                )
                                            )
                                        }
                                    }

                                    composable<EmailDetails>(
                                        enterTransition = {
                                            return@composable slideIntoContainer(
                                                AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                                            )
                                        },
                                        popExitTransition = {
                                            return@composable slideOutOfContainer(
                                                AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                                            )
                                        }
                                    ) {
                                        topAppbarState = TopAppbarState.DETAILS
                                        val args = it.toRoute<EmailDetails>()
                                        val viewModel: EmailDetailsViewModel = hiltViewModel()
                                        val state by viewModel.state.collectAsState()

                                        EmailDetailsScreen(
                                            state = state,
                                            from = args.from,
                                            profileImage = args.profileImage,
                                            subject = args.subject,
                                            isPromotional = args.isPromotional,
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun DrawerContent() {
        val drawerItemList: List<DrawerData> = getDrawerItemsList()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.8f)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(drawerItemList) {
                    when (it) {
                        is DrawerData.Category -> DrawerCategory(it.title)
                        DrawerData.Divider -> DrawerDivider()
                        is DrawerData.Item -> DrawerMenuItem (it.title, it.icon) {}
                        is DrawerData.Title -> DrawerTitleItem(it.title)
                    }
                }
            }
        }
    }
}