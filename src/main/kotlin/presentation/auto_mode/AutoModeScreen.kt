package presentation.auto_mode

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.device_statistics.DeviceStateScreen
import presentation.source_statistics.SourceStatisticsScreen


@Composable
fun AutoModeScreen(viewModel: AutoModeViewModel = AutoModeViewModel()) {
    LazyColumn {

        item {
            Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                                 viewModel.start()
                }, modifier = Modifier.height(56.dp).width(380.dp).padding(end = 8.dp)) {
                    Text(
                        "Start emulation"
                    )
                }
              /*  Button(onClick = {}, modifier = Modifier.height(56.dp).width(380.dp).padding(end = 8.dp)) {
                    Text(
                        "Stop emulation"
                    )
                }*/
            }

        }
        item {

            Box(modifier = Modifier.fillMaxWidth().padding(top = 32.dp), contentAlignment = Alignment.Center) {
                Text(
                    "Source statistics:"
                )
            }
        }
        item {
            SourceStatisticsScreen(viewModel)
        }
        item {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    "Device statistics:"
                )
            }
        }
        item {
            DeviceStateScreen(viewModel)
        }
    }
}
