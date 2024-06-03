
package uz.promo.movieapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.promo.movieapp.ui.theme.MovieAppTheme


@Composable
fun ContentWrapper(
    text: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(4.dp))

        content()
    }
}


@Preview
@Composable
fun ContentWrapperPreview(){
    MovieAppTheme {
        ContentWrapper(text = "Title") {
        }
    }
}

