import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class TextBoxViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    // State for the text field, with initial value retrieved from SavedStateHandle if present
    var text by mutableStateOf(savedStateHandle["text"] ?: "")
        private set

    // Update the text state and store it in SavedStateHandle
    fun onTextChange(newText: String) {
        text = newText
        savedStateHandle["text"] = newText
    }

    // Factory for creating the ViewModel with SavedStateHandle
    companion object {
        fun provideFactory(
            savedStateHandle: SavedStateHandle
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(TextBoxViewModel::class.java)) {
                    return TextBoxViewModel(savedStateHandle) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}
