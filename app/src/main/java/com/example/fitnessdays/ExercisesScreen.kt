package com.example.fitnessdays

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessdays.data.ExercisesRepository
import com.example.fitnessdays.model.Exercise
import com.example.fitnessdays.ui.theme.FitnessDaysTheme


@Composable
fun ExerciseItem(
    item: Exercise,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            ExerciseItemHeader(
                day = item.dayNumber,
                title = item.titleRes,
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
            )
            ExerciseItemImage(
                image = item.imageRes,
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
            )
            ExerciseItemDescription(
                description = item.descriptionRes
            )
        }
    }
}

@Composable
fun ExerciseItemHeader(
    day: Int,
    @StringRes title: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.day, day),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Composable
fun ExerciseItemImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .requiredHeight(dimensionResource(R.dimen.image_size))
            .fillMaxWidth()
    )
}

@Composable
fun ExerciseItemDescription(
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(description),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ExerciseItemPreview() {
    val exercise = ExercisesRepository.exercises[0]
    FitnessDaysTheme {
        ExerciseItem(
            item = exercise
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseItemDarkThemePreview() {
    val exercise = ExercisesRepository.exercises[0]
    FitnessDaysTheme(darkTheme = true) {
        ExerciseItem(
            item = exercise
        )
    }
}