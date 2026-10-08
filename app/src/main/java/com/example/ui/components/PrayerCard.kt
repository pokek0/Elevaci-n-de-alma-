package com.example.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GuideColor
import com.example.ui.theme.ResponseColor

@Composable
fun PrayerCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    guide: String? = null,
    response: String? = null,
    fontScale: Float = 1.0f,
    testTag: String = "prayer_card",
    content: @Composable (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag(testTag)
            .animateContentSize(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            if (!title.isNullOrBlank()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(5.dp)
                            .height(22.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = (19 * fontScale).sp
                    )
                }
            }

            if (!guide.isNullOrBlank()) {
                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(
                        text = "Guía: ",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = GuideColor,
                        fontSize = (16 * fontScale).sp
                    )
                    Text(
                        text = guide,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = (16 * fontScale).sp,
                        lineHeight = (24 * fontScale).sp
                    )
                }
            }

            if (!response.isNullOrBlank()) {
                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(
                        text = "Todos: ",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = ResponseColor,
                        fontSize = (16 * fontScale).sp
                    )
                    Text(
                        text = response,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = (16 * fontScale).sp,
                        lineHeight = (24 * fontScale).sp
                    )
                }
            }

            content?.invoke()
        }
    }
}
