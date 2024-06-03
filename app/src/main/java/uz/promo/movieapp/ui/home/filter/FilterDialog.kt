package uz.promo.movieapp.ui.home.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.promo.movieapp.ui.theme.MovieAppTheme


data class ModelFilter(
    val gender: SelectedButton = SelectedButton.NONE,
    val mediaType: SelectedButton = SelectedButton.NONE,
    val lang: List<String> = emptyList()
)


@Composable
fun BottomSheetContent(
    onClickListen: (ModelFilter) -> Unit,
    preselectedValues: ModelFilter
) {

    var gender by remember {
        mutableStateOf(preselectedValues.gender)
    }

    var mediaType by remember {
        mutableStateOf(preselectedValues.mediaType)
    }


    val selectedLanguages = remember {
        mutableStateOf(preselectedValues.lang)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text("Filter", style = MaterialTheme.typography.headlineMedium)
        HorizontalOptionsSelector(
            selectedItem = {
                gender = it
            },
            firstItem = "Female",
            secondItem = "Male",
            filterName = "Select Gender",
            gender
        )

        HorizontalOptionsSelector(
            selectedItem = {
                mediaType = it
            },
            filterName = "Media Type",
            firstItem = "TV",
            secondItem = "Movie",
            preselectedItems = mediaType
        )

        MultipleChoiceDialog(
            onDismiss = {
                selectedLanguages.value = it
            },
            preselectedLanguages = selectedLanguages.value
        )

        val isButtonEnabled = (gender != SelectedButton.NONE
                || mediaType != SelectedButton.NONE
                || selectedLanguages.value.isNotEmpty())

        Button(
            onClick = {
                onClickListen.invoke(
                    ModelFilter(
                        gender = gender,
                        mediaType = mediaType,
                        lang = selectedLanguages.value
                    )
                )
            },
            enabled = isButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Apply Filter")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun BottomSheetContentPreview() {
    MovieAppTheme {
        BottomSheetContent(
            onClickListen = {},
            preselectedValues = ModelFilter()
        )
    }
}