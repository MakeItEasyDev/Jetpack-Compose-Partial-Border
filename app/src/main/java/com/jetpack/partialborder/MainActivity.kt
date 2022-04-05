package com.jetpack.partialborder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.partialborder.ui.theme.PartialBorderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PartialBorderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Compose Partial Border",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        PartialBorder()
                    }
                }
            }
        }
    }
}

@Composable
fun PartialBorder() {
    var textCoordinates by remember { mutableStateOf<Rect?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.makeiteasy),
            contentDescription = "Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF37C7D7).copy(alpha = 0.6f))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithoutRect(textCoordinates)
                .padding(20.dp, 30.dp)
                .border(
                    width = 0.8.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(32.dp)
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithoutRect(textCoordinates)
                .padding(28.dp, 38.dp)
                .border(
                    width = 0.8.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(28.dp)
                )
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(20.dp, 40.dp)
                .onGloballyPositioned {
                    textCoordinates = it.boundsInParent()
                }
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = "Make it Easy",
                modifier = Modifier.padding(0.dp, 15.dp)
            )
            Text(
                text = "Jetpack Compose",
                modifier = Modifier.padding(0.dp, 15.dp)
            )
        }
    }
}

fun Modifier.drawWithoutRect(rect: Rect?) =
    drawWithContent {
        if (rect != null) {
            clipRect(
                left = rect.left,
                top = rect.top,
                right = rect.right,
                bottom = rect.bottom,
                clipOp = ClipOp.Difference
            ) {
                this@drawWithContent.drawContent()
            }
        } else {
            drawContent()
        }
    }



















