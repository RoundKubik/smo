package presentation.step_mode

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun StepModeScreen(viewModel: StepModeViewModel = StepModeViewModel()) {

    LazyColumn {
        item {
            Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                Row {
                    Button(
                        onClick = {
                            viewModel.nextStep()
                        },

                        modifier = Modifier.height(56.dp).width(380.dp).padding(start = 8.dp)
                    ) {
                        Text("Next step")
                    }
                }
            }
        }
        item {
            EventCalendarScreen(viewModel)
            BufferStateScreen(viewModel)
        }
    }
}

