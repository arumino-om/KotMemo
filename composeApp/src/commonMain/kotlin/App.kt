import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var expanded by remember { mutableStateOf(false) }
        var memoContent by remember { mutableStateOf("") }

        Column(Modifier.fillMaxWidth()) {
            TopAppBar(
                title = { Text(getFirstLineText(memoContent)) },
                actions = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Rounded.MoreVert,
                            contentDescription = "メニュー",
                        )
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            DropdownMenuItem(text = { Text("名前をつけて保存…") }, onClick = {
                                expanded = false
                            })
                            HorizontalDivider()
                            DropdownMenuItem(text = { Text("このアプリについて") }, onClick = {
                                expanded = false
                            })
                        }
                    }
                }
            )

            OutlinedTextField(
                memoContent,
                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                onValueChange = { memoContent = it },
                singleLine = false,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text("メモを入力…")
                }
            )
        }
    }
}

fun getFirstLineText(text: String): String {
    val splited = text.split("\n")[0]
    return splited.ifEmpty { "No title" }
}