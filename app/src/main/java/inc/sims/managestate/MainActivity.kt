package inc.sims.managestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import inc.sims.managestate.ui.theme.ManageStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManageStateTheme {
                Column(modifier = Modifier.fillMaxSize()){
                    var textState by remember {
                        mutableStateOf("")
                    }
                    val nameListState = remember {
                        mutableStateListOf<String>()
                    }
                    LazyColumn(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)) {
                        items(nameListState.size){
                            Text(text = nameListState[it])
                        }
                    }
                    MyTextField(
                        textValue = textState,
                        onValueChanged = {
                            textState = it
                        },
                        onAddClick = {
                            nameListState.add(textState)
                            textState = ""
                    })
                }
            }
        }
    }
}

@Composable
fun MyTextField(textValue: String, onValueChanged: (String) -> Unit, onAddClick: () -> Unit){
    TextField(
        value = textValue,
        onValueChange = {
            onValueChanged(it)
        },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.clickable { onAddClick() }
            )
        }
    )
}