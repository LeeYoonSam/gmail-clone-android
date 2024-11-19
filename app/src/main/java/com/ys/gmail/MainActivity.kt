package com.ys.gmail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ys.coreui.component.DetailsAppBar
import com.ys.coreui.component.HomeAppBar
import com.ys.gmail.navigation.EmailDetails
import com.ys.gmail.navigation.EmailList
import com.ys.gmail.ui.theme.GmailCloneTheme
import com.ys.presentation.emaildetails.EmailDetailsScreen
import com.ys.presentation.emaillist.EmailListScreen
import dagger.hilt.android.AndroidEntryPoint

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

                    Scaffold(
                        topBar = {
                            when(topAppbarState) {
                                TopAppbarState.HOME -> HomeAppBar(modifier = Modifier.padding(horizontal = 16.dp))
                                TopAppbarState.DETAILS -> DetailsAppBar(navController = navController)
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = EmailList,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable<EmailList> {
                                topAppbarState = TopAppbarState.HOME
                                EmailListScreen { model ->
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

                            composable<EmailDetails> {
                                topAppbarState = TopAppbarState.DETAILS
                                val args = it.toRoute<EmailDetails>()
                                EmailDetailsScreen(
                                    from = args.from,
                                    profileImage = args.profileImage,
                                    subject = args.subject,
                                    isPromotional = args.isPromotional,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}