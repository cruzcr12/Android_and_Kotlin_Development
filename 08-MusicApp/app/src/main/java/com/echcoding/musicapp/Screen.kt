package com.echcoding.musicapp

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {

    sealed class BottomScreen(bTitle: String, bRoute: String, @DrawableRes val bIcon: Int)
        :Screen(bTitle, bRoute){
        object Home: BottomScreen(
            bTitle = "Home",
            bRoute = "home",
            bIcon = R.drawable.ic_music_note
        )
        object Library: BottomScreen(
            bTitle = "Library",
            bRoute = "library",
            bIcon = R.drawable.ic_music_library
        )
        object Browse: BottomScreen(
            bTitle = "Browse",
            bRoute = "browse",
            bIcon = R.drawable.ic_search
        )
    }

    sealed class DrawerScreen(dTitle: String, dRoute: String, @DrawableRes val dIcon: Int)
        : Screen(dTitle, dRoute)
    {
        object Account: DrawerScreen(
            dTitle = "Account",
            dRoute = "account",
            dIcon = R.drawable.ic_account_circle
        )
        object Subscription: DrawerScreen(
            dTitle = "Subscription",
            dRoute = "subscribe",
            dIcon = R.drawable.ic_subscription
        )
        object AddAccount: DrawerScreen(
            dTitle = "Add Account",
            dRoute = "add_account",
            dIcon = R.drawable.ic_person_add
        )

    }
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)

val screensInBottomBar = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Browse,
    Screen.BottomScreen.Library
)