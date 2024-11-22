package com.example.composetutorial

import SampleData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                Conversation(SampleData.conversationSample)
            }
        }
    }
}

data class Message(val author: String, val body: String)

// 화면 레이아웃 설정
@Composable
fun MessageCard(msg: Message) {
    // padding 설정
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Contact Profile Image",
            modifier = Modifier
                .size(40.dp)    // 이미지 크기 지정
                .clip(CircleShape)  // 이미지 원형으로 clip
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape) // 이미지 테두리
        )
        // 수평 간격 주기
        Spacer(modifier = Modifier.width(8.dp))

        // 확장 상태 추적 변수
        var isExpanded by remember { mutableStateOf(false) }

        // Surface 색상 설정
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )

        // Column 클릭 시 상태 변경
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall // 글자 크기
            )
            // 수직 간격 주기
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                // 배경 색상 점진적으로 변경 primary -> surface
                color = surfaceColor,
                // Surface 내부 Text의 maxLines가 변하면 높이를 부드럽게 변경 (padding 크기는 고정)
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // 확장된 상태면 전체 보여주기, 아니면 첫줄만 보여주기
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

// MessageCard에 들어갈 내용 설정
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}