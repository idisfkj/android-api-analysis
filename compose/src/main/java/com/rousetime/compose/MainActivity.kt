package com.rousetime.compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rousetime.compose.ui.theme.AndroidapianalysisTheme

private val mData = listOf(
    Message("Android", "Jetpack Compose 0 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 1 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 2 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 3 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 4 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 5 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 6 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 7 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 8 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 9 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 10 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 11 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
    Message("Android", "Jetpack Compose 12 jdlsafjlkfnlknsfwelkjf;ewj;fj;sadj;ndsa;vn;ldnas;vn;iwniv;kbvou;hoik"),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidapianalysisTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Conversation(messages = mData)
//                    Greeting(Message("Hello, Android", "Jetpack Compose"))
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun Greeting(message: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = message.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(showBackground = true, name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    AndroidapianalysisTheme {
        Greeting(Message("Hello, Android", "Jetpack Compose"))
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            Greeting(message = message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    AndroidapianalysisTheme {
        Conversation(messages = mData)
    }
}