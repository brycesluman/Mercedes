package org.sluman.mercedes.presentation.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.sluman.mercedes.R
import org.sluman.mercedes.data.UiState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsScreen(
    modifier: Modifier,
    state: State<UiState>,
    userId: String?,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Box(modifier = modifier) {
            Column() {
                AsyncImage(
                    model = state.value.userDetail?.avatarUrl,
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.Companion
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "image-$userId"),
                            animatedVisibilityScope = animatedContentScope
                        ).height(dimensionResource(R.dimen.detail_image_height)),
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    state.value.userDetail?.name?.let { DetailRow("Name", it) }
                    state.value.userDetail?.location?.let { DetailRow("Location", it) }
                    state.value.userDetail?.company?.let { DetailRow("Company", it) }
                    state.value.userDetail?.blog?.let { DetailRow("Blog", it) }
                    state.value.userDetail?.publicRepos?.let {
                        DetailRow(
                            "Public Repository",
                            it.toString()
                        )
                    }
                    state.value.userDetail?.publicGists?.let {
                        DetailRow(
                            "Public Gists",
                            it.toString()
                        )
                    }
                    state.value.userDetail?.followers?.let { DetailRow("Followers", it.toString()) }
                    state.value.userDetail?.following?.let { DetailRow("Following", it.toString()) }
                }
            }
            if (state.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }

        }
    }
}

@Composable
fun DetailRow(key: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()
        .padding(top = 4.dp,bottom = 4.dp)) {
        Text(
            text = key,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        Text(text = value,
            modifier = Modifier.weight(1f))
    }
    Divider(thickness = .5.dp)
}