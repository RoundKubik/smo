package presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.model.Settings

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = SettingsViewModel()) {

    val stateCountSources = remember { mutableStateOf(Config.SOURCES_COUNT.toString()) }
    val stateCountDevices = remember { mutableStateOf(Config.DEVICES_COUNT.toString()) }
    val stateBufferSize = remember { mutableStateOf(Config.BUFFER_SIZE.toString()) }
    val stateRequestCount = remember { mutableStateOf(Config.REQUEST_COUNT.toString()) }
    val stateA = remember { mutableStateOf(Config.a.toString()) }
    val stateB = remember { mutableStateOf(Config.b.toString()) }
    val stateLambda = remember { mutableStateOf(Config.lambda.toString()) }

    LazyColumn(
        Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.Start,

        ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(60.dp)) {
                Text("Count of sources:", modifier = Modifier.weight(1f).padding(end = 16.dp))
                TextField(
                    value = stateCountSources.value, onValueChange = { value ->
                        stateCountSources.value = value.filter { it.isDigit() }
                    },
                    maxLines = 1
                )

            }
            Divider()
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(60.dp)) {
                Text("Count of devices:", modifier = Modifier.weight(1f).padding(end = 16.dp))
                TextField(
                    value = stateCountDevices.value, onValueChange = { value ->
                        stateCountDevices.value = value.filter { it.isDigit() }
                    },
                    maxLines = 1
                )
            }
            Divider()
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(60.dp)) {
                Text("Buffer size:", modifier = Modifier.weight(1f).padding(end = 16.dp))
                TextField(
                    value = stateBufferSize.value, onValueChange = { value ->
                        stateBufferSize.value = value.filter { it.isDigit() }
                    },
                    maxLines = 1
                )
            }
            Divider()
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Requests count:", modifier = Modifier.weight(1f).padding(end = 16.dp))
                TextField(
                    value = stateRequestCount.value, onValueChange = { value ->
                        stateRequestCount.value = value.filter { it.isDigit() }
                    },
                    maxLines = 1
                )
            }
            Divider()
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(60.dp)) {
                Text(
                    "Min for uniform distribution of requests from source:",
                    modifier = Modifier.weight(1f).padding(end = 16.dp)
                )
                TextField(
                    value = stateA.value, onValueChange = { value ->
                        stateA.value = value.filter { it.isDigit() or (it == '.') }
                    },
                    maxLines = 1
                )
            }
            Divider()
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(60.dp)) {
                Text(
                    "Max for uniform distribution of request from source",
                    modifier = Modifier.weight(1f).padding(end = 16.dp)
                )
                TextField(
                    value = stateB.value, onValueChange = { value ->
                        stateB.value = value.filter { it.isDigit() or (it == '.') }
                    },
                    maxLines = 1
                )
            }
            Divider()
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(60.dp)) {
                Text(
                    "Lambda parameter for log distribution of work for device:",
                    modifier = Modifier.weight(1f).padding(end = 16.dp)
                )
                TextField(
                    value = stateLambda.value, onValueChange = { value ->
                        stateLambda.value = value.filter { it.isDigit() or (it == '.') }
                    },
                    maxLines = 1
                )
            }
        }
        item {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = {
                        viewModel.saveSettings(
                            Settings(
                                stateCountSources.value.toInt(),
                                stateCountDevices.value.toInt(),
                                stateBufferSize.value.toInt(),
                                stateRequestCount.value.toInt(),
                                stateA.value.toDouble(),
                                stateB.value.toDouble(),
                                stateLambda.value.toDouble()
                            )
                        )
                    },

                    modifier = Modifier.height(56.dp).width(380.dp)
                ) {
                    Text("Save system model settings")
                }
            }

        }

    }

}