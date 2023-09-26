package com.example.pdfviewertest

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.pdfviewertest.ui.theme.PdfViewerTestTheme
import com.pspdfkit.PSPDFKit
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.ui.PdfActivity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PdfViewerTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    Column {
                        Greeting()  
                        Button(onClick = {
                            val uri =
                                Uri.parse("assets/umowa.pdf")
                            val config = PdfActivityConfiguration.Builder(context).build()
                            PdfActivity.showDocument(context, uri, config)
                        }) {
                            Text(text = "launch")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Button(onClick = {
        coroutineScope.launch {
            PSPDFKit.initialize(context, null)
        }
    }) {
        Text(text = "init")
    }

}
