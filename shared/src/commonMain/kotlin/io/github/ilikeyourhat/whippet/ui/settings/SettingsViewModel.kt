package io.github.ilikeyourhat.whippet.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import io.github.ilikeyourhat.whippet.ui.navigation.Navigator
import kotlinx.coroutines.launch

@Inject
@ViewModelKey
@ContributesIntoMap(AppScope::class)
class SettingsViewModel(
    val navigator: Navigator
) : ViewModel() {

    fun onGitHubLinkClick() {
        viewModelScope.launch {
            navigator.openLink("https://github.com/ILikeYourHat/Whippet")
        }
    }
}
