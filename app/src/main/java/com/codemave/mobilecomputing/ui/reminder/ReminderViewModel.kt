package com.codemave.mobilecomputing.ui.reminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codemave.mobilecomputing.Graph
import com.codemave.mobilecomputing.data.entity.Reminder
import com.codemave.mobilecomputing.data.repository.ReminderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReminderViewModel(
    private val reminderRepository: ReminderRepository = Graph.reminderRepository
): ViewModel() {
    private val _state = MutableStateFlow(ReminderViewState())

    val state: StateFlow<ReminderViewState>
        get() = _state

    suspend fun saveReminder(reminder: Reminder): Long {
        return reminderRepository.addReminder(reminder)
    }

    fun reminder(id : Long): Reminder? {
        return reminderRepository.reminder(id)
    }

    init {
        viewModelScope.launch {
            reminderRepository.reminders().collect { reminders ->
                _state.value = ReminderViewState(reminders)
            }
        }
    }
}

data class ReminderViewState(
    val reminders: List<Reminder> = emptyList()
)

val iconList = listOf("Sports","Work","Restaurant")

