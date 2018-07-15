package com.binarybricks.coiny.adapterdelegates

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binarybricks.coiny.components.DashboardCoinModule
import com.binarybricks.coiny.components.ModuleItem
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by Pranay Airan
 * Adapter delegate that takes care of coin row in dashboard.
 */

class DashboardCoinAdapterDelegate(private val toCurrency: String) : AdapterDelegate<List<ModuleItem>>() {
    private val dashboardCoinModule by lazy {
        DashboardCoinModule(toCurrency)
    }

    override fun isForViewType(items: List<ModuleItem>, position: Int): Boolean {
        return items[position] is DashboardCoinModule.DashboardCoinModuleData
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val dashboardCoinModuleView = dashboardCoinModule.init(LayoutInflater.from(parent.context), parent)
        return DashboardCoinViewHolder(dashboardCoinModuleView, dashboardCoinModule)
    }

    override fun onBindViewHolder(items: List<ModuleItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: List<Any>) {
        val aboutCoinViewHolder = holder as DashboardCoinViewHolder
        aboutCoinViewHolder.showCoinInfo(items[position] as DashboardCoinModule.DashboardCoinModuleData)
    }

    class DashboardCoinViewHolder(override val containerView: View, private val dashboardCoinModule: DashboardCoinModule)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun showCoinInfo(dashboardCoinModuleData: DashboardCoinModule.DashboardCoinModuleData) {
            dashboardCoinModule.showCoinInfo(itemView, dashboardCoinModuleData)
        }
    }
}