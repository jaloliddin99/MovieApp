
package uz.promo.movieapp.ui.home.filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uz.promo.movieapp.ui.home.ContentWrapper
import uz.promo.movieapp.ui.theme.MovieAppTheme


private val languageList = listOf(
    "en",
    "ja",
    "fr",
    "es",
    "ko"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultipleChoiceDialog(
    modifier: Modifier = Modifier,
    languages: List<String> = languageList,
    onDismiss: (List<String>) -> Unit,
    preselectedLanguages: List<String>
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedItemList by remember {
        mutableStateOf(preselectedLanguages)
    }


    ContentWrapper(text = "Select Language(s)") {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(56.dp)
            ,
            onClick = {
                showBottomSheet = true
            }
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(selectedItemList) {
                    Spacer(modifier = Modifier.width(6.dp))
                    FilterChip(
                        onClick = {  },
                        label = {
                            Text(it)
                        },
                        selected = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        },
                    )

                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                onDismiss(selectedItemList)
            },
            sheetState = sheetState,
            modifier = modifier.fillMaxHeight()
        ) {
            MultipleChoiceQuestion(
                possibleAnswers = languages,
                selectedAnswers = selectedItemList,
                onOptionSelected = { selected: Boolean, answer: String ->
                    selectedItemList = if (selected){
                        selectedItemList + answer
                    }else {
                        val list = selectedItemList.toMutableList()
                        list.remove(answer)
                        list
                    }
                },
                onButtonClicked = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                            onDismiss(selectedItemList)
                        }
                    }
                }
            )
        }
    }
}



@Composable
fun MultipleChoiceQuestion(
    possibleAnswers: List<String>,
    selectedAnswers: List<String>,
    onOptionSelected: (selected: Boolean, answer: String) -> Unit,
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ContentWrapper(
            text = "Select Language(s)",
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            possibleAnswers.forEach {
                val selected = selectedAnswers.contains(it)
                CheckboxRow(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = it,
                    selected = selected,
                    onOptionSelected = { onOptionSelected(!selected, it) }
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onButtonClicked,
                modifier = modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Select",
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}

@Composable
fun CheckboxRow(
    text: String,
    selected: Boolean,
    onOptionSelected: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = if (selected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outline
            }
        ),
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable(onClick = onOptionSelected)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text, Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Box(Modifier.padding(8.dp)) {
                Checkbox(selected, onCheckedChange = null)
            }
        }
    }
}

@Preview
@Composable
fun CheckboxRowPreview() {
    MovieAppTheme {
        CheckboxRow(
            "English",
            selected = true,
            onOptionSelected = {}
        )
    }
}

@Preview
@Composable
fun MultipleChoiceQuestionPreview() {
    MovieAppTheme {
        MultipleChoiceQuestion(
            possibleAnswers = languageList,
            selectedAnswers = listOf("en",
                "ja",
                "fr",),
            onOptionSelected = {selected, answer ->  },
            onButtonClicked = {}
        )
    }
}

@Preview
@Composable
fun MultipleChoiceDialogPreview(){
    MovieAppTheme {
        MultipleChoiceDialog(
           onDismiss = {},
            preselectedLanguages = listOf("en",
                "ja",
                "fr",)
        )
    }
}