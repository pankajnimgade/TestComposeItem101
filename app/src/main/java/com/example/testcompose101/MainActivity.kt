package com.example.testcompose101

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testcompose101.datamodels.Quote
import com.example.testcompose101.datamodels.getDefaultQuote
import com.example.testcompose101.ui.theme.TestCOmpose101Theme
import com.example.testcompose101.viewmodel.QuoteViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    var viewModel: QuoteViewModel = QuoteViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getQuoteList()
        enableEdgeToEdge()
        setContent {

            ContentBody(viewModel)


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyTopBar() {
    TopAppBar(
        title = { Text("Quotes") },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary) // Modifier applied properly
    )
}

@Composable
fun ContentBody(viewModel: QuoteViewModel) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(64.dp)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Text(text = "Quote app")
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
        )
        ActionRow(viewModel)
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
        )

        LazyColumn(modifier = Modifier.padding(4.dp)) {
            items(viewModel.currentList) {
                CardQuote(it)
            }
        }

    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestCOmpose101Theme {
        Greeting("Android")
    }
}


@Composable
private fun ActionRow(viewModel: QuoteViewModel) {
    Column(
        modifier = Modifier.padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var words by remember { mutableStateOf("") }
        TextField(value = words, onValueChange = {
            words = it
        })
        Button(onClick = {
            viewModel.getQuoteList()
        }) {
            Text("Show All")
        }
        Button(onClick = {
           viewModel.searchWithAuthor(words)
        }) {
            Text("Search with Author")
        }
        Button(onClick = {
            viewModel.searchWithQuote(words)
        }) {
            Text("Search with Quote")

        }
    }
}

@Preview
@Composable
fun ShowPreviewOfActionRow() {
    ActionRow(QuoteViewModel())
}


@Composable
private fun CardQuote(quote: Quote = getDefaultQuote()) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation()
    ) {

        Column(
            modifier = Modifier
                .padding(4.dp)
                .wrapContentHeight()
                .padding(4.dp)
        ) {
            Text(text = "Title: ${quote.quote}", style = MaterialTheme.typography.bodyMedium)
            Spacer(
                modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth()
            )
            Text(text = "Author: ${quote.author}", style = MaterialTheme.typography.bodySmall)
        }

    }
}

@Preview
@Composable
fun ShowPreviewOfCardQuote() {
    CardQuote()
}
