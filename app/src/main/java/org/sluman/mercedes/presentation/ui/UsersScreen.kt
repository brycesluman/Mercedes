package org.sluman.mercedes.presentation.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.sluman.mercedes.data.UiState
import org.sluman.mercedes.data.UserEntity

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UsersScreen(
    modifier: Modifier,
    onItemClicked: (itemId: String) -> Unit = {},
    state: State<UiState>,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    Box(modifier = modifier) {
        Column {
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
                state.value.users?.let { users ->
                    items(users) {
                        UserGridItem(it, onItemClicked, sharedTransitionScope, animatedContentScope)
                    }
                }
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserGridItem(
    item: UserEntity,
    onItemClicked: (item: String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {

    Row(
        modifier = Modifier.padding(4.dp)
    ) {
        with(sharedTransitionScope) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                modifier = Modifier
                    .clickable { onItemClicked(item.login) },
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Column(modifier = Modifier.padding(2.dp)
                    .fillMaxSize()) {
                    Card(
                        modifier = Modifier
                            .fillMaxSize(),
                        shape = RoundedCornerShape(5.dp)

                    ) {
                        AsyncImage(
                            model = item.avatarUrl,
                            contentDescription = "image",
                            contentScale = Crop,
                            clipToBounds = true,
                            modifier = Modifier.Companion
                                .sharedElement(
                                    sharedTransitionScope.rememberSharedContentState(key = "image-${item.login}"),
                                    animatedVisibilityScope = animatedContentScope
                                ).fillMaxSize()
                        )
                    }
                    Text(
                        text = item.login,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp, 2.dp),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

