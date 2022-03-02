package com.codemave.mobilecomputing.ui.reminder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.systemBarsPadding
import kotlinx.coroutines.launch

@Composable
fun Reminder(
    onBackPress: () -> Unit,
    viewModel: ReminderViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    val message = rememberSaveable { mutableStateOf("") }
    val day = rememberSaveable { mutableStateOf("") }
    val month = rememberSaveable { mutableStateOf("") }
    val year = rememberSaveable { mutableStateOf("") }
    val hour = rememberSaveable { mutableStateOf("") }
    val minute = rememberSaveable { mutableStateOf("") }

    var selectedIcon = rememberSaveable { mutableStateOf("") }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            TopAppBar {
                IconButton(
                    onClick = onBackPress
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(text = "Add Reminder")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = message.value,
                    onValueChange = { message.value = it },
                    label = { Text(text = "Reminder message")},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = day.value,
                    onValueChange = { day.value = it },
                    label = { Text(text = "Reminder day : dd")},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = month.value,
                    onValueChange = { month.value = it },
                    label = { Text(text = "Reminder month : mm")},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = year.value,
                    onValueChange = { year.value = it },
                    label = { Text(text = "Reminder year : yy")},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = hour.value,
                    onValueChange = { hour.value = it },
                    label = { Text(text = "Reminder hour : hh")},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = minute.value,
                    onValueChange = { minute.value = it },
                    label = { Text(text = "Reminder minute : mm")},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                CategoryListDropdown(
                    selectedIcon = selectedIcon
                )
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    enabled = true,
                    onClick = {
                        if(
                            day.value.length == 2
                            && month.value.length == 2
                            && year.value.length == 2
                            && hour.value.length == 2
                            && minute.value.length == 2
                        ) {
                            coroutineScope.launch {
                                viewModel.saveReminder(
                                    com.codemave.mobilecomputing.data.entity.Reminder(
                                        reminderMessage = message.value,
                                        location_x = "",
                                        location_y = "",
                                        //reminder time format ddmmyyhhmm
                                        reminder_time = day.value + month.value + year.value + hour.value + minute.value,
                                        creation_time = "",
                                        creator_id = 0,
                                        reminder_seen = 0,
                                        reminder_icon = selectedIcon.value
                                    )
                                )
                            }
                            onBackPress()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(55.dp)
                ) {
                    Text("Save reminder")
                }
            }
        }
    }
}


@Composable
fun CategoryListDropdown(selectedIcon :  MutableState<String>) {

    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded) {
        Icons.Filled.ArrowDropUp // requires androidx.compose.material:material-icons-extended dependency
    } else {
        Icons.Filled.ArrowDropDown
    }

    Column {
        OutlinedTextField(
            value = selectedIcon.value,
            onValueChange = { selectedIcon.value = it},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Icon") },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            iconList.forEach { dropDownOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedIcon.value = dropDownOption
                        expanded = false
                    },
                    enabled = true
                ) {
                    Text(text = dropDownOption)
                }

            }
        }
    }
}
