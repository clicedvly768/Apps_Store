package dev.clicedvly768.appsstore

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import dev.clicedvly768.appsstore.databinding.ActivityMainBinding

data class OpenSourceApp(
    val id: String,
    val name: String,
    val developer: String,
    val license: String,  
    val rating: Float,
    val category: String,
    val repoUrl: String, 
    val lastUpdated: String, 
    val stars: Int  
) {
    val description: Any
        get() {
            TODO()
        }
}

class OpenSourceAppStore {
    private val apps = mutableListOf<OpenSourceApp>()
    private val categories = mutableSetOf<String>()

    fun addApp(app: OpenSourceApp) {
        apps.add(app)
        categories.add(app.category)
    }


    fun getAllApps(): List<OpenSourceApp> = apps.toList()
    
    fun searchApps(query: String): List<OpenSourceApp> {
        return apps.filter {
            it.name.contains(query, ignoreCase = true) == true ||
                    it.developer.contains(query, ignoreCase = true) == true ||
                    it.description?.contains(query, ignoreCase = true) ?: false
        }
    }
    
    fun getAppsByCategory(category: String): List<OpenSourceApp> {
        return apps.filter { it.category.equals(category, ignoreCase = true) }
    }
    
    fun getAllCategories(): Set<String> = categories
    
    fun getTopRatedApps(limit: Int = 10): List<OpenSourceApp> {
        return apps.sortedByDescending { it.rating }.take(limit)
    }
    
    fun getMostStarredApps(limit: Int = 10): List<OpenSourceApp> {
        return apps.sortedByDescending { it.stars }.take(limit)
    }
    
    fun getRecentlyUpdatedApps(limit: Int = 10): List<OpenSourceApp> {
        return apps.sortedByDescending { it.lastUpdated }.take(limit)
    }
    
    fun getAppsByLicense(license: String): List<OpenSourceApp> {
        return apps.filter { it.license.equals(license, ignoreCase = true) }
    }
}

private fun Any.contains(query: String, ignoreCase: Boolean): Boolean? {
    TODO("Not yet implemented")
}

fun main() {
    val store = OpenSourceAppStore()
    
    store.addApp(
        OpenSourceApp(
            "1",
            "Signal",
            "Signal Foundation",
            "GPL-3.0",
            4.8f,
            "Communication",
            "https://github.com/signalapp/Signal-Android",
            "2023-05-15",
            24500
        )
    )

    store.addApp(
        OpenSourceApp(
            "2",
            "VLC",
            "VideoLAN",
            "GPL-2.0",
            4.7f,
            "Media",
            "https://github.com/videolan/vlc-android",
            "2023-06-01",
            18900
        )
    )

    store.addApp(
        OpenSourceApp(
            "3",
            "Nextcloud",
            "Nextcloud GmbH",
            "AGPL-3.0",
            4.5f,
            "Productivity",
            "https://github.com/nextcloud/android",
            "2023-05-28",
            3200
        )
    )

    // Примеры запросов
    println("Все категории: ${store.getAllCategories()}")

    println("\nТоп приложений:")
    store.getTopRatedApps(3).forEach { println("- ${it.name} (${it.rating})") }

    println("\nНедавно обновленные:")
    store.getRecentlyUpdatedApps(2).forEach { println("- ${it.name} (${it.lastUpdated})") }

    println("\nПриложения с лицензией GPL:")
    store.getAppsByLicense("GPL").forEach { println("- ${it.name}") }
}
