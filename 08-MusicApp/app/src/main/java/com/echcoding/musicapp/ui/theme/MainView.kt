package com.echcoding.musicapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.echcoding.musicapp.MainViewModel
import com.echcoding.musicapp.R
import com.echcoding.musicapp.Screen
import com.echcoding.musicapp.screensInBottomBar
import com.echcoding.musicapp.screensInDrawer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(){

    // Initialize the drawer state
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // Initialize the coroutine scope
    val scope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()

    // Allow us to find out on which screen we are
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val dialogOpen = remember {
        mutableStateOf(false)
    }

    val currentScreen = remember {
        viewModel.currentScreen.value
    }
    val title = remember {
        // Change that to currentScreen.title
        mutableStateOf(currentScreen.title)
    }

    // Variables used by the ModalBottomSheet
    var showBottomSheet by remember { mutableStateOf(false) }
    val isSheetFullScreen by remember { mutableStateOf(false) }
    val modifier = if(isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
    val modalSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = { true }
    )
    val roundedCornerRadius = if(isSheetFullScreen) 0.dp else 12.dp
    // Helper function to handle smooth animated closing
    val closeBottomSheet = {
        scope.launch {
            modalSheetState.hide()
        }.invokeOnCompletion {
            if(!modalSheetState.isVisible){
                showBottomSheet = false
            }
        }
    }


    // In current version, use a ModalNavigationDrawer and wrap the scaffold inside of it
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                LazyColumn(Modifier.padding(16.dp)) {
                    items(screensInDrawer) { item ->
                        DrawerItem(selected = currentRoute == item.route, item = item) {
                            scope.launch {
                                drawerState.close()
                            }
                            if(item.route == "add_account") {
                                // Open dialog
                                dialogOpen.value = true
                            }else{
                                controller.navigate(item.route)
                                title.value = item.title
                            }
                        }
                    }
                }
            }
        }
    ) {
        val bottomBar: @Composable () -> Unit = {
            if(currentScreen is Screen.DrawerScreen || currentScreen is Screen.BottomScreen.Home ){
                NavigationBar(
                    Modifier.wrapContentSize()
                ) {
                    screensInBottomBar.forEach {item ->
                        val isSelected = currentRoute == item.route
                        val tint = if(isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inverseSurface
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                controller.navigate(item.route)
                                      title.value = item.title
                                      },
                            icon = {

                                Icon(painterResource(id = item.bIcon),
                                    contentDescription = item.title,
                                    tint = tint)
                            },
                            label = { Text(item.title, color = tint ) },
                            colors = NavigationBarItemColors(
                                selectedIndicatorColor = Color.White,
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                unselectedIconColor = MaterialTheme.colorScheme.secondary,
                                unselectedTextColor = MaterialTheme.colorScheme.secondary,
                                disabledIconColor = Color.DarkGray,
                                disabledTextColor = Color.DarkGray,
                            )
                        )
                    }
                }

            }
        }

        Scaffold(
            bottomBar = {
                bottomBar()
            },
            topBar = {
                TopAppBar(
                    title = { Text(title.value) },
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    showBottomSheet = true
                                    /*
                                    if(modalSheetState.isVisible)
                                        modalSheetState.hide()
                                    else
                                        modalSheetState.show()
                                    */
                                }
                            }
                        ) {
                            Icon(painterResource(id = R.drawable.ic_more_vert), contentDescription = "More")
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            // Open the drawer safely in the coroutine scope
                            scope.launch {
                                drawerState.open()
                            }
                        }){
                            Icon(painterResource(id = R.drawable.ic_account_circle), contentDescription = "Menu")
                        }}
                )
            }
        ) {
            Navigation(
                navController = controller,
                viewModel = viewModel,
                pd = it
            )

            AccountDialog(dialogOpen = dialogOpen)

            if(showBottomSheet) {
                ModalBottomSheet(
                    sheetState = modalSheetState,
                    shape = RoundedCornerShape(
                        topStart = roundedCornerRadius,
                        topEnd = roundedCornerRadius
                    ),
                    onDismissRequest = { showBottomSheet = false }
                ) {
                    MoreBottomSheet(modifier = modifier)
                }
            }
        }

    }


}

@Composable
fun MoreBottomSheet(modifier: Modifier = Modifier){
    Box(Modifier.fillMaxWidth()
        .height(300.dp)
        .background(MaterialTheme.colorScheme.surfaceContainer)
    ){
        Column(modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = modifier.padding(16.dp)){
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Settings",
                    Modifier.padding(end = 8.dp))
                Text(text = "Settings", fontSize = 20.sp, color = Color.Black)

            }

            Row(modifier = modifier.padding(16.dp)){
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = "Share",
                    Modifier.padding(end = 8.dp))
                Text(text = "Share", fontSize = 20.sp, color = Color.Black)

            }

            Row(modifier = modifier.padding(16.dp)){
                Icon(
                    painter = painterResource(id = R.drawable.ic_help),
                    contentDescription = "Help",
                    Modifier.padding(end = 8.dp))
                Text(text = "Help", fontSize = 20.sp, color = Color.Black)

            }
        }

    }
}

@Preview
@Composable
fun MoreBottomSheetPreview(){
    MoreBottomSheet(Modifier.fillMaxSize())
}


@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit,
){
    val backgroundColor = if(selected) Color.DarkGray else Color.White
    Row(
        Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp).background(backgroundColor)
            .clickable { onDrawerItemClicked() }
    ) {
        Icon(
            painter = painterResource(id = item.dIcon),
            contentDescription = item.title,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(text = item.title, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues){
    NavHost(navController = navController as NavHostController,
        startDestination = Screen.DrawerScreen.Account.route,
        modifier = Modifier.padding(pd)){

        composable(Screen.DrawerScreen.Account.route){
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route){
            Subscription()
        }
        composable(Screen.BottomScreen.Home.route){
            HomeView()
        }
        composable(Screen.BottomScreen.Browse.route){
            BrowserView()
        }
        composable(Screen.BottomScreen.Library.route){
            LibraryView()
        }
    }
}