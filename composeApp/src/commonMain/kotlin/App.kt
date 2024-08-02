import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
@Preview
fun App() {

    var duration by remember {
        mutableStateOf(0L.toDuration(DurationUnit.SECONDS))
    }
    LaunchedEffect(
        Unit,
    ) {
        while (true) {

            duration = Duration.ZERO
            duration += 1L.toDuration(DurationUnit.SECONDS)
            delay(1000L)
        }
    }
    MaterialTheme {

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(
                cities,
            ) { city ->


                Box(
                    Modifier.wrapContentWidth()
                        .border(1.dp, color = Color.LightGray)
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(6.dp)
                    ) {
                        Text(
                            city.name,
                            fontWeight = FontWeight.Bold,
                        )
                        HorizontalDivider()
                        Text(
                            city.dateTime(duration = duration).HH_mm_ss,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                            )
                        )
                        Text(
                            city.dateTime(duration = duration).dMy,
                        )
                    }
                }

            }
        }
    }
}