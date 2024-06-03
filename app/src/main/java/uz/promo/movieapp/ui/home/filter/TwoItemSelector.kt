package uz.promo.movieapp.ui.home.filter

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.promo.movieapp.ui.home.ContentWrapper
import uz.promo.movieapp.ui.theme.MovieAppTheme

enum class SelectedButton {
    NONE, FIRST, SECOND
}

@Composable
fun HorizontalOptionsSelector(
    selectedItem: (SelectedButton) -> Unit,
    firstItem: String,
    secondItem: String,
    filterName: String,
    preselectedItems: SelectedButton
) {
    var selectedButton by remember { mutableStateOf(preselectedItems) }

    ContentWrapper(text = filterName) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    selectedButton = SelectedButton.FIRST
                    selectedItem.invoke(SelectedButton.FIRST)
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = if (selectedButton == SelectedButton.FIRST) Color.White else Color.Black,
                    containerColor = if (selectedButton == SelectedButton.FIRST) Color.Blue else Color.Transparent
                )
            ) {
                Text(
                    text = firstItem,
                    modifier = Modifier.padding(start = 8.dp),
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    selectedButton = SelectedButton.SECOND
                    selectedItem.invoke(SelectedButton.SECOND)
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = if (selectedButton == SelectedButton.SECOND) Color.White else Color.Black,
                    containerColor = if (selectedButton == SelectedButton.SECOND) Color.Blue else Color.Transparent
                )
            ) {
                Text(
                    text = secondItem,
                    modifier = Modifier.padding(start = 8.dp),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun HorizontalOptionsSelectorPreview() {
    MovieAppTheme {
        HorizontalOptionsSelector(
            filterName = "Select Gender",
            firstItem = "Male",
            secondItem = "Female",
            preselectedItems = SelectedButton.SECOND,
            selectedItem = {}
        )
    }
}
