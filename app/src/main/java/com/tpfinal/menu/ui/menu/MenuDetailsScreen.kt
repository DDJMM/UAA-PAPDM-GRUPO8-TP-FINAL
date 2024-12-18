package com.tpfinal.menu.ui.menu

import com.tpfinal.menu.R
import com.tpfinal.menu.ui.navigation.NavigationDestination

object MenuDetailsDestination : NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.item_detail_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}